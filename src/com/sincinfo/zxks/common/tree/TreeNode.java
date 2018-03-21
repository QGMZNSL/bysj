package com.sincinfo.zxks.common.tree;

/**
 * @ClassName: TreeNode
 * @Description: 菜单树节点 <br>
 *               <br>
 * @author litian
 * @date Jan 18, 2012 4:43:36 PM<br>
 * 
 */
public class TreeNode {
	private String nodeId;
	private String nodeName;
	private String parentNode;
	private String nodeOrder;
	private String icon;
	private String powCtrlIndex;
	private String projectId;
	private String projectUrl;
	private String linkUrl;
	private String menuId;
	private String nodeClose;
	private String navigator;
	private String functionId;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public String getNodeOrder() {
		return nodeOrder;
	}

	public void setNodeOrder(String nodeOrder) {
		this.nodeOrder = nodeOrder;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPowCtrlIndex() {
		return powCtrlIndex;
	}

	public void setPowCtrlIndex(String powCtrlIndex) {
		this.powCtrlIndex = powCtrlIndex;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectUrl() {
		return projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getNodeClose() {
		return nodeClose;
	}

	public void setNodeClose(String nodeClose) {
		this.nodeClose = nodeClose;
	}

	public String getNavigator() {
		return navigator;
	}

	public void setNavigator(String navigator) {
		this.navigator = navigator;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

}
