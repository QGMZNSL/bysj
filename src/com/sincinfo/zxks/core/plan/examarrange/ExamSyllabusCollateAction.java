package com.sincinfo.zxks.core.plan.examarrange;

import java.util.List;

import org.apache.log4j.helpers.DateLayout;

import com.sincinfo.zxks.bean.SingleEntity;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.zxksdbs.ExamSyllabusCollateService;

/**
 * 开考专业课程校对
 * 
 * @author duanwj 2013-4-16
 * 
 */
public class ExamSyllabusCollateAction extends WebActionSupport {

	private static final long serialVersionUID = 1L;

	private ExamSyllabusCollateService escs = new ExamSyllabusCollateService();

	// 控件，考试年月
	private List<SingleEntity> ymList;

	// 考试编号
	private String examinationCode;

	// 科目代码
	private String syllabusCode;

	// 科目名称
	private String syllabusName;

	// 数据集
	private List<String[]> dataList;
	private int dataListSize;
	private String ccKey;

	/**
	 * 查询重复科目
	 * 
	 * @return
	 */
	public String getSyllabus() {
		this.examinationCode = this.examinationCode == null ? ""
				: this.examinationCode.trim();
		this.syllabusCode = this.syllabusCode == null ? "" : this.syllabusCode
				.trim();
		this.syllabusName = this.syllabusName == null ? "" : this.syllabusName
				.trim();
		this.ccKey = this.ccKey == null ? "" : this.ccKey
				.trim();
		this.ymList = escs.qryExamYmList();
		if (!StringTool.isEmpty(examinationCode)) {
			dataList = escs.getSyllabus(examinationCode, syllabusCode,
					syllabusName);
		} else {
			dataList = null;
		}
		if (StringTool.isEmpty(examinationCode)) {
			examinationCode = escs.getCurrExam();
		}
		if(ccKey.equals("0")){
			return "syllabus";
		}else if(ccKey.equals("1")){
			dataListSize = dataList.size();
			return "syllabustime";
		}else{
			return "syllabus";
		}
	}

	public List<SingleEntity> getYmList() {
		return ymList;
	}

	public void setYmList(List<SingleEntity> ymList) {
		this.ymList = ymList;
	}

	public String getExaminationCode() {
		return examinationCode;
	}

	public void setExaminationCode(String examinationCode) {
		this.examinationCode = examinationCode;
	}

	public String getSyllabusCode() {
		return syllabusCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	public String getSyllabusName() {
		return syllabusName;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}

	public List<String[]> getDataList() {
		return dataList;
	}

	public void setDataList(List<String[]> dataList) {
		this.dataList = dataList;
	}
	public int getDataListSize() {
		return dataListSize;
	}
	public void setDataListSize(int dataListSize) {
		this.dataListSize = dataListSize;
	}
	public String getCcKey() {
		return ccKey;
	}
	public void setCcKey(String ccKey) {
		this.ccKey = ccKey;
	}
	
	

}
