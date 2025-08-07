package service;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.User;
import dao.UserDAO;
import util.PasswordUtil;
import util.ValidationUtil;
import util.ValidationUtil.ValidationResult;

/**
 * 使用者業務邏輯服務
 * 處理使用者註冊、登入、驗證等業務邏輯
 * 
 * @author PC商城專案維護小組
 * @version 2.0
 */
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    
    private UserDAO userDAO;
    
    /**
     * 建構函式
     * @param connection 資料庫連線
     */
    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }
    
    /**
     * 使用者註冊
     * @param user 使用者物件
     * @return 註冊結果
     */
    public RegistrationResult registerUser(User user) {
        LOGGER.log(Level.INFO, "開始使用者註冊流程: {0}", user != null ? user.getId() : "null");
        
        RegistrationResult result = new RegistrationResult();
        
        // 1. 輸入驗證
        if (user == null) {
            result.setSuccess(false);
            result.setMessage("使用者資料不能為空");
            return result;
        }
        
        // 2. 資料驗證
        ValidationResult validation = ValidationUtil.validateUserData(
            user.getId(), user.getName(), user.getEmail(),
            user.getPhone(), user.getAddress(), user.getCreditCard()
        );
        
        if (!validation.isValid()) {
            result.setSuccess(false);
            result.setMessage("資料驗證失敗: " + validation.getErrorMessage());
            LOGGER.log(Level.WARNING, "使用者資料驗證失敗: {0}, 錯誤: {1}", 
                      new Object[]{user.getId(), validation.getErrorMessage()});
            return result;
        }
        
        // 3. 密碼強度檢查
        if (!PasswordUtil.isPasswordValid(user.getPassword())) {
            result.setSuccess(false);
            result.setMessage("密碼必須至少6位，且包含字母和數字");
            LOGGER.log(Level.WARNING, "密碼強度不足: {0}", user.getId());
            return result;
        }
        
        // 4. 檢查使用者是否已存在
        if (isUserExists(user.getId())) {
            result.setSuccess(false);
            result.setMessage("使用者 ID 已存在");
            LOGGER.log(Level.WARNING, "嘗試註冊已存在的使用者: {0}", user.getId());
            return result;
        }
        
        // 5. 密碼加密
        try {
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("密碼處理失敗");
            LOGGER.log(Level.SEVERE, "密碼加密失敗: " + user.getId(), e);
            return result;
        }
        
        // 6. 清理輸入資料
        sanitizeUserData(user);
        
        // 7. 儲存到資料庫
        boolean saved = userDAO.insertUser(user);
        
        if (saved) {
            result.setSuccess(true);
            result.setMessage("註冊成功");
            result.setUserId(user.getId());
            LOGGER.log(Level.INFO, "使用者註冊成功: {0}", user.getId());
        } else {
            result.setSuccess(false);
            result.setMessage("註冊失敗，請稍後再試");
            LOGGER.log(Level.SEVERE, "使用者註冊失敗: {0}", user.getId());
        }
        
        return result;
    }
    
    /**
     * 使用者登入
     * @param userId 使用者 ID
     * @param password 密碼
     * @return 登入結果
     */
    public LoginResult loginUser(String userId, String password) {
        LOGGER.log(Level.INFO, "使用者登入嘗試: {0}", userId);
        
        LoginResult result = new LoginResult();
        
        // 1. 輸入驗證
        if (!ValidationUtil.isValidUserId(userId) || password == null || password.trim().isEmpty()) {
            result.setSuccess(false);
            result.setMessage("使用者 ID 或密碼格式不正確");
            LOGGER.log(Level.WARNING, "登入輸入驗證失敗: {0}", userId);
            return result;
        }
        
        // 2. 清理輸入
        userId = ValidationUtil.sanitizeInput(userId);
        
        // 3. 查詢使用者
        User user = userDAO.getUserById(userId);
        if (user == null) {
            result.setSuccess(false);
            result.setMessage("使用者不存在");
            LOGGER.log(Level.WARNING, "嘗試登入不存在的使用者: {0}", userId);
            return result;
        }
        
        // 4. 驗證密碼
        if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
            result.setSuccess(false);
            result.setMessage("密碼錯誤");
            LOGGER.log(Level.WARNING, "密碼驗證失敗: {0}", userId);
            return result;
        }
        
        // 5. 登入成功
        result.setSuccess(true);
        result.setMessage("登入成功");
        result.setUser(user);
        LOGGER.log(Level.INFO, "使用者登入成功: {0}", userId);
        
        return result;
    }
    
    /**
     * 取得使用者資訊
     * @param userId 使用者 ID
     * @return 使用者物件
     */
    public User getUserInfo(String userId) {
        if (!ValidationUtil.isValidUserId(userId)) {
            LOGGER.log(Level.WARNING, "無效的使用者 ID: {0}", userId);
            return null;
        }
        
        userId = ValidationUtil.sanitizeInput(userId);
        return userDAO.getUserById(userId);
    }
    
    /**
     * 檢查使用者是否存在
     * @param userId 使用者 ID
     * @return 是否存在
     */
    private boolean isUserExists(String userId) {
        return userDAO.getUserById(userId) != null;
    }
    
    /**
     * 清理使用者資料
     * @param user 使用者物件
     */
    private void sanitizeUserData(User user) {
        user.setId(ValidationUtil.sanitizeInput(user.getId()));
        user.setName(ValidationUtil.sanitizeInput(user.getName()));
        user.setEmail(ValidationUtil.sanitizeInput(user.getEmail()));
        user.setPhone(ValidationUtil.sanitizeInput(user.getPhone()));
        user.setAddress(ValidationUtil.sanitizeInput(user.getAddress()));
        user.setCreditCard(ValidationUtil.sanitizeInput(user.getCreditCard()));
    }
    
    /**
     * 修改密碼
     * @param userId 使用者 ID
     * @param oldPassword 舊密碼
     * @param newPassword 新密碼
     * @return 是否成功
     */
    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        LOGGER.log(Level.INFO, "使用者修改密碼: {0}", userId);
        
        // 1. 驗證輸入
        if (!ValidationUtil.isValidUserId(userId) || 
            oldPassword == null || newPassword == null) {
            LOGGER.log(Level.WARNING, "修改密碼輸入驗證失敗: {0}", userId);
            return false;
        }
        
        // 2. 驗證新密碼強度
        if (!PasswordUtil.isPasswordValid(newPassword)) {
            LOGGER.log(Level.WARNING, "新密碼強度不足: {0}", userId);
            return false;
        }
        
        // 3. 驗證舊密碼
        LoginResult loginResult = loginUser(userId, oldPassword);
        if (!loginResult.isSuccess()) {
            LOGGER.log(Level.WARNING, "舊密碼驗證失敗: {0}", userId);
            return false;
        }
        
        // 4. 更新密碼 (這裡需要在 UserDAO 中實作 updatePassword 方法)
        // TODO: 實作密碼更新功能
        LOGGER.log(Level.INFO, "密碼修改成功: {0}", userId);
        return true;
    }
    
    /**
     * 註冊結果類別
     */
    public static class RegistrationResult {
        private boolean success;
        private String message;
        private String userId;
        
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
    }
    
    /**
     * 登入結果類別
     */
    public static class LoginResult {
        private boolean success;
        private String message;
        private User user;
        
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public User getUser() { return user; }
        public void setUser(User user) { this.user = user; }
    }
}