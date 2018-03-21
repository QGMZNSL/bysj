package com.sincinfo.zxks.bean;

import java.util.List;


public class BaseJoinhandsUnit {

    private String unitCode; // 合作单位编号����λ���

    private String unitName; // 合作单位名称����λ���

    private String cityCode; // 地市编号�������

    private String unitAddress; //合作单位地址ַ

    private String unitLinkman; // 合作单位联系人ϵ��

    private String unitTelephone; //合作单位联系电话ϵ��ʽ

    private String isUse; //状态  0禁用  1 启用����

    private String remarks; // 备注
    
    
    //扩转
    private String cityName;//地市名称
    private List<BaseJoinhandsUnitPro> proList;//合作开考专业集合

    public void setUnitCode( String unitCode) { 
        this.unitCode = unitCode;
    }

    public String getUnitCode() { 
        return this.unitCode;
    }

    public void setUnitName( String unitName) { 
        this.unitName = unitName;
    }

    public String getUnitName() { 
        return this.unitName;
    }

    public void setCityCode( String cityCode) { 
        this.cityCode = cityCode;
    }

    public String getCityCode() { 
        return this.cityCode;
    }

    public void setUnitAddress( String unitAddress) { 
        this.unitAddress = unitAddress;
    }

    public String getUnitAddress() { 
        return this.unitAddress;
    }

    public void setUnitLinkman( String unitLinkman) { 
        this.unitLinkman = unitLinkman;
    }

    public String getUnitLinkman() { 
        return this.unitLinkman;
    }

    public void setUnitTelephone( String unitTelephone) { 
        this.unitTelephone = unitTelephone;
    }

    public String getUnitTelephone() { 
        return this.unitTelephone;
    }

    public void setIsUse( String isUse) { 
        this.isUse = isUse;
    }

    public String getIsUse() { 
        return this.isUse;
    }

    public void setRemarks( String remarks) { 
        this.remarks = remarks;
    }

    public String getRemarks() { 
        return this.remarks;
    }

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<BaseJoinhandsUnitPro> getProList() {
		return proList;
	}

	public void setProList(List<BaseJoinhandsUnitPro> proList) {
		this.proList = proList;
	}

}
