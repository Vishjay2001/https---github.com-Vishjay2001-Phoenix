package com.phoenix.StaffManagement.model;

public class Staff {
	private int Sid;
	private String Sname;
	private String Sphone;
	private String Sgrade;
	private String Semail;
	private String Saddress;
	private String Spw;
	private String Sdob;
	
	
	
	
	public Staff(int sid, String sname, String sphone, String sgrade, String semail, String saddress, String spw, String sdob) {
		super();
		Sid = sid;
		Sname=sname;
		Sphone = sphone;
		Sgrade = sgrade;
		Semail = semail;
		Saddress = saddress;
		Spw = spw;
		Sdob = sdob;
	}
	
	
	public Staff(String sname, String sphone, String sgrade, String semail, String saddress, String spw, String sdob) {
		super();
		Sname=sname;
		Sphone = sphone;
		Sgrade = sgrade;
		Semail = semail;
		Saddress = saddress;
		Spw = spw;
		Sdob = sdob;
	}


	public int getSid() {
		return Sid;
	}
	public void setsid(int sid) {
		Sid = sid;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSphone() {
		return Sphone;
	}
	public void setSphone(String sphone) {
		Sphone = sphone;
	}
	public String getSgrade() {
		return Sgrade;
	}
	public void setSgrade(String sgrade) {
		Sgrade = sgrade;
	}
	public String getSemail() {
		return Semail;
	}
	public void setSemail(String semail) {
		Semail = semail;
	}
	public String getSaddress() {
		return Saddress;
	}
	public void setSaddress(String saddress) {
		Saddress = saddress;
	}
	public String getSpw() {
		return Spw;
	}
	public void setSpw(String spw) {
		Spw = spw;
	}
	public String getSdob() {
		return Sdob;
	}
	public void setSdob(String sdob) {
		Sdob = sdob;
	}
	
	
}
