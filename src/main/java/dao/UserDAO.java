package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.User;

/**
 * 使用者資料存取物件 - 改良版本
 * - 使用 PreparedStatement 防止 SQL 注入
 * - 改善異常處理
 * - 加入日誌記錄
 * 
 * @author PC商城專案維護小組
 * @version 2.0
 */
public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    
    private Connection connection;
    
    // SQL 語句常數
    private static final String INSERT_USER_SQL = 
        "INSERT INTO users (id, name, password, address, phone, creditCard, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SELECT_USER_BY_ID_SQL = 
        "SELECT * FROM users WHERE id = ?";
    
    private static final String SELECT_USER_BY_CREDENTIALS_SQL = 
        "SELECT * FROM users WHERE id = ? AND password = ?";
    
    /**
     * 建構函式
     * @param connection 資料庫連線物件
     */
    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * 新增使用者到資料庫
     * @param user 使用者物件
     * @return 是否新增成功
     */
    public boolean insertUser(User user) {
        // 輸入驗證
        if (user == null) {
            LOGGER.log(Level.WARNING, "嘗試插入空的使用者物件");
            return false;
        }
        
        if (!isValidUser(user)) {
            LOGGER.log(Level.WARNING, "使用者資料驗證失敗: {0}", user.getId());
            return false;
        }
        
        PreparedStatement pstmt = null;
        
        try {
            pstmt = connection.prepareStatement(INSERT_USER_SQL);
            
            // 設定參數 - 防止 SQL 注入
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword()); // 注意：密碼應該已經加密
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getCreditCard());
            pstmt.setString(7, user.getEmail());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "成功新增使用者: {0}", user.getId());
                return true;
            } else {
                LOGGER.log(Level.WARNING, "新增使用者失敗，沒有資料列受影響: {0}", user.getId());
                return false;
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "新增使用者時發生 SQL 異常: " + user.getId(), e);
            return false;
        } finally {
            closeStatement(pstmt);
        }
    }
    
    /**
     * 根據使用者 ID 查詢使用者
     * @param userId 使用者 ID
     * @return User 物件，如果找不到則回傳 null
     */
    public User getUserById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "查詢使用者時提供了無效的 ID");
            return null;
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = connection.prepareStatement(SELECT_USER_BY_ID_SQL);
            pstmt.setString(1, userId.trim());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "查詢使用者時發生 SQL 異常: " + userId, e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        
        return null;
    }
    
    /**
     * 驗證使用者登入憑證
     * @param userId 使用者 ID
     * @param password 密碼（已加密）
     * @return User 物件，驗證失敗則回傳 null
     */
    public User authenticateUser(String userId, String password) {
        if (userId == null || password == null || 
            userId.trim().isEmpty() || password.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "登入驗證時提供了無效的憑證");
            return null;
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = connection.prepareStatement(SELECT_USER_BY_CREDENTIALS_SQL);
            pstmt.setString(1, userId.trim());
            pstmt.setString(2, password); // 假設密碼已經加密
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                LOGGER.log(Level.INFO, "使用者登入成功: {0}", userId);
                return mapResultSetToUser(rs);
            } else {
                LOGGER.log(Level.WARNING, "使用者登入失敗: {0}", userId);
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "驗證使用者時發生 SQL 異常: " + userId, e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        
        return null;
    }
    
    /**
     * 驗證使用者資料有效性
     * @param user 使用者物件
     * @return 是否有效
     */
    private boolean isValidUser(User user) {
        return user.getId() != null && !user.getId().trim().isEmpty() &&
               user.getName() != null && !user.getName().trim().isEmpty() &&
               user.getPassword() != null && !user.getPassword().trim().isEmpty();
    }
    
    /**
     * 將 ResultSet 映射到 User 物件
     * @param rs ResultSet
     * @return User 物件
     * @throws SQLException SQL 異常
     */
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setAddress(rs.getString("address"));
        user.setPhone(rs.getString("phone"));
        user.setCreditCard(rs.getString("creditCard"));
        user.setEmail(rs.getString("email"));
        return user;
    }
    
    /**
     * 關閉 PreparedStatement
     * @param stmt PreparedStatement
     */
    private void closeStatement(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "關閉 PreparedStatement 時發生異常", e);
            }
        }
    }
    
    /**
     * 關閉 ResultSet
     * @param rs ResultSet
     */
    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "關閉 ResultSet 時發生異常", e);
            }
        }
    }
}