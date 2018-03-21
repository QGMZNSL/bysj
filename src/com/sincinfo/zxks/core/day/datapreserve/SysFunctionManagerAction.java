package com.sincinfo.zxks.core.day.datapreserve;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sincinfo.zxks.bean.SysFunctionManagerPo;
import com.sincinfo.zxks.bean.SysTreeVO;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.SysFunctionManagerService;

public class SysFunctionManagerAction extends WebActionSupport {
	/**
	 * 
	 */

	private static final long serialVersionUID = -5251475082351602192L;
	private SysFunctionManagerService sysFunctionManagerService = new SysFunctionManagerService();
	private SysFunctionManagerPo sysFunctionManagerPo;
	private List<SysFunctionManagerPo> parentList;
	private List<SysFunctionManagerPo> sonList;
	private SysTreeVO sysTreeVO;
	private List<SysTreeVO> littlesonList;
	private String sonId;
	private String parentId;
	private String menuId;
	private String nodeId;
	private String[] ckb;
	private String method;
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAllParentList() {
		this.parentId = this.parentId == null ? "" : this.parentId;
		this.menuId = this.menuId == null ? "" : this.menuId;

		// 获得所有一层节点目录
		parentList = sysFunctionManagerService.getAllParentList();
		return "intoSysFun";
	}

	public void checkSon() {
		// 获得一个父节点Id传到后台
		sonList = sysFunctionManagerService.getAllSonList(parentId);
		// 根据父节点id来查询子节点内容
		StringBuilder sb = new StringBuilder();
		sb
				.append("<select class='inputText inputTextM' id='menuId' name='menuId'>");
		sb.append("<option value=''>--请选择--</option>");
		if("1".equals(parentId)){
			sb.append("<option value='1'>首页</option>");
		}
		SysFunctionManagerPo sfmpo = null;
		if (sonList != null) {
			for (int i = 0; i < sonList.size(); i++) {
				sfmpo = sonList.get(i);
				sb.append(String.format("<option value='%1$s'>%2$s</option>",
						sfmpo.getMenuId(), sfmpo.getMenuName()));
				if (sfmpo.getMenuId() == null || sfmpo.getMenuName() == null) {
					SysFunctionManagerPo sfmpo1 = new SysFunctionManagerPo();
					sfmpo1 = sonList.get(i);
					sb.append(String.format(
							"<option value='%1$s'>%2$s</option>", sfmpo1
									.getMenuId(), sfmpo1.getMenuName()));
				}
			}
		}
		sb.append("</select>");
		aJax(sb.toString());
	}

	public String getTreeByMenuId() {
		parentList = sysFunctionManagerService.getAllParentList();
		// 获得所有的Tree 根据二级节点也就是menuId
		StringBuilder url = new StringBuilder();
		url.append(this.request.getContextPath());
		url.append("/manager/day/message/sysFun_getTreeByMenuId.do?menuId="
				+ menuId);

		littlesonList = sysFunctionManagerService.getTreeByMenuId(menuId, page);

		page.setPath(url.toString());
		return "intoSysFun";
	}

	public void aJax(String retStr) {
		// ajax 的编码方式
		response.setContentType("text/html;charese=utf-8");
		PrintWriter pw = null;
		// ajax页面菜单级联
		try {
			pw = response.getWriter();
			pw.write(retStr);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public String function() {
		boolean flg;
		if ("1".equals(method)) {
			flg = sysFunctionManagerService.openFunction(ckb);
			if (flg == true) {
				this.Alert("开启成功！");
				this.PostJs(String.format(
					"location.href='%1$s/manager/day/message/sysFun_getTreeByMenuId.do?menuId="
								+ menuId + "&parentId=" +parentId + "';", request.getContextPath()));
			} else {
				this.Alert("开启失败！");
				this.Alert("请先选择要开启的功能!");
				
				this.PostJs(String.format(
						"location.href='%1$s/manager/day/message/sysFun_getTreeByMenuId.do?menuId="
									+ menuId + "&parentId=" +parentId + "';", request.getContextPath()));
			}
		} else if ("0".equals(method)) {
			sysFunctionManagerService.closeFunction(ckb);
			this.Alert("关闭失败！");
			this.Alert("请先选择要关闭的功能!");
			this.PostJs(String.format(
					"location.href='%1$s/manager/day/message/sysFun_getTreeByMenuId.do?menuId="
								+ menuId + "&parentId=" +parentId + "';", request.getContextPath()));
		} else {
			this.Alert("关闭成功！");
			this.PostJs(String.format(
					"location.href='%1$s/manager/day/message/sysFun_getTreeByMenuId.do?menuId="
								+ menuId + "&parentId=" +parentId + "';", request.getContextPath()));
		}
		return null;
	}

	public SysFunctionManagerService getSysFunctionManagerService() {
		return sysFunctionManagerService;
	}

	public void setSysFunctionManagerService(
			SysFunctionManagerService sysFunctionManagerService) {
		this.sysFunctionManagerService = sysFunctionManagerService;
	}

	public List<SysFunctionManagerPo> getParentList() {
		return parentList;
	}

	public void setParentList(List<SysFunctionManagerPo> parentList) {
		this.parentList = parentList;
	}

	public SysFunctionManagerPo getSysFunctionManagerPo() {
		return sysFunctionManagerPo;
	}

	public void setSysFunctionManagerPo(
			SysFunctionManagerPo sysFunctionManagerPo) {
		this.sysFunctionManagerPo = sysFunctionManagerPo;
	}

	public List<SysFunctionManagerPo> getSonList() {
		return sonList;
	}

	public void setSonList(List<SysFunctionManagerPo> sonList) {
		this.sonList = sonList;
	}

	public String getSonId() {
		return sonId;
	}

	public void setSonId(String sonId) {
		this.sonId = sonId;
	}

	public List<SysTreeVO> getLittlesonList() {
		return littlesonList;
	}

	public void setLittlesonList(List<SysTreeVO> littlesonList) {
		this.littlesonList = littlesonList;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public SysTreeVO getSysTreeVO() {
		return sysTreeVO;
	}

	public void setSysTreeVO(SysTreeVO sysTreeVO) {
		this.sysTreeVO = sysTreeVO;
	}

	public String[] getCkb() {
		return ckb;
	}

	public void setCkb(String[] ckb) {
		this.ckb = ckb;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
