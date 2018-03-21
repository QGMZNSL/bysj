/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.dailywork.TransSettingAction.java<br>
 * @Description: 考籍跨省调转条件设置 <br>
 * <br>
 * @author litian<br>
 * @date Mar 27, 2012 8:48:43 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.dailywork;

import java.util.List;

import com.sincinfo.zxks.bean.BaseInformation;
import com.sincinfo.zxks.bean.BaseSwitch;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.StatusSettingDbService;

/**
 * @ClassName: TransSettingAction
 * @Description: 考籍跨省调转条件设置 <br>
 *               <br>
 * @author litian
 * @date Mar 27, 2012 8:48:43 AM<br>
 * 
 */
public class StatusSettingAction extends WebActionSupport {

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 817612579057139536L;

	// 转入
	private String tin_localSyllabusNum;
	private String tin_startDate;
	private String tin_endDate;
	private String tin_noticeId;
	private String tin_noticeTitle;

	// 转出
	private String tout_localSyllabusNum;
	private String tout_startDate;
	private String tout_endDate;
	private String tout_noticeId;
	private String tout_noticeTitle;

	// 选择须知
	private Page page = new Page();

	// 控件id
	private String noticeType;
	private String noticeTitle;
	private String noticeTextId;
	private String noticeTextName;
	private List<BaseInformation> noticeList;

	/**
	 * 查询出开关设置列表
	 * 
	 * @return
	 */
	public String showSwitches() {
		// 查询
		StatusSettingDbService ssds = new StatusSettingDbService();
		List<BaseSwitch> allSwitches = ssds.qryAllStatusSwitches();

		// 归类赋值
		for (BaseSwitch bs : allSwitches) {
			if ("0".equals(bs.getSwitchType())) {
				// 毕业不处理
			} else if ("1".equals(bs.getSwitchType())) {
				// 转出
				if (StatusSettingDbService.SWITCH_TRANSOUT_OPENINFO.equals(bs
						.getSwitchCode())) {
					String[] dates = bs.getSwitchValue().split(":");
					this.tout_startDate = dates[0];
					this.tout_endDate = dates[1];
				} else if (StatusSettingDbService.SWTICH_TRANSOUT_MIN_SYLLABUS_NUM
						.equals(bs.getSwitchCode())) {
					this.tout_localSyllabusNum = bs.getSwitchValue();
				} else if (StatusSettingDbService.SWTICH_TRANSOUT_NOTICE
						.equals(bs.getSwitchCode())) {
					this.tout_noticeId = bs.getSwitchValue();
					this.tout_noticeTitle = ssds
							.getNoticeTitle(this.tout_noticeId);
				}
			} else if ("2".equals(bs.getSwitchType())) {
				// 转出
				if (StatusSettingDbService.SWITCH_TRANSIN_OPENINFO.equals(bs
						.getSwitchCode())) {
					String[] dates = bs.getSwitchValue().split(":");
					this.tin_startDate = dates[0];
					this.tin_endDate = dates[1];
				} else if (StatusSettingDbService.SWTICH_TRANSIN_MIN_SYLLABUS_NUM
						.equals(bs.getSwitchCode())) {
					this.tin_localSyllabusNum = bs.getSwitchValue();
				} else if (StatusSettingDbService.SWTICH_TRANSIN_NOTICE
						.equals(bs.getSwitchCode())) {
					this.tin_noticeId = bs.getSwitchValue();
					this.tin_noticeTitle = ssds
							.getNoticeTitle(this.tin_noticeId);
				}
			}
		}

		return "showSwitches";
	}

