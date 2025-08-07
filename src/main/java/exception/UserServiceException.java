package exception;

/**
 * 使用者服務異常類別
 * 
 * @author PC商城專案維護小組
 * @version 2.0
 */
public class UserServiceException extends PCShopException {
    private static final long serialVersionUID = 1L;
    
    // 錯誤代碼常數
    public static final String USER_NOT_FOUND = "USER_001";
    public static final String USER_ALREADY_EXISTS = "USER_002";
    public static final String INVALID_CREDENTIALS = "USER_003";
    public static final String INVALID_DATA = "USER_004";
    public static final String PASSWORD_TOO_WEAK = "USER_005";
    public static final String DATABASE_ERROR = "USER_006";
    
    public UserServiceException(String message) {
        super(message);
    }
    
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UserServiceException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }
    
    public UserServiceException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }
}