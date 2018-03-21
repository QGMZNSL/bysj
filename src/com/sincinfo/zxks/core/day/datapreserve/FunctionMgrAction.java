/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.datapreserve.FunctionMgrAction.java<br>
 * @Description: 基础功能维护 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:13:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.datapreserve;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BasePlacePayMethod;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.zxksdbs.DayDbService;
import com.sincinfo.zxks.zxksdbs.FunctionMgrDbService;

/**
 * @ClassName: FunctionMgrAction
 * @Description: 基础功能维护 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:13:20 AM<br>
 * 
 */
public class FunctionMgrAction extends WebActionSupport {

	private static final long serialVersionUID = -6791013303505285462L;

	// 操作员
	private BaseUser optUser;

	// 地市列表
	private List<BaseCity> cityList;

	// 地市代码
	private String cityCode;

	// 交费配置列表
	private List<BasePlacePayMethod> payMethodList;

	// 主键，标识更改记录
	private String placePmethodId;

	// 交费方式，1-网上交费 2-现场交费 3-银行交费
	private int payMethod;

	// 开启状态 0-关闭 1-开启
	private int methodFlag;

	/**
	 * 初始化页面元素
	 */
	private void init() {
		this.optUser = getCOperUser();
		DayDbService dds = new DayDbService();
		this.cityList = dds.qryCitys(optUser.getUserRole(), this.optUser
				.getCityCode());
		this.cityCode = this.cityCode == null ? "" : this.cityCode;
	}

	/**
	 * 查询出地市下的考区列表以及交费开关
	 * 
	 * @return
	 */
	public String qryCityAreas() {
		// 初始化
		init();

		// 查询
		FunctionMgrDbService fmds = new FunctionMgrDbService();
		this.payMethodList = fmds.qryPayMethodList(cityCode);

		// 跳转
		return "showCityAreas";
	}

	/**
	 * ajax更改数据库开关，并返回执行状态
	 */
	public void changeSwitch() {
		String retSign = "error";
		
		// 构建操作日志对象
		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "系统维护，基础功能维护");
		
		// 更新数据库
		FunctionMgrDbService fmds = new FunctionMgrDbService();
		boolean switchFlag = fmds.switchPMethod( this.placePmethodId, this.payMethod, this.methodFlag, optLog);
		retSign = switchFlag ? "success" : "error";

		// 返回页面
		PrintWriter pw = null;
		response.setContentType("text/html;charset=utf-8");
		try {
			pw = response.getWriter();
			pw.write(retSign);
			pw.flush();
		} catch (IOException e) {
			new Log().error( this.getClass(), "ajax修改交费启用状态失败！", e);
		} finally {
			if ( pw != null) pw.close();
		}
	}

	public BaseUser getOptUser() {
		return optUser;
	}

	public void setOptUser(BaseUser optUser) {
		this.optUser = optUser;
	}

	public List<BaseCity> getCityList() {
		return cityList;
	}

	public void setCityList(List<BaseCity> cityList) {
		this.cityList = cityList;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public List<BasePlacePayMethod> getPayMethodList() {
		return payMethodList;
	}

	public void setPayMethodList(List<BasePlacePayMethod> payMethodList) {
		this.payMethodList = payMethodList;
	}

	public String getPlacePmethodId() {
		return placePmethodId;
	}

	public void setPlacePmethodId(String placePmethodId) {
		this.placePmethodId = placePmethodId;
	}

	public int getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}

	public int getMethodFlag() {
		return methodFlag;
	}

	public void setMethodFlag(int methodFlag) {
		this.methodFlag = methodFlag;
	}

}
