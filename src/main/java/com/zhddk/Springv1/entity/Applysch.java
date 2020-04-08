package com.zhddk.Springv1.entity;

import java.io.Serializable;

public class Applysch implements Serializable {
	private String schcode;
	private String schname;
	private String zycode;
	private String zyname;
	public String getSchcode() {
		return schcode;
	}
	public void setSchcode(String schcode) {
		this.schcode = schcode;
	}
	public String getSchname() {
		return schname;
	}
	public void setSchname(String schname) {
		this.schname = schname;
	}
	public String getZycode() {
		return zycode;
	}
	public void setZycode(String zycode) {
		this.zycode = zycode;
	}
	public String getZyname() {
		return zyname;
	}
	public void setZyname(String zyname) {
		this.zyname = zyname;
	}
	
	
	
}
