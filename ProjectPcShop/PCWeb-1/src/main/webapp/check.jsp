<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*, shoppingPackage.*, bean.*"  %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="after1299">
    <meta name="robots" content="index, follow">
    <meta name="description" content="">
    <title>Check</title>
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
    <link rel="stylesheet" href="./css/check.css">
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
      <div class="check-con container">
        <h1>Check</h1>
        <div class="check-box">
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
                </tr>
         <%
         // Scriptlet 1: Display the items in shopping cart
         List<CartItem> theCart = (List<CartItem>) session.getAttribute("cart");
         for (CartItem item : theCart) {
         %>
         <tr class="table">
                    <td><%= item.getC_itemName()%></td>
                    <td>$<%= item.getC_itemPrice()%></td>
                    <td><%= item.getC_itemCount()%></td>
                </tr>
         <%
         } // for
         session.invalidate();
         %>
         </table>
            <div class="total">
                <h3>Total : </h3>
                <h3>$&#32;<%= request.getAttribute("totalPrice")%></h3>
            </div>
 <div class="cart-footer">
                <form class="footer-in" name="checkoutForm">
                    <input class="buybtn" type="submit" value="結帳">
                </form>
            </div>
        </div>
    </div>
</body>
</html>