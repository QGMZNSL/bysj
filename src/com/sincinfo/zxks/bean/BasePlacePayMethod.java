package com.sincinfo.zxks.bean;

/**
 * @ClassName: BasePlacePayMethod
 * @Description: 地区交费方式 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 8:49:59 AM<br>
 * 
 */
public class BasePlacePayMethod {

	// 主键
	private String placePmethodId;

	// 地市代码
	private String cityCode;

	// 考区代码
	private String examAreaCode;

	// 网上交费
	private String payMethod1;

	// 手工交费
	private String payMethod2;

	// 银行交费
	private String payMethod3;

	/* 扩展部分 */
	// 地市名称
	private String cityName;

	// 考区名称
	private String examAreaName;

	/**
	 * 返回sql的select-from部分， 启用给予查询表别名 t
	 * 
	 * @return String
	 */
	public String getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.place_pmethod_id,");
		sql.append(" t.city_code,");
		sql.append(" t.exam_area_code,");
		sql.append(" t.pay_method_1,");
		sql.append(" t.pay_method_2,");
		sql.append(" t.pay_method_3,");
		sql
				.append(" (select c.city_name from base_city c where c.city_code = t.city_code) as cityName,");
		sql.append(" (select a.exam_area_name");
		sql.append(" from base_exam_area a");
		sql.append(" where a.exam_area_code = t.exam_area_code) as examAreaName");
		sql.append(" from base_place_pay_method t ");
		return sql.toString();
	}

	/*---------get/set--------*/
	public void setPlacePmethodId(String placePmethodId) {
		this.placePmethodId = placePmethodId;
	}

	public String getPlacePmethodId() {
		return this.placePmethodId;
	}

	public void setPayMethod1(String payMethod1) {
		this.payMethod1 = payMethod1;
	}

	public String getPayMethod1() {
		return this.payMethod1;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setExamAreaCode(String examAreaCode) {
		this.examAreaCode = examAreaCode;
	}

	public String getExamAreaCode() {
		return this.examAreaCode;
	}

	public void setPayMethod2(String payMethod2) {
		this.payMethod2 = payMethod2;
	}

	public String getPayMethod2() {
		return this.payMethod2;
	}

	public void setPayMethod3(String payMethod3) {
		this.payMethod3 = payMethod3;
	}

	public String getPayMethod3() {
		return this.payMethod3;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getExamAreaName() {
		return examAreaName;
	}

	public void setExamAreaName(String examAreaName) {
		this.examAreaName = examAreaName;
	}

}
