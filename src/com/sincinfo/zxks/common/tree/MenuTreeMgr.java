package com.sincinfo.zxks.common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MenuTreeMgr
 * @Description: 菜单树管理类 <br>
 *               <br>
 * @author litian
 * @date Jan 18, 2012 4:44:59 PM<br>
 * 
 */
public class MenuTreeMgr {
	private String userRole;
	private String powerArray;
	private MenuTreeDbService treeDb = null;

	/**
	 * 构造
	 * 
	 * @param 用户角色
	 */
	public MenuTreeMgr(String userRole, String powerArray) {
		this.userRole = userRole;
		this.powerArray = powerArray;
		this.treeDb = new MenuTreeDbService();
	}

	/**
	 * 构建菜单对象列表
	 * 
	 * @return List<HeadMenu>
	 */
	public List<HeadMenu> getHeadMenus() {
		List<HeadMenu> headMenuList = new ArrayList<HeadMenu>();
		headMenuList = null;
		return headMenuList;
	}
	
	/**
	 * 返回指定菜单项
	 * @param menuId 菜单项编号
	 * @return MenuNode
	 */
	public MenuNode getMenuNode( String menuId) {
		return treeDb.getMenuNode(menuId);
	}
	
	/**
	 * 获取菜单树
	 * 
	 * @return List<TreeNode>
	 */
	public List<TreeNode> getTreeNodes( String menuId) {
		List<TreeNode> tree = null;
		tree = treeDb.getTree(menuId, powerArray);
		return tree == null ? new ArrayList<TreeNode>() : tree;
	}
	
	/**
	 * 移除业务不需要的节点
	 * @param menuId 头部菜单id
	 * @param treeNodes 菜单树列表
	 * @return treeNodes
	 */
	public List<TreeNode> removeAnyUnUseNodes( String menuId, List<TreeNode> treeNodes) {
		if ( "13".equals(menuId)) {
			String[] flowArr = treeDb.getUnNeedsGraAuditNode();
			TreeNode tmp = null;
			for ( String f : flowArr) {
				for ( int i = 0; i < treeNodes.size(); i++) {
					tmp = treeNodes.get(i);
					if ( f.equals(tmp.getNodeId())) {
						treeNodes.remove(i);
					}
				}
			}
		}
		return treeNodes;
	}

	/**
	 * 判断菜单树节点是否为包节点
	 * @param nodeId 节点id
	 * @return boolean
	 */
	public boolean isPackage( String nodeId) {
		return this.treeDb.isPackage(nodeId);
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getPowerArray() {
		return powerArray;
	}

	public void setPowerArray(String powerArray) {
		this.powerArray = powerArray;
	}

}
