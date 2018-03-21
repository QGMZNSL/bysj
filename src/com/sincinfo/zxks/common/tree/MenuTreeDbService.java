/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.common.tree.MenuTreeDbService.java<br>
 * @Description: 专为菜单项服务的db处理类 <br>
 * <br>
 * @author litian<br>
 * @date Jan 18, 2012 5:38:00 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.common.tree;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.common.db.DbUtil;

/**
 * @ClassName: MenuTreeDbService
 * @Description: 专为菜单项服务的db处理类 <br>
 *               <br>
 * @author litian
 * @date Jan 18, 2012 5:38:00 PM<br>
 * 
 */
public class MenuTreeDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public MenuTreeDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据用户角色获取头部第一级菜单列表
	 * 
	 * @param userRole
	 * @return
	 */
	public List<MenuNode> getHeadNodes(String userRole) {
		List<MenuNode> headNodes = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from sys_menu m");
		sql.append(" where m.menu_type = 0");
		sql.append(" and m.menu_lock = 0");
		sql.append(" and m.parent_menu = 0");
		sql.append(String.format(" and bitand(m.user_role, %1$s) = %1$s",
				userRole));
		sql.append(" order by m.menu_order asc");
		headNodes = this.dbUtil.getObjList(sql.toString(), MenuNode.class);
		return headNodes == null ? new ArrayList<MenuNode>() : headNodes;
	}

	/**
	 * 根据头部节点的menu_id
	 * 
	 * @param parentMenu
	 * @param userRole
	 * @return
	 */
	public List<MenuNode> getScndHeadNodes(String parentMenu, String userRole) {
		List<MenuNode> scndHeadNodes = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from sys_menu m");
		sql.append(" where m.menu_type = 0");
		sql.append(" and m.menu_lock = 0");
		sql.append(String.format(" and m.parent_menu = %1$s", parentMenu));
		sql.append(String.format(" and bitand(m.user_role, %1$s) = %1$s",
				userRole));
		sql.append(" order by m.menu_order asc");
		scndHeadNodes = this.dbUtil.getObjList(sql.toString(), MenuNode.class);
		return scndHeadNodes == null ? new ArrayList<MenuNode>()
				: scndHeadNodes;
	}

	/**
	 * 获取指定菜单项对象
	 * 
	 * @param menuId
	 *            菜单项的标识id
	 * @return MenuNode
	 */
	public MenuNode getMenuNode(String menuId) {
		MenuNode menu = null;
		StringBuilder sql = new StringBuilder();
		sql.append(String.format(
				"select * from sys_menu m where m.menu_id='%1$s'", menuId));
		menu = this.dbUtil.getObject(sql.toString(), MenuNode.class);
		return menu;
	}

	/**
	 * 获取菜单树
	 * 
	 * @param menuId
	 *            头部菜单项id
	 * @param powerArray
	 *            权限码
	 * @return
	 */
	public List<TreeNode> getTree(String menuId, String powerArray) {
		List<TreeNode> tree = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,");
		sql.append(" (select p.project_base_url");
		sql.append(" from sys_project p");
		sql.append(" where p.project_id = t.project_id) as projectUrl");
		sql.append(" from sys_tree t");
		sql.append(" where t.node_close = '0'");
		sql.append(String.format(" and t.menu_id = %1$s", menuId));
		sql
				.append(String
						.format(
								" and (substr('%1$s', t.pow_ctrl_index, 1) = '1' or t.pow_ctrl_index = 0)",
								powerArray));
		sql.append(" order by t.node_order");
//		System.out.println("sql=="+sql);
		tree = dbUtil.getObjList(sql.toString(), TreeNode.class);
		return tree;
	}

	/**
	 * 查询菜单树对应的节点
	 * 
	 * @param nodeId
	 *            菜单节点编号
	 * @return TreeNode
	 */
	public TreeNode getTreeNode(String nodeId) {
		TreeNode node = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,");
		sql.append(" (select p.project_base_url");
		sql.append(" from sys_project p");
		sql.append(" where p.project_id = t.project_id) as projectUrl");
		sql.append(String.format(" from sys_tree t where t.node_id=%1$s",
				nodeId));
		node = dbUtil.getObject(sql.toString(), TreeNode.class);
		return node;
	}

	/**
	 * 获得所需的毕业审核节点
	 * 
	 * @return
	 */
	public String[] getNeedsGraAuditNode() {
		String flowStr = dbUtil
				.getFirstCol("select s.switch_value from base_switch s where s.switch_code='003'");
		String[] flowArr = flowStr.split(",");
		for (int i = 0; i < flowArr.length; i++) {
			if ("1".equals(flowArr[i])) {
				flowArr[i] = "61";
			} else if ("2".equals(flowArr[i])) {
				flowArr[i] = "62";
			} else if ("3".equals(flowArr[i])) {
				flowArr[i] = "63";
			} else if ("4".equals(flowArr[i])) {
				flowArr[i] = "127";
			}
		}

		return flowArr;
	}

	/**
	 * 获取毕业不需要的审核节点
	 * 
	 * @return
	 */
	public String[] getUnNeedsGraAuditNode() {
		String[] allNodeIds = new String[] { "61", "62", "63", "127" };
		String[] needNodeIds = getNeedsGraAuditNode();
		for (int i = 0; i < allNodeIds.length; i++) {
			for (int j = 0; j < needNodeIds.length; j++) {
				if (allNodeIds[i].equals(needNodeIds[j])) {
					allNodeIds[i] = "";
				}
			}
		}

		// 整理结果
		int count = 0;
		for (String n : allNodeIds) {
			if (!"".equals(n)) {
				count++;
			}
		}
		String[] unNeedNodeIds = new String[count];
		int index = 0;
		for (String n : allNodeIds) {
			if (!"".equals(n)) {
				unNeedNodeIds[index] = n;
				index++;
			}
		}
		return unNeedNodeIds;
	}

	/**
	 * 判断菜单树节点是否为包节点
	 * @param nodeId 节点id
	 * @return boolean
	 */
	public boolean isPackage(String nodeId) {
		return dbUtil.getNum(String.format(
				"select count(1) from sys_tree t where t.parent_node='%1$s'",
				nodeId)) >= 1;
	}
}
