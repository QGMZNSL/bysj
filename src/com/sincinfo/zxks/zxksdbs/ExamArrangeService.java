package com.sincinfo.zxks.zxksdbs;

import com.sincinfo.zxks.bean.BaseProSyllabus;
import com.sincinfo.zxks.bean.BaseSyllabusArrange;
import com.sincinfo.zxks.bean.BaseSyllabusTime;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.StringTool;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ExamArrangeService extends DbUtil
{
  public int publicArrange(BaseSyllabusTime baseSyllabusTime)
  {
    StringBuilder sql = new StringBuilder();
    sql.append(" insert into base_examination_syllabus ");
    sql.append(" select t.examination_code, t.examination_time_code, b.syllabus_code, b.pro_code ");
    sql.append(" from base_syllabus_arrange a left join base_examination_time t ");
    sql
      .append(" on t.examination_date=a.year || '-' || a.month || '-' ||a.day and a.examination_time=t.examination_time ");
    sql.append(" ,base_syllabus_time b,base_pro d,base_syllabus e ");
    sql.append(" where a.syllabus_arrange_id=b.examination_time_code ");
    sql.append(" and b.syllabus_code=e.syllabus_code and b.pro_code=d.pro_code ");
    sql.append(" and d.is_use=1 and e.is_use=1 and e.syllabus_type='0' ");
    if (!StringTool.isEmpty(baseSyllabusTime.getProCode())) {
      sql.append(" and d.pro_code like '%" + StringTool.trim(baseSyllabusTime.getProCode()) + "%' ");
    }
    if (!StringTool.isEmpty(baseSyllabusTime.getProName())) {
      sql.append(" and d.pro_name like '%" + StringTool.trim(baseSyllabusTime.getProName()) + "%' ");
    }
    if (!StringTool.isEmpty(baseSyllabusTime.getProCode())) {
      sql.append(" and e.syllabus_code like '%" + StringTool.trim(baseSyllabusTime.getSyllabusCode()) + "%' ");
    }
    if (!StringTool.isEmpty(baseSyllabusTime.getProCode())) {
      sql.append(" and e.syllabus_name like '%" + StringTool.trim(baseSyllabusTime.getSyllabusName()) + "%' ");
    }
    if (!StringTool.isEmpty(baseSyllabusTime.getApplyYear())) {
      sql.append(" and a.year='" + StringTool.trim(baseSyllabusTime.getApplyYear()) + "' ");
    }
    if (!StringTool.isEmpty(baseSyllabusTime.getExaminationDate())) {
      String examinationDate = baseSyllabusTime.getExaminationDate();
      int i = examinationDate.lastIndexOf("-");
      int day = Integer.parseInt(examinationDate.substring(i + 1));
      examinationDate = examinationDate.substring(0, i);
      i = examinationDate.lastIndexOf("-");
      int month = Integer.parseInt(examinationDate.substring(i + 1));
      int year = Integer.parseInt(examinationDate.substring(0, i));
      sql.append(" and a.year=" + year + " and a.month=" + month + " and a.day=" + day);
    }
    if ((!StringTool.isEmpty(baseSyllabusTime.getExaminationTime())) && 
      ("0".equals(baseSyllabusTime.getExaminationTime()))) {
      sql.append(" and a.examination_time='" + StringTool.trim(baseSyllabusTime.getExaminationTime()) + "'");
    }

    System.out.println(sql.toString());
    return super.saveOrUpdate(sql.toString(), new String[0]);
  }

  public List<String> lExaminationYear()
  {
    List s = new ArrayList();
    String sql = "select distinct(t.YEAR) from BASE_SYLLABUS_ARRANGE t order by t.YEAR desc";
    List ss = super.getRsArrayList(sql, 1, new String[0]);
    int l = ss.size();
    for (int i = 0; i < l; i++) {
      s.add(((String[])ss.get(i))[0]);
    }
    return s;
  }

  public List<String> distinctMonth(BaseSyllabusTime baseSyllabusTime) {
    String year = baseSyllabusTime.getApplyYear();
    String sql = "select distinct(MONTH) from BASE_SYLLABUS_ARRANGE where YEAR = " + year;
    if (!StringTool.isEmpty(baseSyllabusTime.getExaminationDate())) {
      String date = baseSyllabusTime.getExaminationDate();
      int i = date.lastIndexOf("-");
      int day = Integer.parseInt(date.substring(i + 1));
      date = date.substring(0, i);
      i = date.lastIndexOf("-");
      int month = Integer.parseInt(date.substring(i + 1));
      year = date.substring(0, i);
      sql = sql + " and YEAR=" + year + " and month=" + month + " and day=" + day;
    }
    String examinationTime = baseSyllabusTime.getExaminationTime();
    if ((!StringTool.isEmpty(examinationTime)) && (!examinationTime.equals("0"))) {
      sql = sql + " and EXAMINATION_TIME='" + baseSyllabusTime.getExaminationTime() + "'";
    }
    sql = sql + " order by MONTH";
    List lss = super.getRsArrayList(sql, 1, new String[0]);
    List ss = new ArrayList();
    int l = lss.size();
    for (int i = 0; i < l; i++) {
      String[] s = (String[])lss.get(i);
      ss.add(s[0]);
    }
    return ss;
  }

  public List<String> distinctMonthAll(String year)
  {
    String sql = "select distinct(MONTH) from BASE_SYLLABUS_ARRANGE where YEAR = " + year + " order by MONTH";
    List lss = super.getRsArrayList(sql, 1, new String[0]);
    List ss = new ArrayList();
    int l = lss.size();
    for (int i = 0; i < l; i++) {
      String[] s = (String[])lss.get(i);
      ss.add(s[0]);
    }
    return ss;
  }

  public List<String> lSyllabusArrangeId(String year, String month)
  {
    String sql = "select SYLLABUS_ARRANGE_ID from BASE_SYLLABUS_ARRANGE where YEAR=" + year + " and MONTH=" + month + " order by DAY,EXAMINATION_TIME";
    List lss = super.getRsArrayList(sql, 1, new String[0]);
    List ss = new ArrayList();
    int l = lss.size();
    for (int i = 0; i < l; i++) {
      String[] s = (String[])lss.get(i);
      ss.add(s[0]);
    }
    return ss;
  }

  public boolean CopyBaseSyllabusTime(List<String> lSyllabusArrangeIdA, List<String> lSyllabusArrangeIdB)
  {
    int l = lSyllabusArrangeIdA.size();

    boolean bool = false;

    ArrayList list = new ArrayList();

    for (int i = 0; i < l; i++) {
      String aid = (String)lSyllabusArrangeIdA.get(i);
      String bid = (String)lSyllabusArrangeIdB.get(i);
      String sql = "delete from BASE_SYLLABUS_TIME where COUNTRY_PROVINCE='2' and EXAMINATION_TIME_CODE=" + bid;
      super.saveOrUpdate(sql, new String[0]);
      sql = "select * from BASE_SYLLABUS_TIME where COUNTRY_PROVINCE='2' and EXAMINATION_TIME_CODE=" + aid;
      List lBaseSyllabusTime = super.getObjList(sql, BaseSyllabusTime.class, new String[0]);
      int ll = lBaseSyllabusTime.size();
      for (int j = 0; j < ll; j++) {
        BaseSyllabusTime baseSyllabusTime = (BaseSyllabusTime)lBaseSyllabusTime.get(j);
        sql = "insert into BASE_SYLLABUS_TIME(SYLLABUS_CODE,PRO_CODE,PRO_NAME,SYLLABUS_NAME,EXAMINATION_TIME_CODE,COUNTRY_PROVINCE,COMM_SYLLABUS) values('%1$s','%2$s','%3$s','%4$s',%5$s,'2','0')";
        sql = String.format(sql, new Object[] { baseSyllabusTime.getSyllabusCode(), baseSyllabusTime.getProCode(), baseSyllabusTime.getProName(), baseSyllabusTime.getSyllabusName(), bid, baseSyllabusTime.getCommSysllabus() });

        list.add(sql);
      }
    }
    if (list.size() > 0) {
      bool = super.transExeSqls(list) > 0;
    }
    return bool;
  }

  public List<BaseSyllabusArrange> lBaseSyllabusArrange(String year, String month, String examinationDate, String examinationTime) {
    String sql = "select * from BASE_SYLLABUS_ARRANGE where YEAR=" + year + " and MONTH = " + month;
    if (!StringTool.isEmpty(examinationDate)) {
      int i = examinationDate.lastIndexOf("-");
      int day = Integer.parseInt(examinationDate.substring(i + 1));
      examinationDate = examinationDate.substring(0, i);
      i = examinationDate.lastIndexOf("-");
      int month1 = Integer.parseInt(examinationDate.substring(i + 1));
      int year1 = Integer.parseInt(examinationDate.substring(0, i));
      sql = sql + " and YEAR=" + year1 + " and month=" + month1 + " and day=" + day;
    }
    if ((!StringTool.isEmpty(examinationTime)) && (!examinationTime.equals("0"))) {
      sql = sql + " and EXAMINATION_TIME='" + examinationTime + "'";
    }
    sql = sql + " order by DAY,EXAMINATION_TIME";
    List ss = super.getObjList(sql, BaseSyllabusArrange.class, new String[0]);
    return ss;
  }

  public List<BaseSyllabusArrange> lBaseSyllabusArrangeAll(String year, String month)
  {
    String sql = "select * from BASE_SYLLABUS_ARRANGE where YEAR=" + year + " and MONTH = " + month + " order by DAY,EXAMINATION_TIME";
    List ss = super.getObjList(sql, BaseSyllabusArrange.class, new String[0]);
    return ss;
  }

  public String getSyllabus2(String examTimeCode, String proCode, String syllabusCode, String syllabusName, String str)
  {
    String sql = "select distinct(a.SYLLABUS_CODE),b.SYLLABUS_NAME from BASE_PRO_SYLLABUS a left join BASE_SYLLABUS b on a.SYLLABUS_CODE=b.SYLLABUS_CODE left join BASE_SYLLABUS_TIME c on a.SYLLABUS_CODE=c.SYLLABUS_CODE where b.IS_USE='1' and b.SYLLABUS_TYPE='0' and a.PRO_CODE='" + 
      proCode + "'" + 
      " and c.EXAMINATION_TIME_CODE = " + examTimeCode + " and c.COUNTRY_PROVINCE='1'";
    if (!StringTool.isEmpty(syllabusCode)) {
      sql = sql + " and c.SYLLABUS_CODE like '%" + syllabusCode.trim().toUpperCase() + "%'";
    }
    if (!StringTool.isEmpty(syllabusName)) {
      sql = sql + " and c.SYLLABUS_NAME like '%" + syllabusName.trim().toUpperCase() + "%'";
    }
    sql = sql + " order by a.SYLLABUS_CODE";
    List lss = super.getRsArrayList(sql, 2, new String[0]);
    String s = "";
    int l = lss.size();
    for (int i = 0; i < l; i++) {
      String[] ss = (String[])lss.get(i);
      if ((!StringTool.isEmpty(ss[1])) && (!ss[1].equals("0"))) {
        s = s + ss[1];
      }
      s = s + "（" + ss[0] + "）";
      if (i < l - 1) {
        s = s + str;
      }
    }
    return s;
  }

  public String getSyllabus13(String examTimeCode, String proCode, String countryProvince, String syllabusCode, String syllabusName, String str) {
    String sql = "select a.SYLLABUS_CODE,a.SYLLABUS_NAME from BASE_SYLLABUS_TIME a where a.EXAMINATION_TIME_CODE = " + 
      examTimeCode + " and a.PRO_CODE='" + proCode + "'" + 
      " and a.COUNTRY_PROVINCE='" + countryProvince + "'";
    if (!StringTool.isEmpty(syllabusCode)) {
      sql = sql + " and a.SYLLABUS_CODE like '%" + syllabusCode.trim().toUpperCase() + "%'";
    }
    if (!StringTool.isEmpty(syllabusName)) {
      sql = sql + " and a.SYLLABUS_NAME like '%" + syllabusName.trim().toUpperCase() + "%'";
    }
    sql = sql + " order by a.SYLLABUS_CODE";
    List lss = super.getRsArrayList(sql, 2, new String[0]);
    String s = "";
    int l = lss.size();
    for (int i = 0; i < l; i++) {
      String[] ss = (String[])lss.get(i);
      if ((!StringTool.isEmpty(ss[1])) && (!ss[1].equals("0"))) {
        s = s + ss[1];
      }
      s = s + "（" + ss[0] + "）";
      if (i < l - 1) {
        s = s + str;
      }
    }
    return s;
  }

  public String getSyllabus4(String examTimeCode, String proCode, String syllabusCode, String syllabusName, String examUnitary, String str)
  {
    String sql = "select distinct(a.SYLLABUS_CODE),b.SYLLABUS_NAME,c.COUNTRY_PROVINCE from BASE_PRO_SYLLABUS a left join BASE_SYLLABUS b on a.SYLLABUS_CODE=b.SYLLABUS_CODE left join BASE_SYLLABUS_TIME c on a.SYLLABUS_CODE=c.SYLLABUS_CODE where b.IS_USE='1' and b.SYLLABUS_TYPE='0' and a.PRO_CODE='" + 
      proCode + "'" + 
      " and c.EXAMINATION_TIME_CODE = " + examTimeCode + " and c.COUNTRY_PROVINCE='1'";
    if (!StringTool.isEmpty(syllabusCode)) {
      sql = sql + " and c.SYLLABUS_CODE like '%" + syllabusCode.trim().toUpperCase() + "%'";
    }
    if (!StringTool.isEmpty(syllabusName)) {
      sql = sql + " and c.SYLLABUS_NAME like '%" + syllabusName.trim().toUpperCase() + "%'";
    }
    if ((!StringTool.isEmpty(examUnitary)) && (examUnitary.equals("0"))) {
      sql = sql + " and c.COUNTRY_PROVINCE='0'";
    }
    sql = sql + " order by a.SYLLABUS_CODE";
    List lss = super.getRsArrayList(sql, 3, new String[0]);
    sql = "select a.SYLLABUS_CODE,a.SYLLABUS_NAME,a.COUNTRY_PROVINCE from BASE_SYLLABUS_TIME a where a.EXAMINATION_TIME_CODE = " + 
      examTimeCode + " and a.PRO_CODE='" + proCode + "'" + 
      " and a.COUNTRY_PROVINCE='2'";
    if (!StringTool.isEmpty(syllabusCode)) {
      sql = sql + " and a.SYLLABUS_CODE like '%" + syllabusCode.trim().toUpperCase() + "%'";
    }
    if (!StringTool.isEmpty(syllabusName)) {
      sql = sql + " and a.SYLLABUS_NAME like '%" + syllabusName.trim().toUpperCase() + "%'";
    }
    if ((!StringTool.isEmpty(examUnitary)) && (examUnitary.equals("1"))) {
      sql = sql + " and a.COUNTRY_PROVINCE='0'";
    }
    sql = sql + " order by a.SYLLABUS_CODE";
    List lss1 = super.getRsArrayList(sql, 3, new String[0]);
    lss.addAll(lss1);
    String s = "";
    int l = lss.size();
    for (int i = 0; i < l; i++) {
      String[] ss = (String[])lss.get(i);
      if ((!StringTool.isEmpty(ss[1])) && (!ss[1].equals("0"))) {
        s = s + ss[1];
      }
      s = s + "（" + ss[0] + "）";
      if ((StringTool.isEmpty(ss[2])) || (!ss[2].equals("1"))) {
        s = s + "*";
      }
      if (i < l - 1) {
        s = s + str;
      }
    }
    return s;
  }

  public String shanxiBaseSyllabusTime1(BaseSyllabusTime baseSyllabusTime, String proTypeCode) {
    StringBuffer sb = new StringBuffer();
    sb.append("select distinct(z.pro_code),b.pro_name from BASE_PRO_SYLLABUS z left join BASE_PRO b on z.pro_code=b.pro_code left join BASE_SYLLABUS a on z.syllabus_code = a.syllabus_code where b.IS_USE='1' and a.SYLLABUS_TYPE='0' and a.IS_USE='1' and b.PRO_TYPE_CODE='" + 
      proTypeCode + "' order by z.pro_code");
    return sb.toString();
  }

  public String getStrBaseSyllabusTime1(BaseSyllabusTime baseSyllabusTime, String proTypeCode) {
    StringBuffer sb = new StringBuffer();
    sb.append("select distinct(a.pro_code),a.pro_name,a.COUNTRY_PROVINCE,a.COMM_SYLLABUS from BASE_SYLLABUS_TIME a left join BASE_PRO b on a.pro_code=b.pro_code where b.PRO_TYPE_CODE='" + 
      proTypeCode + "'");
    String countryProvince = baseSyllabusTime.getCountryProvince();
    if ((countryProvince.equals("1")) || (countryProvince.equals("2"))) {
      sb.append(" and a.COUNTRY_PROVINCE = '" + countryProvince + "'");
    }
    sb.append(" order by a.pro_code");
    return sb.toString();
  }

  public String shanxiBaseSyllabusTime(BaseSyllabusTime baseSyllabusTime)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("select distinct(z.pro_code),b.pro_name from BASE_PRO_SYLLABUS z left join BASE_PRO b on z.pro_code=b.pro_code left join BASE_SYLLABUS a on z.syllabus_code = a.syllabus_code where b.IS_USE='1' and a.SYLLABUS_TYPE='0' and a.IS_USE='1'");

    String proCode = baseSyllabusTime.getProCode();
    if (!StringTool.isEmpty(proCode)) {
      sb.append(" and z.PRO_CODE like '%" + proCode.trim().toUpperCase() + "%'");
    }
    String proName = baseSyllabusTime.getProName();
    if (!StringTool.isEmpty(proName)) {
      sb.append(" and b.PRO_NAME like '%" + proName.trim().toUpperCase() + "%'");
    }
    String syllabusCode = baseSyllabusTime.getSyllabusCode();
    if (!StringTool.isEmpty(syllabusCode)) {
      sb.append(" and z.SYLLABUS_CODE like '%" + syllabusCode.trim().toUpperCase() + "%'");
    }
    String syllabusName = baseSyllabusTime.getSyllabusName();
    if (!StringTool.isEmpty(syllabusName)) {
      sb.append(" and a.SYLLABUS_NAME like '%" + syllabusName.trim().toUpperCase() + "%'");
    }
    sb.append(" order by z.pro_code");
    return sb.toString();
  }

  public String getStrBaseSyllabusTime(BaseSyllabusTime baseSyllabusTime) {
    StringBuffer sb = new StringBuffer();
    sb.append("select distinct(a.pro_code),a.pro_name,a.COUNTRY_PROVINCE,a.COMM_SYLLABUS from BASE_SYLLABUS_TIME");
    sb.append(" a left join base_syllabus_arrange t on a.EXAMINATION_TIME_CODE=t.SYLLABUS_ARRANGE_ID");

    sb.append(" where 1=1");
    String year = baseSyllabusTime.getApplyYear();
    if ((!StringTool.isEmpty(year)) && (!year.equals("0"))) {
      sb.append(" and t.YEAR =" + year);
    }

    if (!StringTool.isEmpty(baseSyllabusTime.getExaminationDate())) {
      String date = baseSyllabusTime.getExaminationDate();
      int i = date.lastIndexOf("-");
      int day = Integer.parseInt(date.substring(i + 1));
      date = date.substring(0, i);
      i = date.lastIndexOf("-");
      int month = Integer.parseInt(date.substring(i + 1));
      year = date.substring(0, i);
      sb.append(" and t.YEAR=" + year + " and t.month=" + month + " and t.day=" + day);
    }
    String examinationTime = baseSyllabusTime.getExaminationTime();
    if ((!StringTool.isEmpty(examinationTime)) && (!examinationTime.equals("0"))) {
      sb.append(" and t.EXAMINATION_TIME = '" + examinationTime + "'");
    }
    String countryProvince = baseSyllabusTime.getCountryProvince();
    if ((countryProvince.equals("1")) || (countryProvince.equals("2"))) {
      sb.append(" and a.COUNTRY_PROVINCE = '" + countryProvince + "'");
    }
    String proCode = baseSyllabusTime.getProCode();
    if (!StringTool.isEmpty(proCode)) {
      sb.append(" and a.PRO_CODE like '%" + proCode.trim().toUpperCase() + "%'");
    }
    String proName = baseSyllabusTime.getProName();
    if (!StringTool.isEmpty(proName)) {
      sb.append(" and a.PRO_NAME like '%" + proName.trim().toUpperCase() + "%'");
    }
    String syllabusCode = baseSyllabusTime.getSyllabusCode();
    if (!StringTool.isEmpty(syllabusCode)) {
      sb.append(" and a.SYLLABUS_CODE like '%" + syllabusCode.trim().toUpperCase() + "%'");
    }
    String syllabusName = baseSyllabusTime.getSyllabusName();
    if (!StringTool.isEmpty(syllabusName)) {
      sb.append(" and a.SYLLABUS_NAME like '%" + syllabusName.trim().toUpperCase() + "%'");
    }
    sb.append(" order by a.COMM_SYLLABUS desc,a.pro_code");
    return sb.toString();
  }

  public String newFilename() {
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

  public String[] getPro(String pro) {
    int i = pro.lastIndexOf("（");
    int j = pro.lastIndexOf("(");
    if ((i < 0) && (j < 0)) {
      String[] ss = { "x", "x" };
      return ss;
    }

    if (j > i) i = j;
    String proname = pro.substring(0, i);
    String procode = pro.substring(i + 1);
    i = procode.lastIndexOf("）");
    j = procode.lastIndexOf(")");
    if (j > i) i = j;
    if (i > 0) {
      procode = procode.substring(0, i);
    }
    String[] ss = { procode.trim(), proname.trim() };
    return ss;
  }

  public boolean checkSyllabuscode(String syllabuscode, String examTimeCode)
  {
    StringBuffer sb = new StringBuffer();
    sb.append(" b where b.examination_time_code in (select SYLLABUS_ARRANGE_ID from BASE_SYLLABUS_ARRANGE where MONTH=(select MONTH from BASE_SYLLABUS_ARRANGE where SYLLABUS_ARRANGE_ID=%2$s) and SYLLABUS_ARRANGE_ID<>%2$s) and b.syllabus_code='%1$s'");

    Long l = Long.valueOf(super.getNum("select count(*) from BASE_SYLLABUS_TIME" + String.format(sb.toString(), new Object[] { syllabuscode, examTimeCode }), new String[0]));
    return l.longValue() <= 0L;
  }

  public boolean checkSyllabusType(String procode, String syllabuscode)
  {
    String sql = "select b.SYLLABUS_TYPE from BASE_PRO_SYLLABUS a left join BASE_SYLLABUS b on a.SYLLABUS_CODE=b.SYLLABUS_CODE where b.IS_USE='1' and a.PRO_CODE='%1$s' and a.SYLLABUS_CODE='%2$s'";

    String syllabusType = super.getFirstCol(String.format(sql, new Object[] { procode, syllabuscode }), new Object[0]);
    if (syllabusType == null) return true;
    return syllabusType.equals("0");
  }

  public boolean saveSyllabus(String proname, String procode, String syllabus, String examTimeCode, String menutype, String commSysllabus)
  {
    boolean bool = false;
    String[] stringSpilit = StringTool.stringSpilit(syllabus, "\\\\N");
    int l = stringSpilit.length;

    String save = ""; String update = ""; String count = "";
    save = "insert into BASE_SYLLABUS_TIME(PRO_NAME,SYLLABUS_NAME,PRO_CODE,SYLLABUS_CODE,EXAMINATION_TIME_CODE,COUNTRY_PROVINCE,COMM_SYLLABUS) values('%1$s','%2$s','%3$s','%4$s',%5$s,'%6$s','%7$s')";

    boolean ifsyll = true;
    ArrayList list = new ArrayList();
    if (commSysllabus.equals("0")) {
      count = "select count(*) from BASE_SYLLABUS_TIME where PRO_CODE='%1$s' and SYLLABUS_CODE='%2$s' and EXAMINATION_TIME_CODE=%3$s and COUNTRY_PROVINCE='%4$s'";
      update = "update BASE_SYLLABUS_TIME set PRO_NAME='%1$s',SYLLABUS_NAME='%2$s',COMM_SYLLABUS='0' where PRO_CODE='%3$s' and SYLLABUS_CODE='%4$s' and EXAMINATION_TIME_CODE=%5$s and COUNTRY_PROVINCE='%6$s'";
      int i = 0;
      do {
        String s = stringSpilit[i].trim();
        if (!s.equals("")) {
          String[] ss = getPro(s);
          String syllabuscode = ss[0];
          String syllabusname = ss[1];
          if ((!syllabuscode.equals("x")) || (!syllabusname.equals("x"))) {
            String sql = String.format(count, new Object[] { procode, syllabuscode, examTimeCode, menutype });
            Long ll = Long.valueOf(super.getNum(sql, new String[0]));
            if (ll.longValue() > 0L) {
              sql = String.format(update, new Object[] { proname, syllabusname, procode, syllabuscode, examTimeCode, menutype });
              list.add(sql);
            }
            else {
              sql = String.format(save, new Object[] { proname, syllabusname, procode, syllabuscode, examTimeCode, menutype, "0" });
              list.add(sql);
            }
          }
          else {
            ifsyll = false;
            list = new ArrayList();
          }
        }
        i++;

        if (!ifsyll) break; 
      }while (i < l);
    }
    else if (commSysllabus.equals("1")) {
      count = "select count(*) from BASE_SYLLABUS_TIME where PRO_CODE='%1$s' and PRO_NAME='%2$s' and SYLLABUS_CODE='%3$s' and EXAMINATION_TIME_CODE=%4$s and COUNTRY_PROVINCE='%5$s'";
      update = "update BASE_SYLLABUS_TIME set SYLLABUS_NAME='%1$s',COMM_SYLLABUS='1' where PRO_CODE='%2$s' and PRO_NAME='%3$s' and SYLLABUS_CODE='%4$s' and EXAMINATION_TIME_CODE=%5$s and COUNTRY_PROVINCE='%6$s'";
      int i = 0;
      while ((ifsyll) && (i < l)) {
        String s = stringSpilit[i].trim();
        if (!s.equals("")) {
          String[] ss = getPro(s);
          String syllabuscode = ss[0];
          String syllabusname = ss[1];
          if ((!syllabuscode.equals("x")) || (!syllabusname.equals("x"))) {
            String sql = String.format(count, new Object[] { procode, proname, syllabuscode, examTimeCode, menutype });
            Long ll = Long.valueOf(super.getNum(sql, new String[0]));
            if (ll.longValue() > 0L) {
              sql = String.format(update, new Object[] { syllabusname, procode, proname, syllabuscode, examTimeCode, menutype });
              list.add(sql);
            }
            else {
              sql = String.format(save, new Object[] { proname, syllabusname, procode, syllabuscode, examTimeCode, menutype, "1" });
              list.add(sql);
            }
          }
          else {
            ifsyll = false;
            list = new ArrayList();
          }
        }
        i++;
      }
    }
    if (list.size() > 0) {
      bool = super.transExeSqls(list) > 0;
    }
    return bool;
  }

  public List<BaseSyllabusTime> lBaseSyllabusTime(String proCode, String examTimeCode, String countryProvince) {
    String sql = "select * from BASE_SYLLABUS_TIME where PRO_CODE='%1$s' and EXAMINATION_TIME_CODE=%2$s and COUNTRY_PROVINCE='%3$s' order by SYLLABUS_CODE";
    return super.getObjList(String.format(sql, new Object[] { proCode, examTimeCode, countryProvince }), BaseSyllabusTime.class, new String[0]);
  }

  public List<BaseSyllabusTime> lBaseSyllabusTime2(String proCode, String examTimeCode)
  {
    List lBaseSyllabusTime = lBaseSyllabusTime(proCode, examTimeCode, "1");
    String sql = "select distinct(a.SYLLABUS_CODE),b.SYLLABUS_NAME from BASE_PRO_SYLLABUS a left join BASE_SYLLABUS b on a.SYLLABUS_CODE=b.SYLLABUS_CODE left join BASE_SYLLABUS_TIME c on a.SYLLABUS_CODE=c.SYLLABUS_CODE where b.IS_USE='1' and b.SYLLABUS_TYPE='0' and a.PRO_CODE='" + 
      proCode + "'" + 
      " and c.EXAMINATION_TIME_CODE = " + examTimeCode + " and c.COUNTRY_PROVINCE='1'" + 
      " order by a.SYLLABUS_CODE";
    List lBaseSyllabusTime1 = super.getObjList(String.format(sql, new Object[] { proCode, examTimeCode }), BaseSyllabusTime.class, new String[0]);
    int l = lBaseSyllabusTime1.size();
    int ll = lBaseSyllabusTime.size();
    List lBaseSyllabusTime2 = new ArrayList();

    boolean bool = true;
    for (int i = 0; i < l; i++) {
      bool = true;
      BaseSyllabusTime baseSyllabusTime1 = (BaseSyllabusTime)lBaseSyllabusTime1.get(i);
      for (int j = 0; j < ll; j++) {
        BaseSyllabusTime baseSyllabusTime = (BaseSyllabusTime)lBaseSyllabusTime.get(j);
        if (baseSyllabusTime1.getSyllabusCode().equals(baseSyllabusTime.getSyllabusCode())) {
          bool = false;
        }
      }
      if (bool) {
        lBaseSyllabusTime2.add(baseSyllabusTime1);
      }
    }
    return lBaseSyllabusTime2;
  }

  public int count(BaseSyllabusTime baseSyllabusTime) {
    String sql = "select count(*) from BASE_SYLLABUS_TIME where PRO_CODE='%1$s' and SYLLABUS_CODE='%2$s' and EXAMINATION_TIME_CODE=%3$s";
    String l = String.valueOf(super.getNum(String.format(sql, new Object[] { baseSyllabusTime.getProCode(), baseSyllabusTime.getSyllabusCode(), baseSyllabusTime.getExaminationTimeCode() }), new String[0]));
    return Integer.parseInt(l);
  }

  public boolean save(BaseSyllabusTime baseSyllabusTime) {
    String sql = "insert into BASE_SYLLABUS_TIME(SYLLABUS_CODE,PRO_CODE,PRO_NAME,SYLLABUS_NAME,EXAMINATION_TIME_CODE,COUNTRY_PROVINCE,COMM_SYLLABUS) values('%1$s','%2$s','%3$s','%4$s',%5$s,'2','0')";
    boolean bool = super.saveOrUpdate(String.format(sql, new Object[] { baseSyllabusTime.getSyllabusCode(), baseSyllabusTime.getProCode(), baseSyllabusTime.getProName(), baseSyllabusTime.getSyllabusName(), baseSyllabusTime.getExaminationTimeCode() }), new String[0]) > 0;
    return bool;
  }

  public boolean delete(BaseSyllabusTime baseSyllabusTime) {
    String sql = "delete BASE_SYLLABUS_TIME where PRO_CODE='%1$s' and SYLLABUS_CODE='%2$s' and EXAMINATION_TIME_CODE=%3$s and COUNTRY_PROVINCE='%4$s'";
    return super.saveOrUpdate(String.format(sql, new Object[] { baseSyllabusTime.getProCode(), baseSyllabusTime.getSyllabusCode(), baseSyllabusTime.getExaminationTimeCode(), baseSyllabusTime.getCountryProvince() }), new String[0]) > 0;
  }

  public BaseSyllabusTime getBaseSyllabusTime(BaseSyllabusTime baseSyllabusTime)
  {
    String sql = "select * from BASE_COUNTRY_TIME where PRO_CODE='%1$s' and SYLLABUS_CODE='%2$s' and EXAMINATION_TIME_CODE=%3$s and COUNTRY_PROVINCE='%4$s'";
    baseSyllabusTime = (BaseSyllabusTime)super.getObject(String.format(sql, new Object[] { baseSyllabusTime.getProCode(), baseSyllabusTime.getSyllabusCode(), baseSyllabusTime.getExaminationTimeCode(), baseSyllabusTime.getCountryProvince() }), BaseSyllabusTime.class, new String[0]);
    return baseSyllabusTime;
  }

  public String getProName(String proCode) {
    String proName = "";
    String sql = "select pro_name from BASE_PRO where PRO_CODE='%1$s'";
    proName = super.getFirstCol(String.format(sql, new Object[] { proCode }), new Object[0]);
    if (proName == null) {
      sql = "select pro_name from BASE_SYLLABUS_TIME where PRO_CODE='%1$s'";
      proName = super.getFirstCol(String.format(sql, new Object[] { proCode }), new Object[0]);
    }
    if ((proName == null) || (proName.equals("0"))) {
      return "";
    }
    return proName;
  }

  public String getSyllabusName(String proCode) {
    String proSyllabus = "";
    String sql = "select SYLLABUS_NAME from BASE_SYLLABUS where SYLLABUS_CODE='%1$s'";
    proSyllabus = super.getFirstCol(String.format(sql, new Object[] { proCode }), new Object[0]);
    if (proSyllabus == null) {
      sql = "select SYLLABUS_NAME from BASE_SYLLABUS_TIME where SYLLABUS_CODE='%1$s'";
      proSyllabus = super.getFirstCol(String.format(sql, new Object[] { proCode }), new Object[0]);
    }
    if ((proSyllabus == null) || (proSyllabus.equals("0"))) {
      return "";
    }
    return proSyllabus;
  }

  public List<BaseProSyllabus> lBaseProSyllabus(String proCode, String examTimeCode) {
    List lB = new ArrayList();
    String sql = "select a.SYLLABUS_CODE,b.SYLLABUS_NAME from BASE_PRO_SYLLABUS a left join BASE_SYLLABUS b on a.SYLLABUS_CODE=b.SYLLABUS_CODE where b.IS_USE='1' and b.SYLLABUS_TYPE='0' and a.PRO_CODE='%1$s' order by a.SYLLABUS_CODE";

    List lBaseProSyllabus = super.getObjList(String.format(sql, new Object[] { proCode }), BaseProSyllabus.class, new String[0]);
    sql = "select SYLLABUS_CODE from BASE_SYLLABUS_TIME where PRO_CODE = '%1$s' and EXAMINATION_TIME_CODE in(select SYLLABUS_ARRANGE_ID from base_syllabus_arrange where MONTH=(select MONTH from base_syllabus_arrange where SYLLABUS_ARRANGE_ID = %2$s))";

    List ls = super.getRsArrayList(String.format(sql, new Object[] { proCode, examTimeCode }), 1, new String[0]);
    int l = ls.size(); int ll = lBaseProSyllabus.size();

    boolean bool = true;
    for (int j = 0; j < ll; j++) {
      bool = true;
      BaseProSyllabus baseProSyllabus = (BaseProSyllabus)lBaseProSyllabus.get(j);
      for (int i = 0; i < l; i++) {
        String[] ss = (String[])ls.get(i);
        if (ss[0].equals(baseProSyllabus.getSyllabusCode())) {
          bool = false;
        }
      }
      if (bool) {
        lB.add(baseProSyllabus);
      }
    }
    return lB;
  }

  public String getDay(String str) {
    String[] dd = getPro(str);
    if ((dd[0].equals("x")) && (dd[1].equals("x"))) {
      return "0";
    }
    str = dd[0];
    int i = str.lastIndexOf("月");
    int j = str.lastIndexOf("日");
    if ((i < 0) || (j < 0) || (i + 1 >= j)) {
      return "0";
    }
    str = str.substring(i + 1, j);
    if (StringTool.testInt(str)) {
      return str;
    }

    return "0";
  }

  public String[] saveArrange(String str, String year, String month, String day)
  {
    if ((year.equals("0")) || (month.equals("0")) || (day.equals("0"))) {
      String[] ss = { "", "" };
      return ss;
    }
    String[] dd = getPro(str);
    if ((dd[0].equals("x")) && (dd[1].equals("x"))) {
      String[] ss = { "", "" };
      return ss;
    }
    String examinationTime = dd[1];
    if (examinationTime.equals("上午")) {
      examinationTime = "1";
    }
    else if (examinationTime.equals("下午")) {
      examinationTime = "2";
    }
    else {
      String[] ss = { "", "" };
      return ss;
    }
    String se = dd[0];
    int i = se.indexOf("--");
    if (i < 0) {
      String[] ss = { "", "" };
      return ss;
    }
    String[] s_week = { "周五", "周六", "周日", "周一", "周二", "周三", "周四" };
    Calendar c = Calendar.getInstance();
    c.set(1, Integer.parseInt(year));
    c.set(2, Integer.parseInt(month));
    c.set(5, Integer.parseInt(day));
    int week = c.get(7) - 1;
    String examStartTime = se.substring(0, i);
    String examEndTime = se.substring(i + 2);
    String sqlId = "select SYLLABUS_ARRANGE_ID from BASE_SYLLABUS_ARRANGE where YEAR=%1$s and MONTH=%2$s and DAY=%3$s and EXAMINATION_TIME='%4$s'";
    sqlId = String.format(sqlId, new Object[] { year, month, day, examinationTime });
    String sql = "";
    String id = super.getFirstCol(sqlId, new Object[0]);
    if ((StringTool.isEmpty(id)) || (Integer.parseInt(id) < 1)) {
      sql = String.format("insert into BASE_SYLLABUS_ARRANGE(SYLLABUS_ARRANGE_ID,YEAR,MONTH,DAY,WEEK,EXAMINATION_TIME,EXAMINATION_START_TIME,EXAMINATION_END_TIME)values(SEQ_SYLLABUS_ARRANGE.nextval,%1$s,%2$s,%3$s,'%4$s','%5$s','%6$s','%7$s')", new Object[] { 
        year, month, day, s_week[week], examinationTime, examStartTime, examEndTime });
      String[] ss = { sql, sqlId };
      return ss;
    }

    sql = String.format("update BASE_SYLLABUS_ARRANGE set EXAMINATION_START_TIME='%1$s',EXAMINATION_END_TIME='%2$s',WEEK='%3$s' where SYLLABUS_ARRANGE_ID=%4$s", new Object[] { 
      examStartTime, examEndTime, s_week[week], id });
    sqlId = "select " + id + " from dual";
    String[] ss = { sql, sqlId };
    return ss;
  }

  public String[] saveArrangeHand(String syllabusArrangeId, String year, String month, String day, String examinationTime, String beginTime, String endTime)
  {
    String sqlId = ""; String sql = "";
    String[] s_week = { "周五", "周六", "周日", "周一", "周二", "周三", "周四" };
    Calendar c = Calendar.getInstance();
    c.set(1, Integer.parseInt(year));
    c.set(2, Integer.parseInt(month));
    c.set(5, Integer.parseInt(day));
    int week = c.get(7) - 1;
    if (syllabusArrangeId.equals("")) {
      sqlId = "select SYLLABUS_ARRANGE_ID from BASE_SYLLABUS_ARRANGE where YEAR=%1$s and MONTH=%2$s and DAY=%3$s and EXAMINATION_TIME='%4$s'";
      sqlId = String.format(sqlId, new Object[] { year, month, day, examinationTime });
      String id = super.getFirstCol(sqlId, new Object[0]);
      if ((StringTool.isEmpty(id)) || (Integer.parseInt(id) < 1)) {
        sql = String.format("insert into BASE_SYLLABUS_ARRANGE(SYLLABUS_ARRANGE_ID,YEAR,MONTH,DAY,WEEK,EXAMINATION_TIME,EXAMINATION_START_TIME,EXAMINATION_END_TIME)values(SEQ_SYLLABUS_ARRANGE.nextval,%1$s,%2$s,%3$s,'%4$s','%5$s','%6$s','%7$s')", new Object[] { 
          year, month, day, s_week[week], examinationTime, beginTime, endTime });
        String[] ss = { sql, sqlId };
        return ss;
      }

      sql = String.format("update BASE_SYLLABUS_ARRANGE set EXAMINATION_START_TIME='%1$s',EXAMINATION_END_TIME='%2$s',WEEK='%3$s' where SYLLABUS_ARRANGE_ID=%4$s", new Object[] { 
        beginTime, endTime, s_week[week], id });
      sqlId = "select " + id + " from dual";
      String[] ss = { sql, sqlId };
      return ss;
    }

    sql = String.format("update BASE_SYLLABUS_ARRANGE set EXAMINATION_START_TIME='%1$s',EXAMINATION_END_TIME='%2$s',WEEK='%3$s' where SYLLABUS_ARRANGE_ID=%4$s", new Object[] { 
      beginTime, endTime, s_week[week], syllabusArrangeId });
    sqlId = "select " + syllabusArrangeId + " from dual";
    String[] ss = { sql, sqlId };
    return ss;
  }

  public Long countArrange(String examTimeCode, String menutype)
  {
    String sql = "select count(*) from BASE_SYLLABUS_TIME where EXAMINATION_TIME_CODE=%1$s and COUNTRY_PROVINCE='%2$s'";
    return Long.valueOf(super.getNum(String.format(sql, new Object[] { examTimeCode, menutype }), new String[0]));
  }

  public String deleteArrange(String examTimeCode, String menutype) {
    String sql = "delete from BASE_SYLLABUS_TIME where EXAMINATION_TIME_CODE=%1$s and COUNTRY_PROVINCE='%2$s'";
    return String.format(sql, new Object[] { examTimeCode, menutype });
  }

  public String getMaxYear() {
    String sql = "select max(YEAR) from BASE_SYLLABUS_ARRANGE";
    String year = super.getFirstCol(sql, new Object[0]);
    if (year == null) {
      Date date = new Date();
      DateFormat format = new SimpleDateFormat("yyyy");
      year = format.format(date);
    }
    return year;
  }
}