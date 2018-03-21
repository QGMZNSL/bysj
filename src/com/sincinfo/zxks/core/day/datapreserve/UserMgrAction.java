/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.datapreserve.UserMgrAction.java<br>
 * @Description: 系统用户管理 <br>
 * <br>
 * @author litian<br>
 * @date Jan 20, 2012 8:41:09 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.datapreserve;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.sincinfo.zxks.bean.BaseAcademy;
import com.sincinfo.zxks.bean.BaseCameraPlace;
import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseJoinhandsUnit;
import com.sincinfo.zxks.bean.BasePosition;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.SingleEntity;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.Power;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.core.day.datapreserve.busi.ModuleStruct;
import com.sincinfo.zxks.core.day.datapreserve.busi.ModuleTool;
import com.sincinfo.zxks.core.day.datapreserve.busi.UserPre;
import com.sincinfo.zxks.zxksdbs.DayDbService;
import com.sincinfo.zxks.zxksdbs.SysDbService;

/**
 * @ClassName: UserMgrAction
 * @Description: 系统用户管理 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 8:41:09 AM<br>
 * 
 */
public class UserMgrAction extends WebActionSupport {

	private static final long serialVersionUID = -4250939247421447491L;

	/* 业务操作对象 */
	private DayDbService dds = new DayDbService();
	private SysDbService sds = new SysDbService();

	// 操作员
	private BaseUser optUser;

	// 分页对象
	private Page page = new Page();

	// 地市列表
	private List<BaseCity> cityList;

	// 用户类型 （对应User_Role，""-全部，1-省级用户， 2-市级用户，4-考区用户，8-主考院校用户， 16-摄像点用户）
	private List<SingleEntity> userRoleList;

	// 条件对象
	private BaseUser userEntity;

	// 传递查询结果集
	private List<BaseUser> userList;

	// 添加修改用户对象
	private UserPre cuser;

	// 用来进行ajax局部刷新的命令字
	private String ajaxType;
	// 用户角色ajax参数
	private String ajaxUserRole;
	// 地市代码ajax参数
	private String ajaxCityCode;

	// 保存用户对象的方式 forNew 添加,forOld 修改
	private String userSaveMethod;

	// 用于修改的对象
	private String usrnameForEdit;

	// 被设置的用户的角色
	private String clkUserRole;

	// 模块权限
	private List<ModuleStruct> msList;

	// 用来传递权限码
	private String[] pwrIndexes;

	/**
	 * 初始化用户类型（省上可查看全部，地市可以查看地市及其下级的，考区用户只能查询自己，其余用户无权限）
	 */
	private void initUserTypes(BaseUser optUser) {
		this.userRoleList = new ArrayList<SingleEntity>();
		if (Power.checkRole(optUser.getUserRole(), Power.USER_ROLE_PROV)) {
			this.userRoleList.add(new SingleEntity("1", "省级用户"));
			this.userRoleList.add(new SingleEntity("2", "市级用户"));
			this.userRoleList.add(new SingleEntity("4", "考区用户"));
			this.userRoleList.add(new SingleEntity("8", "主考院校用户"));
			this.userRoleList.add(new SingleEntity("16", "摄像点用户"));
			this.userRoleList.add(new SingleEntity("32", Power.USER_ROLE_UNIT_NAME));
			return;
		}
		if (Power.checkRole(optUser.getUserRole(), Power.USER_ROLE_CITY)) {
			this.userRoleList.add(new SingleEntity("2", "市级用户"));
			this.userRoleList.add(new SingleEntity("4", "考区用户"));
			this.userRoleList.add(new SingleEntity("8", "主考院校用户"));
			this.userRoleList.add(new SingleEntity("16", "摄像点用户"));
			this.userRoleList.add(new SingleEntity("32", Power.USER_ROLE_UNIT_NAME));
			return;
		}
		if (Power.checkRole(optUser.getUserRole(), Power.USER_ROLE_AREA)) {
			this.userRoleList.add(new SingleEntity("4", "考区用户"));
			return;
		}
		if (Power.checkRole(optUser.getUserRole(), Power.USER_ROLE_UNIT)) {
			this.userRoleList.add(new SingleEntity("32", Power.USER_ROLE_UNIT_NAME));
			return;
		}
		
		this.userRoleList.add(new SingleEntity("", "---请选择---"));
	}

