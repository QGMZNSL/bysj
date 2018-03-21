/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.dailywork.StudChangeAuditAction.java<br>
 * @Description: 考生基本信息变更审核功能 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:13:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.dailywork;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseStudinfoChange;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.SingleEntity;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.core.day.dailywork.busi.ChangeAudit;
import com.sincinfo.zxks.zxksdbs.StudentMgrDbService;

/**
 * @ClassName: StudChangeAuditAction
 * @Description: 考生基本信息变更审核功能 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:13:20 AM<br>
 * 
 */
public class StudChangeAuditAction extends WebActionSupport {

	private static final long serialVersionUID = 5286133655230649491L;

	// 数据操作
	private StudentMgrDbService smds = new StudentMgrDbService();

	// 分页对象
	private Page page = new Page();

	// 地市列表
	private List<BaseCity> cityList;

	// 审核状态列表
	private List<SingleEntity> statusList;

	// 地市代码
	private String cityCode;

	// 状态代码
	private String status;

	// 考生准考证号
	private String studExamCode;

	// 考生证件号
	private String idnum;

	// 开始时间
	private String startDate;

	// 结束时间
	private String endDate;

	// 考生变更申请记录列表
	private List<BaseStudinfoChange> sinfoChangeList;

	// 操作员
	private BaseUser optUser;

	// 反选的框体
	private String[] auditChangeIds;

	// 审核状态（1-不通过，9-通过）
	private String auditStatus;

	// 不通过原因
	private String unAuditReason;

	/* 考生修改信息来源 add by litian 2012-08-20 */
	private int fillinBy;

	/**
	 * 初始化分页地址
	 */
	public void initUrl() {
		StringBuilder url = new StringBuilder();
		url.append(request.getContextPath());
		url.append("/day/dailywork/studAudit_qryChangeInfos.do?shfy=1");
		url.append(String.format("&cityCode=%1$s", this.cityCode));
		url.append(String.format("&status=%1$s", this.status));
		url.append(String.format("&studExamCode=%1$s", this.studExamCode));
		url.append(String.format("&idnum=%1$s", this.idnum));
		url.append(String.format("&startDate=%1$s", this.startDate));
		url.append(String.format("&endDate=%1$s", this.endDate));
		url.append(String.format("&fillinBy=%1$s", String
				.valueOf(this.fillinBy)));
		page.setPath(url.toString());
	}

	/**
	 * 初始化表单列表及数据
	 */
	public void init() {
		this.optUser = this.getCOperUser();

		// 初始化值
		this.cityCode = this.cityCode == null ? this.optUser.getCityCode()
				: this.cityCode;
		this.status = this.status == null ? "0" : this.status;
		this.studExamCode = this.studExamCode == null ? "" : this.studExamCode;
		this.idnum = this.idnum == null ? "" : this.idnum;
		this.startDate = this.startDate == null ? "" : this.startDate;
		this.endDate = this.endDate == null ? "" : this.endDate;

		// 初始化列表
		this.cityList = smds.qryCityList(this.optUser.getUserRole(),
				this.optUser.getCityCode());
		this.statusList = new ArrayList<SingleEntity>();
		this.statusList.add(new SingleEntity("", "全部"));
		this.statusList.add(new SingleEntity("0", "未审核"));
		this.statusList.add(new SingleEntity("1", "审核不通过"));
		this.statusList.add(new SingleEntity("8", "待省上处理"));
		this.statusList.add(new SingleEntity("9", "已审核"));

		// 设置分页地址
		initUrl();
	}

	/**
	 * 根据条件查询考生基本信息修改的申请记录
	 * 
	 * @return
	 */
	public String qryChangeInfos() {
		// 初始化信息
		init();

		// 查询考生申请
		this.sinfoChangeList = this.smds.qryStudChanges(this.cityCode,
				this.studExamCode, this.idnum, this.status, this.startDate,
				this.endDate, this.fillinBy, this.page);

		// 处理结果集中的特殊数据
		for (BaseStudinfoChange ic : this.sinfoChangeList) {
			// 设置审核状态
			if ("0".equals(ic.getChangeAuditStatus())) {
				ic.setChangeAuditStatus("未审核");
			} else if ("1".equals(ic.getChangeAuditStatus())) {
				ic.setChangeAuditStatus(String.format(
						"<label title='%1$s'>审核不通过</label>", ic
								.getChangeAuditReason()));
			} else if ("8".equals(ic.getChangeAuditStatus())) {
				ic.setChangeAuditStatus("地市已审核");
			} else if ("9".equals(ic.getChangeAuditStatus())) {
				ic.setChangeAuditStatus("已审核");
			}

			// 处理oldInfo与newInfo
			String oldInfo = null;
			String newInfo = null;
			if ("2".equals(ic.getChangeTypeCode())) {
				oldInfo = this.smds.getIdnoTypeName(ic.getOldInfo());
				newInfo = this.smds.getIdnoTypeName(ic.getNewInfo());
			} else if ("4".equals(ic.getChangeTypeCode())) {
				oldInfo = this.smds.getGenderName(ic.getOldInfo());
				newInfo = this.smds.getGenderName(ic.getNewInfo());
			} else if ("6".equals(ic.getChangeTypeCode())) {
				oldInfo = this.smds.getForkName(ic.getOldInfo());
				newInfo = this.smds.getForkName(ic.getNewInfo());
			} else {
				oldInfo = ic.getOldInfo();
				newInfo = ic.getNewInfo();
			}

			// 格式化信息变更内容
			ic.setChangeTemplate(String.format(ic.getChangeTemplate(), ic
					.getChangeTypeName(), oldInfo, newInfo));
		}

		return "changesShow";
	}

