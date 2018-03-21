package com.sincinfo.zxks.core.camera;

import java.io.File;
import java.util.List;

import com.sincinfo.zxks.bean.AlterTrans;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.Constants;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

public class InfoConfirmAction extends WebActionSupport {
	private static final long serialVersionUID = 480473907908419530L;
	DbUtil du = new DbUtil();
	private Page page = new Page();

	String idno;
	String preapplyCode;
	List<BaseStudentInfo> students;
	BaseStudentInfo student;
	String cameraPlaceCode;
	String photoPath;
	String year;
	String month;
	String printtype = "";
	String canConfirm;
	List<AlterTrans> alterLists;
	AlterTrans alterList;
	String alterListPlace;
	String alterListTime;

	public String getCanConfirm() {
		return canConfirm;
	}

	public void setCanConfirm(String canConfirm) {
		this.canConfirm = canConfirm;
	}

	public String init() {
		idno = "";
		preapplyCode = "";
		return list();
	}

	private void loadMainData() {
		// demo
		// BaseUser demoUser=new BaseUser();
		// demoUser.setUserName("guanm");
		// demoUser.setCityCode("01");
		// session.put(Constants.ZK_OPERATOR, demoUser);

		Object optUserObj = session.get(Constants.ZK_OPERATOR);
		BaseUser optUser = (BaseUser) optUserObj;
		cameraPlaceCode = optUser.getCameraPlaceCode();
	}
	
	/**
	 * 查询当前考试编号
	 * 
	 * @return
	 */
	public String getCurrExam() {
		String sql = "select e.examination_code from base_examination e where e.nonce = '1'";
		String currExaminationCode = du.getFirstCol(sql);
		currExaminationCode = currExaminationCode == null ? ""
				: currExaminationCode;
		return currExaminationCode;
	}

	public String list() {
		loadMainData();

		StringBuilder url = new StringBuilder();
		url.append("/ZK_CORE/manager/camera/infoconfirm_list.do");
		url.append("?idno=");
		url.append(StringTool.trim(idno));
		url.append("&preapplyCode=");
		url.append(StringTool.trim(preapplyCode));

		page.setPath(url.toString());

		String condition = null;
		if (idno != null && ("".equals(idno) == false)) {
			condition = String.format(" t.STUD_IDNUM = '%1$s'", StringTool.trim(idno));
		} else if (preapplyCode != null && ("".equals(preapplyCode) == false)) {
			condition = String.format(" t.PREAPPLY_CODE = '%1$s'", StringTool.trim(preapplyCode));
		} else {
		    condition = " 1=1 ";
		}
		
		String sql = null;
		if ("16".equals(this.getCOperUser().getUserRole())) {
		    sql = String
                .format(
                        "select t.*,(select g.name from sys_code_gender g where g.code=t.stud_gender) gender_name,(select i.name from sys_code_idno_type i where i.code=t.stud_gender) id_type_name,(select u.stipend_unit_name from base_stipend_unit u where u.stipend_unit_code = t.stipend_unit_code) stipend_unit_name from base_student_info t where %1$s and t.camera_place_code='%2$s' and examination_code='%3$s'",
                        condition, this.getCOperUser().getCameraPlaceCode(), getCurrExam());
		} else {
		    sql = String
                    .format(
                            "select t.*,(select g.name from sys_code_gender g where g.code=t.stud_gender) gender_name,(select i.name from sys_code_idno_type i where i.code=t.stud_gender) id_type_name,(select u.stipend_unit_name from base_stipend_unit u where u.stipend_unit_code = t.stipend_unit_code) stipend_unit_name from base_student_info t where %1$s and t.city_code='%2$s' and examination_code='%3$s'",
                            condition, this.getCOperUser().getCityCode(), getCurrExam());
		}
		String sqlPage = page.setPagecount(du.getNum(String.format(
				"select count(*) from (%1$s)", sql)), sql);
		students = du.getObjList(sqlPage, BaseStudentInfo.class);

		canConfirm = du
				.getFirstCol("select count(*) from base_examination t where (to_char(sysdate,'yyyy-MM-dd') between to_char(t.pre_apply_start,'yyyy-MM-dd') and to_char(t.gather_end,'yyyy-MM-dd')) and t.nonce='1'");
		return "list";
	}

	/**
	 * 判断是否允许采集照片，至少在一个考试的正式报名时间段内
	 * 
	 * @return boolean
	 */
	public boolean canGather() {
		String sql = "select count(*) from base_examination e where e.pre_apply_start <= %1$s and e.gather_end >= %1$s";
		sql = String.format(sql, "to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd')");
		return du.getNum(sql) >= 1;
	}

