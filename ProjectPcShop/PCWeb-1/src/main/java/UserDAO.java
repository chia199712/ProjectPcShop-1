// DAO: Database Access Object
// �M�d�PDept Table���s�W,�ק�,�R���P�d��

import java.sql.*;
import mvcshop.bean.UserBean;

public class UserDAO {

  private Connection conn;

  //建構子
  public UserDAO(Connection conn) {
		this.conn = conn;
  }

 
  //新增學生資料
  public boolean insertUser(UserBean userData) {
    try {
      String sqlString = "insert into cus_info (cus_id,cus_name,cus_pwd,cus_add,cus_phone,cus_crdt_no) values('"
	                  	   	+userData.getCus_id()+"','"
		                    +userData.getCus_name()+"','" 
                            + userData.getCus_pwd()+"','"
                            + userData.getCus_add()+"','"
                            + userData.getCus_phone()+"','"
                            + userData.getCus_crdt_no()+ "')";
                           
      Statement stmt = conn.createStatement();
      System.out.println(sqlString);
	  int updatecount = stmt.executeUpdate(sqlString);
      stmt.close();
      if (updatecount >= 1) return true;
      else                  return false;
	  } catch (Exception e) {
	    System.err.println("新增會員資料時發生錯誤:" + e);
		return false;
    }
  }


}