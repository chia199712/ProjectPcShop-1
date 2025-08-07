# 🖥️ PC商城 Web 應用程式 v2.0

[![Java](https://img.shields.io/badge/Java-11+-blue.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg)](https://github.com/chia199712/ProjectPcShop-1)

> 電腦硬體購物網站 - 改良版本

## 📋 專案概述

PC商城是一個電子商務平台，專門銷售電腦硬體和相關配件。此版本針對原有專案進行了安全性修復和功能改善。

### ✨ 主要特色

- 🔒 **安全改善**: 修復安全漏洞，加強密碼處理和輸入驗證
- 🏗️ **程式改善**: 採用分層架構，改善程式碼結構
- 📱 **介面更新**: 支援響應式設計，改善使用體驗
- 🚀 **效能提升**: 優化載入速度
- 🔧 **維護改善**: 加入日誌系統和錯誤處理

## 🛠️ 技術棧

### 後端
- **Java 11+** - 程式語言
- **Maven 3.6+** - 專案管理和建構工具
- **Servlet 4.0** - Web 服務器技術
- **JSP/JSTL** - 視圖層技術
- **MySQL 8.0** - 關聯式資料庫
- **SLF4J + Logback** - 日誌框架
- **JUnit 5** - 單元測試框架

### 前端
- **HTML5** - 標記語言
- **CSS3** - 樣式表語言
- **JavaScript (ES6+)** - 腳本語言
- **響應式設計** - 支援多種裝置
- **Progressive Web App** - 漸進式網頁應用

### 開發工具
- **IntelliJ IDEA** - 整合開發環境
- **Git** - 版本控制
- **Docker** - 容器化（可選）
- **JaCoCo** - 程式碼覆蓋率
- **Checkstyle** - 程式碼風格檢查

## 📁 專案結構

```
PCShop-Improved/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── bean/              # 資料模型
│   │   │   ├── dao/               # 資料訪問層
│   │   │   ├── service/           # 業務邏輯層
│   │   │   ├── servlet/           # 控制器層
│   │   │   ├── util/              # 工具類別
│   │   │   └── exception/         # 異常處理
│   │   ├── resources/
│   │   │   ├── config/            # 配置檔案
│   │   │   └── logback.xml        # 日誌配置
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml        # Web 應用配置
│   │       ├── css/               # 樣式檔案
│   │       ├── js/                # JavaScript 檔案
│   │       ├── images/            # 圖片資源
│   │       └── *.jsp              # JSP 頁面
│   └── test/
│       └── java/                  # 測試程式碼
├── pom.xml                        # Maven 配置
├── README.md                      # 專案說明
├── SPRING_BOOT_MIGRATION_PLAN.md  # Spring Boot 遷移計劃
└── docker-compose.yml             # Docker 編排
```

## 🚀 快速開始

### 環境需求

- Java 11 或更高版本
- Maven 3.6 或更高版本
- MySQL 8.0
- Tomcat 9.0（或其他 Servlet 容器）

### 安裝步驟

1. **Clone 專案**
   ```bash
   git clone https://github.com/chia199712/ProjectPcShop-1.git
   cd ProjectPcShop-1/PCShop-Improved
   ```

2. **設定資料庫**
   ```sql
   CREATE DATABASE pcshop_dev CHARACTER SET utf8mb4;
   CREATE USER 'pcshop_user'@'localhost' IDENTIFIED BY 'pcshop_pass';
   GRANT ALL PRIVILEGES ON pcshop_dev.* TO 'pcshop_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **配置應用程式**
   編輯 `src/main/resources/config/app.properties`:
   ```properties
   database.url=jdbc:mysql://localhost:3306/pcshop_dev
   database.username=pcshop_user
   database.password=pcshop_pass
   ```

4. **建構專案**
   ```bash
   mvn clean compile
   ```

5. **執行測試**
   ```bash
   mvn test
   ```

6. **打包部署**
   ```bash
   mvn clean package
   # 將生成的 target/pc-shop-web.war 部署到 Tomcat
   ```

### Docker 部署（推薦）

1. **使用 Docker Compose**
   ```bash
   docker-compose up -d
   ```

2. **訪問應用程式**
   - 應用程式：http://localhost:8080
   - 資料庫管理：http://localhost:8081 (phpMyAdmin)

## 📖 API 文檔

### 使用者相關 API

| 方法 | 端點 | 描述 |
|------|------|------|
| POST | `/register` | 使用者註冊 |
| POST | `/login` | 使用者登入 |
| GET | `/profile` | 獲取使用者資料 |
| PUT | `/profile` | 更新使用者資料 |

### 商品相關 API

| 方法 | 端點 | 描述 |
|------|------|------|
| GET | `/products` | 獲取商品列表 |
| GET | `/products/{id}` | 獲取商品詳情 |
| POST | `/cart/add` | 加入購物車 |
| GET | `/cart` | 查看購物車 |

## 🔒 安全特性

### 已修復的安全問題

- ✅ **SQL 注入防護**: 使用 PreparedStatement
- ✅ **密碼加密**: SHA-256 + 鹽值雜湊
- ✅ **輸入驗證**: 加入輸入清理和驗證機制
- ✅ **XSS 防護**: HTML 實體編碼
- ✅ **CSRF 防護**: Token 驗證機制
- ✅ **Session 安全**: 安全的 Cookie 設定

### 安全最佳實務

```java
// 密碼加密範例
String hashedPassword = PasswordUtil.hashPassword(plainPassword);

// 輸入驗證範例
ValidationResult result = ValidationUtil.validateUserData(userData);

// SQL 安全範例
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
stmt.setString(1, userId);
```

## 🧪 測試

### 執行所有測試
```bash
mvn test
```

### 測試覆蓋率報告
```bash
mvn jacoco:report
# 報告位於 target/site/jacoco/index.html
```

### 程式碼風格檢查
```bash
mvn checkstyle:check
```

## 📊 效能監控

### 日誌配置

專案使用 Logback 進行日誌管理：

- **應用日誌**: `logs/pcshop.log`
- **錯誤日誌**: `logs/pcshop-error.log`
- **日誌輪轉**: 每日輪轉，保存30天

### 監控指標

- 響應時間監控
- 錯誤率統計
- 資料庫連接池監控
- 記憶體使用率

## 🛣️ 未來規劃

### 短期目標
- [ ] 完成所有核心功能
- [ ] 提升測試覆蓋率到 80%
- [ ] 效能優化
- [ ] 安全性測試

### 中期目標
- [ ] 遷移到 Spring Boot
- [ ] 實作微服務架構
- [ ] 前後端分離
- [ ] API First 設計

### 長期目標
- [ ] 雲端部署（AWS/Azure）
- [ ] 容器化和編排
- [ ] CI/CD 自動化
- [ ] 大數據分析

詳細的遷移計劃請參考：[Spring Boot 遷移計劃](SPRING_BOOT_MIGRATION_PLAN.md)

## 🤝 貢獻指南

我們歡迎任何形式的貢獻！請遵循以下步驟：

1. Fork 這個專案
2. 建立功能分支 (`git checkout -b feature/amazing-feature`)
3. 提交你的修改 (`git commit -m 'Add amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 開啟 Pull Request

### 程式碼規範

- 遵循 Google Java Style Guide
- 確保所有測試通過
- 添加適當的文檔和註釋
- 保持向後相容性

## 📝 變更日誌

### v2.0.0 (2025-08-07)
- 🔄 改善程式架構
- 🔒 修復安全漏洞
- 🎨 更新 UI 設計
- 📱 加入響應式布局
- 🧪 加入測試框架
- 📚 改善文檔

### v1.0.0 (原始版本)
- 基礎的購物車功能
- 使用者註冊和登入
- 商品展示頁面

## 📞 支援和聯繫

- **問題回報**: [GitHub Issues](https://github.com/chia199712/ProjectPcShop-1/issues)
- **功能請求**: [GitHub Discussions](https://github.com/chia199712/ProjectPcShop-1/discussions)
- **Email**: developer@pcshop.com
- **文檔**: [Wiki](https://github.com/chia199712/ProjectPcShop-1/wiki)

## 📄 授權條款

此專案採用 MIT 授權條款 - 詳情請參閱 [LICENSE](LICENSE) 檔案。

## 🙏 致謝

感謝所有為這個專案做出貢獻的開發者和使用者。特別感謝：

- [Spring Framework](https://spring.io/) 提供優秀的 Java 框架
- [Bootstrap](https://getbootstrap.com/) 提供響應式 CSS 框架
- 所有開源社群的貢獻

---

<p align="center">
  Made with ❤️ by PC商城開發團隊
</p>

<p align="center">
  <a href="#top">回到頂部 ⬆️</a>
</p>