package mvcshop.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
	  private String cus_name;
	  private String cus_id;
	  private String cus_pwd;
	  private String cus_add;
	  private String cus_phone;
	  private String cus_crdt_no;
	  
	public UserBean() {	
}



	public UserBean(String pCus_name, String pCus_id, String pCus_pwd, 
					String pCus_add, String pCus_phone, String pCus_crdt_no) {
		this.cus_name = pCus_name;
		this.cus_id = pCus_id;
		this.cus_pwd = pCus_pwd;
		this.cus_add = pCus_add;
		this.cus_phone = pCus_phone;
		this.cus_crdt_no = pCus_crdt_no;
}



	public String getCus_name() {
		return cus_name;
	}



	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}



	public String getCus_id() {
		return cus_id;
	}



	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}



	public String getCus_pwd() {
		return cus_pwd;
	}



	public void setCus_pwd(String cus_pwd) {
		this.cus_pwd = cus_pwd;
	}



	public String getCus_add() {
		return cus_add;
	}



	public void setCus_add(String cus_add) {
		this.cus_add = cus_add;
	}



	public String getCus_phone() {
		return cus_phone;
	}



	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}



	public String getCus_crdt_no() {
		return cus_crdt_no;
	}



	public void setCus_crdt_no(String cus_crdt_no) {
		this.cus_crdt_no = cus_crdt_no;
	}

	

}