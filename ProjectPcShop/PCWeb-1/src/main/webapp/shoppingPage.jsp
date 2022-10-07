<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, shoppingPackage.*, bean.*"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="after1299">
    <meta name="robots" content="index, follow">
    <meta name="description" content="">
    <title>Shopping</title>
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
    <link rel="stylesheet" href="./css/shoppingPage.css">
</head>

<body>
    <header>
        <div class="header-con">
            <div class="logo-icon">
                <a href="./shoppingPage.jsp"><i class="fa-solid fa-desktop"></i></a>
            </div>
            <div class="r-icon">
                <a href="./cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a>
                <a href=""><i class="fa-solid fa-circle-user"></i></a>
            </div>
        </div>
    </header>
    <div class="header-page"></div>

    <h1>全站商品</h1>

    <section class="main-body">
        <article class="classification">
            <h3>商品分類</h3>
            <ul>
                <li><a href="">分類1</a></li>
                <li><a href="">分類2</a></li>
                <li><a href="">分類3</a></li>
                <li><a href="">分類4</a></li>
                <li><a href="">分類5</a></li>
                <li><a href="">分類6</a></li>
            </ul>
        </article>

        <article class="all-item">
        
        	<form action = "shoppingServlet" method = "GET">
        			<div class="all-box row">
        				<%
	        			for (int i = 0; i < shoppingDB.size(); i++) {
	        				out.println("<div class='box-container col-4'>");
	        				out.println("<div class='box'>");
							out.println("<div class='item-img'><img src='" +  shoppingDB.getPro_image(i) + "' alt=''></div>");
							out.println("<a style='text-decoration: none; color: rgb(85, 85, 85);' href='./itemPage.jsp?index=" + i + "'><h3 style='font-size: 1rem; font-weight: bold;'>");
							out.println(shoppingDB.getPro_name(i));
							out.println("</h3></a>");
							out.println("<p style='font-size: .8rem'>" + shoppingDB.getPro_info(i) + "</p>");
							out.println("<div class='box-footer'>");
							out.println("<p>剩餘數量: " + shoppingDB.getPro_amount(i) + "</p>");
							out.println("<h5><span>$: " + shoppingDB.getPro_price(i) + "</span></h5>");
							out.println("</div>");
							out.println("</div>");
							out.println("</div>");
								}
        				%>
        			</div>
			</form>
        
            <%--
            out.println("<form action = 'itemServlet' method='GET'>");
            out.println("<input type='hidden' name='go' value='" + shoppingDB.getPro_name(i) + "'>");
            out.println("</form>");
            
            <template id="tem" style="display: none;">
                <div class="box-container col-4">
                    <div class="box">
                        <div class="item-img"><img src="IMG_SRC" alt=""></div>
                        <h3><a href="./itemPage.jsp">ITEM_TITLE</a></h3>
                        <p>ITEM_DETAIL</p>
                        <div class="box-footer">
                            <div class="add-cart-btn">
                                <input type="button" name="ITEM_TITLE" value="">
                                <i class="fa-solid fa-cart-plus"></i><span>| 加入購物車</span>                        
                            </div>
                            <h5><span>$ITEM_PRICE</span></h5>
                        </div>
                    </div>
                </div>
            </template>
             --%>
        </article>
    </section>

    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
        crossorigin="anonymous"></script>
    <!-- jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"
    integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA=="
    crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- my js -->
    <%--
    <script>
        const imgStr = '../img/shopImg/IMG_NAME.jpg';

        let itemAry = ['GTX', 'GTX1080', 'RTX', 'intelI7', 'intelI9', 'AsusMB', 'AMDCPU', 'HyperxRAM'];
        let itemPC = [1999, 1080, 2080, 2999, 3999, 4999, 100, 99]

        $(document).ready(function(){
            let template = $('#tem');
            for(let i = 0; i < itemAry.length; i++) {
                let node = template.html();
                let imgURL = imgStr.replace(/IMG_NAME/g, itemAry[i]);
                node = node.replace("IMG_SRC", imgURL);
                node = node.replace("ITEM_TITLE", itemAry[i]);
                node = node.replace("ITEM_DETAIL", itemAry[i]);
                node = node.replace("ITEM_PRICE", itemPC[i]);
                $('.all-item').append(node);
            }
        })
    </script>
     --%>
</body>

</html>