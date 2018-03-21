package com.sincinfo.zxks.bean;

public class BaseJoinhandsUnitPro {

    private String unitCode; // 合作单位代码

    private String proCode; // 专业代码

    private String isShow; // 是否公开开考     0不公开    1 公开

    private String proName;//专业名称
    private String levelCode;//专业层次 代码
    private String levelName;//专业层次 名称
    
    public void setUnitCode( String unitCode) { 
        this.unitCode = unitCode;
    }

    public String getUnitCode() { 
        return this.unitCode;
    }

    public void setProCode( String proCode) { 
        this.proCode = proCode;
    }

    public String getProCode() { 
        return this.proCode;
    }

    public void setIsShow( String isShow) { 
        this.isShow = isShow;
    }

    public String getIsShow() { 
        return this.isShow;
    }

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
