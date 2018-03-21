package com.sincinfo.zxks.bean;

public class imageInfo {
	private int hight;
	private int weigth;
	private int dpi;
	
	
	
	public imageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public imageInfo(int hight, int weigth, int dpi) {
		super();
		this.hight = hight;
		this.weigth = weigth;
		this.dpi = dpi;
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
	public int getWeigth() {
		return weigth;
	}
	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}
	public int getDpi() {
		return dpi;
	}
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}

}