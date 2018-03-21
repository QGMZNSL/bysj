package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.BaseCertificateQuery;
import com.sincinfo.zxks.bean.BaseGraduateStudentInfo;
import com.sincinfo.zxks.bean.SysCodeCertificate;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @see 证书查询管理/证书查询 业务类
 * @author guanm
 * 
 */
public class CertificateManagerDbService extends DbUtil {

	/**
	 * @see 获得证书类型 可用的
	 */
	public List<SysCodeCertificate> getCertificateList() {
		String sql = "select * from sys_code_certificate where is_use='1' order by is_default desc,list_order";
		return super.getObjList(sql, SysCodeCertificate.class);
	}

	/**
	 * @see 获得 所有证书 sql
	 */
	public String getCertificateSql(String ctype, String cid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select q.*");
		sql
				.append(",(select c.name from sys_code_certificate c where c.code=q.certificat_type) as certificat_type_name");
		sql.append(" from base_certificate_query q");
		sql.append(" where q.is_modify='1'");
		if (cid != null && !cid.equals("")) {
			sql.append(String.format(" and q.certificat_query_id=%1$s", cid));
		}
		if (ctype != null && !ctype.equals("")) {
			sql.append(String.format(" and q.certificat_type='%1$s'", ctype));
		}

		sql.append(" order by q.certificat_type");
		return sql.toString();
	}

