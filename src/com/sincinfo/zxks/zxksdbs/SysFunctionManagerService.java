package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.SysFunctionManagerPo;
import com.sincinfo.zxks.bean.SysTreeVO;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;

public class SysFunctionManagerService {
	//系统功能管理
		private SysFunctionManagerPo sysFunctionManagerPo;
		public List<SysFunctionManagerPo> sysFunList =null;
		public List <SysTreeVO> sysSonyList =null;
		DbUtil dbUtil = new DbUtil();
		public List<SysFunctionManagerPo>  getAllParentList(){
			//获得所有父节点列表
			String selectSQL = "select * from sys_menu s where s.parent_menu = '0' and s.menu_lock = '0'";
			sysFunList = dbUtil.getObjList(selectSQL,SysFunctionManagerPo.class);  
			//把所有查到的父类列表放入在sysFunList 列表中
			return sysFunList;
		}
		
		public List<SysFunctionManagerPo> getAllSonList(String parentId){
			//查询出所有父节点下的子节点
			String selectSQL = "select * from sys_menu s where s.parent_menu = '"+parentId+"'";
			sysFunList = dbUtil.getObjList(selectSQL,SysFunctionManagerPo.class);
			//把所有查到的子节点放入到sysFunList中
			return sysFunList;
		}
		public List<SysTreeVO> getTreeByMenuId(String menuId, Page page){
			
			//查询出所有tree下的菜单根据menuId
			String selectSQL = "select * from sys_tree where menu_id = '"+menuId+"'";
			StringBuilder sb = new StringBuilder();
			sb.append("select count(*)");
			sb.append("from sys_tree where menu_id = '"+menuId+"'");
			String sql = page.setPagecount(dbUtil.getNum(sb.toString()),selectSQL.toString());
			sysSonyList = dbUtil.getObjList(sql,SysTreeVO.class);
			return sysSonyList;
		}
		public boolean openFunction(String[] ckb){
			
			int  flag = 0;
			if(ckb!=null){
				for(int i=0;i<ckb.length;i++){
					String updateSQL = "update sys_tree s set s.node_close = '0' where node_id = '"+ckb[i]+"'";
					flag= dbUtil.saveOrUpdate(updateSQL);
				}
				if(flag ==1){
					return  true;
				}else{
					return false;
				}
			}
			return false;
		}
		public boolean closeFunction(String[] ckb){
			int flag =0;
			if(ckb!=null){
				for(int i=0;i<ckb.length;i++){
					String updateSQL = "update sys_tree s set s.node_close = '1' where node_id = '"+ckb[i]+"'";
					flag= dbUtil.saveOrUpdate(updateSQL);
				}
				if(flag ==1){
					 return  true;
				 }
			}
			else{
				 return false;
			 }
			return false;
		}
		public SysFunctionManagerPo getSysFunctionManagerPo() {
			return sysFunctionManagerPo;
		}
		public void setSysFunctionManagerPo(SysFunctionManagerPo sysFunctionManagerPo) {
			this.sysFunctionManagerPo = sysFunctionManagerPo;
		}
}
