package com.sincinfo.zxks.help;

import java.util.List;

import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;

/**
 * @Title:ManageHelpAction.java
 * @Package:com.wsbm.help
 * @Description:管理页面帮助的action类
 * @author:Cheng
 * @date:2013-5-29上午9:19:24
 * @version:1.0
 */
public class ManageHelpAction extends WebActionSupport {
	private static final long serialVersionUID = 4767632772902235326L;

	private String hid;// 审核表id
	private String help_id;// 帮助表id
	private String auditType;// 审核类型 1，通过 2，不通过 ，0默认值
	private String functionDesc;// 功能描述
	private String optDesc;// 操作描述
	private String annDesc;// 注释说明
	private String isCurrent;// 是否当前帮助
	private String count;// 待审核数量
	private Page page = new Page();
	private String[] helpInfo;
	private List<String[]> helpList;

	/**
	 * 获取页面帮助内容
	 * 
	 * @return
	 */
	public String getHelp() {
		ManageHelpDao mh = new ManageHelpDao();
		helpInfo = mh.getHelp(help_id);
		count = mh.getCount(help_id);
		return SUCCESS;
	}

	/**
	 * 获取编辑数据
	 * 
	 * @return
	 */
	public String edit() {
		ManageHelpDao mh = new ManageHelpDao();
		helpInfo = mh.getHelpByhId(hid);
		return "edit";
	}

	/**
	 * 保存编辑信息
	 */
	public String save() {
		ManageHelpDao mh = new ManageHelpDao();
		BaseUser baseUser = getCOperUser();
		String reStr = "save";
		functionDesc = this.valueFilter(functionDesc);
		optDesc = this.valueFilter(optDesc);
		annDesc = this.valueFilter(annDesc);
		// 过滤html标签和&nbsp;以及空格
		if (functionDesc.replaceAll("\\s|<[^>]*>|&nbsp;", "").equals("")
				|| optDesc.replaceAll("\\s|<[^>]*>|&nbsp;", "").equals("")
				|| annDesc.replaceAll("\\s|<[^>]*>|&nbsp;", "").equals("")) {
			GoBack("请填写完整信息！");
		} else {
			boolean f = mh.saveHelp(hid, help_id, functionDesc, optDesc,
					annDesc, baseUser, isCurrent);
			if (f) {
				Alert("保存成功！");
				helpInfo = mh.getHelp(help_id);
				count = mh.getCount(help_id);
				reStr = "success";
			} else {
				GoBack("保存失败，请重试！");
			}
		}
		return reStr;
	}

	/**
	 * 获取某个页面所有帮助信息
	 * 
	 * @return
	 */
	public String getHList() {
		this.getHelp();
		ManageHelpDao mh = new ManageHelpDao();
		helpList = mh.getHelpAuditList(help_id, page);
		return "list";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	public String audit() {
		ManageHelpDao mh = new ManageHelpDao();
		String userName = getCOperUser().getUserName();
		boolean bl = mh.auditHelp(hid, help_id, userName);
		String ref = "list";
		if (bl) {
			Alert("操作成功！");
			helpInfo = mh.getHelp(help_id);
			ref = SUCCESS;
		} else {
			GoBack("操作失败！");
		}
		return ref;
	}

	/**
	 * @see 转字符
	 * @param v
	 * @return
	 */
	private String valueFilter(String v) {
		v = v.replace("&#x26;", "&");
		// v = v.replace(":", "：");
		v = v.replace("&#x3D;", "=");
		v = v.replace("&#x3C;", "<");
		v = v.replace("&#x3E;", ">");
		// v = v.replace(" ", "&#x20;");
		v = v.replace("&#34;", "\"");
		return v;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getHelp_id() {
		return help_id;
	}

	public void setHelp_id(String help_id) {
		this.help_id = help_id;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String[] getHelpInfo() {
		return helpInfo;
	}

	public void setHelpInfo(String[] helpInfo) {
		this.helpInfo = helpInfo;
	}

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getOptDesc() {
		return optDesc;
	}

	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}

	public String getAnnDesc() {
		return annDesc;
	}

	public void setAnnDesc(String annDesc) {
		this.annDesc = annDesc;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<String[]> getHelpList() {
		return helpList;
	}

	public void setHelpList(List<String[]> helpList) {
		this.helpList = helpList;
	}

	public String getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(String isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}