package com.phoenix.FlightManagement.model;

public class Flight {
	private int Fid;
	private String Fcode;
	private String Fmodel;
	private String Fstart;
	private String Fdate;
	private String Ftime;
	private String Fdestination;
	
	
	
	
	public Flight(int fid, String fcode, String fmodel, String fstart, String fdate, String ftime, String fdestination) {
		super();
		Fid = fid;
		Fcode=fcode;
		Fmodel = fmodel;
		Fstart = fstart;
		Fdate = fdate;
		Ftime = ftime;
		Fdestination = fdestination;
	}
	
	
	public Flight(String fcode, String fmodel, String fstart, String fdate, String ftime, String fdestination) {
		super();
		Fcode=fcode;
		Fmodel = fmodel;
		Fstart = fstart;
		Fdate = fdate;
		Ftime = ftime;
		Fdestination = fdestination;
	}


	public int getFid() {
		return Fid;
	}
	public void setFid(int fid) {
		Fid = fid;
	}
	public String getFcode() {
		return Fcode;
	}
	public void setFcode(int fcode) {
		Fid = fcode;
	}
	public String getFmodel() {
		return Fmodel;
	}
	public void setFmodel(String fmodel) {
		Fmodel = fmodel;
	}
	public String getFstart() {
		return Fstart;
	}
	public void setFstart(String fstart) {
		Fstart = fstart;
	}
	public String getFdate() {
		return Fdate;
	}
	public void setFdate(String fdate) {
		Fdate = fdate;
	}
	public String getFtime() {
		return Ftime;
	}
	public void setFtime(String ftime) {
		Ftime = ftime;
	}
	public String getFdestination() {
		return Fdestination;
	}
	public void setFdestination(String fdestination) {
		Fdestination = fdestination;
	}
	
	
}
