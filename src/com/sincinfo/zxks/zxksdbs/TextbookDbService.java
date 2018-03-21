/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.DayDbService.java<br>
 * @Description: 报考层次管理 <br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-26 15:58
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.Textbook;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 教材设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class TextbookDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public TextbookDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有报考层次列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<Textbook> qry(Textbook textbook, Page page) {
		List<Textbook> textbooks = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.*,t.SYLLABUS_NAME from base_textbook d left join BASE_SYLLABUS t" +
				" on d.SYLLABUS_CODE=t.SYLLABUS_CODE where d.IS_USE='1' ");
		sqlCount.append("select count(*) from base_textbook d left join BASE_SYLLABUS t" +
				" on d.SYLLABUS_CODE=t.SYLLABUS_CODE where d.IS_USE='1' ");
		String s;
		if(!StringTool.isEmpty(textbook)){
			if(!StringTool.isEmpty(textbook.getSyllabusCode())){
				s=textbook.getSyllabusCode().trim().toUpperCase();
				sql.append(String.format(" and d.SYLLABUS_CODE like '%%%1$s%%'",s));
				sqlCount.append(String.format(" and d.SYLLABUS_CODE like '%%%1$s%%'",s));
			}
			if(!StringTool.isEmpty(textbook.getSyllabusName())){
				s=textbook.getSyllabusName().trim().toUpperCase();
				sql.append(String.format(" and t.SYLLABUS_NAME like '%%%1$s%%'",s));
				sqlCount.append(String.format(" and t.SYLLABUS_NAME like '%%%1$s%%'",s));
			}
			if(!StringTool.isEmpty(textbook.getTextbookCode())){
				s=textbook.getTextbookCode().trim().toUpperCase();
				int i=s.indexOf("-");
				if(i>-1){
					sql.append(String.format(" and d.SYLLABUS_CODE like '%%%1$s%%'",s.substring(0,i)));
					sqlCount.append(String.format(" and d.SYLLABUS_CODE like '%%%1$s%%'",s.substring(0,i)));
					sql.append(String.format(" and d.textbook_code like '%%%1$s%%'",s.substring((i+1))));
					sqlCount.append(String.format(" and d.textbook_code like '%%%1$s%%'",s.substring((i+1))));
				}
				else{
					sql.append(String.format(" and (d.SYLLABUS_CODE like '%%%1$s%%'",s));
					sqlCount.append(String.format(" and (d.SYLLABUS_CODE like '%%%1$s%%'",s));
					sql.append(String.format(" or d.textbook_code like '%%%1$s%%')",s));
					sqlCount.append(String.format(" or d.textbook_code like '%%%1$s%%')",s));
				}
			}
			if(!StringTool.isEmpty(textbook.getTextbookName())){
				s=textbook.getTextbookName().trim().toUpperCase();
				sql.append(String.format(" and d.textbook_name like '%%%1$s%%'",s));
				sqlCount.append(String.format(" and d.textbook_name like '%%%1$s%%'",s));
			}
			if(!StringTool.isEmpty(textbook.getPublishTime())){
				s=textbook.getPublishTime().trim().toUpperCase();
				sql.append(String.format(" and d.publish_time like '%%%1$s%%'",s));
				sqlCount.append(String.format(" and d.publish_time like '%%%1$s%%'",s));
			}
			if(!StringTool.isEmpty(textbook.getTextbookPublisher())){
				s=textbook.getTextbookPublisher().trim().toUpperCase();
				sql.append(String.format(" and d.textbook_publisher like '%%%1$s%%'",s));
				sqlCount.append(String.format(" and d.textbook_publisher like '%%%1$s%%'",s));
			}			
		}
		sql.append(" order by d.SYLLABUS_CODE,d.textbook_code");
		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		textbooks = dbUtil.getObjList(sqlPage, Textbook.class);
		return textbooks;
	}

	/**
	 * 根据编号，查询报考层次
	 * 
	 * @param levelCode
	 *        层次编号
	 * @return
	 */
	public Textbook qry(String textbookCode) {
		Textbook textbook = null;
		String sql = String.format("select d.* from base_textbook d where d.textbook_code = '%1$s'",textbookCode);
		textbook = dbUtil.getObject(sql, Textbook.class);
		return textbook;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param textbook
	 *            报考层次
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(Textbook textbook, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			String s="select count(*) from base_textbook b where b.textbook_code=?";
			Long l=dbUtil.getNum(s, textbook.getTextbookCode().trim().toUpperCase());
			if(l==0){
				sql.append("insert into base_textbook");
				sql.append("(textbook_code,textbook_name,textbook_editor,textbook_publisher,publish_time,textbook_price,remarks,IS_USE,UNIFIED_BOOK,SYLLABUS_CODE)");
				sql.append(" values");
				sql.append(String.format("('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s','%10$s')",
						textbook.getTextbookCode().trim().toUpperCase(),textbook.getTextbookName().trim().toUpperCase(),textbook.getTextbookEditor(),textbook.getTextbookPublisher().trim().toUpperCase(),
						textbook.getPublishTime().trim().toUpperCase(),textbook.getTextbookPrice(),textbook.getRemarks(),"1",textbook.getUnifiedBook(),textbook.getSyllabusCode()));
			}
			else{
				sql.append("update base_textbook");
				sql.append(String.format(" set textbook_name = '%1$s',",textbook.getTextbookName().trim().toUpperCase()));
				sql.append(String.format(" textbook_editor = '%1$s',", textbook.getTextbookEditor()));
				sql.append(String.format(" textbook_publisher = '%1$s',", textbook.getTextbookPublisher().trim().toUpperCase()));
			  //  sql.append(String.format(" publish_time = '%1$s',", textbook.getPublishTime()==null?textbook.getPublishTime().substring(0,5):null));
			    sql.append(String.format(" publish_time = '%1$s',", textbook.getPublishTime().trim().toUpperCase()));
				sql.append(String.format(" textbook_price = '%1$s',", textbook.getTextbookPrice()));
				sql.append(String.format(" IS_USE = '%1$s',", "1"));
				sql.append(String.format(" UNIFIED_BOOK = '%1$s',", textbook.getUnifiedBook()));
				sql.append(String.format(" SYLLABUS_CODE = '%1$s',", textbook.getSyllabusCode()));
				sql.append(String.format(" remarks = '%1$s'", textbook.getRemarks()));
				sql.append(String.format(" where textbook_code = '%1$s'", textbook.getTextbookCode().trim().toUpperCase()));
			}
			break;
		case 1:
			sql.append("update base_textbook");
			sql.append(String.format(" set textbook_name = '%1$s',",textbook.getTextbookName().trim().toUpperCase()));
			sql.append(String.format(" textbook_editor = '%1$s',", textbook.getTextbookEditor()));
			sql.append(String.format(" textbook_publisher = '%1$s',", textbook.getTextbookPublisher().trim().toUpperCase()));
		  //  sql.append(String.format(" publish_time = '%1$s',", textbook.getPublishTime()==null?textbook.getPublishTime().substring(0,5):null));
		    sql.append(String.format(" publish_time = '%1$s',", textbook.getPublishTime().trim().toUpperCase()));
			sql.append(String.format(" textbook_price = '%1$s',", textbook.getTextbookPrice()));
			sql.append(String.format(" IS_USE = '%1$s',", "1"));
			sql.append(String.format(" UNIFIED_BOOK = '%1$s',", textbook.getUnifiedBook()));
			sql.append(String.format(" SYLLABUS_CODE = '%1$s',", textbook.getSyllabusCode()));
			sql.append(String.format(" remarks = '%1$s'", textbook.getRemarks()));
			sql.append(String.format(" where textbook_code = '%1$s'", textbook.getTextbookCode().trim().toUpperCase()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}

	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * 
	 * @param levelCode
	 * @return
	 */
	public boolean del(String levelCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_textbook");
		sql.append(String.format(" where textbook_code = '%1$s'",levelCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
	/**
	 * 产生自动编号
	 * yuansh
	 * @param levelCode
	 * @return
	 */
	public String getJcbh() {
		String sql="select concat('JC',LPAD(max(substr(textbook_code,3,8)+1),8,'0')) from base_textbook t";
		return dbUtil.getString(sql);
	}	
	
	public boolean isUseDel(String textbookCode){
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_textbook b set b.IS_USE='0'");
		sql.append(String.format(" where b.textbook_code = '%1$s'",textbookCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
	public boolean checkSyllabusCode(String syllabusCode){
		String sql="select count(*) from base_syllabus b where b.SYLLABUS_CODE=?";
		return dbUtil.getNum(sql, syllabusCode)>0;
	}
	
	public boolean checkTextbookCode(String textbookCode){
		String sql="select count(*) from base_textbook b where b.TEXTBOOK_CODE=? and b.IS_USE='1'";
		return dbUtil.getNum(sql, textbookCode)>0;
	}
}