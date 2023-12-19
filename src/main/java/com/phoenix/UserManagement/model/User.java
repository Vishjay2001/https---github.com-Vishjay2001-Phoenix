package com.phoenix.UserManagement.model;

public class User {
	private int Uid;
	private String Uname;
	private String Uemail;
	private String Udob;
	private String Uaddress;
	private String Utp;
	private String Upw;
	private String Upassport;
	
	
	
	
	public User(int uid, String uname, String uemail, String udob, String uaddress, String utp, String upw, String upassport) {
		super();
		Uid = uid;
		Uname=uname;
		Uemail = uemail;
		Udob = udob;
		Uaddress = uaddress;
		Utp = utp;
		Upw = upw;
		Upassport = upassport;
	}
	
	
	public User(String uname, String uemail, String udob, String uaddress, String utp, String upw, String upassport) {
		super();
		Uname=uname;
		Uemail = uemail;
		Udob = udob;
		Uaddress = uaddress;
		Utp = utp;
		Upw = upw;
		Upassport = upassport;
	}


	public int getUid() {
		return Uid;
	}
	public void setUid(int uid) {
		Uid = uid;
	}
	public String getUname() {
		return Uname;
	}
	public void setUname(String uname) {
		Uname = uname;
	}
	public String getUemail() {
		return Uemail;
	}
	public void setUemail(String uemail) {
		Uemail = uemail;
	}
	public String getUdob() {
		return Udob;
	}
	public void setUdob(String udob) {
		Udob = udob;
	}
	public String getUaddress() {
		return Uaddress;
	}
	public void setUaddress(String uaddress) {
		Uaddress = uaddress;
	}
	public String getUtp() {
		return Utp;
	}
	public void setUtp(String utp) {
		Utp = utp;
	}
	public String getUpw() {
		return Upw;
	}
	public void setUpw(String upw) {
		Upw = upw;
	}
	public String getUpassport() {
		return Upassport;
	}
	public void setUpassport(String upassport) {
		Upw = upassport;
	}
	
	
}