	/**
	 * 初始化查询使用的表单控件
	 */
	private void initCitys(BaseUser optUser) {
		this.cityList = dds.qryCitys(optUser.getUserRole(), optUser
				.getCityCode());
	}

	/**
	 * 初始化分页地址
	 */
	private void initPagePath() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/day/datapreserve/usrMgr_userMgr.do",
				request.getContextPath()));
		url.append(String.format("?userEntity.cityCode=%1$s", this.userEntity
				.getCityCode()));
		url.append(String.format("&userEntity.userRole=%1$s", this.userEntity
				.getUserRole()));
		url.append(String.format("&userEntity.userLock=%1$s", this.userEntity
				.getUserLock()));
		url.append(String.format("&userEntity.userName=%1$s", this.userEntity
				.getUserName()));
		url.append(String.format("&userEntity.realName=%1$s", this.userEntity
				.getRealName()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化表单默认值
	 */
	private void defaultParam(BaseUser optUser) {
		this.userEntity = this.userEntity == null ? new BaseUser()
				: this.userEntity;
		this.userEntity
				.setCityCode(this.userEntity.getCityCode() == null ? optUser
						.getCityCode() : this.userEntity.getCityCode());
		this.userEntity
				.setUserRole(this.userEntity.getUserRole() == null ? optUser
						.getUserRole() : this.userEntity.getUserRole());
		this.userEntity.setUserLock(this.userEntity.getUserLock() == null ? ""
				: this.userEntity.getUserLock());
		this.userEntity.setUserName(this.userEntity.getUserName() == null ? ""
				: this.userEntity.getUserName());
		this.userEntity.setRealName(this.userEntity.getRealName() == null ? ""
				: this.userEntity.getRealName());
	}

	/**
	 * 初始化页面控件
	 */
	private void init() {
		this.optUser = getCOperUser();

		// 初始化地市列表
		initCitys(optUser);
		// 初始化用户类型列表
		initUserTypes(optUser);

		// 表单默认值
		defaultParam(optUser);
		// 初始化分页地址
		initPagePath();
	}

	/**
	 * 查询用户列表
	 * 
	 * @return
	 */
	public String userMgr() {
		// 页面初始化
		init();

		// 查询用户列表
		this.userList = this.dds.qryUsers(this.userEntity, this.page);

		return "userList";
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	public String addPre() {
		// 获取操作员
		this.optUser = getCOperUser();

		// 初始化页面选择部分
		this.cuser = new UserPre();
		cuser.initLists(optUser.getUserRole(), optUser.getCityCode());

		return "add";
	}

	/**
	 * ajax更换列表
	 */
	public void ajaxChgSelect() {
		// 判断ajax获取内容
		this.ajaxType = StringTool.trim(this.ajaxType);
		List resList = null;
		String ret = null;
		if ("positionSct".equals(ajaxType)) {
			// 根据用户角色取得职位
			resList = this.dds.qryPostionByGrade(this.ajaxUserRole);
		} else if ("academySct".equals(ajaxType)) {
			// 根据地市，取得启用的主考院校
			resList = this.sds.qryAcademys(this.ajaxCityCode);
		} else if ("cameraPlaceSct".equals(ajaxType)) {
			// 根据地市，取得启用的摄像点
			resList = this.sds.qryCameraPlaces(this.ajaxCityCode);
		}else if("unitSct".equals(ajaxType)){			
			resList = this.sds.qryJoinHandsUnit(this.ajaxCityCode);
		}

		// 整理返回字符串
		ret = makeUpSelect(this.ajaxType, resList);

		// 返回页面
		PrintWriter pw = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			pw = response.getWriter();
			pw.write(ret);
			pw.flush();
		} catch (Exception e) {
			new Log().error(this.getClass(), "用户管理，AJAX写回页面失败！", e);
		} finally {
			if (pw != null)
				pw.close();
		}
	}

	/**
	 * 根据列表及相关参数组合select控件
	 * 
	 * @param cont
	 *            String 生成内容 positionSct-职位 academySct-主考院校 cameraPlaceSct-摄像点
	 * @param vals
	 *            List 列表
	 * @return String select字符串
	 */
	private String makeUpSelect(String ajaxType, List vals) {
		StringBuilder retBuff = new StringBuilder();
		retBuff.append("<select class='inputText inputTextM'");
		if ("positionSct".equals(ajaxType)) {
			// 根据用户角色取得职位
			retBuff.append(" name='cuser.positionCode' id='positionCode'>");
			retBuff.append("<option value=''>--- 请选择 ---</option>");
			BasePosition posi = null;
			for (Object obj : vals) {
				posi = (BasePosition) obj;
				retBuff.append(String.format(
						"<option value='%1$s'>%2$s-%3$s</option>", posi
								.getPositionCode(), posi.getDepartmentName(),
						posi.getPositionName()));
			}
		} else if ("academySct".equals(ajaxType)) {
			// 根据地市，取得启用的主考院校
			retBuff.append(" name='cuser.academyCode' id='academyCode'>");
			retBuff.append("<option value=''>--- 请选择 ---</option>");
			BaseAcademy acad = null;
			for (Object obj : vals) {
				acad = (BaseAcademy) obj;
				retBuff.append(String.format(
						"<option value='%1$s'>%2$s</option>", acad
								.getAcademyCode(), acad.getAcademyName()));
			}
		} else if ("cameraPlaceSct".equals(ajaxType)) {
			// 根据地市，取得启用的摄像点
			retBuff
					.append(" name='cuser.cameraPlaceCode' id='cameraPlaceCode'>");
			retBuff.append("<option value=''>--- 请选择 ---</option>");
			BaseCameraPlace camera = null;
			for (Object obj : vals) {
				camera = (BaseCameraPlace) obj;
				retBuff.append(String.format(
						"<option value='%1$s'>%2$s</option>", camera
								.getCameraPlaceCode(), camera
								.getCameraPlaceName()));
			}
		} else if("unitSct".equals(ajaxType)){
			retBuff
					.append(" name='cuser.unitCode' id='unitCode'>");
			retBuff.append("<option value=''>--- 请选择 ---</option>");
			BaseJoinhandsUnit unitBean=null;
			for (Object obj : vals) {
				unitBean = (BaseJoinhandsUnit) obj;
				retBuff.append(String.format(
						"<option value='%1$s'>%2$s</option>", unitBean.getUnitCode()
								,unitBean.getUnitName()));
			}
		}
		retBuff.append("</select>");
		return retBuff.toString();
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String editPre() {
		// 获取操作员
		this.optUser = getCOperUser();

		// 初始化页面选择部分及修改目标对象信息
		BaseUser buser = this.sds.qryOperator(this.usrnameForEdit);
		this.cuser = makeUserPre(buser);
		this.cuser.initLists(optUser.getUserRole(), optUser.getCityCode());

		return "edit";
	}

	/**
	 * 保存用户对象
	 */
	public void saveUser() {
		// 构建操作日志对象
		OperatLog optLog = this.getOptLog("", "系统维护，用户管理");

		// 查询列表地址
		String urlJs = String.format(
				"location.href='%1$s/day/datapreserve/usrMgr_userMgr.do';",
				request.getContextPath());

		// 设置值
//		System.out.println("this.cuser.getUserRole()="+this.cuser.getUserRole());
		if ("1".equals(this.cuser.getUserRole())) {
			this.cuser.setCityCode("");
			this.cuser.setAcademyCode("");
			this.cuser.setCameraPlaceCode("");
			this.cuser.setUnitCode("");
		} else if ("2".equals(this.cuser.getUserRole())) {
			this.cuser.setAcademyCode("");
			this.cuser.setCameraPlaceCode("");
			this.cuser.setUnitCode("");
		} else if ("8".equals(this.cuser.getUserRole())) {
			this.cuser.setCameraPlaceCode("");
			this.cuser.setUnitCode("");
		} else if ("16".equals(this.cuser.getUserRole())) {
			this.cuser.setPositionCode("");
			this.cuser.setAcademyCode("");
			this.cuser.setUnitCode("");
		}else if("32".equals(this.cuser.getUserRole())){
			this.cuser.setPositionCode("");
			this.cuser.setAcademyCode("");
			this.cuser.setCameraPlaceCode("");
		}
		this.cuser.setUserLock("".equals(StringTool.trim(this.cuser
				.getUserLock())) ? "0" : this.cuser.getUserLock());

		// 进行保存
		if ("forNew".equals(userSaveMethod)) {
			optLog.setLogOptMethod(OperatLog.DB_INSERT);
			// 添加
			if (sds.saveOperator(this.cuser, true, optLog)) {
				this.Alert("添加用户成功！");
			} else {
				this.Alert("添加用户失败！");
			}
		} else if ("forOld".equals(userSaveMethod)) {
			optLog.setLogOptMethod(OperatLog.DB_UPDATE);
			// 修改
			if (sds.saveOperator(this.cuser, false, optLog)) {
				this.Alert("修改用户成功！");
			} else {
				this.Alert("修改用户失败！");
			}
		} else {
			this.Alert("执行失败！缺少参数！");
		}
		this.PostJs(urlJs);
	}

	/**
	 * 进入权限设置页面
	 * 
	 * @return
	 */
	public String powerPre() {
		// 获取操作员
		this.optUser = getCOperUser();

		// 初始化页面选择部分及修改目标对象信息
		BaseUser buser = this.sds.qryOperator(this.usrnameForEdit);
		this.cuser = makeUserPre(buser);
		this.cuser.initLists(optUser.getUserRole(), optUser.getCityCode());

		// 加载权限列表
		ModuleTool mt = new ModuleTool();
		this.msList = mt.moduleTreeInitial(this.clkUserRole);

		return "power";
	}

	/**
	 * 保存权限
	 */
	public void savePower() {
		// 查询列表地址
		String urlJs = String.format(
				"location.href='%1$s/day/datapreserve/usrMgr_userMgr.do';",
				request.getContextPath());

		// 构建日志对象
		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "系统维护，修改用户权限");

		// 组成权限码
		this.cuser.setPowerArray(pwrLink());
		// 保存
		if (this.sds.savePower(this.cuser, optLog)) {
			this.Alert("设置权限成功！");
			this.PostJs(urlJs);
		} else {
			this.GoBack("设置权限失败！");
		}
	}

	/**
	 * 将BaseUser对象传值给业务UserPre对象
	 * 
	 * @param buser
	 *            BaseUser对象
	 * @return UserPre
	 */
	private UserPre makeUserPre(BaseUser buser) {
		UserPre cuser = new UserPre();
		cuser.setUserName(buser.getUserName());
		cuser.setRealName(StringTool.trim(buser.getRealName()));
		cuser.setUserGender(StringTool.trim(buser.getGender()));
		cuser.setUserPassword(StringTool.trim(buser.getUserPassword()));
		cuser.setTelephone(StringTool.trim(buser.getTelephone()));
		cuser.setPostalAddress(StringTool.trim(buser.getPostalAddress()));
		cuser.setUserRole(StringTool.trim(buser.getUserRole()));
		cuser.setPositionCode(StringTool.trim(buser.getPositionCode()));
		cuser.setCityCode(StringTool.trim(buser.getCityCode()));
		cuser.setAcademyCode(StringTool.trim(buser.getAcademyCode()));
		cuser.setCameraPlaceCode(StringTool.trim(buser.getCameraPlaceCode()));
		cuser.setUnitCode(StringTool.trim(buser.getUnitCode()));
		cuser.setUserLock(StringTool.trim(buser.getUserLock()));
		cuser.setPowerArray(StringTool.trim(buser.getPowerArray()));
		return cuser;
	}

	/**
	 * 连接权限码
	 * 
	 * @return
	 */
	private String pwrLink() {
		int[] power = new int[ModuleTool.MODULE_MAX_INDEX_COUNT];
		// 对传递过来的权限列表排序
		String tmpPwr = null;
		for (int i = 0; i < this.pwrIndexes.length; i++) {
			for (int j = i + 1; j < this.pwrIndexes.length; j++) {
				if (Integer.parseInt(this.pwrIndexes[i]) > Integer
						.parseInt(this.pwrIndexes[j])) {
					tmpPwr = this.pwrIndexes[i];
					this.pwrIndexes[i] = this.pwrIndexes[j];
					this.pwrIndexes[j] = tmpPwr;
				}
			}
		}

		// idx直接表示位数（即下标+1）
		int point = 0;
		int pwrIndex = 0;
		boolean indexesIsNull = this.pwrIndexes == null
				|| this.pwrIndexes.length == 0;
		for (int idx = 1; idx <= power.length; idx++) {
			if (indexesIsNull) {
				power[idx - 1] = 0;
			} else {
				pwrIndex = Integer.parseInt(this.pwrIndexes[point]);
				// 位数相等时，位数-1的值变为1
				if (pwrIndex == idx) {
					power[idx - 1] = 1;
					point++;

					// 保存的权限位数组的指针位达到数组末尾时，结束循环
					if (point == this.pwrIndexes.length)
						break;
				}
			}
		}

		// 连接权限码
		StringBuilder pwrBuf = new StringBuilder();
		for (int p : power)
			pwrBuf.append(p);

		return pwrBuf.toString();
	}

	/*----------get/set----------*/
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<BaseCity> getCityList() {
		return cityList;
	}

	public void setCityList(List<BaseCity> cityList) {
		this.cityList = cityList;
	}

	public List<SingleEntity> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<SingleEntity> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public BaseUser getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(BaseUser userEntity) {
		this.userEntity = userEntity;
	}

	public List<BaseUser> getUserList() {
		return userList;
	}

	public void setUserList(List<BaseUser> userList) {
		this.userList = userList;
	}

	public UserPre getCuser() {
		return cuser;
	}

	public void setCuser(UserPre cuser) {
		this.cuser = cuser;
	}

	public BaseUser getOptUser() {
		return optUser;
	}

	public void setOptUser(BaseUser optUser) {
		this.optUser = optUser;
	}

	public String getAjaxType() {
		return ajaxType;
	}

	public void setAjaxType(String ajaxType) {
		this.ajaxType = ajaxType;
	}

	public String getAjaxUserRole() {
		return ajaxUserRole;
	}

	public void setAjaxUserRole(String ajaxUserRole) {
		this.ajaxUserRole = ajaxUserRole;
	}

	public String getAjaxCityCode() {
		return ajaxCityCode;
	}

	public void setAjaxCityCode(String ajaxCityCode) {
		this.ajaxCityCode = ajaxCityCode;
	}

	public String getUserSaveMethod() {
		return userSaveMethod;
	}

	public void setUserSaveMethod(String userSaveMethod) {
		this.userSaveMethod = userSaveMethod;
	}

	public List<ModuleStruct> getMsList() {
		return msList;
	}

	public void setMsList(List<ModuleStruct> msList) {
		this.msList = msList;
	}

	public String[] getPwrIndexes() {
		return pwrIndexes;
	}

	public void setPwrIndexes(String[] pwrIndexes) {
		this.pwrIndexes = pwrIndexes;
	}

	public String getUsrnameForEdit() {
		return usrnameForEdit;
	}

	public void setUsrnameForEdit(String usrnameForEdit) {
		this.usrnameForEdit = usrnameForEdit;
	}

	public String getClkUserRole() {
		return clkUserRole;
	}

	public void setClkUserRole(String clkUserRole) {
		this.clkUserRole = clkUserRole;
	}

}