	public String confirm() {
		loadMainData();
		students = du
				.getObjList(
						String
								.format(
										"select t.* from v_student_info t where t.preapply_code='%1$s' and t.STUD_PHOTO_FILE_1 is not null",
										preapplyCode, cameraPlaceCode),
						BaseStudentInfo.class);
		if (canGather()==false) {
			this.GoBack("信息确认期限已过，无法确认！");
			return null;
		}
		if (students.size() == 0) {
			this.GoBack("该生照片未采集，无法确认！请先采集照片。");
			return null;
		}

		String sql = String
				.format("call p_info_confirm( '%1$s')", preapplyCode);
		System.out.println("sql=="+sql);
		if (du.saveOrUpdate(sql) >= 0) {
			// 记录日志
			OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "摄像点，信息确认-确认");
			optLog.setLogOptSql(sql);
			OperateLogTool.saveOptLog(optLog);
			
			// this.PostJs("alert('确认成功！');document.location='infoconfirm_init.do';");
			// 查询已确认的考生，取得应当使用的新照片文件名
			BaseStudentInfo bsi = du
					.getObject(
							String
									.format(
											"select t.* from v_student_info t where t.preapply_code='%1$s' and t.STUD_PHOTO_FILE_1 is not null",
											preapplyCode, cameraPlaceCode),
							BaseStudentInfo.class);
			String oldFileName = bsi.getStudPhotoFile1();
			String newFileName = String.format("%1$s.jpg", bsi
					.getStudExamCode());
			String[] paths = du.getPaths();
			String subPath = du.getConfig("21");
			String fileDir = paths[0] + subPath;
			File oldFile = new File(fileDir
					+ System.getProperty("file.separator") + oldFileName);
			File newFile = new File(fileDir
					+ System.getProperty("file.separator") + newFileName);
			if (oldFile.exists()) {
				boolean renameFlag = oldFile.renameTo(newFile); 
				if ( renameFlag) {
					// 更新photo1在数据库的值
					if ( du
							.saveOrUpdate(String
									.format(
											"update base_student_info i set i.stud_photo_file_1='%2$s' where i.stud_exam_code='%1$s'",
											bsi.getStudExamCode(), newFileName)) == 1) {
					} else {
						this.Alert("信息确认成功！文件重命名失败！");
					}
				} else {
					this.Alert("信息确认成功！文件重命名失败！");
				}
			}
		} else
			this.GoBack("确认失败！");

		printtype = "1";
		return print();
	}

	public String deconfirm() {
		loadMainData();
		String sql = String.format("call p_info_deconfirm( '%1$s')",
				preapplyCode);
		if (canGather()==false) {
			this.GoBack("信息确认期限已过，无法撤销确认！");
			return null;
		}

		if (du.saveOrUpdate(sql) >= 0){
			// 记录日志
			OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "摄像点，信息确认-撤销确认");
			optLog.setLogOptSql(sql);
			OperateLogTool.saveOptLog(optLog);

			this
					.PostJs("alert('撤销确认成功！');document.location='infoconfirm_init.do';");
		}
		else
			this.GoBack("撤销确认失败！");

		return print();
	}

	public String print() {
		loadMainData();
		photoPath = du.getPaths()[1] + "\\" + du.getConfig("21") + "\\";
		students = du
				.getObjList(
						String.format("select t.* from v_student_info t where t.preapply_code='%1$s' and t.city_code='%2$s'",
										preapplyCode, this.getCOperUser().getCityCode()),
						BaseStudentInfo.class);
		if (students.size() > 0)
			student = students.get(0);
		else
			this.GoBack("将要打印的考生不存在！");
		String[] ss = du
				.getRsArray(
						"select t.examination_year,t.examination_month from base_examination t where t.nonce='1'",
						2);
		if (ss != null) {
			year = ss[0];
			month = ss[1];
		}
//		alterLists=du
//				.getObjList(
//					"select a.examination_code as ExaminationCode,a.altertrans_place as AlteransPlace,a.altertrans_time as AlteransTime from sys_altertrans a,base_examination t where t.examination_code=a.examination_code and t.nonce='1'",
//						AlterTrans.class);
//		alterList=alterLists.get(0);
//		alterListPlace=alterList.getAlteransPlace();
		return "print";
	}

	// public String modify(){
	// loadMainData();
	// String areaCode=place.getExamAreaCode().split("-")[1];
	// String cityCode=place.getExamAreaCode().split("-")[0];
	// String sql=String.format("update base_camera_place set
	// exam_area_code='%2$s',camera_place_name='%3$s',camera_place_address='%5$s',camera_place_link_man='%6$s',camera_place_link_telephon='%7$s',by_bus='%8$s',is_use=%9$s
	// where camera_PLACE_CODE='%1$s'",
	// place.getCameraPlaceCode(),
	// areaCode,
	// place.getCameraPlaceName(),
	// cityCode,
	// place.getCameraPlaceAddress(),
	// place.getCameraPlaceLinkMan(),
	// place.getCameraPlaceLinkTelephon(),
	// place.getByBus(),
	// place.getIsUse());
	// if(du.saveOrUpdate(sql)>0)
	// this.PostJs("alert('保存成功！');document.location='cpm_init.do';");
	// else
	// this.GoBack("保存失败！");
	// return null;
	// }

	public String getCameraPlaceCode() {
		return cameraPlaceCode;
	}

	public void setCameraPlaceCode(String cameraPlaceCode) {
		this.cameraPlaceCode = cameraPlaceCode;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getPreapplyCode() {
		return preapplyCode;
	}

	public void setPreapplyCode(String preapplyCode) {
		this.preapplyCode = preapplyCode;
	}

	public List<BaseStudentInfo> getStudents() {
		return students;
	}

	public void setStudents(List<BaseStudentInfo> students) {
		this.students = students;
	}

	public BaseStudentInfo getStudent() {
		return student;
	}

	public void setStudent(BaseStudentInfo student) {
		this.student = student;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPrinttype() {
		return printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}

	public AlterTrans getAlterList() {
		return alterList;
	}

	public void setAlterList(AlterTrans alterList) {
		this.alterList = alterList;
	}

	public String getAlterListPlace() {
		return alterListPlace;
	}

	public void setAlterListPlace(String alterListPlace) {
		this.alterListPlace = alterListPlace;
	}

	public String getAlterListTime() {
		return alterListTime;
	}

	public void setAlterListTime(String alterListTime) {
		this.alterListTime = alterListTime;
	}

	public List<AlterTrans> getAlterLists() {
		return alterLists;
	}

	public void setAlterLists(List<AlterTrans> alterLists) {
		this.alterLists = alterLists;
	}
	
	

	
}
