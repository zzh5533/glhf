package com.zhddk.Springv1.entity;

import java.io.Serializable;

public class Applyreal implements Serializable {
	private String schcode;
	private String schname;
	private String zycode;
	private String zyname;
	private String forecast = "暂无分数";
	private int sum;
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
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	
	
}
