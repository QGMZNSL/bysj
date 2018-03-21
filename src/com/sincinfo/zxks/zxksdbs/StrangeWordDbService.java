package com.sincinfo.zxks.zxksdbs;

import java.lang.reflect.Method;

import com.sincinfo.zxks.bean.BaseStrangeWord;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Log;

/**
 * @see 生僻字管理 业务处理类
 * @author guanm
 * 
 */
public class StrangeWordDbService extends DbUtil {
	/**
	 * @see 添加生僻字
	 */
	public String doadd(BaseStrangeWord bean,OperatLog log) {
		String info = null;
		if (bean == null) {
			return info = "对象未创建!";
		}
		bean=this.trimObject(BaseStrangeWord.class, bean);
		String selsql = "select count(*) from base_strange_word where strange_word=?";
		long count = super.getNum(selsql, bean.getStrangeWord());
		if (count > 0) {
			return info = "该字已添加!";
		}
		String inssql = "insert into base_strange_word values(seq_word.nextval,?,?)";
		int k = super.saveOrUpdate(inssql, bean.getStrangeWholeWord(), bean
				.getStrangeWord());
		if (k < 1) {
			return info = "服务器繁忙!";
		}
		log.setLogOptSql(inssql);
		OperateLogTool.saveOptLog(log);
		return info;

	}

	/**
	 * @see 搜索 count
	 */
	public long getResCount(String selmethod, String where) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from (");
		sql.append(this.getResSql(selmethod, where));
		sql.append(")");
		return super.getNum(sql.toString());
	}

	/**
	 * @see 搜索 sql
	 */
	public String getResSql(String selmethod, String where) {
		StringBuilder sql = new StringBuilder();
		if (selmethod.equals("shouzimu")) {// 按首字母搜索
			if (where != null && where.equals("1")) {// 所有
				sql.append("select w.* from base_strange_word w ");
				sql.append(" order by ");
				sql.append(" strange_whole_word");
			} else {// 单个
				sql.append("select w.* from base_strange_word w ");
				sql.append(" where substr(w.strange_whole_word, 1, 1)='%1$s'");
				sql.append(" order by ");
				sql.append(" strange_whole_word");
			}
		} else if (selmethod.equals("pinyin")) {// 按拼音搜索
			sql.append("select w.* from base_strange_word w ");
			sql.append(" where w.strange_whole_word='%1$s'");
		}
		return String.format(sql.toString(), where);
	}

	/**
	 * @see 根据id获得生僻字对象
	 */
	public BaseStrangeWord getBaseStrangeWordById(String strangeId) {
		String sql = "select w.* from base_strange_word w where w.strange_word_id=%1$s";
		return super.getObject(String.format(sql, strangeId),
				BaseStrangeWord.class);

	}

	/**
	 * @see do修改
	 */
	public String doedit(BaseStrangeWord bean,OperatLog log) {
		String info = null;
		if (bean == null) {
			return info = "对象没有创建!";
		}
		bean=this.trimObject(BaseStrangeWord.class, bean);
		String selsql = "select count(*) from base_strange_word where strange_word_id!=? and strange_word=? ";
		long count = super.getNum(selsql,bean.getStrangeWordId(), bean.getStrangeWord());
		if (count > 0) {
			return info = "该字已添加!";
		}
		String sql="update base_strange_word set strange_whole_word='%1$s',strange_word='%2$s' where strange_word_id=%3$s";
		int k=super.saveOrUpdate(String.format(sql, bean.getStrangeWholeWord(),bean.getStrangeWord(),bean.getStrangeWordId()));
		if(k<1){
			return info="服务器繁忙!";
		}
		log.setLogOptSql(sql);
		OperateLogTool.saveOptLog(log);
		return info;
	}
	/**
	 * @see 去掉bean中String属性中空格
	 * @param <T>
	 * @param cls
	 * @param bean
	 * @return
	 */
	public <T> T trimObject(Class<T> cls, T bean) {
		Method[] ms = cls.getDeclaredMethods();
		try {
			for (Method md : ms) {
				if (md.getName().startsWith("get")) {
					if (md.getParameterTypes().length > 0) {
						continue;
					}
					if(md.getReturnType()!=String.class){
						continue;
					}
					String setName = "set" + md.getName().substring(3);

					Method setMd = cls.getMethod(setName, String.class);
					if (md.invoke(bean) != null) {
						setMd.invoke(bean, ((String) md.invoke(bean)).trim());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new Log().error(this.getClass(),"去空格有误！",e);
		} 
		return bean;
	}
}