<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="PC商城 - 專業的電腦硬體購物網站，提供最新的CPU、顯卡、主機板等電腦配件">
    <meta name="keywords" content="PC, 電腦, 硬體, CPU, 顯卡, 主機板, 記憶體, 電腦配件">
    <meta name="author" content="PC商城開發團隊">
    
    <title>PC商城 - 專業電腦硬體購物網站</title>
    
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    
    <!-- Preload critical resources -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300;400;500;700&display=swap" rel="stylesheet">
</head>
<body>
    <!-- 導覽列 -->
    <nav class="navbar">
        <div class="container">
            <a href="${pageContext.request.contextPath}/" class="navbar-brand">
                🖥️ PC商城
            </a>
            
            <button class="navbar-toggler" id="navbar-toggler" aria-label="切換導覽選單">
                <span>☰</span>
            </button>
            
            <ul class="navbar-nav" id="navbar-nav">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/" class="nav-link">首頁</a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/products" class="nav-link">商品</a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/cart" class="nav-link">購物車</a>
                </li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/profile" class="nav-link">會員中心</a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/logout" class="nav-link">登出</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/loginPage.jsp" class="nav-link">登入</a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/RegisterForm.jsp" class="nav-link">註冊</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>

    <!-- 主要內容 -->
    <main>
        <!-- 英雄區塊 -->
        <section class="hero-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 text-center">
                        <h1 class="hero-title fade-in">PC網路購物商城</h1>
                        <p class="hero-subtitle fade-in">
                            專業的電腦硬體購物平台<br>
                            最新硬體、最優價格、最快配送
                        </p>
                        <div class="mt-4">
                            <c:choose>
                                <c:when test="${not empty sessionScope.user}">
                                    <a href="${pageContext.request.contextPath}/shoppingPage.jsp" 
                                       class="btn btn-primary btn-lg me-3">立即購物</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/shoppingPage.jsp" 
                                       class="btn btn-primary btn-lg me-3">先去逛逛</a>
                                    <a href="${pageContext.request.contextPath}/loginPage.jsp" 
                                       class="btn btn-outline-primary btn-lg">會員登入</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 功能特色 -->
        <section class="container mt-5">
            <div class="row">
                <div class="col-12 text-center mb-5">
                    <h2 class="mb-3">為什麼選擇我們？</h2>
                    <p class="text-muted">專業團隊為您提供最優質的服務體驗</p>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card text-center">
                        <div class="card-body">
                            <div style="font-size: 3rem; margin-bottom: 1rem;">🚀</div>
                            <h5 class="card-title">極速配送</h5>
                            <p class="card-text text-muted">
                                當日下單，隔日到貨<br>
                                全台配送網路覆蓋
                            </p>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card text-center">
                        <div class="card-body">
                            <div style="font-size: 3rem; margin-bottom: 1rem;">🛡️</div>
                            <h5 class="card-title">品質保證</h5>
                            <p class="card-text text-muted">
                                原廠認證商品<br>
                                提供完整保固服務
                            </p>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card text-center">
                        <div class="card-body">
                            <div style="font-size: 3rem; margin-bottom: 1rem;">💰</div>
                            <h5 class="card-title">優惠價格</h5>
                            <p class="card-text text-muted">
                                直營通路價格透明<br>
                                定期推出促銷活動
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 熱門商品 -->
        <section class="bg-light mt-5 py-5">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center mb-5">
                        <h2 class="mb-3">熱門商品</h2>
                        <p class="text-muted">精選最受歡迎的電腦硬體商品</p>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card product-card">
                            <img src="${pageContext.request.contextPath}/images/products/cpu-sample.jpg" 
                                 alt="Intel Core i7 處理器" class="product-image" 
                                 onerror="this.src='data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAwIiBoZWlnaHQ9IjIwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cmVjdCB3aWR0aD0iMTAwJSIgaGVpZ2h0PSIxMDAlIiBmaWxsPSIjZGRkIi8+PHRleHQgeD0iNTAlIiB5PSI1MCUiIGZvbnQtc2l6ZT0iMTgiIGZpbGw9IiM5OTkiIHRleHQtYW5jaG9yPSJtaWRkbGUiIGR5PSIuM2VtIj5Qcm9kdWN0IEltYWdlPC90ZXh0Pjwvc3ZnPg=='">
                            <div class="card-body">
                                <h5 class="card-title">Intel Core i7</h5>
                                <p class="card-text text-muted">高效能處理器，遊戲與工作兼備</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <span class="price">NT$ 15,999</span>
                                    <button class="btn btn-primary btn-sm">加入購物車</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card product-card">
                            <img src="${pageContext.request.contextPath}/images/products/gpu-sample.jpg" 
                                 alt="NVIDIA RTX 顯卡" class="product-image"
                                 onerror="this.src='data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAwIiBoZWlnaHQ9IjIwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cmVjdCB3aWR0aD0iMTAwJSIgaGVpZ2h0PSIxMDAlIiBmaWxsPSIjZGRkIi8+PHRleHQgeD0iNTAlIiB5PSI1MCUiIGZvbnQtc2l6ZT0iMTgiIGZpbGw9IiM5OTkiIHRleHQtYW5jaG9yPSJtaWRkbGUiIGR5PSIuM2VtIj5Qcm9kdWN0IEltYWdlPC90ZXh0Pjwvc3ZnPg=='">
                            <div class="card-body">
                                <h5 class="card-title">NVIDIA RTX 4070</h5>
                                <p class="card-text text-muted">頂級顯卡，支援光線追蹤技術</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <span class="price">NT$ 22,999</span>
                                    <button class="btn btn-primary btn-sm">加入購物車</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card product-card">
                            <img src="${pageContext.request.contextPath}/images/products/ram-sample.jpg" 
                                 alt="DDR4 記憶體" class="product-image"
                                 onerror="this.src='data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAwIiBoZWlnaHQ9IjIwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cmVjdCB3aWR0aD0iMTAwJSIgaGVpZ2h0PSIxMDAlIiBmaWxsPSIjZGRkIi8+PHRleHQgeD0iNTAlIiB5PSI1MCUiIGZvbnQtc2l6ZT0iMTgiIGZpbGw9IiM5OTkiIHRleHQtYW5jaG9yPSJtaWRkbGUiIGR5PSIuM2VtIj5Qcm9kdWN0IEltYWdlPC90ZXh0Pjwvc3ZnPg=='">
                            <div class="card-body">
                                <h5 class="card-title">Corsair 32GB DDR4</h5>
                                <p class="card-text text-muted">高速記憶體，提升系統效能</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <span class="price">NT$ 4,999</span>
                                    <button class="btn btn-primary btn-sm">加入購物車</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card product-card">
                            <img src="${pageContext.request.contextPath}/images/products/ssd-sample.jpg" 
                                 alt="SSD 固態硬碟" class="product-image"
                                 onerror="this.src='data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAwIiBoZWlnaHQ9IjIwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cmVjdCB3aWR0aD0iMTAwJSIgaGVpZ2h0PSIxMDAlIiBmaWxsPSIjZGRkIi8+PHRleHQgeD0iNTAlIiB5PSI1MCUiIGZvbnQtc2l6ZT0iMTgiIGZpbGw9IiM5OTkiIHRleHQtYW5jaG9yPSJtaWRkbGUiIGR5PSIuM2VtIj5Qcm9kdWN0IEltYWdlPC90ZXh0Pjwvc3ZnPg=='">
                            <div class="card-body">
                                <h5 class="card-title">Samsung 1TB SSD</h5>
                                <p class="card-text text-muted">極速存取，系統開機更快速</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <span class="price">NT$ 3,299</span>
                                    <button class="btn btn-primary btn-sm">加入購物車</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-12 text-center mt-4">
                        <a href="${pageContext.request.contextPath}/shoppingPage.jsp" 
                           class="btn btn-primary btn-lg">查看更多商品</a>
                    </div>
                </div>
            </div>
        </section>

        <!-- 客戶評價 -->
        <section class="container mt-5 mb-5">
            <div class="row">
                <div class="col-12 text-center mb-5">
                    <h2 class="mb-3">客戶評價</h2>
                    <p class="text-muted">聽聽我們客戶的真實回饋</p>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="mb-3">
                                <span style="color: #ffc107;">★★★★★</span>
                            </div>
                            <p class="card-text">
                                "配送速度很快，商品包裝完善，客服態度也很好！推薦大家選擇這家店。"
                            </p>
                            <footer class="text-muted">
                                <cite title="客戶評價">— 張先生</cite>
                            </footer>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="mb-3">
                                <span style="color: #ffc107;">★★★★★</span>
                            </div>
                            <p class="card-text">
                                "價格實惠，商品品質很好，網站操作也很方便，會再次購買！"
                            </p>
                            <footer class="text-muted">
                                <cite title="客戶評價">— 李小姐</cite>
                            </footer>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="mb-3">
                                <span style="color: #ffc107;">★★★★☆</span>
                            </div>
                            <p class="card-text">
                                "專業的建議和快速的配送，讓我很滿意這次的購物體驗。"
                            </p>
                            <footer class="text-muted">
                                <cite title="客戶評價">— 王工程師</cite>
                            </footer>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <!-- 頁尾 -->
    <footer class="bg-dark text-white mt-5">
        <div class="container py-4">
            <div class="row">
                <div class="col-lg-4 mb-3">
                    <h5>PC商城</h5>
                    <p class="text-muted">
                        專業的電腦硬體購物平台<br>
                        為您提供最優質的產品與服務
                    </p>
                </div>
                
                <div class="col-lg-4 mb-3">
                    <h6>快速連結</h6>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-muted">關於我們</a></li>
                        <li><a href="#" class="text-muted">聯絡我們</a></li>
                        <li><a href="#" class="text-muted">配送資訊</a></li>
                        <li><a href="#" class="text-muted">退換貨政策</a></li>
                    </ul>
                </div>
                
                <div class="col-lg-4 mb-3">
                    <h6>客戶服務</h6>
                    <ul class="list-unstyled text-muted">
                        <li>📞 客服專線：(02) 1234-5678</li>
                        <li>✉️ Email：service@pcshop.com</li>
                        <li>🕒 服務時間：週一至週五 9:00-18:00</li>
                    </ul>
                </div>
            </div>
            
            <hr class="border-secondary">
            
            <div class="row align-items-center">
                <div class="col-md-6">
                    <p class="mb-0 text-muted">
                        &copy; 2025 PC商城. 版權所有.
                    </p>
                </div>
                <div class="col-md-6 text-md-end">
                    <p class="mb-0 text-muted">
                        Powered by PC商城開發團隊 v2.0
                    </p>
                </div>
            </div>
        </div>
    </footer>

    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    
    <script>
        // 簡單的導覽列切換功能
        document.getElementById('navbar-toggler').addEventListener('click', function() {
            var nav = document.getElementById('navbar-nav');
            nav.classList.toggle('show');
        });
        
        // 顯示歡迎訊息
        <c:if test="${param.message == 'registration_success'}">
            alert('註冊成功！歡迎加入PC商城！');
        </c:if>
        
        <c:if test="${param.message == 'login_success'}">
            alert('登入成功！歡迎回來！');
        </c:if>
    </script>
</body>
</html>