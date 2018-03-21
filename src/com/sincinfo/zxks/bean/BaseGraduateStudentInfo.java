package com.sincinfo.zxks.bean;


public class BaseGraduateStudentInfo {

    private String diplomaNum;//毕业证编号

    private String studExamCode;//准考证号

    private String studName;//姓名

    private String studIdnum;//身份证号

    private String studGender;//性别

    private String studBirthday;//出生日期    格式yyyy-mm-dd

    private String studFolk;//民族

    private String studPolitics;//政治面貌

    private String studOccupation;//职业

    private String studCulture;//文化程度

    private String studHousehold;//户籍

    private String region;//市区代码

    private String examRegion;//考区代码

    private String graduateDate;//毕业时间

    private String levelCode;//毕业层次代码

    private String proCode;//毕业证书专业代码

    private String proDirection;//毕业专业方向

    private String diplomaDescribe;//毕业证书描述

    private String academyProCode;//毕业院校
    
    /*扩展*/
    private String levelName;
    private String proName;
    private String academyProName;
    private String studPhotoFile1;//毕业证照片
    public void setDiplomaNum( String diplomaNum) { 
        this.diplomaNum = diplomaNum;
    }

    public String getDiplomaNum() { 
        return this.diplomaNum;
    }

    public void setStudExamCode( String studExamCode) { 
        this.studExamCode = studExamCode;
    }

    public String getStudExamCode() { 
        return this.studExamCode;
    }

    public void setStudName( String studName) { 
        this.studName = studName;
    }

    public String getStudName() { 
        return this.studName;
    }

    public void setStudIdnum( String studIdnum) { 
        this.studIdnum = studIdnum;
    }

    public String getStudIdnum() { 
        return this.studIdnum;
    }

    public void setStudGender( String studGender) { 
        this.studGender = studGender;
    }

    public String getStudGender() { 
        return this.studGender;
    }

    public void setStudBirthday( String studBirthday) { 
        this.studBirthday = studBirthday;
    }

    public String getStudBirthday() { 
        return this.studBirthday;
    }

    public void setStudFolk( String studFolk) { 
        this.studFolk = studFolk;
    }

    public String getStudFolk() { 
        return this.studFolk;
    }

    public void setStudPolitics( String studPolitics) { 
        this.studPolitics = studPolitics;
    }

    public String getStudPolitics() { 
        return this.studPolitics;
    }

    public void setStudOccupation( String studOccupation) { 
        this.studOccupation = studOccupation;
    }

    public String getStudOccupation() { 
        return this.studOccupation;
    }

    public void setStudCulture( String studCulture) { 
        this.studCulture = studCulture;
    }

    public String getStudCulture() { 
        return this.studCulture;
    }

    public void setStudHousehold( String studHousehold) { 
        this.studHousehold = studHousehold;
    }

    public String getStudHousehold() { 
        return this.studHousehold;
    }

    public void setRegion( String region) { 
        this.region = region;
    }

    public String getRegion() { 
        return this.region;
    }

    public void setExamRegion( String examRegion) { 
        this.examRegion = examRegion;
    }

    public String getExamRegion() { 
        return this.examRegion;
    }

    public void setGraduateDate( String graduateDate) { 
        this.graduateDate = graduateDate;
    }

    public String getGraduateDate() { 
        return this.graduateDate;
    }

    public void setLevelCode( String levelCode) { 
        this.levelCode = levelCode;
    }

    public String getLevelCode() { 
        return this.levelCode;
    }

    public void setProCode( String proCode) { 
        this.proCode = proCode;
    }

    public String getProCode() { 
        return this.proCode;
    }

    public void setProDirection( String proDirection) { 
        this.proDirection = proDirection;
    }

    public String getProDirection() { 
        return this.proDirection;
    }

    public void setDiplomaDescribe( String diplomaDescribe) { 
        this.diplomaDescribe = diplomaDescribe;
    }

    public String getDiplomaDescribe() { 
        return this.diplomaDescribe;
    }

    public void setAcademyProCode( String academyProCode) { 
        this.academyProCode = academyProCode;
    }

    public String getAcademyProCode() { 
        return this.academyProCode;
    }

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getAcademyProName() {
		return academyProName;
	}

	public void setAcademyProName(String academyProName) {
		this.academyProName = academyProName;
	}

	public String getStudPhotoFile1() {
		return studPhotoFile1;
	}

	public void setStudPhotoFile1(String studPhotoFile1) {
		this.studPhotoFile1 = studPhotoFile1;
	}

}