	/**
	 * 进行审核通过或审核不通过操作
	 */
	public void doAudit() {
		// 勾选判断
		if (auditChangeIds == null || auditChangeIds.length == 0) {
			this.Alert("请勾选要审核的变更申请记录！");
			this.PostJs("history.back();");
			return;
		}

		// 判断是否已经被审核过
		if (this.smds.isAnyAudited(this.auditChangeIds)) {
			this.Alert("勾选的变更申请中存在已被审核的记录！");
			this.PostJs("history.back();");
			return;
		}

		// 判断是否已经被审核不通过
		if (this.smds.isAnyAuditedUnpass(this.auditChangeIds)) {
			this.Alert("勾选的变更申请中存在已被审核不通过的记录！");
			this.PostJs("history.back();");
			return;
		}

		// 调用审核处理
		this.optUser = this.getCOperUser();
		ChangeAudit caudit = new ChangeAudit(this.smds);
		int[] sucAfai = caudit.doChangeAudit(this.auditChangeIds, this.optUser,
				this.auditStatus, StringTool.trim(this.unAuditReason));
		if (sucAfai[0] == this.auditChangeIds.length && sucAfai[1] == 0) {
			// 执行成功的对所有为地市已核对的记录进行再次审核，本操作不处理返回值
			List<BaseStudinfoChange> status8Chgs = this.smds
					.qryStudinfoChangeOnlyAudByCity(this.auditChangeIds);
			List<String> reAudIds = new ArrayList<String>();
			for ( BaseStudinfoChange sc : status8Chgs) {
				if ( "0".equals(sc.getIsPeopleChange())) {
					reAudIds.add(sc.getChangeId());
				}
			}
			if ( reAudIds.size() >= 1) {
				String[] reAudIdsArr = new String[reAudIds.size()];
				reAudIdsArr = reAudIds.toArray(reAudIdsArr);
				caudit.doChangeAudit( reAudIdsArr, optUser, "9", "");
			}

			// 提示并返回
			this.Alert("审核操作执行成功！");
			init();
			StringBuilder urlJs = new StringBuilder();
			urlJs.append("location.href='");
			urlJs.append(request.getContextPath());
			urlJs.append("/day/dailywork/studAudit_qryChangeInfos.do?shfy=1");
			urlJs.append(String.format("&fillinBy=%1$s", String
					.valueOf(this.fillinBy)));
			urlJs.append(String.format("&cityCode=%1$s", this.cityCode));
			urlJs.append(String.format("&status=%1$s", this.status));
			urlJs
					.append(String.format("&studExamCode=%1$s",
							this.studExamCode));
			urlJs.append(String.format("&idnum=%1$s", this.idnum));
			urlJs.append(String.format("&startDate=%1$s", this.startDate));
			urlJs.append(String.format("&endDate=%1$s", this.endDate));
			urlJs.append("';");
			this.PostJs(urlJs.toString());
		} else if (sucAfai[1] >= 0 || sucAfai[2] >= 0) {
			String msg = "本次操作成功审核%1$s条记录，失败%2$s条记录！\\n其中存在%3$s条记录为证件号与姓名同时修改的情况且地市尚未核对相关材料。";
			msg = String.format(msg, sucAfai[0], sucAfai[1], sucAfai[2]);
			this.Alert(msg);
			init();
			StringBuilder urlJs = new StringBuilder();
			urlJs.append("location.href='");
			urlJs.append(request.getContextPath());
			urlJs.append("/day/dailywork/studAudit_qryChangeInfos.do?shfy=1");
			urlJs.append(String.format("&fillinBy=%1$s", String
					.valueOf(this.fillinBy)));
			urlJs.append(String.format("&cityCode=%1$s", this.cityCode));
			urlJs.append(String.format("&status=%1$s", this.status));
			urlJs
					.append(String.format("&studExamCode=%1$s",
							this.studExamCode));
			urlJs.append(String.format("&idnum=%1$s", this.idnum));
			urlJs.append(String.format("&startDate=%1$s", this.startDate));
			urlJs.append(String.format("&endDate=%1$s", this.endDate));
			urlJs.append("';");
			this.PostJs(urlJs.toString());
		} else {
			this.Alert("审核操作执行失败！");
			this.PostJs("history.back()");
		}
	}

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

	public List<SingleEntity> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<SingleEntity> statusList) {
		this.statusList = statusList;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStudExamCode() {
		return studExamCode;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
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

	public BaseUser getOptUser() {
		return optUser;
	}

	public void setOptUser(BaseUser optUser) {
		this.optUser = optUser;
	}

	public List<BaseStudinfoChange> getSinfoChangeList() {
		return sinfoChangeList;
	}

	public void setSinfoChangeList(List<BaseStudinfoChange> sinfoChangeList) {
		this.sinfoChangeList = sinfoChangeList;
	}

	public String[] getAuditChangeIds() {
		return auditChangeIds;
	}

	public void setAuditChangeIds(String[] auditChangeIds) {
		this.auditChangeIds = auditChangeIds;
	}

	public String getUnAuditReason() {
		return unAuditReason;
	}

	public void setUnAuditReason(String unAuditReason) {
		this.unAuditReason = unAuditReason;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getFillinBy() {
		return fillinBy;
	}

	public void setFillinBy(int fillinBy) {
		this.fillinBy = fillinBy;
	}

}
