<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="after1299">
    <meta name="robots" content="index, follow">
    <meta name="description" content="">
    <title>註冊資料確認</title>
    <!-- google font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300;400;500&display=swap" rel="stylesheet">
    <!-- this page css -->
    <link rel="stylesheet" href="./css/DisplayUser.css">
</head>
<body>
	<div class="body-container">
	<jsp:useBean id="reg_user" class="mvcshop.bean.UserBean" scope="session" />
	<form action=".\RegisterServlet" method="post" class="form-container">
		<h2>註冊資料如下請確認</h2>

	<table  cellspacing="2" cellpadding="1" border="1" >
		<tr >
		    <td>姓名:</td>
		    <td><jsp:getProperty name="reg_user" property="cus_name" /></td>
		</tr>
		
		<tr >
		    <td>E-mail:</td>
		    <td>
		    <jsp:getProperty name="reg_user" property="cus_id" />
		    </td>
		</tr>
		
		<tr >
		    <td>密碼:</td>
		    <td><jsp:getProperty name="reg_user" property="cus_pwd" /></td>
		</tr>
		
		<tr >
		    <td width="150">寄件地址:</td>
		    <td><jsp:getProperty name="reg_user" property="cus_add" /></td>
		</tr>
		
		<tr >
		    <td>聯絡電話</td>
		    <td><jsp:getProperty name="reg_user" property="cus_phone" /></td>
		</tr>
		
		<tr >
		    <td>信用卡號:</td>
		    <td><jsp:getProperty name="reg_user" property="cus_crdt_no" /></td>
		</tr>
	</table>
<input id="btnsub" type="submit" name="confirm" value="確認" >

</form>
</div>
</body>
</html>