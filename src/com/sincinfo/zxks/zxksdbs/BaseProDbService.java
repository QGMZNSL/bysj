package com.sincinfo.zxks.zxksdbs;

import com.sincinfo.zxks.bean.BaseAcademy;
import com.sincinfo.zxks.bean.BaseGraduateCondition;
import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.BaseProSyllabus;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.bean.ProSeq;
import com.sincinfo.zxks.bean.ProSyllabus;
import com.sincinfo.zxks.bean.ProType;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BaseProDbService
{
  private DbUtil dbUtil = null;

  public String newFilename()
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    Date currentDate = new Date();
    String filename = formatter.format(currentDate).toString();
    String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuffer sb = new StringBuffer();
    Random random = new Random();
    for (int i = 0; i < 4; i++) {
      sb.append(allChar.charAt(random.nextInt(allChar.length())));
    }
    filename = filename + sb.toString();
    return filename;
  }

  public String[] getPaths()
  {
    String cfgTypeOs = java.lang.System.getProperty("os.name").split(" ")[0]
      .toLowerCase();

    StringBuilder sql = new StringBuilder();
    sql.append("select (select c.sys_cfg_content from sys_config c");
    sql
      .append(" where c.sys_cfg_type='%1$s' and (c.sys_cfg_id = '1' or c.sys_cfg_id = '3')) as phyPath,");
    sql.append(" (select c.sys_cfg_content from sys_config c");
    sql
      .append(" where c.sys_cfg_type='%1$s' and (c.sys_cfg_id = '2' or c.sys_cfg_id = '4')) as netPath");
    sql.append(" from dual");
    DbUtil dbUtil = new DbUtil();
    String[] paths = dbUtil.getRsArray(String.format(sql.toString(), new Object[] { cfgTypeOs }), 2, new String[0]);
    return paths;
  }

  public String getConfig(String cfgId)
  {
    String sql = 
      String.format(
      "select c.sys_cfg_content from sys_config c where c.sys_cfg_id = '%1$s'", new Object[] { 
      cfgId });
    DbUtil dbUtil = new DbUtil();
    return dbUtil.getFirstCol(sql, new Object[0]);
  }

  public BaseProDbService()
  {
    this.dbUtil = new DbUtil();
  }

  public List<BasePro> qry(BasePro basepro, Page page)
  {
    List basepros = null;
    StringBuilder sql = new StringBuilder();
    StringBuilder sqlCount = new StringBuilder();
    sql.append("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name,(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name,(select count(*) from base_pro_syllabus s where s.pro_code=d.pro_code)syllabus_set from base_pro d where d.is_use='1' ");

    sqlCount.append("select count(*) from base_pro d where d.is_use='1' ");
    if (!StringTool.isEmpty(basepro)) {
      if (!StringTool.isEmpty(basepro.getProCode())) {
        sql.append(String.format(" and pro_code like '%%%1$s%%'", new Object[] { basepro.getProCode() }));
        sqlCount.append(String.format(" and pro_code like '%%%1$s%%'", new Object[] { basepro.getProCode() }));
      }
      if (!StringTool.isEmpty(basepro.getProName())) {
        sql.append(String.format(" and pro_name like '%%%1$s%%'", new Object[] { basepro.getProName() }));
        sqlCount.append(String.format(" and pro_name like '%%%1$s%%'", new Object[] { basepro.getProName() }));
      }
      if (!StringTool.isEmpty(basepro.getProTypecode())) {
        sql.append(String.format(" and d.pro_type_code = '%1$s'", new Object[] { basepro.getProTypecode() }));
        sqlCount.append(String.format(" and d.pro_type_code = '%1$s'", new Object[] { basepro.getProTypecode() }));
      }
      if (!StringTool.isEmpty(basepro.getProPartcode())) {
        sql.append(String.format(" and d.pro_part_code = '%1$s'", new Object[] { basepro.getProPartcode() }));
        sqlCount.append(String.format(" and d.pro_part_code = '%1$s'", new Object[] { basepro.getProPartcode() }));
      }
      if (!StringTool.isEmpty(basepro.getLevelCode())) {
        sql.append(String.format(" and level_code = '%1$s'", new Object[] { basepro.getLevelCode() }));
        sqlCount.append(String.format(" and level_code = '%1$s'", new Object[] { basepro.getLevelCode() }));
      }
      if (!StringTool.isEmpty(basepro.getIsUse())) {
        sql.append(String.format(" and is_use = '%1$s'", new Object[] { basepro.getIsUse() }));
        sqlCount.append(String.format(" and is_use = '%1$s'", new Object[] { basepro.getIsUse() }));
      }
    }
    sql.append(" order by d.pro_code asc");

    String sqlPage = page.setPagecount(this.dbUtil.getNum(sqlCount.toString(), new String[0]), sql.toString());
    basepros = this.dbUtil.getObjList(sqlPage, BasePro.class, new String[0]);
    return basepros;
  }

  public List<BasePro> qryForExcel(BasePro basepro)
  {
    List basepros = null;
    StringBuilder sql = new StringBuilder();
    sql.append("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name,(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name,(select count(*) from base_pro_syllabus s where s.pro_code=d.pro_code)syllabus_set from base_pro d where d.is_use='1' ");

    if (!StringTool.isEmpty(basepro)) {
      if (!StringTool.isEmpty(basepro.getProCode())) {
        sql.append(String.format(" and pro_code like '%%%1$s%%'", new Object[] { basepro.getProCode() }));
      }
      if (!StringTool.isEmpty(basepro.getProName())) {
        sql.append(String.format(" and pro_name like '%%%1$s%%'", new Object[] { basepro.getProName() }));
      }
      if (!StringTool.isEmpty(basepro.getProTypecode())) {
        sql.append(String.format(" and d.pro_type_code = '%1$s'", new Object[] { basepro.getProTypecode() }));
      }
      if (!StringTool.isEmpty(basepro.getProPartcode())) {
        sql.append(String.format(" and d.pro_part_code = '%1$s'", new Object[] { basepro.getProPartcode() }));
      }
      if (!StringTool.isEmpty(basepro.getLevelCode())) {
        sql.append(String.format(" and level_code = '%1$s'", new Object[] { basepro.getLevelCode() }));
      }
      if (!StringTool.isEmpty(basepro.getIsUse())) {
        sql.append(String.format(" and is_use = '%1$s'", new Object[] { basepro.getIsUse() }));
      }
    }
    sql.append(" order by d.pro_code asc");

    basepros = this.dbUtil.getObjList(sql.toString(), BasePro.class, new String[0]);

    return basepros;
  }

  public BasePro qry(String proCode)
  {
    BasePro basepro = null;
    String sql = String.format("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name,(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name,(select a.academy_name from base_academy_pro ap,base_academy a where ap.academy_code=a.academy_code and ap.pro_code=d.pro_code)academy_name from base_pro d where d.pro_code = '%1$s'", new Object[] { 
      proCode });
    basepro = (BasePro)this.dbUtil.getObject(sql, BasePro.class, new String[0]);
    return basepro;
  }

  public BasePro qryWindows(String proCode)
  {
    BasePro basepro = null;
    String sql = String.format("select b.*,(select s.graduate_condition_describe from base_graduate_condition s where s.pro_code='%1$s') as graduate_condition_describe from base_pro b where b.pro_code='%1$s'", new Object[] { 
      proCode });
    basepro = (BasePro)this.dbUtil.getObject(sql, BasePro.class, new String[0]);
    return basepro;
  }

  public BasePro qry(BasePro bp)
  {
    BasePro basepro = null;
    StringBuilder sql = new StringBuilder();
    sql.append("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name,(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name,(select a.academy_name from base_academy_pro ap,base_academy a where ap.academy_code=a.academy_code and ap.pro_code=d.pro_code)academy_name from base_pro d where 1=1  ");

    if (!StringTool.isEmpty(bp)) {
      if (!StringTool.isEmpty(bp.getProCode())) {
        sql.append(String.format(" and pro_code = '%1$s'", new Object[] { bp.getProCode() }));
      }
      if (!StringTool.isEmpty(bp.getProName())) {
        sql.append(String.format(" and pro_name like '%%%1$s%%'", new Object[] { bp.getProName() }));
      }
    }
    basepro = (BasePro)this.dbUtil.getObject(sql.toString(), BasePro.class, new String[0]);
    return basepro;
  }

  public List<ProSyllabus> qryProSyllabus(BasePro basepro, Page page)
  {
    List syls = null;
    StringBuilder sql = new StringBuilder();

    sql.append(String.format("select d.*,(select s.syllabus_name from base_syllabus s where s.syllabus_code=d.syllabus_code)syllabus_name,(select t.textbook_name from base_textbook t where t.textbook_code=d.textbook_code)textbook_name from base_pro_syllabus d where d.pro_code= '%1$s'", new Object[] { basepro.getProCode() }));

    sql.append(" order by d.substitute_code asc");

    syls = this.dbUtil.getObjList(sql.toString(), ProSyllabus.class, new String[0]);
    return syls;
  }

  public boolean save(BasePro basepro, int type)
  {
    boolean saveFlag = false;
    StringBuilder sql = new StringBuilder();
    String level_code = getLeveCode(basepro.getProTypecode());

    switch (type) {
    case 0:
      String s = "select count(*) from base_pro b where b.pro_code =?";
      Long l = Long.valueOf(this.dbUtil.getNum(s, new String[] { basepro.getProCode() }));
      if (l.longValue() == 0L) {
        sql.append("insert into base_pro");
        sql.append("(pro_code,pro_name,pro_part_code, pro_type_code,is_gb, is_use,level_code,is_allow_new_stu)");
        sql.append(" values");
        sql.append(
          String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s')", new Object[] { 
          basepro.getProCode(), basepro.getProName(), basepro.getProPartcode(), basepro.getProTypecode(), basepro.getIsGb(), basepro.getIsUse(), basepro.getLevelCode(), basepro.getIsAllowNewStu() }));
      }
      else {
        sql.append("update base_pro");
        sql.append(String.format(" set pro_name = '%1$s',", new Object[] { basepro.getProName() }));
        sql.append(String.format(" pro_part_code = '%1$s',", new Object[] { basepro.getProPartcode() }));
        sql.append(String.format(" pro_type_code = '%1$s',", new Object[] { basepro.getProTypecode() }));
        sql.append(String.format(" is_GB = '%1$s',", new Object[] { basepro.getIsGb() }));
        sql.append(String.format(" is_use = '%1$s', ", new Object[] { basepro.getIsUse() }));
        sql.append(String.format(" is_allow_new_stu = '%1$s', ", new Object[] { basepro.getIsAllowNewStu() }));
        sql.append(String.format(" level_code = '%1$s'", new Object[] { level_code }));
        sql.append(String.format(" where pro_code = '%1$s'", new Object[] { basepro.getProCode() }));
      }
      break;
    case 1:
      sql.append("update base_pro");
      sql.append(String.format(" set pro_name = '%1$s',", new Object[] { basepro.getProName() }));
      sql.append(String.format(" pro_part_code = '%1$s',", new Object[] { basepro.getProPartcode() }));
      sql.append(String.format(" pro_type_code = '%1$s',", new Object[] { basepro.getProTypecode() }));
      sql.append(String.format(" is_GB = '%1$s',", new Object[] { basepro.getIsGb() }));
      sql.append(String.format(" is_use = '%1$s', ", new Object[] { basepro.getIsUse() }));
      sql.append(String.format(" is_allow_new_stu = '%1$s', ", new Object[] { basepro.getIsAllowNewStu() }));
      sql.append(String.format(" level_code = '%1$s'", new Object[] { basepro.getLevelCode() }));
      sql.append(String.format(" where pro_code = '%1$s'", new Object[] { basepro.getProCode() }));
      break;
    }

    saveFlag = this.dbUtil.saveOrUpdate(sql.toString(), new String[0]) == 1;
    return saveFlag;
  }

  public boolean saveProSyllabus(BasePro basepro, int type)
  {
    boolean saveFlag = false;
    StringBuilder sql = new StringBuilder();
    String level_code = getLeveCode(basepro.getProTypecode());

    switch (type) {
    case 0:
      sql.append("insert into base_pro");
      sql.append("(pro_code,pro_name,pro_part_code, pro_type_code,is_gb, is_use,level_code)");
      sql.append(" values");
      sql.append(
        String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s')", new Object[] { 
        basepro.getProCode(), basepro.getProName(), basepro.getProPartcode(), basepro.getProTypecode(), basepro.getIsGb(), "1", level_code }));

      break;
    case 1:
      sql.append("update base_pro");
      sql.append(String.format(" set pro_name = '%1$s',", new Object[] { basepro.getProName() }));
      sql.append(String.format(" pro_part_code = '%1$s',", new Object[] { basepro.getProPartcode() }));
      sql.append(String.format(" pro_type_code = '%1$s',", new Object[] { basepro.getProTypecode() }));
      sql.append(String.format(" is_GB = '%1$s',", new Object[] { basepro.getIsGb() }));
      sql.append(String.format(" is_use = '%1$s', ", new Object[] { "1" }));
      sql.append(String.format(" level_code = '%1$s'", new Object[] { level_code }));
      sql.append(String.format(" where pro_code = '%1$s'", new Object[] { basepro.getProCode() }));

      break;
    }

    saveFlag = this.dbUtil.saveOrUpdate(sql.toString(), new String[0]) == 1;
    return saveFlag;
  }

  public boolean del(String proCode)
  {
    boolean delFlag = false;
    StringBuilder sql = new StringBuilder();
    sql.append("delete from base_pro");
    sql.append(String.format(" where pro_code = '%1$s'", new Object[] { proCode }));
    delFlag = this.dbUtil.saveOrUpdate(sql.toString(), new String[0]) == 1;
    return delFlag;
  }

  public List<ProType> qryProTypeClasses()
  {
    List pList = null;
    StringBuilder sql = new StringBuilder();
    sql.append("select pro_type_code, pro_type_name from base_pro_type where is_use='1' order by pro_type_code ");
    pList = this.dbUtil.getObjList(sql.toString(), ProType.class, new String[0]);
    return pList;
  }

  public List<ProSeq> qryProSeqClasses()
  {
    List pList = null;
    StringBuilder sql = new StringBuilder();
    sql.append("select pro_part_code, pro_part_name from base_pro_partitions where is_use='1' order by pro_part_code ");
    pList = this.dbUtil.getObjList(sql.toString(), ProSeq.class, new String[0]);
    return pList;
  }

  public List<PlanLevel> qryPlanLevelClasses()
  {
    List pList = null;
    StringBuilder sql = new StringBuilder();
    sql.append("select level_code,level_name from base_level where is_use='1' order by level_code");
    pList = this.dbUtil.getObjList(sql.toString(), PlanLevel.class, new String[0]);
    return pList;
  }

  public String getLeveCode(String protypeCode)
  {
    String leve_code = "";
    StringBuilder sql = new StringBuilder();
    sql.append("select level_code from base_pro_type");
    sql.append(String.format("where is_use='1' and pro_type_code= '%1$s'", new Object[] { protypeCode }));
    leve_code = this.dbUtil.getString(sql.toString());
    return leve_code;
  }

  public List<BaseProSyllabus> findProSyllabusList(String proCode)
  {
    StringBuilder sql = new StringBuilder();
    sql.append(" select p.syllabus_code,p.exam_unitary,p.textbook_unitary,p.syllabus_credit ");
    sql.append(" ,s.syllabus_name,g.syllabus_group_name,g.remarks ");
    sql.append(" ,p.syllabus_remark ");
    sql.append(" from base_pro_syllabus p left join base_syllabus_group g ");
    sql.append(" on p.syllabus_group_code=g.syllabus_group_code ");
    sql.append(" , base_syllabus s ");
    sql.append(" where p.syllabus_code=s.syllabus_code ");
    sql.append(" and p.pro_code=? ");
    sql.append(" order by p.syllabus_order ");
    return this.dbUtil.getObjList(sql.toString(), BaseProSyllabus.class, new String[] { proCode });
  }

  public BaseGraduateCondition findGraduateCond(String proCode)
  {
    StringBuilder sql = new StringBuilder();
    sql.append(" select p.pro_code,p.pro_name ");
    sql.append(" ,c.graduate_condition_credit,c.graduate_condition_describe ");
    sql.append(" from base_pro p left join base_graduate_condition c ");
    sql.append(" on p.pro_code=c.pro_code ");
    sql.append(" where p.pro_code=? ");
    return (BaseGraduateCondition)this.dbUtil.getObject(sql.toString(), BaseGraduateCondition.class, new String[] { proCode });
  }

  public List<BaseAcademy> findAcademyList(String proCode)
  {
    StringBuilder sql = new StringBuilder();
    sql.append(" select a.* ");
    sql.append(" from base_academy a,base_academy_pro p ");
    sql.append(" where a.academy_code=p.academy_code and p.pro_code=? ");
    sql.append(" order by a.academy_code asc ");
    return this.dbUtil.getObjList(sql.toString(), BaseAcademy.class, new String[] { proCode });
  }
}