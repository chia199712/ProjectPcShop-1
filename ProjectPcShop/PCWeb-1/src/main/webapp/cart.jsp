<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, shoppingPackage.*, bean.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>

<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="after1299">
    <meta name="robots" content="index, follow">
    <meta name="description" content="">
    <title>Cart</title>
    <!-- google font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Alkalami&family=Noto+Sans+TC:wght@300;400;500&display=swap"
        rel="stylesheet">
    <!-- bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <!-- font awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <!-- this page css -->
    <link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/cart.css">
</head>
<body>
    <header>
        <div class="header-con">
            <div class="logo-icon">
                <a href="./shoppingPage.jsp"><i class="fa-solid fa-desktop"></i></a>
            </div>
            <div class="r-icon">
                <a href=""><i class="fa-solid fa-cart-shopping"></i></a>
                <a href="./cart.jsp"><i class="fa-solid fa-circle-user"></i></a>
            </div>
        </div>
    </header>
<%String todo = request.getParameter("todo");
%>

<%
      // Scriptlet 2: Check whether the shopping cart is empty.
      List<CartItem> theCart = (List<CartItem>) session.getAttribute("cart");
      %>
      <div class="cart-con container">
        <h1>Shopping-Cart</h1>
        <div class="cart-box">
            <hr>
            <table>
            
                <tr class="table">
                    <th>
                        <h3>title</h3>
                    </th>
                    <th>
                        <h3>price</h3>
                    </th>
                    <th>
                        <h3>count</h3>
                    </th>
                    <th>&nbsp;</th>
                </tr>
         <%

         if (theCart != null && theCart.size() > 0) {
         // Scriptlet 3: display the books in the shopping cart.
         for (int i = 0; i < theCart.size(); ++i) {
            CartItem aCartItem = theCart.get(i);
         %>
         <tr class="table table-in">
                    <form name="removeForm" action="shoppingServlet" method="GET">
                    	<input type="hidden" name="todo" value="remove">
               			<input type="hidden" name="cartIndex" value="<%= i %>">
                        <td><%= aCartItem.getC_itemName() %></td>
                        <td><%= aCartItem.getC_itemPrice() %></td>
                        <td><%= aCartItem.getC_itemCount() %></td>
                        <td><input type="submit" value="Remove"></td>
                    </form>
                 </tr>
         <%
         } // for loop
         %>
<%
         } // for loop
         
         if(theCart == null || theCart.size() == 0){
         %>
         <tr class="table">
                    <th colspan="3"><h3>購物車無商品</h3></th>
                </tr>
                <%
                } 
                %>
       </table>
         
       <div class="cart-footer">
                <form class="footer-in" name="checkoutForm" action="shoppingServlet" method="GET">
                    <input type="hidden" name="todo" value="goback">
                    <input class="keepbtn" type="submit" value="繼續購物">
                </form>
                <form class="footer-in" name="checkoutForm" action="shoppingServlet" method="GET">
                    <input type="hidden" name="todo" value="check">
                    <input class="buybtn" type="submit" value="去買單">
                </form>
            </div>
</body>
</html>