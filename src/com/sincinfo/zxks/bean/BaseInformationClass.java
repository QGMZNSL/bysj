package com.sincinfo.zxks.bean;

public class BaseInformationClass {

    private String classId;//须知分类编号

    private String className;//须知分类名称

    public void setClassId( String classId) { 
        this.classId = classId;
    }

    public String getClassId() { 
        return this.classId;
    }

    public void setClassName( String className) { 
        this.className = className;
    }

    public String getClassName() { 
        return this.className;
    }

}