	/**
	 * @see 获得 所有证书 count
	 */
	public long getCertificateCount(String ctype, String cid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from (");
		sql.append(this.getCertificateSql(ctype, cid));
		sql.append(")");
		return super.getNum(sql.toString());
	}

	/**
	 * @see 根据证书类型编号 获得该类型下的所有查询证书
	 */
	public List<BaseCertificateQuery> getCertificateQueryByType(String ctype) {
		String sql = this.getCertificateSql(ctype, null);
		return super.getObjList(sql, BaseCertificateQuery.class);
	}

	/**
	 * @see 获得 某证书类型下的查询证书 html
	 */
	public String ajaxGetStr(String ctype) {
		StringBuilder str = new StringBuilder();
		str.append("<select class=\"inputText inputTextM\" name=\"cid\">");
		str.append("<option value=\"\">---请选择---</option>");
		List<BaseCertificateQuery> list = this.getCertificateQueryByType(ctype);
		for (BaseCertificateQuery bean : list) {
			str
					.append(String.format(
							"<option value=\"%1$s\">%2$s</option>", bean
									.getCertificatQueryId(), bean
									.getCertificatQueryName()));
		}
		str.append("</select>");
		return str.toString();
	}

	/**
	 * @see 执行添加证书查询
	 */
	public boolean save(String ctype, String cname, String curl, String isuse) {
		String sql = "insert into base_certificate_query(certificat_query_id,certificat_query_name,certificat_type,certificat_query_url,is_use) values(seq_certificate.nextval,?,?,?,?)";
		int k = super.saveOrUpdate(sql, cname, ctype, curl, isuse);
		if (k > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @see 根据id 获得该查询证书信息
	 */
	public BaseCertificateQuery getCertBean(String cid) {
		String sql = "select * from base_certificate_query where certificat_query_id=?";
		return super.getObject(sql, BaseCertificateQuery.class, cid);
	}

	/**
	 * @see 更新证书查询
	 */
	public boolean update(String cid, String ctype, String cname, String curl,
			String isuse) {
		String sql = "update base_certificate_query set certificat_query_name=?,certificat_type=?,certificat_query_url=?,is_use=? where certificat_query_id=?";
		int k = super.saveOrUpdate(sql, cname, ctype, curl, isuse, cid);
		if (k > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see 删除记录
	 */
	public boolean delete(String cid) {
		String sql = "delete from base_certificate_query where certificat_query_id=?";
		int k = super.saveOrUpdate(sql, cid);
		if (k > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* 证书管理 */
	/**
	 * @see 获得可用的证书类型
	 */
	public List<SysCodeCertificate> getCodeList() {
		String sql = "select * from sys_code_certificate where code in (select q.certificat_type as code  from base_certificate_query q where q.is_use='1' group by q.certificat_type ) and is_use='1' order by list_order,code";
		return super.getObjList(sql, SysCodeCertificate.class);
	}

	/**
	 * @see 获得所有证书查询信息
	 */
	public List<BaseCertificateQuery> getCertifiQuery() {
		String sql = "select q.* from base_certificate_query q where q.is_use='1' and q.is_modify='1' order by q.certificat_type,q.certificat_query_id";
		return super.getObjList(sql, BaseCertificateQuery.class);
	}

	/**
	 * @see 自考证书查询
	 */
	public BaseGraduateStudentInfo getGraduateStudentInfo(String diplomaNum,
			String studIdnum) {
		StringBuilder sql = new StringBuilder();
		sql.append("select g.*");
		sql
				.append(",(select l.level_name from base_level l where l.level_code=g.level_code) as level_name");
		sql
				.append(",(select p.pro_name from base_pro p where p.pro_code=g.pro_code) as pro_name");
		sql.append(",g.academy_name as academy_pro_name");
		sql.append(",g.graduate_photo_file as stud_photo_file_1");
		sql.append(" from base_graduate_student_info g");
		if (diplomaNum != null && !diplomaNum.equals("")) {
			sql
					.append(String.format(" where g.diploma_num='%1$s'",
							diplomaNum));
		} else if (studIdnum != null && !studIdnum.equals("")) {
			sql.append(String.format(" where g.stud_idnum='%1$s'", studIdnum));
		} else {
			return null;
		}
		BaseGraduateStudentInfo bean = super.getObject(sql.toString(),
				BaseGraduateStudentInfo.class);
		if (bean == null) {
			return null;
		}
		String[] str = super.getPaths();
		String filePath = super.getConfig("21");// 毕业证二级路径
		bean.setStudPhotoFile1(str[1] + filePath + "/"
				+ bean.getStudPhotoFile1());
		return bean;
	}

	/**
	 * 查询2003年以前合格课程 add by litian
	 * 
	 * @param studOldCode
	 *            旧准考证号
	 * @return List<String[]>
	 */
	public List<String[]> qryOldSyllabusScore(String studOldCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select s.stud_old_code,");
		sql.append(" s.stud_exam_code,");
		sql.append(" s.stud_name,");
		sql.append(" s.old_syllabus_code,");
		sql.append(" s.syllabus_name,");
		sql.append(" s.stud_score,");
		sql.append(" to_char(s.stud_pass_date, 'yyyy-mm-dd')");
		sql.append(" from base_student_old_score s");
		sql.append(" where rtrim(s.stud_old_code) = '%1$s'");
		sql.append(" order by s.stud_old_code, s.old_syllabus_code");
		List<String[]> oldList = super.getRsArrayList(String.format(sql
				.toString(), StringTool.trim(studOldCode)), 7);
		return oldList;
	}

	/**
	 * 查询学历文凭毕业证 add by litian
	 * 
	 * @param diplomaNum
	 *            证书编号
	 * @param studIdnum
	 *            证件号
	 * @return String[]
	 */
	public String[] qryDiplomaInfo(String diplomaNum, String studIdnum) {
		StringBuilder sql = new StringBuilder();
		sql.append("select cx.grad_code,");
		sql.append(" cx.grad_name,");
		sql.append(" (select cg.name");
		sql.append(" from sys_code_gender cg");
		sql.append(" where cg.code = cx.grad_gender),");
		sql.append(" cx.grad_idnum,");
		sql.append(" cx.grad_school,");
		sql.append(" to_char(cx.grad_date, 'yyyy-mm-dd'),");
		sql.append(" cx.grad_pro");
		sql.append(" from base_cert_xlwp cx");
		sql.append(" where 1=1");
		if (!"".equals(diplomaNum)) {
			sql.append(String.format(" and rtrim(cx.grad_code) = '%1$s'",
					StringTool.trim(diplomaNum)));
		}
		if (!"".equals(studIdnum)) {
			sql.append(String.format(" and rtrim(cx.grad_idnum) = '%1$s'",
					StringTool.trim(studIdnum)));
		}
		String[] diplomaInfo = super.getRsArray(sql.toString(), 7);
		return diplomaInfo;
	}
}
