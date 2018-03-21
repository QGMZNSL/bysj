package com.sincinfo.zxks.bean;

/**
 * @ClassName: SingleEntity
 * @Description: 简单的实例 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 9:34:30 AM<br>
 * 
 */
public class SingleEntity {
	// 标识
	private String entityCode;
	
	// 名称
	private String entityName;
	
	public SingleEntity() {
		
	}
	
	public SingleEntity( String code, String name) {
		this.entityCode = code;
		this.entityName = name;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
