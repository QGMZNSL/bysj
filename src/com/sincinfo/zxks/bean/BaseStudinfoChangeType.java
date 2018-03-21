/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.BaseStudinfoChangeType.java<br>
 * @Description: 考生变更记录变更类型 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:13:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

/**
 * @ClassName: BaseStudinfoChangeType
 * @Description: 考生变更记录变更类型 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:13:20 AM<br>
 * 
 */
public class BaseStudinfoChangeType {

	// 考生变更记录变更类型代码
    private String changeTypeCode;

	// 考生变更记录变更类型名称
    private String changeTypeName;

	// 考生变更记录变更类型输出字符串模板
    private String changeStringTemplate;

    public void setChangeTypeCode( String changeTypeCode) { 
        this.changeTypeCode = changeTypeCode;
    }

    public String getChangeTypeCode() { 
        return this.changeTypeCode;
    }

    public void setChangeTypeName( String changeTypeName) { 
        this.changeTypeName = changeTypeName;
    }

    public String getChangeTypeName() { 
        return this.changeTypeName;
    }

    public void setChangeStringTemplate( String changeStringTemplate) { 
        this.changeStringTemplate = changeStringTemplate;
    }

    public String getChangeStringTemplate() { 
        return this.changeStringTemplate;
    }

}
