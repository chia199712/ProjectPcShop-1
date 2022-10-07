<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.sql.*,java.net.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String cus_id = request.getParameter("cus_id"); 
String cus_pwd = request.getParameter("cus_pwd");

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String url = "jdbc:mysql://127.0.0.1:3306/projectdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    String user1 = "hr";
    String pass1 = "hr";
    Connection connection = DriverManager.getConnection(url,user1,pass1);
    String sql = "select * from cus_info where cus_id=? and cus_pwd=?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1,cus_id);
    ps.setString(2,cus_pwd);
    ResultSet re = ps.executeQuery();
    if (re.next()){
        response.sendRedirect("./shoppingPage.jsp?id=" + cus_id);
        session.setAttribute("cus_id",cus_id);
    }else { 
    	response.getWriter().write("<script language='javascript'> alert('登入失敗，帳號密碼錯誤');"
  		  +"window.location.href='./loginPage.jsp'</script>");
    	}
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
%>
</body>
</html>