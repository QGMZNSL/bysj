package com.sincinfo.zxks.bean;

public class GenericEntity {
	protected String id;
	protected String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GenericEntity() {
		super();
	}
	public GenericEntity(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
