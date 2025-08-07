package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.User;
import service.UserService;
import service.UserService.RegistrationResult;
import util.ValidationUtil;

/**
 * 使用者註冊控制器 - 重構版本
 * - 使用 Service 層處理業務邏輯
 * - 改善安全性和錯誤處理
 * - 加入日誌記錄
 * 
 * @author PC商城專案維護小組
 * @version 2.0
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());
    
    // JNDI 資源名稱
    private static final String DATASOURCE_JNDI = "java:comp/env/jdbc/EmployeeDB";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 設定字符編碼
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // 防止快取
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        String action = request.getParameter("action");
        
        try {
            if ("submit".equals(action)) {
                handleSubmitRegistration(request, response);
            } else if ("confirm".equals(action)) {
                handleConfirmRegistration(request, response);
            } else {
                // 預設顯示註冊表單
                response.sendRedirect("RegisterForm.jsp");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "處理註冊請求時發生異常", e);
            handleError(request, response, "系統暫時無法處理您的請求，請稍後再試");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    /**
     * 處理提交註冊資料
     */
    private void handleSubmitRegistration(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        LOGGER.log(Level.INFO, "處理註冊資料提交");
        
        // 1. 收集並驗證輸入資料
        User user = collectUserData(request);
        if (user == null) {
            handleError(request, response, "請填寫完整的註冊資料");
            return;
        }
        
        // 2. 將使用者資料存入 Session 供確認頁面使用
        HttpSession session = request.getSession();
        session.setAttribute("pendingUser", user);
        
        // 3. 轉到確認頁面
        request.getRequestDispatcher("DisplayUser.jsp").forward(request, response);
    }
    
    /**
     * 處理確認註冊
     */
    private void handleConfirmRegistration(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        LOGGER.log(Level.INFO, "處理註冊確認");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("pendingUser");
        
        if (user == null) {
            handleError(request, response, "註冊資料已過期，請重新填寫");
            return;
        }
        
        // 取得資料庫連線並執行註冊
        try (Connection conn = getConnection()) {
            UserService userService = new UserService(conn);
            RegistrationResult result = userService.registerUser(user);
            
            // 清除 Session 中的暫存資料
            session.removeAttribute("pendingUser");
            
            if (result.isSuccess()) {
                // 註冊成功
                session.setAttribute("registrationSuccess", true);
                session.setAttribute("newUserId", result.getUserId());
                
                LOGGER.log(Level.INFO, "使用者註冊成功: {0}", result.getUserId());
                
                // 重導向到成功頁面
                response.sendRedirect("index.jsp?message=registration_success");
            } else {
                // 註冊失敗
                LOGGER.log(Level.WARNING, "使用者註冊失敗: {0}", result.getMessage());
                handleError(request, response, result.getMessage());
            }
            
        } catch (SQLException | NamingException e) {
            LOGGER.log(Level.SEVERE, "資料庫連線錯誤", e);
            handleError(request, response, "系統暫時無法處理您的請求，請稍後再試");
        }
    }
    
    /**
     * 從請求中收集使用者資料
     */
    private User collectUserData(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String creditCard = request.getParameter("creditCard");
            String email = request.getParameter("email");
            
            // 基本驗證
            if (id == null || name == null || password == null || address == null) {
                LOGGER.log(Level.WARNING, "必填欄位為空");
                return null;
            }
            
            // 密碼確認
            if (!password.equals(confirmPassword)) {
                LOGGER.log(Level.WARNING, "密碼確認不符");
                return null;
            }
            
            // 建立使用者物件
            User user = new User();
            user.setId(id.trim());
            user.setName(name.trim());
            user.setPassword(password);
            user.setAddress(address.trim());
            user.setPhone(phone != null ? phone.trim() : "");
            user.setCreditCard(creditCard != null ? creditCard.trim() : "");
            user.setEmail(email != null ? email.trim() : "");
            
            return user;
            
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "收集使用者資料時發生錯誤", e);
            return null;
        }
    }
    
    /**
     * 取得資料庫連線
     */
    private Connection getConnection() throws SQLException, NamingException {
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(DATASOURCE_JNDI);
        return dataSource.getConnection();
    }
    
    /**
     * 處理錯誤
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) 
            throws ServletException, IOException {
        
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}