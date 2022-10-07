<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, shoppingPackage.*, bean.*"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="after1299">
    <meta name="robots" content="index, follow">
    <meta name="description" content="">
    <title>Product</title>
    <!-- google font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300;400;500&display=swap" rel="stylesheet">
    <!-- bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <!-- font awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <!-- this page css -->
    <link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/itemPage.css">
</head>

<body>
    <header>
        <div class="header-con">
            <div class="logo-icon">
                <a href="./shoppingPage.jsp"><i class="fa-solid fa-desktop"></i></a>
            </div>
            <div class="r-icon">
                <a href=""><i class="fa-solid fa-cart-shopping"></i></a>
                <a href=""><i class="fa-solid fa-circle-user"></i></a>
            </div>
        </div>
    </header>

    <form action="shoppingServlet" method="GET">
        <section class="item-container">
    	
		<%
		String ind = request.getParameter("index");
		int itemPageIndex = Integer.parseInt(ind);

  	    shoppingDB.setItemPageIndex(itemPageIndex);
		%>
		
        
        <article class="item-img">
            <img src="<%= shoppingDB.getItemPage_image() %>" alt="">
        </article>
        
        <article class="item-box">
            <h3><%= shoppingDB.getItemPage_name()%></h3>
            <h4>Price: <fmt:formatNumber value="${shoppingDB.getItemPage_price() }" type="currency"/></h4>
            <div class="item-detail">
                <%= shoppingDB.getItemPage_info() %>
            </div>
            <div class="item-footer">
                <div class="no">
                    <label for="count">數量: </label>
                    <input type="number" name="count" id="count" min="1" max="${shoppingDB.getItemPage_amount() }" value="1"> 
                </div>
                <div class="cart">
                    <input type="submit" value="">
                    <input type="hidden" name="todo" value="add">
                    <input type="hidden" name="itemID" value="${shoppingDB.getItemPage_no() }">
                    <input type="hidden" name="itemName" value="${shoppingDB.getItemPage_name() }">
                    <input type="hidden" name="itemPrice" value="${shoppingDB.getItemPage_price() }">
                    <input type="hidden" name="itemID" value="${shoppingDB.getItemPage_no() }">
                    <i class="fa-solid fa-cart-plus"></i><span>| 加入購物車</span>
                </div>
            </div>
        </article>
    </section>
    </form>
</body>

</html>
		
	<%-- 
  	  	out.println(shoppingDB.getItemPage_name() + "<br>");
  	    out.println(shoppingDB.getItemPage_info() + "<br>");
		處理購物車 搭配下一行input
		<form action="" method="GET"></form>
	 --%>