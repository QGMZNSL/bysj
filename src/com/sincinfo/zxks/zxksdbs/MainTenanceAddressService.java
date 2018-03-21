package com.sincinfo.zxks.zxksdbs;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.MainTenanceAddress;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;

public class MainTenanceAddressService {
	private DbUtil dbUtil =new DbUtil();
	//获得所有本省内的所有城市列表
	public List<BaseCity> qryCitys(String cityCode){
		if("610000".equals(cityCode)){
			List<BaseCity> citys = new ArrayList<BaseCity>();
			String selectSQL = "select * from  base_city c order by city_code asc";
			citys = dbUtil.getObjList(selectSQL ,BaseCity.class);
			if(citys!=null && !"".equals(citys)){
				return citys;
			}
		}else{
			List<BaseCity> citys = new ArrayList<BaseCity>();
			String selectSQL = "select * from  base_city c where c.city_code = '"+cityCode+"'";
			citys = dbUtil.getObjList(selectSQL ,BaseCity.class);
			return citys;
		}
		return null;
	}
	public List<MainTenanceAddress> allAddressList(String cityCode, Page page){
		if("610000".equals(cityCode)){
			//如果他查看的是陕西省
			List<MainTenanceAddress> allAddressList = new ArrayList<MainTenanceAddress>();
			String selectSQL ="select * from base_bureau bb where bb.city_code = '00'  ";
			
			String sqlCount = "select count(*) from base_bureau bb where bb.city_code = '00' ";
			//总页数
			
			String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()),selectSQL.toString());
			allAddressList = dbUtil.getObjList(sqlPage, MainTenanceAddress.class);
			return allAddressList;
		}
		//根据传过来的cityCode进行查询
		List<MainTenanceAddress> allAddressList = new ArrayList<MainTenanceAddress>();
		StringBuilder sb = new StringBuilder();
		sb.append(" select b.*,");
		sb.append(" (select c.city_name from base_city c where c.city_code = b.city_code) as cityName");
		sb.append(" from base_bureau b");
		sb.append(" where 1 = 1");
		sb.append(" and b.city_code = '"+cityCode+"'");
		sb.append(" order by b.city_code, b.oper_code");
		
		
		StringBuilder sqlCount = new StringBuilder();
		//总页数
				sqlCount.append(" select count(*)");
				sqlCount.append(" from base_bureau b ");
				sqlCount.append(" where 1 = 1 ");
				sqlCount.append(" and b.city_code = '"+cityCode+"'");
				sqlCount.append(" order by b.city_code, b.oper_code ");
		   
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()),sb.toString());
		allAddressList = dbUtil.getObjList(sqlPage, MainTenanceAddress.class);
		//返回一个所得的List
		return allAddressList;
	}
	public boolean  addAddress(String cityCode,String operCode,String studPostalAddress ,String byBus, String telephone ){
	//添加一个地址维护
		int flag =0;
		if("610000".equals(cityCode)){
			   cityCode="00";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into base_bureau b");
		sb.append(" (b.city_code,");
		sb.append(" b.oper_code,");
		sb.append(" b.stud_postal_address,");
		sb.append(" b.link_telephone,");
		sb.append(" b.by_bus)");
		sb.append(" values");
		sb.append(" ('"+cityCode+"', '"+operCode+"', '"+studPostalAddress+"', '"+telephone+"', '"+byBus+"')");
		flag = dbUtil.saveOrUpdate(sb.toString());
		if(flag>=1){
			return true;
		}
		return false;
	}
	public boolean deleteInfo(String cityCode,String operCode){
		//删除一个地址维护
		int flag =0;
		if("610000".equals(cityCode)){
			   cityCode="00";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" delete from base_bureau b");
		sb.append(" where b.city_code = '"+cityCode+"'");
		sb.append(" and b.oper_code = '"+operCode+"'");
		flag = dbUtil.saveOrUpdate(sb.toString());
		if(flag>=1){
			return true;
		}
		return false;
	}
	public List<MainTenanceAddress> getUpdateInfoByCondition(String cityCode,String operCode){
		//修改时根据修改的编号进入修改页面  初始化他们的值
		List<MainTenanceAddress> updateList = new ArrayList<MainTenanceAddress>(); 
		String selectSQL = "select b.*,rowid from base_bureau b where b.city_code ='"+cityCode+"' and b.oper_code = '"+operCode+"'";
		updateList = dbUtil.getObjList(selectSQL, MainTenanceAddress.class);
		return updateList;
	}
	public boolean updateInfoByCondition(MainTenanceAddress mta,String cityCode){
		int  flag  ;
		String updateSQL = "update base_bureau b set b.stud_postal_address = '"+mta.getStudPostalAddress()+"' , b.by_bus ='"+mta.getByBus()+"' , b.link_telephone = '"+mta.getLinkTelephone()+"' where  b.city_code = '"+cityCode+"' and b.oper_code = '"+mta.getOperCode()+"'";
		flag = dbUtil.saveOrUpdate(updateSQL);
		if(flag==1){
			return true;
		}
		return false;
	}
}
