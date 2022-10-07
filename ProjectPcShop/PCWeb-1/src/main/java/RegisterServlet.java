
import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.PrintWriter;
//import java.io.IOException;
import mvcshop.bean.*;
import mvcshop.bean.UserBean;

import java.io.*;

import java.sql.*;
//import javax.rmi.*;
import javax.naming.*;
import javax.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config);
	 }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);

	    // To prevent caching 
	   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
	   response.setHeader("Pragma","no-cache"); // HTTP 1.0
	   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server

	    
	   if (request.getParameter("submit")!=null)
	     gotoSubmitProcess(request, response);
	   else if (request.getParameter("confirm")!=null)
	     gotoConfirmProcess(request, response);
	}
	
	 public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
	    String cus_name;
	    String cus_id;
	    String cus_pwd;
	    String cus_add;
	    String cus_phone;
	    String cus_crdt_no;
	   
	    cus_name = request.getParameter("cus_name").trim();
	    cus_id = request.getParameter("cus_id").trim();
	    cus_pwd= request.getParameter("cus_pwd").trim();
	    cus_add = request.getParameter("cus_add").trim();
	    cus_phone = request.getParameter("cus_phone").trim();
	    cus_crdt_no = request.getParameter("cus_crdt_no").trim();

	    UserBean reg_user =  new UserBean(cus_name, cus_id, cus_pwd, cus_add, cus_phone, cus_crdt_no);
	    request.getSession(true).setAttribute("reg_user",reg_user);
	    request.getRequestDispatcher("/DisplayUser.jsp").forward(request,response);
	  }

	  public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		PrintWriter out = response.getWriter();

	    DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
	    try {
	     
	      //建立Context Object,連到JNDI Server	
	      ctxt = new InitialContext();

	      //使用JNDI API找到DataSource
	      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
	      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
	      //向DataSource要Connection
	      conn = ds.getConnection();

	      //建立Database Access Object,負責Table的Access
	      UserDAO userDAO = new UserDAO(conn);
	      UserBean userData = (UserBean)request.getSession(true).getAttribute("reg_user");
	      if (userDAO.insertUser(userData))
	        {
	          System.out.println("Get some SQL commands done!");
	          request.getSession(true).invalidate();
	          response.getWriter().write("<script language='javascript'> alert('註冊成功! 將自動跳轉至登入頁面');"
	        		  +"window.location.href='./loginPage.jsp'</script>");
	        }else {
	          System.out.println("Some SQL commands failed!");
	          request.getSession(true).invalidate();
	          response.getWriter().write("<script language='javascript'> alert('註冊失敗! 此信箱以重複註冊，將自動跳轉至註冊頁面，請重新註冊');"
	        		  +"window.location.href='./RegisterForm.jsp'</script>");
	        }
	    } catch (NamingException ne) {
	      System.out.println("Naming Service Lookup Exception");  
	    } catch (SQLException e) {
	      System.out.println("Database Connection Error"); 
	    } finally {
	      try {
	        if (conn != null) conn.close();
	      } catch (Exception e) {
	        System.out.println("Connection Pool Error!");
	      }
	    }
	           
	  }

}