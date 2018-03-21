/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.datapreserve.busi.ModuleTool.java<br>
 * @Description: 权限码管理辅助类 <br>
 * <br>
 * @author litian<br>
 * @date Jan 20, 2012 4:05:59 PM 
 * @version V1.0   
*/
package com.sincinfo.zxks.core.day.datapreserve.busi;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.zxksdbs.DayDbService;

/**
 * @ClassName: ModuleTool 
 * @Description: 权限码管理辅助类 <br>
 * <br>
 * @author litian
 * @date Jan 20, 2012 4:05:59 PM<br>
 *  
 */
public class ModuleTool {
	/**
	 * @Fields MODULE_MAX_COUNT : 权限码位数上限
	 */
	public final static int MODULE_MAX_INDEX_COUNT = 500;
	
	private List<ModuleStruct> modStucts;
	
	public ModuleTool() {
		
	}
	
	/**
	 * 权限树
	 * @param userRole 用户角色
	 * @return
	 */
	public List<ModuleStruct> moduleTreeInitial( String userRole) {
		DayDbService dds = new DayDbService();
		this.modStucts = new ArrayList<ModuleStruct>();
		
		// 获取权限分类
		List<ModuleClass> mcs = null;
		mcs = dds.qryModuleClasses();
		mcs = mcs == null ? new ArrayList<ModuleClass>() : mcs;
		
		// 根据分类初始化权限菜单树
		ModuleStruct tmpMs = null;
		for ( ModuleClass mc : mcs) {
			tmpMs = new ModuleStruct( mc);
			tmpMs.setModules( dds.qrySysModules( mc.getClassId(), userRole));
			this.modStucts.add(tmpMs);
		}
		
		return this.modStucts;
	}
	
}
