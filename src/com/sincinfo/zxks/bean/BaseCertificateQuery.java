package com.sincinfo.zxks.bean;


public class BaseCertificateQuery {

    private String certificatQueryId;

    private String certificatQueryName;

    private String certificatType;

    private String certificatQueryUrl;

    private String isUse;

    private String isModify;
    
    private String certificatTypeName;//证书类型名称
    public void setCertificatQueryId( String certificatQueryId) { 
        this.certificatQueryId = certificatQueryId;
    }

    public String getCertificatQueryId() { 
        return this.certificatQueryId;
    }

    public void setCertificatQueryName( String certificatQueryName) { 
        this.certificatQueryName = certificatQueryName;
    }

    public String getCertificatQueryName() { 
        return this.certificatQueryName;
    }

    public void setCertificatType( String certificatType) { 
        this.certificatType = certificatType;
    }

    public String getCertificatType() { 
        return this.certificatType;
    }

    public void setCertificatQueryUrl( String certificatQueryUrl) { 
        this.certificatQueryUrl = certificatQueryUrl;
    }

    public String getCertificatQueryUrl() { 
        return this.certificatQueryUrl;
    }

    public void setIsUse( String isUse) { 
        this.isUse = isUse;
    }

    public String getIsUse() { 
        return this.isUse;
    }

    public void setIsModify( String isModify) { 
        this.isModify = isModify;
    }

    public String getIsModify() { 
        return this.isModify;
    }

	public String getCertificatTypeName() {
		return certificatTypeName;
	}

	public void setCertificatTypeName(String certificatTypeName) {
		this.certificatTypeName = certificatTypeName;
	}

}
