package com.sincinfo.zxks.bean;


public class LogLogin {

    private String logLoginId;
	//日志编号
    private String userName;
    //登陆名
    private String loginIp;
    //登陆IP
    private String loginTime;
    //登陆时间
    private String loginPassword;
    //错误密码
    public void setLogLoginId( String logLoginId) { 
        this.logLoginId = logLoginId;
    }

    public String getLogLoginId() { 
        return this.logLoginId;
    }

    public void setUserName( String userName) { 
        this.userName = userName;
    }

    public String getUserName() { 
        return this.userName;
    }

    public void setLoginIp( String loginIp) { 
        this.loginIp = loginIp;
    }

    public String getLoginIp() { 
        return this.loginIp;
    }

    public void setLoginTime( String loginTime) { 
        this.loginTime = loginTime;
    }

    public String getLoginTime() { 
        return this.loginTime;
    }

    public void setLoginPassword( String loginPassword) { 
        this.loginPassword = loginPassword;
    }

    public String getLoginPassword() { 
        return this.loginPassword;
    }

}
