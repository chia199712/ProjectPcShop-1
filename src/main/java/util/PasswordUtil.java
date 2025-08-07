package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 密碼加密工具類
 * 提供密碼雜湊和驗證功能
 * 
 * @author PC商城專案維護小組
 * @version 2.0
 */
public class PasswordUtil {
    private static final Logger LOGGER = Logger.getLogger(PasswordUtil.class.getName());
    
    // 鹽值長度
    private static final int SALT_LENGTH = 32;
    // 迭代次數
    private static final int ITERATIONS = 10000;
    // 雜湊演算法
    private static final String HASH_ALGORITHM = "SHA-256";
    
    /**
     * 對密碼進行雜湊處理
     * @param password 明文密碼
     * @return 雜湊後的密碼字串（包含鹽值）
     */
    public static String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("密碼不能為空");
        }
        
        try {
            // 生成隨機鹽值
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);
            
            // 執行雜湊
            byte[] hash = hashPasswordWithSalt(password, salt, ITERATIONS);
            
            // 將鹽值和雜湊值組合並編碼
            byte[] hashWithSalt = new byte[SALT_LENGTH + hash.length];
            System.arraycopy(salt, 0, hashWithSalt, 0, SALT_LENGTH);
            System.arraycopy(hash, 0, hashWithSalt, SALT_LENGTH, hash.length);
            
            return Base64.getEncoder().encodeToString(hashWithSalt);
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "密碼雜湊處理失敗", e);
            throw new RuntimeException("密碼處理失敗", e);
        }
    }
    
    /**
     * 驗證密碼
     * @param password 明文密碼
     * @param hashedPassword 已雜湊的密碼
     * @return 是否匹配
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        if (password == null || hashedPassword == null) {
            return false;
        }
        
        try {
            // 解碼雜湊密碼
            byte[] hashWithSalt = Base64.getDecoder().decode(hashedPassword);
            
            if (hashWithSalt.length < SALT_LENGTH) {
                LOGGER.log(Level.WARNING, "無效的雜湊密碼格式");
                return false;
            }
            
            // 提取鹽值
            byte[] salt = new byte[SALT_LENGTH];
            System.arraycopy(hashWithSalt, 0, salt, 0, SALT_LENGTH);
            
            // 提取雜湊值
            byte[] storedHash = new byte[hashWithSalt.length - SALT_LENGTH];
            System.arraycopy(hashWithSalt, SALT_LENGTH, storedHash, 0, storedHash.length);
            
            // 使用相同的鹽值對輸入密碼進行雜湊
            byte[] testHash = hashPasswordWithSalt(password, salt, ITERATIONS);
            
            // 比較雜湊值
            return MessageDigest.isEqual(storedHash, testHash);
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "密碼驗證失敗", e);
            return false;
        }
    }
    
    /**
     * 使用鹽值對密碼進行雜湊
     * @param password 密碼
     * @param salt 鹽值
     * @param iterations 迭代次數
     * @return 雜湊值
     */
    private static byte[] hashPasswordWithSalt(String password, byte[] salt, int iterations) 
            throws NoSuchAlgorithmException {
        
        MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
        
        // 將密碼和鹽值結合
        md.update(salt);
        byte[] hash = md.digest(password.getBytes());
        
        // 執行多次迭代增加安全性
        for (int i = 0; i < iterations; i++) {
            md.reset();
            hash = md.digest(hash);
        }
        
        return hash;
    }
    
    /**
     * 檢查密碼強度
     * @param password 密碼
     * @return 密碼強度分數 (0-100)
     */
    public static int checkPasswordStrength(String password) {
        if (password == null) return 0;
        
        int score = 0;
        
        // 長度檢查
        if (password.length() >= 8) score += 25;
        else if (password.length() >= 6) score += 15;
        else if (password.length() >= 4) score += 5;
        
        // 包含小寫字母
        if (password.matches(".*[a-z].*")) score += 15;
        
        // 包含大寫字母
        if (password.matches(".*[A-Z].*")) score += 15;
        
        // 包含數字
        if (password.matches(".*\\d.*")) score += 15;
        
        // 包含特殊字符
        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) score += 20;
        
        // 無重複字符
        if (password.length() == password.chars().distinct().count()) score += 10;
        
        return Math.min(score, 100);
    }
    
    /**
     * 檢查密碼是否符合最低安全要求
     * @param password 密碼
     * @return 是否符合要求
     */
    public static boolean isPasswordValid(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        
        // 至少包含字母和數字
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        
        return hasLetter && hasDigit;
    }
    
    /**
     * 生成隨機密碼
     * @param length 密碼長度
     * @return 隨機密碼
     */
    public static String generateRandomPassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("密碼長度至少為4位");
        }
        
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return password.toString();
    }
}