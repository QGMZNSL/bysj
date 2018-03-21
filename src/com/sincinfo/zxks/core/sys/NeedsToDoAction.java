/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.sys.NeedsToDoAction.java<br>
 * @Description: 待办事宜 <br>
 * <br>
 * @author litian<br>
 * @date Jun 15, 2012 8:58:00 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.sys;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.zxksdbs.NeedsToDoDbService;

/**
 * @ClassName: NeedsToDoAction
 * @Description: 待办事宜 <br>
 *               <br>
 * @author litian
 * @date Jun 15, 2012 8:58:00 AM<br>
 * 
 */
public class NeedsToDoAction extends WebActionSupport {

	private static final long serialVersionUID = -4655181125139279498L;

	// 待办事宜链接列表
	private List<String> needsToDoLinks;

	/**
	 * 进入显示页
	 * 
	 * @return
	 */
	public String showToDo() {
		// 获取操作员
		BaseUser optUser = this.getCOperUser();
		NeedsToDoDbService ntdDs = new NeedsToDoDbService();
		this.needsToDoLinks = new ArrayList<String>();
		String linkFormat = "<a href='%1$s'>%2$s</a>";
		String linkTmp = null;
		String urlTmp = null;

		// 毕业-初审
		linkTmp = ntdDs.graNeedsFirstAudit(optUser);
		if (!"".equals(StringTool.trim(linkTmp))) {
			urlTmp = "/ZK_CORE/sys/core_loginAcross.do?nid=61&needsToDo=needsToDoAutoQuery";
			linkTmp = String.format(linkFormat, urlTmp, linkTmp);
			needsToDoLinks.add(linkTmp);
		}

		// 毕业-审核
		linkTmp = ntdDs.graNeedsSecondAudit(optUser);
		if (!"".equals(StringTool.trim(linkTmp))) {
			urlTmp = "/ZK_CORE/sys/core_loginAcross.do?nid=62&needsToDo=needsToDoAutoQuery";
			linkTmp = String.format(linkFormat, urlTmp, linkTmp);
			needsToDoLinks.add(linkTmp);
		}

		// 毕业-复审
		linkTmp = ntdDs.graNeedsThirdAudit(optUser);
		if (!"".equals(StringTool.trim(linkTmp))) {
			urlTmp = "/ZK_CORE/sys/core_loginAcross.do?nid=63&needsToDo=needsToDoAutoQuery";
			linkTmp = String.format(linkFormat, urlTmp, linkTmp);
			needsToDoLinks.add(linkTmp);
		}

		// 毕业-终审
		linkTmp = ntdDs.graNeedsFinalAudit(optUser);
		if (!"".equals(StringTool.trim(linkTmp))) {
			urlTmp = "/ZK_CORE/sys/core_loginAcross.do?nid=127&needsToDo=needsToDoAutoQuery";
			linkTmp = String.format(linkFormat, urlTmp, linkTmp);
			needsToDoLinks.add(linkTmp);
		}

		// 转出-确认
		linkTmp = ntdDs.transOutForConfirm(optUser);
		if (!"".equals(StringTool.trim(linkTmp))) {
			urlTmp = "/ZK_CORE/sys/core_loginAcross.do?nid=76&needsToDo=needsToDoAutoQuery";
			linkTmp = String.format(linkFormat, urlTmp, linkTmp);
			needsToDoLinks.add(linkTmp);
		}

		// 转出-登记
		linkTmp = ntdDs.transOutForSign(optUser);
		if (!"".equals(StringTool.trim(linkTmp))) {
			urlTmp = "/ZK_CORE/sys/core_loginAcross.do?nid=77&needsToDo=needsToDoAutoQuery";
			linkTmp = String.format(linkFormat, urlTmp, linkTmp);
			needsToDoLinks.add(linkTmp);
		}

		// 转入-复核
		linkTmp = ntdDs.transInForAudit(optUser);
		if (!"".equals(StringTool.trim(linkTmp))) {
			urlTmp = "/ZK_CORE/sys/core_loginAcross.do?nid=135&needsToDo=needsToDoAutoQuery";
			linkTmp = String.format(linkFormat, urlTmp, linkTmp);
			needsToDoLinks.add(linkTmp);
		}

		return "needsToDo";
	}

	public List<String> getNeedsToDoLinks() {
		return needsToDoLinks;
	}

	public void setNeedsToDoLinks(List<String> needsToDoLinks) {
		this.needsToDoLinks = needsToDoLinks;
	}

}
