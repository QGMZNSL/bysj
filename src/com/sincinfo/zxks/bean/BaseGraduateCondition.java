package com.sincinfo.zxks.bean;


/**
 * 专业毕业条件
 * 
 * @author JIANGHE
 * 
 */
public class BaseGraduateCondition {
    // 专业代码
    private String proCode;
    // 专业毕业学分
    private String graduateConditionCredit;
    // 专业毕业说明
    private String graduateConditionDescribe;
    // 专业类型名称
    private String proName;
    
    // 扩展属性
    private String proTypeName;

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getGraduateConditionCredit() {
        return graduateConditionCredit;
    }

    public void setGraduateConditionCredit(String graduateConditionCredit) {
        this.graduateConditionCredit = graduateConditionCredit;
    }

    public String getGraduateConditionDescribe() {
        return graduateConditionDescribe;
    }

    public void setGraduateConditionDescribe(String graduateConditionDescribe) {
        this.graduateConditionDescribe = graduateConditionDescribe;
    }

    public String getProTypeName() {
        return proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }

}
