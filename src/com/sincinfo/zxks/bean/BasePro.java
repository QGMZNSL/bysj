package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

public class BasePro
{
  private String proCode;
  private String proName;
  private String proPartcode;
  private String proTypecode;
  private String isGb;
  private String isUse;
  private String allowGraduate;
  private String levelCode;
  private String syllabusSet;
  private String proTypeName;
  private String proPartName;
  private String levelName;
  private String academyName;
  private String isAllowNewStu;
  @Validate
  public String getProCode()
  {
    return this.proCode;
  }

  public void setProCode(String proCode) {
    this.proCode = proCode;
  }
  @Validate
  public String getProName() {
    return this.proName;
  }

  public void setProName(String proName) {
    this.proName = proName;
  }
  @Validate
  public String getProPartcode() {
    return this.proPartcode;
  }

  public void setProPartcode(String proPartcode) {
    this.proPartcode = proPartcode;
  }
  @Validate
  public String getProTypecode() {
    return this.proTypecode;
  }

  public void setProTypecode(String proTypecode) {
    this.proTypecode = proTypecode;
  }
  @Validate
  public String getIsGb() {
    return this.isGb;
  }

  public void setIsGb(String isGb) {
    this.isGb = isGb;
  }
  @Validate
  public String getIsUse() {
    return this.isUse;
  }

  public void setIsUse(String isUse) {
    this.isUse = isUse;
  }
  @Validate
  public String getLevelCode() {
    return this.levelCode;
  }

  public void setLevelCode(String levelCode) {
    this.levelCode = levelCode;
  }
  @Validate
  public String getProTypeName() { return this.proTypeName; }

  public void setProTypeName(String proTypeName)
  {
    this.proTypeName = proTypeName;
  }
  @Validate
  public String getProPartName() { return this.proPartName; }

  public void setProPartName(String proPartName)
  {
    this.proPartName = proPartName;
  }
  @Validate
  public String getLevelName() { return this.levelName; }

  public void setLevelName(String levelName)
  {
    this.levelName = levelName;
  }

  public String getSyllabusSet() {
    return this.syllabusSet;
  }

  public void setSyllabusSet(String syllabusSet) {
    this.syllabusSet = syllabusSet;
  }

  public String getAcademyName() {
    return this.academyName;
  }

  public void setAcademyName(String academyName) {
    this.academyName = academyName;
  }

  public String getAllowGraduate() {
    return this.allowGraduate;
  }

  public void setAllowGraduate(String allowGraduate) {
    this.allowGraduate = allowGraduate;
  }

public String getIsAllowNewStu() {
	return isAllowNewStu;
}

public void setIsAllowNewStu(String isAllowNewStu) {
	this.isAllowNewStu = isAllowNewStu;
}
}