package exception;

/**
 * PC商城自訂異常基底類別
 * 
 * @author PC商城專案維護小組
 * @version 2.0
 */
public class PCShopException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private String errorCode;
    private String userMessage;
    
    public PCShopException(String message) {
        super(message);
        this.userMessage = message;
    }
    
    public PCShopException(String message, Throwable cause) {
        super(message, cause);
        this.userMessage = message;
    }
    
    public PCShopException(String errorCode, String message, String userMessage) {
        super(message);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }
    
    public PCShopException(String errorCode, String message, String userMessage, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public String getUserMessage() {
        return userMessage != null ? userMessage : getMessage();
    }
}