package com.sincinfo.zxks.core.day.datapreserve.busi;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.SysModule;

/**
 * @ClassName: ModuleStruct
 * @Description: 模块权限 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 4:16:19 PM<br>
 * 
 */
public class ModuleStruct {
	private ModuleClass clazz;
	private List<SysModule> modules;
	
	public ModuleStruct() {
		this.clazz = new ModuleClass();
		this.modules = new ArrayList<SysModule>();
	}
	
	public ModuleStruct( ModuleClass clazz) {
		this.clazz = clazz;
		this.modules = new ArrayList<SysModule>();
	}
	
	public ModuleStruct( ModuleClass clazz, List<SysModule> modules) {
		this.clazz = clazz;
		this.modules = modules;
	}
	
	public void addModule( SysModule module) {
		this.modules.add(module);
	}
	
	public void remove( int index) {
		this.modules.remove(index);
	}
	
	public void remove( SysModule module) {
		for ( SysModule sm : this.modules) {
			if ( sm == module) this.modules.remove(module);
			break;
		}
	} 

	public void clear() {
		this.modules.clear();
	}
	
	public ModuleClass getClazz() {
		return clazz;
	}

	public void setClazz(ModuleClass clazz) {
		this.clazz = clazz;
	}

	public List<SysModule> getModules() {
		return modules;
	}

	public void setModules(List<SysModule> modules) {
		this.modules = modules;
	}

}
