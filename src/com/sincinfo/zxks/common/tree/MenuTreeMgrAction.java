package com.sincinfo.zxks.common.tree;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.action.WebActionSupport;

public class MenuTreeMgrAction extends WebActionSupport {

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 5303636578459193175L;

	// 头部菜单
	private List<HeadMenu> headMenu = null;

	// 菜单树对应的菜单项
	private MenuNode topMenu = null;

	// 侧边栏菜单
	private List<TreeNode> treeNodes = null;

	/**
	 * 头部菜单列表
	 * 
	 * @return
	 */
	public String initTopTree() {
		BaseUser optUser = getCOperUser();
		MenuTreeMgr mtMgr = new MenuTreeMgr(optUser.getUserRole(), optUser
				.getPowerArray());
		this.headMenu = mtMgr.getHeadMenus();
		return "initTop";
	}

	/**
	 * 侧边栏菜单列表
	 * 
	 * @return
	 */
	public String initLeftTree() {
		BaseUser optUser = getCOperUser();
		MenuTreeMgr mtMgr = new MenuTreeMgr(optUser.getUserRole(), optUser
				.getPowerArray());
		this.topMenu = mtMgr.getMenuNode(this.topMenu.getMenuId());
		this.treeNodes = mtMgr.getTreeNodes(this.topMenu.getMenuId());

		String loginAcrossUrl = "/sys/core_loginAcross.do?nid=%1$s";
		boolean hasChilds = false;
		TreeNode nd = null;
		TreeNode tn = null;
		List<TreeNode> revNodes = new ArrayList<TreeNode>();
		for (int i = 0; i < this.treeNodes.size(); i++) {
			nd = this.treeNodes.get(i);
			hasChilds = false;
			if (!request.getContextPath().equals(nd.getProjectUrl())) {
				nd.setProjectUrl(request.getContextPath());
				nd.setLinkUrl(String.format(loginAcrossUrl, nd.getNodeId()));
			}
			// 过滤空连接
			if ("javascript:void(0);".equals(nd.getLinkUrl())) {
				nd.setProjectUrl("");
			}
			// 处理包节点
			if (mtMgr.isPackage(nd.getNodeId())) {
				// 判断是否是包节点
				for (int j = 0; j < this.treeNodes.size(); j++) {
					tn = this.treeNodes.get(j);
					if (!nd.getNodeId().equals(tn.getNodeId())) {
						if (nd.getNodeId().equals(tn.getParentNode())) {
							hasChilds = true;
						}
					}
				}

				// 记录空包节点
				if (!hasChilds) {
					revNodes.add(nd);
				}
			}
		}
		// 移除空包节点
		for ( TreeNode nullPack : revNodes) {
			this.treeNodes.remove( nullPack);
		}

		// 过滤掉一部分菜单
		this.treeNodes = mtMgr.removeAnyUnUseNodes(this.topMenu.getMenuId(),
				this.treeNodes);

		return "initLeft";
	}

	public List<HeadMenu> getHeadMenu() {
		return headMenu;
	}

	public void setHeadMenu(List<HeadMenu> headMenu) {
		this.headMenu = headMenu;
	}

	public MenuNode getTopMenu() {
		return topMenu;
	}

	public void setTopMenu(MenuNode topMenu) {
		this.topMenu = topMenu;
	}

	public List<TreeNode> getTreeNodes() {
		return treeNodes;
	}

	public void setTreeNodes(List<TreeNode> treeNodes) {
		this.treeNodes = treeNodes;
	}

}
