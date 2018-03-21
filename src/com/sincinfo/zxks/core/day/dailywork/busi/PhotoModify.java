/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.dailywork.busi.PhotoModify.java<br>
 * @Description: 修改考生照片相关数据 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:13:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.dailywork.busi;

import com.sincinfo.zxks.bean.BaseStudentInfo;

/**
 * @ClassName: PhotoModify
 * @Description: 修改考生照片相关数据 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:13:20 AM<br>
 * 
 */
public class PhotoModify {
	public static String STUD_MGR_PHOTO_MODIFY = "stud_mgr_photo_modify";

	// 考生信息
	private BaseStudentInfo student;
	// 变更类型
	private String changeTypeCode;
	// 修改原因
	private String modifyReason;
	// 证明材料
	private String proveMaterial;

	/* 考生修改信息来源 add by litian 2012-08-20 */
	private int fillinBy;

	/**
	 * 构造
	 * 
	 * @param student
	 *            考生对象
	 * @param changeTypeCode
	 *            变更类型代码
	 * @param modifyReason
	 *            修改原因
	 * @param proveMaterial
	 *            证明材料
	 */
	public PhotoModify(BaseStudentInfo student, String changeTypeCode,
			String modifyReason, String proveMaterial, int fillinBy) {
		this.student = student;
		this.changeTypeCode = changeTypeCode;
		this.modifyReason = modifyReason;
		this.proveMaterial = proveMaterial;
		this.fillinBy = fillinBy;
	}

	public BaseStudentInfo getStudent() {
		return student;
	}

	public void setStudent(BaseStudentInfo student) {
		this.student = student;
	}

	public String getChangeTypeCode() {
		return changeTypeCode;
	}

	public void setChangeTypeCode(String changeTypeCode) {
		this.changeTypeCode = changeTypeCode;
	}

	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	public String getProveMaterial() {
		return proveMaterial;
	}

	public void setProveMaterial(String proveMaterial) {
		this.proveMaterial = proveMaterial;
	}

	public int getFillinBy() {
		return fillinBy;
	}

	public void setFillinBy(int fillinBy) {
		this.fillinBy = fillinBy;
	}

}
