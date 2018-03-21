/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.IdCardEntity.java<br>
 * @Description: 读取二代身份证信息 <br>
 * <br>
 * @author litian<br>
 * @date Feb 2, 2012 9:34:52 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

/**
 * @ClassName: IdCardEntity
 * @Description: 读取二代身份证信息 <br>
 *               <br>
 * @author litian
 * @date Feb 2, 2012 9:34:52 AM<br>
 * 
 */
public class IdCardEntity {
	// 姓名
	private String name;
	// 性别
	private String sex;
	// 民族
	private String nation;
	// 出生日期
	private String bornDate;
	// 地址
	private String address;
	// 身份证号
	private String idNo;
	// 签发机关
	private String signGov;
	// 有效期开始日期
	private String startDate;
	// 有效期结束日期
	private String endDate;
	// 照片文件
	private String photoBuffer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBornDate() {
		return bornDate;
	}

	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getSignGov() {
		return signGov;
	}

	public void setSignGov(String signGov) {
		this.signGov = signGov;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPhotoBuffer() {
		return photoBuffer;
	}

	public void setPhotoBuffer(String photoBuffer) {
		this.photoBuffer = photoBuffer;
	}

}