	/**
	 * 保存设置
	 */
	public void saveSwitches() {
		// 保存
		StatusSettingDbService ssds = new StatusSettingDbService();
		boolean outFlag = ssds.saveTransOutSetting(this.tout_startDate,
				this.tout_endDate, this.tout_localSyllabusNum,
				this.tout_noticeId);
		boolean inFlag = ssds.saveTransInSetting(this.tin_startDate,
				this.tin_endDate, this.tin_localSyllabusNum, this.tin_noticeId);

		// 判断操作结果
		if ( inFlag && outFlag) {
			this.Alert("保存考籍调转设定成功！");
		} else {
			this.GoBack("保存考籍调转设定失败！");
			return;
		}

		// 跳转
		String url = "%1$s/day/dailywork/staSwch_showSwitches.do";
		url = String.format(url, request.getContextPath());
		String jsUrl = String.format("location.href='%1$s';", url);
		this.PostJs(jsUrl);
	}

	/**
	 * 选择须知页面
	 * 
	 * @return
	 */
	public String qryNotices() {
		// 处理分页地址
		StringBuilder url = new StringBuilder();
		url.append(request.getContextPath());
		url.append("/day/dailywork/staSwch_qryNotices.do");
		page.setPath(url.toString());
		page.pushParam("noticeType", this.noticeType);
		page.pushParam("noticeTitle", this.noticeTitle);
		page.pushParam("noticeTextId", this.noticeTextId);
		page.pushParam("noticeTextName", this.noticeTextName);

		// 处理类型
		String inforClass = null;
		if ("transout".equals(this.noticeType)) {
			inforClass = "3";
		} else if ("transin".equals(this.noticeType)) {
			inforClass = "4";
		} else if ("graduate".equals(this.noticeType)) {
			inforClass = "5";
		}

		// 获取列表
		this.noticeTitle = this.noticeTitle == null ? "" : this.noticeTitle;
		StatusSettingDbService ssds = new StatusSettingDbService();
		this.noticeList = ssds.qryNotices(inforClass, this.noticeTitle, page);

		return "showNotice";
	}

	public String getTin_localSyllabusNum() {
		return tin_localSyllabusNum;
	}

	public void setTin_localSyllabusNum(String tin_localSyllabusNum) {
		this.tin_localSyllabusNum = tin_localSyllabusNum;
	}

	public String getTin_startDate() {
		return tin_startDate;
	}

	public void setTin_startDate(String tin_startDate) {
		this.tin_startDate = tin_startDate;
	}

	public String getTin_endDate() {
		return tin_endDate;
	}

	public void setTin_endDate(String tin_endDate) {
		this.tin_endDate = tin_endDate;
	}

	public String getTin_noticeId() {
		return tin_noticeId;
	}

	public void setTin_noticeId(String tin_noticeId) {
		this.tin_noticeId = tin_noticeId;
	}

	public String getTin_noticeTitle() {
		return tin_noticeTitle;
	}

	public void setTin_noticeTitle(String tin_noticeTitle) {
		this.tin_noticeTitle = tin_noticeTitle;
	}

	public String getTout_localSyllabusNum() {
		return tout_localSyllabusNum;
	}

	public void setTout_localSyllabusNum(String tout_localSyllabusNum) {
		this.tout_localSyllabusNum = tout_localSyllabusNum;
	}

	public String getTout_startDate() {
		return tout_startDate;
	}

	public void setTout_startDate(String tout_startDate) {
		this.tout_startDate = tout_startDate;
	}

	public String getTout_endDate() {
		return tout_endDate;
	}

	public void setTout_endDate(String tout_endDate) {
		this.tout_endDate = tout_endDate;
	}

	public String getTout_noticeId() {
		return tout_noticeId;
	}

	public void setTout_noticeId(String tout_noticeId) {
		this.tout_noticeId = tout_noticeId;
	}

	public String getTout_noticeTitle() {
		return tout_noticeTitle;
	}

	public void setTout_noticeTitle(String tout_noticeTitle) {
		this.tout_noticeTitle = tout_noticeTitle;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getNoticeTextId() {
		return noticeTextId;
	}

	public void setNoticeTextId(String noticeTextId) {
		this.noticeTextId = noticeTextId;
	}

	public String getNoticeTextName() {
		return noticeTextName;
	}

	public void setNoticeTextName(String noticeTextName) {
		this.noticeTextName = noticeTextName;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public List<BaseInformation> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<BaseInformation> noticeList) {
		this.noticeList = noticeList;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

}
