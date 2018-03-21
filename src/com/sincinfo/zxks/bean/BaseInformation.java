package com.sincinfo.zxks.bean;

import java.util.Date;


public class BaseInformation {

    private String informationId;//信息编号

    private String informationTitle;//标题

    private String classId;//信息分类编号

    private String informationMainContent;//内容

    private String informationFile;//附件

    private String informationAddUser;//添加人

    private Date informationAddTime;//添加时间

    private String informationUnauditStatus;//审核状态   默认0： 0未发布 1已发布

    private String informationUnauditUser;//审核人

    private String informationUnauditTime;//审核时间

    private String informationUnauditReason;//审核不通过原因
    
    /*扩展*/
    private String className;//栏目名称

    public void setInformationId( String informationId) { 
        this.informationId = informationId;
    }

    public String getInformationId() { 
        return this.informationId;
    }

    public void setInformationTitle( String informationTitle) { 
        this.informationTitle = informationTitle;
    }

    public String getInformationTitle() { 
        return this.informationTitle;
    }

    public void setClassId( String classId) { 
        this.classId = classId;
    }

    public String getClassId() { 
        return this.classId;
    }

    public void setInformationMainContent( String informationMainContent) { 
        this.informationMainContent = informationMainContent;
    }

    public String getInformationMainContent() { 
        return this.informationMainContent;
    }

    public void setInformationFile( String informationFile) { 
        this.informationFile = informationFile;
    }

    public String getInformationFile() { 
        return this.informationFile;
    }

    public void setInformationAddUser( String informationAddUser) { 
        this.informationAddUser = informationAddUser;
    }

    public String getInformationAddUser() { 
        return this.informationAddUser;
    }

    public Date getInformationAddTime() {
		return informationAddTime;
	}

	public void setInformationAddTime(Date informationAddTime) {
		this.informationAddTime = informationAddTime;
	}

	public void setInformationUnauditStatus( String informationUnauditStatus) { 
        this.informationUnauditStatus = informationUnauditStatus;
    }

    public String getInformationUnauditStatus() { 
        return this.informationUnauditStatus;
    }

    public void setInformationUnauditUser( String informationUnauditUser) { 
        this.informationUnauditUser = informationUnauditUser;
    }

    public String getInformationUnauditUser() { 
        return this.informationUnauditUser;
    }

    public void setInformationUnauditTime( String informationUnauditTime) { 
        this.informationUnauditTime = informationUnauditTime;
    }

    public String getInformationUnauditTime() { 
        return this.informationUnauditTime;
    }

    public void setInformationUnauditReason( String informationUnauditReason) { 
        this.informationUnauditReason = informationUnauditReason;
    }

    public String getInformationUnauditReason() { 
        return this.informationUnauditReason;
    }

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
