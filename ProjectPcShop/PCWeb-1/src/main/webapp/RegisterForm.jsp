<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="after1299">
    <meta name="robots" content="index, follow">
    <meta name="description" content="">
    <title>註冊</title>
    <!-- google font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300;400;500&display=swap" rel="stylesheet">
    <!-- this page css -->
    <link rel="stylesheet" href="./css/registerPage.css">
<title>會員註冊</title>
</head>
<body>
	<div class="body-container">
		<form action=".\RegisterServlet" method="post" class="form-container">
		<h2>註冊資料輸入</h2>
			<div class="input-container">
				
                <div class="input-con">
                    <input class="form-in" type="text" name="cus_name" id="uname" placeholder=" " required>
                    <label for="uname">Your Name</label>
                </div>
                
                <div class="input-con">
                    <input class="form-in" type="email" name="cus_id" id="email" placeholder=" " required>
                    <label for="email">Email</label>
                </div>
                
                <div class="input-con">
                    <input class="form-in" type="password" name="cus_pwd" id="ps1" placeholder=" " value="" required>
                    <label for="password">Password</label>
                </div>
                
                <div class="input-con">
                    <input class="form-in" type="password" name="cus_pwd_check" id="ps2" placeholder=" " value=""
                        required>
                    <label for="password">Confirm password</label>
                </div>
                
                <div class="input-con">
                    <input class="form-in" type="text" name="cus_phone" id="phone" placeholder=" " required>
                    <label for="email">Phone</label>
                </div>
                
                <div class="input-con">
                    <input class="form-in" type="text" name="cus_add" id="address" placeholder=" " required>
                    <label for="email">Address</label>
                </div>
                
                <div class="input-con">
                    <input class="form-in" type="text" name="cus_crdt_no" id="crdt" placeholder=" " required>
                    <label for="email">Credit Card Number</label>
                </div>
                
             </div>

			<input id="btnsub" type="submit" name="submit" value="送出"></input>
        </form>
    </div>
<script>
        document.getElementById("btnsub").onclick = function () {
            let psword = document.getElementById('ps1').value;
            let conpsword = document.getElementById('ps2').value;

            if (psword != conpsword) {
                alert("密碼不一致");
                return false;
            }
        }
    </script>

</body>
</html>
<center>
<input type="submit" name="submit" value="送出">
</center>
</form>
</body>
</html>