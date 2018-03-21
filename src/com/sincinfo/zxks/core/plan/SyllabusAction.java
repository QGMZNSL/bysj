/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.sys.LoginAction.java<br>
 * @Description: 报考层次管理<br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-26 15:26
 * @version V1.0   
 */
package com.sincinfo.zxks.core.plan;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.bean.Textbook;
import com.sincinfo.zxks.bean.SingleEntity;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.core.plan.examarrange.ExcelUtil;
import com.sincinfo.zxks.zxksdbs.SyllabusDbService;
import com.sincinfo.zxks.zxksdbs.TextbookDbService;

/**
 * @ClassName: LevelAction
 * @Description: 课程设置 <br>
 * @author yuansh
 * @date 2012-01-26 15:45<br>
 */
public class SyllabusAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();

	// 课程
	private Syllabus syllabus;

	// 用来传递查询结果
	private List<Syllabus> syllabusList;

	// 用来传递教材表
	private List<Textbook> textbookList;

	// 教材
	private Textbook textBook;

	// 出版时间
	ArrayList<SingleEntity> publistTimes;
	// 出版社
	ArrayList<SingleEntity> publists;

	public Textbook getTextBook() {
		return textBook;
	}

	public void setTextBook(Textbook textBook) {
		this.textBook = textBook;
	}

	/**
	 * 初始化
	 */
	private void init() {
		if (syllabus == null && textBook == null) {
			syllabus = new Syllabus();
			textBook = new Textbook();
			// department.setDepartmentGrade(getUser().getUserRole());
		}
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/syllabus_Show.do",
				request.getContextPath()));
		// url.append(String.format("?department.departmentGrade=%1$s",
		// department.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化选择教材分页查询地址
	 */
	private void initPage1Url() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/syllabus_TBooksetPre.do",
				request.getContextPath()));
		// url.append(String.format("?department.departmentGrade=%1$s",
		// department.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {

	}

	/**
	 * 初始化教材列表中的时间与出版社
	 */
	private void initSelect1() {
		SyllabusDbService db = new SyllabusDbService();
		this.publistTimes = db.getPublishTimeList();
		this.publists = db.getPublishList();
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSctForOpert() {

	}

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public String Show() {
		init();
		SyllabusDbService db = new SyllabusDbService();
		this.syllabusList = db.qry(null, page);
		return "Show";
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	public String AddPre() {
		initSctForOpert();
		return "Add";
	}

	/**
	 * 保存添加
	 */
	public void Add() {
		SyllabusDbService db = new SyllabusDbService();
		boolean addFlag = db.save(syllabus, 0);
		if (addFlag) {
			this.PostJs(String
					.format("alert('添加成功！');location.href='%1$s/plan/syllabus_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('添加失败！');location.href='%1$s/plan/syllabus_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String EditPre() {
		initSctForOpert();
		SyllabusDbService db = new SyllabusDbService();
		this.syllabus = db.qry(syllabus.getSyllabusCode());
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		SyllabusDbService db = new SyllabusDbService();
		boolean editFlag = db.save(syllabus, 1);
		if (editFlag) {
			this.PostJs(String
					.format("alert('修改成功！');location.href='%1$s/plan/syllabus_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('修改失败！');location.href='%1$s/plan/syllabus_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 删除，实际为禁用
	 */
	public void isUseDel() {
		SyllabusDbService db = new SyllabusDbService();
		boolean delFlag = db.isUseDel(syllabus.getSyllabusCode());
		if (delFlag) {
			this.PostJs(String
					.format("alert('删除成功！');location.href='%1$s/plan/syllabus_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('删除失败！');location.href='%1$s/plan/syllabus_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 删除
	 */
	public void Del() {
		SyllabusDbService db = new SyllabusDbService();
		boolean delFlag = db.del(syllabus.getSyllabusCode());
		if (delFlag) {
			this.PostJs(String
					.format("alert('删除成功！');location.href='%1$s/plan/syllabus_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('添加失败！');location.href='%1$s/plan/syllabus_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 进入教材设置页面
	 * 
	 * @return
	 */
	public String TBooksetPre() {
		initPage1Url();
		// 显示教材列表
		// 初始化查询列表
		initSelect1();
		TextbookDbService db1 = new TextbookDbService();
		this.textbookList = db1.qry(null, page);
		return "TBookSct";
	}

	/**
	 * 选择教材查询
	 * 
	 * @return
	 */
	public String qryTBook() {
		// 设置分页地址
		setUrl();
		TextbookDbService db = new TextbookDbService();
		this.textbookList = db.qry(textBook, page);
		// 初始化查询列表
		initSelect1();
		return "TBookSct";
	}
	
	/**
	 * 进入教材设置页面(用于专业课程设置)
	 * 
	 * @return
	 */
	public String getTextBooks() {
		initSelect1();
		TextbookDbService dbs = new TextbookDbService();
		page.setPath(String.format("%1$s/plan/syllabus_getTextBooks.do",
				request.getContextPath()));
		this.textbookList = dbs.qry(textBook, page);
		return "textBook";
	}

	/**
	 * 设置查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("location.href='%1$s/plan/syllabus_Show.do';",
				request.getContextPath()));
		// url.append(String.format("?department.departmentCode=%1$s",
		// department.getDepartmentCode()));
		page.setPath(url.toString());
	}

	/**
	 * 获取信息列表
	 * 
	 * @return
	 */
	public String qry() {
		// 设置分页地址
		setUrl();
		SyllabusDbService db = new SyllabusDbService();
		this.syllabusList = db.qry(syllabus, page);
		return "Show";
	}

	/**
	 * 导出excel
	 * 
	 * @return
	 */
	public String doExcel() {
		String msg = null;
		PrintWriter out = null;

		// 获取点击的层次信息
		SyllabusDbService service = new SyllabusDbService();

		// 查询对应层次下的所有职位列表
		List<Syllabus> tmpSyllabusList = service.qryForExcel(syllabus);

		if (tmpSyllabusList == null || tmpSyllabusList.size() == 0) {
			msg = "noData";
		} else {
			// 生成文件
			String excelFileName = "bST" + service.newFilename() + ".xls";
			String[] paths = service.getPaths();
			String subPath = service.getConfig("72");
			String excelPhyPath = paths[0] + subPath
					+ System.getProperty("file.separator");
			String excelNetPath = paths[1] + subPath + "/" + excelFileName;
			// 合成真实的文件名
			String file = "";
			if (excelPhyPath.endsWith(System.getProperty("file.separator"))) {
				file = excelPhyPath + excelFileName;
			} else {
				file = excelPhyPath + System.getProperty("file.separator")
						+ excelFileName;
			}

			File f = new File(file);
			if (!f.exists()) {
				f.getParentFile().mkdirs();
			}

			BufferedOutputStream bos = null;
			WritableWorkbook book = null;
			try {
				bos = new BufferedOutputStream(new FileOutputStream(f));
				book = Workbook.createWorkbook(bos);
				WritableSheet sheet = null;
				ExcelUtil excelUtil = new ExcelUtil();
				int pagesize = 1000;
				int rownum = 0;
				Label label = null;
				WritableCellFormat haligncenter = excelUtil.getTitleformat();
				WritableCellFormat alignleft = excelUtil
						.getBodyformatAlignLeft();
				WritableCellFormat alignleftCenter = excelUtil
						.getBodyformatAlignLeftCenter();
				WritableCellFormat aligncenter = excelUtil
						.getBodyformatAlignCenter();
				WritableCellFormat demoleft = excelUtil.getDemoformat();
				String excelTile = "课程设置报表";
				Syllabus tmpSyl = null;
				for (int i = 0; i < tmpSyllabusList.size(); i++) {
					if (i % pagesize == 0) {
						// 新建sheet
						sheet = book.createSheet("课程设置报表" + i / pagesize, i
								/ pagesize);
						label = new Label(0, 0, excelTile, haligncenter);
						sheet.addCell(label);
						sheet.mergeCells(0, 0, 7, 0);

						label = new Label(0, 1, "序号", aligncenter);
						sheet.addCell(label);
						label = new Label(1, 1, "课程代码", aligncenter);
						sheet.addCell(label);
						label = new Label(2, 1, "课程名称", aligncenter);
						sheet.addCell(label);
						label = new Label(3, 1, "课程英文名", aligncenter);
						sheet.addCell(label);
						label = new Label(4, 1, "课程分类", aligncenter);
						sheet.addCell(label);
						label = new Label(5, 1, "学分", aligncenter);
						sheet.addCell(label);
						label = new Label(6, 1, "指定教材", aligncenter);
						sheet.addCell(label);
						label = new Label(7, 1, "是否全国统考", aligncenter);
						sheet.addCell(label);

						rownum = 2;
					}

					tmpSyl = tmpSyllabusList.get(i);
					label = new Label(0, rownum, String.valueOf(i + 1),
							alignleftCenter); // 序号
					sheet.addCell(label);
					label = new Label(1, rownum, tmpSyl.getSyllabusCode(),
							alignleftCenter); // 课程代码
					sheet.addCell(label);
					label = new Label(2, rownum, tmpSyl.getSyllabusName(),
							alignleftCenter); // 课程名称
					sheet.addCell(label);
					label = new Label(3, rownum,
							tmpSyl.getSyllabusenglishname(), alignleftCenter); // 课程英文名
					sheet.addCell(label);
					if ("0".equals(tmpSyl.getSyllabusType())) { // 专业类型
						label = new Label(4, rownum, "普通课程", alignleftCenter);
					} else if ("1".equals(tmpSyl.getSyllabusType())) {
						label = new Label(4, rownum, "实践课程", alignleftCenter);
					} else if ("2".equals(tmpSyl.getSyllabusType())) {
						label = new Label(4, rownum, "论文答辩", alignleftCenter);
					} else {
						label = new Label(4, rownum, " ", alignleftCenter);
					}
					sheet.addCell(label);
					label = new Label(5, rownum, tmpSyl.getSyllabuscredit(),
							alignleftCenter); // 学分
					sheet.addCell(label);
					label = new Label(6, rownum, tmpSyl.getTextbookName(),
							alignleftCenter); // 学分
					sheet.addCell(label);
					label = new Label(7, rownum,
							"1".equals(tmpSyl.getIsgb()) ? "是" : "否",
							alignleftCenter); // 是否全国统考
					sheet.addCell(label);

					rownum++;
				}
				book.write();

				msg = "<a href='" + excelNetPath + "' target='_blank'>下载</a>";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "error";
			} finally {
				if (book != null) {
					try {
						book.close();
					} catch (WriteException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		try {
			out = this.getResponse().getWriter();
			out.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

		return null;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Syllabus getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

	public List<Syllabus> getSyllabusList() {
		return syllabusList;
	}

	public void setSyllabusList(List<Syllabus> syllabusList) {
		this.syllabusList = syllabusList;
	}

	public List<Textbook> getTextbookList() {
		return textbookList;
	}

	public void setTextbookList(List<Textbook> textbookList) {
		this.textbookList = textbookList;
	}

	public ArrayList<SingleEntity> getPublistTimes() {
		return publistTimes;
	}

	public void setPublistTimes(ArrayList<SingleEntity> publistTimes) {
		this.publistTimes = publistTimes;
	}

	public ArrayList<SingleEntity> getPublists() {
		return publists;
	}

	public void setPublists(ArrayList<SingleEntity> publists) {
		this.publists = publists;
	}

}
