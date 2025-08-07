package util;

import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 輸入驗證和清理工具類
 * 防止 XSS、SQL 注入和其他安全威脅
 * 
 * @author PC商城專案維護小組
 * @version 2.0
 */
public class ValidationUtil {
    private static final Logger LOGGER = Logger.getLogger(ValidationUtil.class.getName());
    
    // 正規表達式模式
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );
    
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^[0-9\\-\\+\\(\\)\\s]{8,15}$"
    );
    
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile(
        "^[0-9]{13,19}$"
    );
    
    private static final Pattern USER_ID_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_]{3,20}$"
    );
    
    private static final Pattern NAME_PATTERN = Pattern.compile(
        "^[\\u4e00-\\u9fa5a-zA-Z\\s]{2,50}$"
    );
    
    // 危險字符和SQL關鍵字
    private static final String[] SQL_KEYWORDS = {
        "SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "CREATE", "ALTER",
        "EXEC", "EXECUTE", "UNION", "SCRIPT", "JAVASCRIPT", "VBSCRIPT"
    };
    
    private static final String[] XSS_PATTERNS = {
        "<script", "</script", "javascript:", "vbscript:", "onclick",
        "onload", "onerror", "onmouseover", "onfocus", "onblur"
    };
    
    /**
     * 清理輸入字串，移除潛在的惡意內容
     * @param input 輸入字串
     * @return 清理後的字串
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        
        String cleaned = input.trim();
        
        // 移除控制字符
        cleaned = cleaned.replaceAll("[\\x00-\\x1f\\x7f]", "");
        
        // HTML 實體編碼
        cleaned = htmlEncode(cleaned);
        
        // 移除SQL注入相關字符
        cleaned = removeSqlInjectionChars(cleaned);
        
        LOGGER.log(Level.FINE, "輸入清理完成");
        return cleaned;
    }
    
    /**
     * 驗證使用者 ID
     * @param userId 使用者 ID
     * @return 是否有效
     */
    public static boolean isValidUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = userId.trim();
        return USER_ID_PATTERN.matcher(trimmed).matches() && 
               !containsSqlKeywords(trimmed) && 
               !containsXssPatterns(trimmed);
    }
    
    /**
     * 驗證使用者姓名
     * @param name 姓名
     * @return 是否有效
     */
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = name.trim();
        return NAME_PATTERN.matcher(trimmed).matches() && 
               !containsXssPatterns(trimmed);
    }
    
    /**
     * 驗證電子郵件
     * @param email 電子郵件
     * @return 是否有效
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = email.trim().toLowerCase();
        return EMAIL_PATTERN.matcher(trimmed).matches() && 
               !containsXssPatterns(trimmed);
    }
    
    /**
     * 驗證電話號碼
     * @param phone 電話號碼
     * @return 是否有效
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = phone.trim();
        return PHONE_PATTERN.matcher(trimmed).matches();
    }
    
    /**
     * 驗證信用卡號碼
     * @param creditCard 信用卡號碼
     * @return 是否有效
     */
    public static boolean isValidCreditCard(String creditCard) {
        if (creditCard == null || creditCard.trim().isEmpty()) {
            return false;
        }
        
        // 移除空格和破折號
        String cleaned = creditCard.replaceAll("[\\s\\-]", "");
        
        if (!CREDIT_CARD_PATTERN.matcher(cleaned).matches()) {
            return false;
        }
        
        // Luhn 算法驗證
        return isValidLuhn(cleaned);
    }
    
    /**
     * 驗證地址
     * @param address 地址
     * @return 是否有效
     */
    public static boolean isValidAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = address.trim();
        return trimmed.length() >= 5 && 
               trimmed.length() <= 200 && 
               !containsXssPatterns(trimmed) &&
               !containsSqlKeywords(trimmed);
    }
    
    /**
     * HTML 實體編碼
     * @param input 輸入字串
     * @return 編碼後的字串
     */
    private static String htmlEncode(String input) {
        if (input == null) return null;
        
        return input.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#x27;")
                   .replace("/", "&#x2F;");
    }
    
    /**
     * 移除 SQL 注入相關字符
     * @param input 輸入字串
     * @return 清理後的字串
     */
    private static String removeSqlInjectionChars(String input) {
        if (input == null) return null;
        
        // 移除或替換危險字符
        String cleaned = input.replace("'", "''")  // SQL 單引號轉義
                              .replace("--", "")    // SQL 註釋
                              .replace("/*", "")    // SQL 多行註釋開始
                              .replace("*/", "");   // SQL 多行註釋結束
        
        return cleaned;
    }
    
    /**
     * 檢查是否包含 SQL 關鍵字
     * @param input 輸入字串
     * @return 是否包含
     */
    private static boolean containsSqlKeywords(String input) {
        if (input == null) return false;
        
        String upperInput = input.toUpperCase();
        for (String keyword : SQL_KEYWORDS) {
            if (upperInput.contains(keyword)) {
                LOGGER.log(Level.WARNING, "檢測到SQL關鍵字: {0}", keyword);
                return true;
            }
        }
        return false;
    }
    
    /**
     * 檢查是否包含 XSS 模式
     * @param input 輸入字串
     * @return 是否包含
     */
    private static boolean containsXssPatterns(String input) {
        if (input == null) return false;
        
        String lowerInput = input.toLowerCase();
        for (String pattern : XSS_PATTERNS) {
            if (lowerInput.contains(pattern.toLowerCase())) {
                LOGGER.log(Level.WARNING, "檢測到XSS模式: {0}", pattern);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Luhn 算法驗證信用卡號
     * @param cardNumber 信用卡號碼
     * @return 是否有效
     */
    private static boolean isValidLuhn(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            
            sum += n;
            alternate = !alternate;
        }
        
        return (sum % 10 == 0);
    }
    
    /**
     * 驗證完整的使用者資料
     * @param userId 使用者 ID
     * @param name 姓名
     * @param email 電子郵件
     * @param phone 電話
     * @param address 地址
     * @param creditCard 信用卡
     * @return 驗證結果物件
     */
    public static ValidationResult validateUserData(String userId, String name, String email, 
                                                  String phone, String address, String creditCard) {
        ValidationResult result = new ValidationResult();
        
        if (!isValidUserId(userId)) {
            result.addError("使用者 ID 格式不正確");
        }
        
        if (!isValidName(name)) {
            result.addError("姓名格式不正確");
        }
        
        if (email != null && !email.trim().isEmpty() && !isValidEmail(email)) {
            result.addError("電子郵件格式不正確");
        }
        
        if (phone != null && !phone.trim().isEmpty() && !isValidPhone(phone)) {
            result.addError("電話號碼格式不正確");
        }
        
        if (!isValidAddress(address)) {
            result.addError("地址格式不正確");
        }
        
        if (creditCard != null && !creditCard.trim().isEmpty() && !isValidCreditCard(creditCard)) {
            result.addError("信用卡號碼格式不正確");
        }
        
        return result;
    }
    
    /**
     * 驗證結果類別
     */
    public static class ValidationResult {
        private java.util.List<String> errors = new java.util.ArrayList<>();
        
        public void addError(String error) {
            errors.add(error);
        }
        
        public boolean isValid() {
            return errors.isEmpty();
        }
        
        public java.util.List<String> getErrors() {
            return errors;
        }
        
        public String getErrorMessage() {
            return String.join(", ", errors);
        }
    }
}