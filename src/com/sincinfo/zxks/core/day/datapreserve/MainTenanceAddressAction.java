package com.sincinfo.zxks.core.day.datapreserve;

import java.util.List;

import org.river.nbf.validate.annotation.Validate;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.MainTenanceAddress;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.MainTenanceAddressService;

public class MainTenanceAddressAction extends WebActionSupport {

	/**
	 * 
	 */

	private Page page = new Page();
	private static final long serialVersionUID = -8365277920282882625L;
	//地址维护VO
	private MainTenanceAddress mta;
	// 地址维护列表
	private List<MainTenanceAddress> allAddressList;
	// 地市列表
	private List<BaseCity> cityList;
	// 地市代码
	String operCode;
	// 业务编号 11 毕业证申请 12 转档 01报名
	String studPostalAddress;
	// 通讯地址
	String byBus;
	// 乘车路线
	String linkTelephone;
	// 咨询电话
	private String cityCode;
	// 操作员用户对象
	private BaseUser optUser;
	// 方法
	private MainTenanceAddressService mt = new MainTenanceAddressService();

	// 初始化列表数据
	public String initRegInfoStat() {

		// 获取操作员
		this.optUser = this.getCOperUser();
		// 初始化表单值
		this.cityCode = this.cityCode == null ? this.optUser.getCityCode()
				: this.cityCode;
		
		// 初始化列表
		this.cityList = mt.qryCitys(optUser.getCityCode());
		return "regInfoStat";
	}

	public String intoAddress() {
		// 进入到页面初始化select下拉框中的值s
//		System.out.println(optUser.getCityCode());
		allAddressLists();
		System.out.println(optUser.getCityCode());
		return "iniT";
	}

	public String intoAdd() {
		return "adD";
	}
	public String deleteInfo(){
		initRegInfoStat();
		boolean flag = mt.deleteInfo(cityCode, operCode);
		if (flag == true) {
			this.Alert("删除成功!");
			this.PostJs(String.format(
					"location.href='%1$s/manager/day/message/address_intoAddress.do';",
					request.getContextPath()));
		} else {
			this.GoBack("删除失败");
		}
		return "iniT";
	}
	public String addAddress() {
		initRegInfoStat();
		boolean flag = mt.addAddress(cityCode, operCode, studPostalAddress,
				byBus, linkTelephone);
		if (flag == true) {
			this.Alert("添加成功!");
			this.PostJs(String.format(
					"location.href='%1$s/manager/day/message/address_intoAddress.do';",
					request.getContextPath()));
		} else {
			this.GoBack("添加失败");
		}
		return null;
	}

	public void allAddressLists() {
		// 根据条件获得List
		qryCitys();
		// 开始进行分页
		allAddressList = mt.allAddressList(cityCode, page);
		StringBuilder url = new StringBuilder();
		url.append(this.request.getContextPath());
		url.append("/manager/day/message/address_intoAddress.do");
		page.setPath(url.toString());
		// 设置跳转
	}
	public void qryCitys() {
		initRegInfoStat();
		if (this.optUser.equals("1")) {// 省用户
			cityList = mt.qryCitys(cityCode);
		}
	}
	public void updateList(){
		//初始化修改页面的值
			allAddressList =mt.getUpdateInfoByCondition(cityCode, operCode);
	}
	public String upDate(){
		boolean flag = mt.updateInfoByCondition(mta,cityCode);
		if (flag == true) {
			this.Alert("修改成功!");
			this.PostJs(String.format(
					"location.href='%1$s/manager/day/message/address_intoAddress.do';",
					request.getContextPath()));
		} else {
			this.GoBack("修改失败!");
		}
		return null; 
	}
	public String updateInfo(){
		//修改用户
		updateList();
		//初始化方法
		return "updateInfo";
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

	public BaseUser getOptUser() {
		return optUser;
	}

	public void setOptUser(BaseUser optUser) {
		this.optUser = optUser;
	}

	public MainTenanceAddressService getMt() {
		return mt;
	}

	public void setMt(MainTenanceAddressService mt) {
		this.mt = mt;
	}

	public List<MainTenanceAddress> getAllAddressList() {
		return allAddressList;
	}

	public void setAllAddressList(List<MainTenanceAddress> allAddressList) {
		this.allAddressList = allAddressList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	@Validate(fname="详细地址",exp="s(50b)")
	public String getStudPostalAddress() {
		return studPostalAddress;
	}

	public void setStudPostalAddress(String studPostalAddress) {
		this.studPostalAddress = studPostalAddress;
	}

	public String getByBus() {
		return byBus;
	}

	public void setByBus(String byBus) {
		this.byBus = byBus;
	}
	@Validate(fname="联系电话",exp="p(15)")
	public String getLinkTelephone() {
		return linkTelephone;
	}

	public void setLinkTelephone(String linkTelephone) {
		this.linkTelephone = linkTelephone;
	}

	public MainTenanceAddress getMta() {
		return mta;
	}

	public void setMta(MainTenanceAddress mta) {
		this.mta = mta;
	}

}
