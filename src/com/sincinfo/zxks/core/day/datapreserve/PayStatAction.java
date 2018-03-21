
/**----------------------------------------------------------------
/*文件名：RefundAction.java
/*文件功能描述：退考审核Action类
/*
/*
/*创建者：Wucf
/*创建时间：2010-09-28
/**----------------------------------------------------------------*/
package com.sincinfo.zxks.core.day.datapreserve;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.ibm.wsdl.Constants;
import com.sincinfo.zxks.bean.BaseStipendUnit;
import com.sincinfo.zxks.bean.BaseStudentSiteUp;
import com.sincinfo.zxks.bean.BuyReturnVo;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

import jxl.HeaderFooter;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

public class PayStatAction extends WebActionSupport {
	private static final long serialVersionUID = 1L;
	private final String YEEPAY_PATH = "yeepay";
	private final String CHILD_PATH = "record2";
	private DbUtil dbUtil = new DbUtil();// 数据库处理类
	private Page page = new Page();// 分页
	private List<String[]> baseBreeauList;// 报考地市列表
	private String regionaLism;
	private String startTime;
	private String endTime;
	private List<String[]> payInfoList;// 查询结果集合
	private List<String[]> payInfoDateList;// 指定日期内的查询结果集合
	private String idno;// 身份证号
	private String serialCode;// 报名注册号
	private String name;// 姓名
	private String payid;// 网上反馈号
	private String excelUrl;
	private List<BaseStipendUnit> units;
	private String unitCode;
	private String subjectCount;
	private String studentCount;
	private String totalMoney;
	
	private List<String[]> personPayList;// 指定注册号交费全记录

	private List<String[]> payInfoList2;// 查询点击了“网上交费”但交费没有成功用户！

	private File uploadFile;// 上传文件

	private String queryDate;// 查询日期

	// 对账功能
	private List<String[]> errInfoList = null;// 对账错误信息集合
												// 对账的csv文件有的用户订单号但本地没有（这种情况理论上是不可能出现的，除非易宝给我们发的对账文件有问题）

	private String dzTotalMoney;// 对账总金额
	private String sjTotalMoney;// 实际总金额
	private String tfTotalMoney;// 退费金额
	private String xcTotalMoney;// 相差金额

	private String regionaLismValue;// 详细查询的行政区划

	private String downloadPath = "";// 下载地址
	private String phyPath = "";// 物理路径
	private final String PAY_RECORD_EXCEL = "payexcel";

	/**
	 * 进入查询列表
	 * 
	 * @return
	 */
	public String list() {
		try {
			// 行政区划列表
			StringBuffer sqlBuf = new StringBuffer(
					"select t.regionalism,t.regionname from BASE_BREEAU t where t.parentregion='610000' ");
			if (!"610000".equals(this.session.get("gUserRegion"))) {
				sqlBuf.append(" and t.regionalism='");
				sqlBuf.append(this.session.get("gUserRegion"));
				sqlBuf.append("' ");
			}
			sqlBuf.append(" and t.regionalism!='610001' ");
			sqlBuf.append(" order by t.regionalism ");
			baseBreeauList = dbUtil.getRsArrayList(sqlBuf.toString(), 2);

			// 如果起始日期或截止日期为空，则默认为当前日期
			if (StringTool.isEmpty(startTime)) {
				startTime = StringTool.getDate(new Date(), "yyyy-MM-dd");
			}
			if (StringTool.isEmpty(endTime)) {
				endTime = StringTool.getDate(new Date(), "yyyy-MM-dd");
			}

			// 是否是第一次进入
			String type = this.request.getParameter("type");
			if ("1".equals(type)) {
				StringBuffer sql = null;
				if (StringTool.isEmpty(serialCode) && StringTool.isEmpty(idno)
						&& StringTool.isEmpty(name)
						&& StringTool.isEmpty(payid)) {
					if (regionaLism == null) {
						this.GoBack("您没有权限查询！");
						return null;
					}

					sql = new StringBuffer();// 查询数据

					sql.append(" select tt.ndate,tt.premoney,nvl(tt.tkmoney,0),tt.money,tt.pnum,tt.pnum-tt.tnum from  (select d.ndate ndate, ");
					sql.append(" (select count(distinct p.examineekey) from base_examinee_pay p where to_char(p.paytime,'yyyy-MM-dd')=d.ndate  ");
					if (!StringTool.isEmpty(regionaLism)) {
						sql.append(" and (select regionalism from base_examinee pe where pe.examineekey=p.examineekey)='");
						sql.append(regionaLism);
						sql.append("' ");
					}
					sql.append(") pnum, ");
					sql.append(" (select count(distinct p.examineekey) from base_examinee_pay p where to_char(p.paytime,'yyyy-MM-dd')=d.ndate and p.isrefund!='9' ");
					if (!StringTool.isEmpty(regionaLism)) {
						sql.append(" and (select regionalism from base_examinee pe where pe.examineekey=p.examineekey)='");
						sql.append(regionaLism);
						sql.append("' ");
					}
					sql.append(") tnum,");
					sql.append(" (select sum(p.paymoney) from base_examinee_pay p where to_char(p.paytime,'yyyy-MM-dd')=d.ndate ");
					if (!StringTool.isEmpty(regionaLism)) {
						sql.append(" and (select regionalism from base_examinee pe where pe.examineekey=p.examineekey)='");
						sql.append(regionaLism);
						sql.append("'");
					}
					sql.append(") premoney,");
					sql.append(" (select sum(p.paymoney) from base_examinee_pay p where to_char(p.paytime,'yyyy-MM-dd')=d.ndate ");
					if (!StringTool.isEmpty(regionaLism)) {
						sql.append(" and (select regionalism from base_examinee pe where pe.examineekey=p.examineekey)='");
						sql.append(regionaLism);
						sql.append("'");
					}
					sql.append(" and p.isrefund='9' ) tkmoney,");
					sql.append(" (select sum(p.paymoney) from base_examinee_pay p where to_char(p.paytime,'yyyy-MM-dd')=d.ndate ");
					if (!StringTool.isEmpty(regionaLism)) {
						sql.append(" and (select regionalism from base_examinee pe where pe.examineekey=p.examineekey)='");
						sql.append(regionaLism);
						sql.append("' ");
					}
					sql.append(" and p.isrefund in('0','1') ) money ");
					sql.append(" from ((select distinct to_char(t.paytime,'yyyy-MM-dd') as ndate from base_examinee_pay t order by to_char(t.paytime,'yyyy-MM-dd')) )d ");
					sql.append(" where d.ndate>='");
					sql.append(startTime);
					sql.append("' and d.ndate<='");
					sql.append(endTime);
					sql.append("' )tt where tt.money is not null");

					List<String[]> payInfoTempList = dbUtil.getRsArrayList(
							sql.toString(), 6);
					String money = "";
					double[] hejiStrs = new double[5];
					for (String[] strs : payInfoTempList) {
						money = StringTool.getMoneyNum(strs[1]);
						strs[1] = money;
						money = StringTool.getMoneyNum(strs[2]);
						strs[2] = money;
						money = StringTool.getMoneyNum(strs[3]);
						strs[3] = money;

						hejiStrs[0] += Double.valueOf(strs[1]);
						hejiStrs[1] += Double.valueOf(strs[2]);
						hejiStrs[2] += Double.valueOf(strs[3]);
						hejiStrs[3] += Double.valueOf(strs[4]);
						hejiStrs[4] += Double.valueOf(strs[5]);
					}

					// 求合计项
					if (payInfoTempList == null || payInfoTempList.size() == 0) {
						payInfoList = null;
					} else {
						String[] strs = new String[6];
						strs[0] = "合计";
						strs[1] = StringTool.getMoneyNum(String
								.valueOf(hejiStrs[0]));
						strs[2] = StringTool.getMoneyNum(String
								.valueOf(hejiStrs[1]));
						strs[3] = StringTool.getMoneyNum(String
								.valueOf(hejiStrs[2]));
						strs[4] = String.valueOf(hejiStrs[3]).substring(0,
								String.valueOf(hejiStrs[3]).indexOf("."));
						strs[5] = String.valueOf(hejiStrs[4]).substring(0,
								String.valueOf(hejiStrs[4]).indexOf("."));
						payInfoTempList.add(strs);
						payInfoList = payInfoTempList;
					}

					// 个人查询集合设置为空
					personPayList = null;
				} else {
					sql = new StringBuffer("");
					sql.append(" select substr(t.examineekey,6,12), ");
					sql.append(" (select p.names from base_exam_apply p where t.examineekey=p.examyear||p.serialcode) names, ");
					sql.append("(select p.sname from base_source p where p.typecode='DM-BANK' and p.scode=t.bankid),");
					sql.append(" t.payid,t.paymoney, ");
					sql.append(" (select p.exammoney from base_exam_apply p where t.examineekey=p.examyear||p.serialcode) exammoney, ");
					sql.append(" to_char(t.paytime,'yyyy-MM-dd hh24:mi:ss'),t.isrefund, ");
					sql.append(" (select p.idno from base_exam_apply p where t.examineekey=p.examyear||p.serialcode) idno,");
					sql.append(" (select p.regionname from base_examinee p where p.examineekey=t.examineekey) regionname,");
					sql.append(" (select p.sitename from base_examinee p where p.examineekey=t.examineekey) sitename ");
					sql.append(" from base_examinee_pay t where 1=1 ");
					if (!StringTool.isEmpty(payid)) {
						sql.append(" and t.payid='");
						sql.append(payid.trim().replaceAll("'", "")
								.replaceAll("\"", ""));
						sql.append("'");
					}
					sql.append(" and t.examineekey in (select p.examyear||p.serialcode from base_exam_apply p where 1=1 ");
					if (!StringTool.isEmpty(idno)) {// 身份证号
						sql.append(" and p.idno='");
						sql.append(idno.trim().replaceAll("'", "")
								.replaceAll("\"", ""));// 去除单引号，双引号
						sql.append("' ");
					}
					if (!StringTool.isEmpty(serialCode)) {// 注册号
						sql.append(" and p.serialcode='");
						sql.append(serialCode.trim().replaceAll("'", "")
								.replaceAll("\"", ""));
						sql.append("' ");
					}
					if (!StringTool.isEmpty(name)) {// 姓名
						sql.append(" and p.names like'%");
						sql.append(name.trim().replaceAll("'", "")
								.replaceAll("\"", ""));
						sql.append("%' ");
					}
					if (regionaLism != null && regionaLism.length() == 6) {// 行政区划
						sql.append(" and p.regionalism='");
						sql.append(regionaLism);
						sql.append("'");
					}
					sql.append(")");

					List<String[]> personPayTempList = dbUtil.getRsArrayList(
							sql.toString(), 11);
					personPayList = new ArrayList<String[]>();
					String money = "";
					for (String[] strs : personPayTempList) {
						money = StringTool.getMoneyNum(strs[4]);
						strs[4] = money;
						money = StringTool.getMoneyNum(strs[5]);
						strs[5] = money;
						personPayList.add(strs);
					}

					// 按日期统计查询集合设置为空
					payInfoList = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	/**
	 * 指定日期交费明细
	 * 
	 * @return
	 */
	public String infoList() {
		// 查询日期
		String queryDate = this.request.getParameter("queryDate");
		regionaLism = this.request.getParameter("regionaLism");// 行政区划
		try {
			// 查询
			StringBuffer sqlCount = new StringBuffer();// 查询数据条数
			StringBuffer sql = new StringBuffer();// 查询数据
			StringBuffer url = new StringBuffer();// 链接地址

			// 查询语句
			sqlCount.append(" select count(*) ");
			sqlCount.append(" from base_examinee_pay t inner join base_examinee p on t.examineekey=p.examineekey ");
			sqlCount.append(" where to_char(t.paytime,'yyyy-MM-dd')='");
			sqlCount.append(queryDate);
			sqlCount.append("' ");
			if (!StringTool.isEmpty(regionaLism)) {
				sqlCount.append(" and substr(t.examineekey,6,2)='");
				sqlCount.append(regionaLism.substring(2, 4));
				sqlCount.append("'");
			}
			sqlCount.append(" order by t.paytime desc  ");

			sql.append(" select substr(t.examineekey,6,12), ");
			sql.append(" p.names,p.idno,p.examtypecode,t.payid,t.paymoney,to_char(t.paytime,'yyyy-MM-dd hh24:mi:ss') , ");
			sql.append(" t.isrefund,p.exammoney, ");
			sql.append(" (select p.sname from base_source p where p.typecode='DM-BANK' and p.scode=t.bankid) bankname,  ");
			sql.append(" p.regionname,p.sitename  ");
			sql.append(" from base_examinee_pay t inner join base_examinee p on t.examineekey=p.examineekey ");
			sql.append(" where to_char(t.paytime,'yyyy-MM-dd')='");
			sql.append(queryDate);
			sql.append("' ");
			if (!StringTool.isEmpty(regionaLism)) {
				sql.append(" and substr(t.examineekey,6,2)='");
				sql.append(regionaLism.substring(2, 4));
				sql.append("'");
			}
			sql.append(" order by t.paytime desc  ");

			url.append("/ACC/wsbm/kb/examsite/payStat_infoList.do?queryDate=");
			url.append(queryDate);
			url.append("&regionaLism=");
			url.append(regionaLism);

			page.setPath(url.toString());
			page.setPagesize(15);// 每页显示15行

			// 展示数据库查询
			String sqlPage = page.setPagecount(
					dbUtil.getNum(sqlCount.toString()), sql.toString());

			// 获取查询结果，并将其中的金额数据转换为指定的金额格式
			List<String[]> payInfoDateTempList = dbUtil.getRsArrayList(sqlPage,
					12);
			payInfoDateList = new ArrayList<String[]>();
			String money = "";
			for (String[] strs : payInfoDateTempList) {
				money = StringTool.getMoneyNum(strs[5]);
				strs[5] = money;
				money = StringTool.getMoneyNum(strs[8]);
				strs[8] = money;
				payInfoDateList.add(strs);
			}
			regionaLismValue = regionaLism;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "queryPay";
	}

	/**
	 * 导出指定日期交费明细
	 * 
	 * @return
	 */
	public void infoListReport() {


			// 生成报表
			boolean result = true;
			String fileName = StringTool
					.getDate(new Date(), "yyyyMMddHHmmssSS") + ".xls";
			String[] paths = dbUtil.getPaths();
			String fileSeparator = System.getProperty("file.separator");
			String userName = this.getCOperUser().getUserName();

			// 如果路径不存在，则创建
			File file = new File(paths[0] + PAY_RECORD_EXCEL + fileSeparator
					+ userName + fileSeparator);
			if (!file.exists()) {
				result = file.mkdirs();
			}
			String resultStr = "";
			if (result) {
				// 获取首页文件路径
				phyPath = paths[0] + PAY_RECORD_EXCEL + fileSeparator
						+ userName + fileSeparator + fileName;
				// 首页文件访问路径
				downloadPath = paths[1] + PAY_RECORD_EXCEL + "/" + userName
						+ "/" + fileName;

				resultStr = exportWorkBook();
				resultStr = "0";
				excelUrl=downloadPath;
			}

//			// 返回结果字符串
//			PrintWriter out = null;
//			try {
//				response.setContentType("text/html;charset=GBK");
//				out = response.getWriter();
//				if ("0".equals(resultStr)) {// 生成报表成功！并返回下载路径地址
//					out.print("<a href='" + downloadPath
//							+ "' target='_blank'>下载</a>");
//				} else if ("1".equals(resultStr)) {// 没有查询到数据
//					out.print("1");
//				} else if ("2".equals(resultStr)) {// 出现异常
//					out.print("2");
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if (out != null) {
//					out.close();
//				}
//			}
	}

	public static String[] ErrorTypes=new String[]{"","系统不存在此订单","系统中金额不一致","系统中时间不一致","科目数与订单创建时不符"};
	/**
	 * 生成报表
	 * 
	 * @param eiList
	 * @return
	 */
	private String exportWorkBook() {
		// 初始化
		jxl.write.WritableWorkbook wwb = null;
		jxl.write.WritableSheet ws = null;
		BufferedOutputStream os = null;
		String result = "";
		int maxline = 10000;
		// 生成文件
		try {
			os = new BufferedOutputStream(new FileOutputStream(phyPath));
			wwb = Workbook.createWorkbook(os);
			/*
			 * 将数据写入文件
			 */
			Label aCell = null;
			int i = 0;
			// 内容
			int row = 1;
			int sheet = 1;
			int totalcount = 0;
			int allNums = 0;// 总记录条数
			for(String[] ss:errInfoList) {
				allNums++;
				if (ws == null || (totalcount % maxline == 0)) {
					// sheet名添加
					String sheetName = "";
					sheetName = "交费明细查询";
					ws = wwb.createSheet(sheetName + sheet++, 0);

					this.setHeaderFooter(20, sheetName, ws,
							PageOrientation.LANDSCAPE);// 设置页眉页脚 打印格式

					i = 0;
					ws.setColumnView(0, 5); // 设置列的宽度
					ws.setColumnView(1, 10); // 设置列的宽度
					ws.setColumnView(2, 10); // 设置列的宽度
					ws.setColumnView(3, 13); // 设置列的宽度
					ws.setColumnView(4, 7); // 设置列的宽度
					ws.setColumnView(5, 19); // 设置列的宽度
					ws.setColumnView(6, 6); // 设置列的宽度

					// 文字样式
					WritableCellFormat format = this.getWritableCellFormat("1");// 得到标题栏的单元格样式

					aCell = new Label(i++, 0, "序号", format);
					ws.addCell(aCell);
					aCell = new Label(i++, 0, "错误类型", format);
					ws.addCell(aCell);
					aCell = new Label(i++, 0, "准考证号", format);
					ws.addCell(aCell);
					aCell = new Label(i++, 0, "订单号", format);
					ws.addCell(aCell);
					aCell = new Label(i++, 0, "本地金额（元）", format);
					ws.addCell(aCell);
					aCell = new Label(i++, 0, "对账文件金额（元）", format);
					ws.addCell(aCell);
					aCell = new Label(i++, 0, "交费时间", format);
					ws.addCell(aCell);

					ws.getSettings().setPrintTitles(0, 0, 10, 0);// 打印页面都显示的导航行"序号 档案号 ..."

					row = 1;
				}
				i = 0;

				WritableCellFormat format2 = this.getWritableCellFormat("2");// 得到内容的单元格样式

				aCell = new Label(i++, row, String.valueOf(allNums), format2);
				ws.addCell(aCell);
				aCell = new Label(i++, row, ErrorTypes[Integer.parseInt(ss[5])], format2);
				ws.addCell(aCell);
				aCell = new Label(i++, row, ss[0], format2);
				ws.addCell(aCell);
				aCell = new Label(i++, row, ss[1], format2);
				ws.addCell(aCell);
				aCell = new Label(i++, row, ss[2], format2);
				ws.addCell(aCell);
				aCell = new Label(i++, row, ss[3], format2);
				ws.addCell(aCell);
				aCell = new Label(i++, row, ss[4], format2);
				ws.addCell(aCell);
				row++;
				totalcount++;
			}
			if (allNums == 0) {// 没有数据，则返回标识符：1
				result = "1";
			} else {// 有数据，则将文件写入硬盘
				wwb.write();
				result = "0";// 成功
			}
		} catch (Exception e) {
			result = "2";// 异常
		} finally {
			try {
				if (wwb != null) {
					wwb.close();
				}
			} catch (Exception e) {

			}
			try {
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {

			}
		}
		return result;
	}

	/**
	 * 设置页眉页脚问题
	 * 
	 * @param fontSize
	 *            ：字体大小
	 * @param sheetName
	 *            ：页眉内容
	 * @param ws
	 *            ：WritableSheet对象
	 * @param pageOrientation
	 *            ：打印格式
	 */
	private void setHeaderFooter(int fontSize, String sheetName,
			WritableSheet ws, PageOrientation pageOrientation) {
		HeaderFooter header = new HeaderFooter();// 页眉
		HeaderFooter footer = new HeaderFooter();// 页脚

		header.getCentre().setFontSize(fontSize);// 设置页眉字体大小
		header.getCentre().toggleBold();// 设置页眉字体加粗
		header.getCentre().append(sheetName); // 页眉内容

		footer.getCentre().append("- 第 &P 页 -");// 页脚内容 &p会自动提取页数

		ws.getSettings().setHeader(header); // 加入页眉
		ws.getSettings().setFooter(footer); // 加入页脚

		ws.getSettings().setOrientation(pageOrientation);// 设置打印样式
	}

	/**
	 * 得到类型
	 * 
	 * @param type
	 * @return
	 */
	private WritableCellFormat getWritableCellFormat(String type) {
		WritableCellFormat format = null;
		try {
			if ("1".equals(type)) {
				// 文字样式
				WritableFont fontStyle = new WritableFont(WritableFont.ARIAL,
						10, WritableFont.BOLD, false,
						UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK); // 定义格式
																				// 字体
																				// 下划线
																				// 斜体
																				// 粗体
																				// 颜色
				format = new WritableCellFormat(fontStyle); // 单元格定义
				format.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
				format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format.setBorder(Border.ALL, BorderLineStyle.THIN);
				format.setWrap(true);// 自动换行
			} else if ("2".equals(type)) {
				format = new WritableCellFormat(); // 单元格定义
				format.setAlignment(jxl.format.Alignment.CENTRE);
				format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
				format.setWrap(true);// 自动换行
			}
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 进入查询列表
	 * 
	 * @return
	 */
	public String list2() {
		// 查询日期
		String queryDate = this.request.getParameter("queryDate");
		try {
			// 查询
			StringBuffer sqlCount = new StringBuffer();// 查询数据条数
			StringBuffer sql = new StringBuffer();// 查询数据
			StringBuffer url = new StringBuffer();// 链接地址

			// 查询语句
			sqlCount.append(" select count(*) from  base_examinee t where  ");
			sqlCount.append(" t.examineekey in (select distinct p.examineekey from base_exam_prepay p) and ");
			sqlCount.append(" t.examineekey not in(select distinct p.examineekey from base_examinee_pay p where p.isrefund='0' ) ");

			sql.append(" select t.serialcode,t.names,t.regionname,t.sitename,t.examtypecode,t.examsubjectcode,t.idno, ");
			sql.append(" (select to_char(max(p.ptime),'yyyy-MM-dd hh24:mi:ss') from base_exam_prepay p where p.examineekey=t.examineekey) ");
			sql.append(" from  base_examinee t where  ");
			sql.append(" t.examineekey in (select distinct p.examineekey from base_exam_prepay p) and ");
			sql.append(" t.examineekey not in(select distinct p.examineekey from base_examinee_pay p where p.isrefund='0' ) ");

			url.append("/ACC/wsbm/kb/paystat/payStat_list2.do?1=1");
			url.append(queryDate);

			page.setPath(url.toString());

			page.setPagesize(1);
			// 展示数据库查询
			String sqlPage = page.setPagecount(
					dbUtil.getNum(sqlCount.toString()), sql.toString());

			// 获取查询结果，并将其中的金额数据转换为指定的金额格式
			payInfoList2 = dbUtil.getRsArrayList(sqlPage, 8);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "list2";
	}

	/**
	 * 
	 * @return
	 */
	public void upload() {
		DbUtil dbUtil = new DbUtil();
		String uploadpath = request.getParameter("uploadpath");
		uploadpath = uploadpath == null ? "" : uploadpath.trim();
		String downFileName = "";
		// 文件后缀名
		// 上传文件路径
		if (!"".equals(uploadpath)) {
			// 将上传路径的 '\' 进行转换
			downFileName = uploadpath.substring(
					uploadpath.lastIndexOf("\\") + 1, uploadpath.length())
					.toLowerCase();

			StringBuffer queryDateBuf = new StringBuffer(
					downFileName.substring(0, 4));
			queryDateBuf.append("-");
			queryDateBuf.append(downFileName.substring(4, 6));
			queryDateBuf.append("-");
			queryDateBuf.append(downFileName.substring(6, 8));
			queryDate = queryDateBuf.toString();

			// 获取上传路径
			String uploadPath = dbUtil.getPaths()[0] + YEEPAY_PATH
					+ System.getProperty("file.separator") + CHILD_PATH
					+ System.getProperty("file.separator");
			// 如果文件夹不存在则创建
			File dir = new File(uploadPath);
			if (!dir.exists()) {
				dir.mkdir();
			}
			boolean result = write(uploadPath, downFileName, uploadFile);
			if (result) {
				this.PostJs("alert('上传成功！');location.href='accountManager.jsp?queryDate="
						+ queryDate + "';");
				return;
			} else {
				this.GoBack("上传失败！");
				return;
			}
		} else {
			this.GoBack("没有要上传的文件！");
			return;
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param targetPath
	 *            写入路径
	 * @param fileName
	 *            写入文件名
	 * @param srcFile
	 *            源文件
	 * @return true：写入成功 false：写入失败
	 */
	public static boolean write(String targetPath, String fileName, File srcFile) {
		BufferedInputStream bis = null;
		FileOutputStream bos = null;
		boolean result = true;

		try {
			// 如果路径不存在，则创建
			File dir = new File(targetPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// 写入文件
			byte[] by = null;

			bis = new BufferedInputStream(new FileInputStream(srcFile));
			bos = new FileOutputStream(targetPath + fileName);
			by = new byte[4096];
			int n = 0;
			while (true) {
				n = bis.read(by);
				if (n == -1) {
					break;
				}
				bos.write(by, 0, n);
			}
		} catch (Exception e) {
			// 写入失败
			e.printStackTrace();
			result = false;
		} finally {
			// 关闭流
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * 
	 * @return
	 */
	public String queryPay() {
		BufferedReader br = null;
		try {
			if (StringTool.isEmpty(queryDate)) {
				this.GoBack("请输入您的查询日期！");
				return null;
			}

			// 获得文件名称
			String fileName = queryDate.replaceAll("-", "") + ".csv";
			String uploadPath = dbUtil.getPaths()[0] + YEEPAY_PATH
					+ System.getProperty("file.separator") + CHILD_PATH
					+ System.getProperty("file.separator");
			File file = new File(uploadPath + fileName);
			if (!file.exists()) {
				this.GoBack("对账文件不存在，请上传您要对账日期的对账文件！");
				return null;
			}

			File csv = new File(uploadPath + fileName); // CSV文件
			br = new BufferedReader(new FileReader(csv));

			// 读取直到最后一行
			String line = "";
			br.readLine();// 去掉第一行
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = null;
			int i = 0;
			String strTemp = "";
			double dzTotalTempMoney = 0.0;
			StringTokenizer st = null;
			while ((line = br.readLine()) != null) {
				// 把一行数据分割成多个字段
				st = new StringTokenizer(line, ",");
				map = new HashMap<String, String>();
				i = 1;
				while (st.hasMoreTokens()) {
					// 每一行的多个字段用TAB隔开表示
					// 分
					strTemp = st.nextToken();
					if (strTemp != null) {
						strTemp = strTemp.trim();
					} else {
						strTemp = "";
					}
					if (i == 1) {// 1.商户订单号
						map.put("productId", strTemp);
					} else if (i == 2) {// 2.银行订单号
						map.put("bankProductId", strTemp);
					} else if (i == 3) {// 3.易宝订单号
						map.put("yeePayId", strTemp);
					} else if (i == 4) {// 4.订单金额
						map.put("money", strTemp);
					} else if (i == 5) {// 5.支付时间
						map.put("payTime", strTemp);
					} else if (i == 6) {// 6.商品名称
						map.put("productName", strTemp.replaceAll("\"", ""));
					} else if (i == 7) {// 7.扩展信息
						map.put("extend", strTemp.replaceAll("\"", ""));
					} else if (i == 8) {// 8.支付银行
						map.put("bankId", strTemp);
					} else if (i == 9) {// 9.账明细
						map.put("paDetail",
								strTemp != null && !"null".equals(strTemp) ? strTemp
										: "");
					}
					i++;
				}

				if (com.sincinfo.zxks.common.Constants.PID_PAY
						.equalsIgnoreCase(map.get("productName"))) {
					list.add(map);
					dzTotalTempMoney += Double.valueOf(map.get("money"));
				}
			}
			dzTotalMoney = StringTool.getMoneyNum(String
					.valueOf(dzTotalTempMoney));

			tryCheck(list);
			// 实际缴费总金额
			StringBuffer sqlB = new StringBuffer(" select ");
			sqlB.append(" (select sum(t.np_money) from base_netpay t where  to_char(t.np_pay_time,'yyyy-MM-dd')='");
			sqlB.append(queryDate);
			sqlB.append("'),");
			sqlB.append(" (select sum(t.np_money) from base_netpay t where  to_char(t.np_pay_time,'yyyy-MM-dd')='");
			sqlB.append(queryDate);
			sqlB.append("') from dual");
			String[] sts = dbUtil.getRsArray(sqlB.toString(), 2);
			if (sts != null && sts.length == 2) {
				if (sts[0] != null) {
					sjTotalMoney = StringTool.getMoneyNum(sts[0]);
				} else {
					sjTotalMoney = "0.00";
				}
				if (sts[1] != null) {
					tfTotalMoney = StringTool.getMoneyNum(sts[1]);
				} else {
					tfTotalMoney = "0.00";
				}
			}

			xcTotalMoney = StringTool.getMoneyNum(String.valueOf(Double
					.parseDouble(sjTotalMoney)
					- Double.parseDouble(dzTotalMoney)));

			// 可能存在的错误信息
			errInfoList = new ArrayList<String[]>();
			String[] strs = null;
			StringBuffer sqlBuf = null;
			StringBuffer errInfo = new StringBuffer();
//			StringBuilder orders = new StringBuilder();
//			for (Map<String, String> m : list) {
//				if (orders.length() > 0)
//					orders.append(",");
//				orders.append("'");
//				orders.append(m.get("productId"));
//				orders.append("'");
//			}
//
//			if (orders.length() == 0)
//				orders.append("'xxxxxxxx'");
//
//			String ordersInDbSql=String.format(
//					"select t.stud_exam_code,t.np_order,t.np_money,0,t.np_pay_time,0 from base_netpay t where  to_char(t.np_pay_time,'yyyy-MM-dd')='%1$s' or t.np_order in (%2$s)",
//					queryDate, orders.toString());
//			System.out.println(ordersInDbSql);
//			List<String[]> ordersInDb = dbUtil
//					.getRsArrayList(ordersInDbSql
//							, 6);

			for (Map<String, String> m : list) {
				String order = m.get("productId");
				String moneyerror=m.get("moneyerror");
				if("1".equals(moneyerror)){
					strs = new String[9];
					strs[0] = order.substring(8, 20);
					strs[1] = m.get("productId");
					strs[2] = StringTool.getMoneyNum(m.get("moneyindb"));
					strs[3] = StringTool.getMoneyNum(m.get("money"));
					strs[4] = m.get("payTime");
					strs[5] = "4";// 表示数据库里的科目数与订单创建时不符
					errInfoList.add(strs);
					errInfo.append(m.get("extend") + "\r\n");
					continue;
				}
				
				String payDate = m.get("payTime");
				String orderInDbSql=String.format(
						"select t.stud_exam_code,t.np_order,t.np_money,0,t.np_pay_time,0 from base_netpay t where  t.np_order ='%1$s'",
						order);
				System.out.println(orderInDbSql);
				List<String[]> ordersInDb = dbUtil
				.getRsArrayList(orderInDbSql
						, 6);
				String[] orderInDb =ordersInDb.size()==0? null:ordersInDb.get(0);
//				for (String[] ss : ordersInDb) {
//					if (ss[1].equals(order)) {
//						orderInDb = ss;
//						break;
//					}
//				}

				if (orderInDb == null) {
					orderInDbSql=String.format(
							"select t.stud_exam_code,t.np_order,t.np_money,0,'',0 from base_netpay_pre t where  t.np_order ='%1$s'",
							order);
					System.out.println(orderInDbSql);
					ordersInDb = dbUtil
					.getRsArrayList(orderInDbSql
							, 6);
					orderInDb =ordersInDb.size()==0? null:ordersInDb.get(0);

					if(orderInDb == null){
						strs = new String[9];
						strs[0] = order.substring(8, 20);
						strs[1] = m.get("productId");
						strs[2] = "0.00";
						strs[3] = StringTool.getMoneyNum(m.get("money"));
						strs[4] = m.get("payTime");
						strs[5] = "1";// 表示数据库里不存在
						errInfoList.add(strs);
						errInfo.append(m.get("extend") + "\r\n");
					}else{
						strs = new String[9];
						strs[0] = order.substring(8, 20);
						strs[1] = m.get("productId");
						strs[2] = StringTool.getMoneyNum("0.00");
						strs[3] = StringTool.getMoneyNum(m.get("money"));
						strs[4] = m.get("payTime");
						strs[5] = "4";// 表示数据库里的科目数与订单创建时不符
						errInfoList.add(strs);
						errInfo.append(m.get("extend") + "\r\n");
					}
				} else {
					orderInDb[3] = m.get("money");
					if (Double.parseDouble(orderInDb[2]) != Double
							.parseDouble(m.get("money"))) {
						orderInDb[5] = "2";// 表示数据库里金额与易宝不符
					} else if (orderInDb[4].startsWith(queryDate) == false) {
						orderInDb[5] = "3";// 表示数据库里日期与易宝不符
					}

					if ("0".equals(orderInDb[5]) == false) {
						errInfoList.add(orderInDb);
						errInfo.append(m.get("extend") + "\r\n");
					}
					// if(m.get("productId").length()==24) {
					// sqlBuf = new
					// StringBuffer("update base_exam_prepay t set t.recordstatus2='1',t.productid2='");
					// sqlBuf.append(m.get("productId"));
					// sqlBuf.append("',t.bankproductid='");
					// sqlBuf.append(m.get("bankProductId"));
					// sqlBuf.append("',t.yeepayid='");
					// sqlBuf.append(m.get("yeePayId"));
					// sqlBuf.append("',t.money='");
					// sqlBuf.append(m.get("money"));
					// sqlBuf.append("',t.paytime=to_date('");
					// sqlBuf.append(m.get("payTime"));
					// sqlBuf.append("','yyyy-MM-dd hh24:mi:ss'),t.productname='");
					// sqlBuf.append(m.get("productName"));
					// sqlBuf.append("',t.extendname='");
					// sqlBuf.append(m.get("extend"));
					// sqlBuf.append("',t.bankid='");
					// sqlBuf.append(m.get("bankId"));
					// sqlBuf.append("',t.padetail='");
					// sqlBuf.append((m.get("paDetail")!=null &&
					// !"null".equals(m.get("paDetail"))) ? m.get("paDetail") :
					// "");
					// sqlBuf.append("' where t.productid='");
					// sqlBuf.append(m.get("productId"));
					// sqlBuf.append("' ");

					// int result = dbUtil.saveOrUpdate(sqlBuf.toString());
					// if(!(result>0)){//
					// strs = new String[9];
					// strs[0] = m.get("productId");
					// strs[1] = m.get("bankProductId");
					// strs[2] = m.get("yeePayId");
					// strs[3] = m.get("money");
					// strs[4] = m.get("payTime");
					// strs[5] = m.get("productName");
					// strs[6] = m.get("extend");
					// strs[7] = m.get("bankId");
					// strs[8] = m.get("paDetail");
					// errInfoList.add(strs);
					// errInfo.append(m.get("extend")+"\r\n");
					// w.write(m.get("extend")+"\r\n");
					// }
				}

			}
			
			if(errInfoList.size()>0){
				infoListReport();
			}else{
				excelUrl="";
			}
			
			// 如果文件夹不存在则创建
			File dir = new File(uploadPath);
			if (!dir.exists()) {
				dir.mkdir();
			}

			FileWriter w = null;
			w = new FileWriter(new File(uploadPath + "tt.txt"), true);// 在后面添加
																		// 如果为false，则将每次都重写
			w.write(StringTool.getDate(new Date(), "yyyy-MM-dd HH:mm:ss")
					+ "\r\n");
			w.write(errInfo.toString());
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "yeepayStat";
	}

	BaseStudentSiteUp selectBaseStudentSiteUpByOrder(String order) {
		String sql = String
				.format("select t.city_code,t.exam_area_code,(select c.city_name from base_city c where c.city_code=t.city_code and rownum=1) city_name,(select a.exam_area_name from base_exam_area a where a.exam_area_code=t.exam_area_code and rownum=1) exam_area_name,count(*) subject_count,sum(t.stud_apply_money) total_money,t.examination_code from base_student_site_up t where t.np_order='%1$s' and t.stud_apply_payoff='0' group by  t.np_order,t.stud_exam_code,t.city_code,t.exam_area_code,t.examination_code",
						order);
		BaseStudentSiteUp r = dbUtil.getObject(sql, BaseStudentSiteUp.class);
		if (r != null && r.getTotalMoney().startsWith("."))
			r.setTotalMoney("0" + r.getTotalMoney());
		return r;
	}

	private void tryCheck(List<Map<String, String>> list) {
		int count=0;
		for (Map<String, String> m : list) {
			count++;
			if(count%50==0)
				System.out.println(count);
			String order = m.get("productId");
			String payDate = m.get("payTime");
			String money = m.get("money");

			// if (i == 1) {// 1.商户订单号
			// map.put("productId", strTemp);
			// } else if (i == 2) {// 2.银行订单号
			// map.put("bankProductId", strTemp);
			// } else if (i == 3) {// 3.易宝订单号
			// map.put("yeePayId", strTemp);
			// } else if (i == 4) {// 4.订单金额
			// map.put("money", strTemp);
			// dzTotalTempMoney += Double.valueOf(strTemp);
			// } else if (i == 5) {// 5.支付时间
			// map.put("payTime", strTemp);
			// } else if (i == 6) {// 6.商品名称
			// map.put("productName", strTemp.replaceAll("\"", ""));
			// } else if (i == 7) {// 7.扩展信息
			// map.put("extend", strTemp.replaceAll("\"", ""));
			// } else if (i == 8) {// 8.支付银行
			// map.put("bankId", strTemp);
			BuyReturnVo buyReturn = new BuyReturnVo();
			buyReturn.setR3_Amt(money);
			buyReturn.setR6_Order(order);
			buyReturn.setRb_BankId(m.get("bankId"));
			buyReturn.setRo_BankOrderId(m.get("bankProductId"));
			buyReturn.setRp_PayDate(m.get("payTime"));

			String stuexamcode = buyReturn.getR6_Order().substring(8, 20);
			BaseStudentSiteUp siteUp = selectBaseStudentSiteUpByOrder(buyReturn
					.getR6_Order());
			if (siteUp == null) {
				// isPaySuccess = "y";
				String update1 = String
					.format("update base_netpay t set t.NP_PAY_TIME=to_date('%2$s','yyyy-mm-dd hh24:mi:ss') where t.np_order='%1$s'",
							buyReturn.getR6_Order(),
							buyReturn.getRp_PayDate());
				String update2 = String
						.format("update base_student_site_up t set t.STUD_APPLY_PAY_TIME=to_date('%3$s','yyyy-mm-dd hh24:mi:ss') where t.np_order='%1$s'",
								buyReturn.getR6_Order(),
								buyReturn.getRo_BankOrderId(),
								buyReturn.getRp_PayDate());
				ArrayList<String> sqls = new ArrayList<String>();
				sqls.add(update1);
				sqls.add(update2);
				int transExeSqls = dbUtil.transExeSqls(sqls);
				String ls=String.valueOf(transExeSqls);
			} else if (Float.parseFloat(siteUp.getTotalMoney()) == Float
					.parseFloat(buyReturn.getR3_Amt())) {
				// 金额匹配，付款成功
				String insert = String
						.format("insert into base_netpay(NP_ORDER,STUD_EXAM_CODE,NP_BANK_ID,PAY_ID,NP_MONEY,NP_PAY_TIME) values('%1$s','%2$s','%3$s','%4$s',%5$s,to_date('%6$s','yyyy-mm-dd hh24:mi:ss'))",
								buyReturn.getR6_Order(), stuexamcode,
								buyReturn.getRb_BankId(),
								buyReturn.getRo_BankOrderId(), siteUp// this.baseSiteUp
										.getTotalMoney(),
								buyReturn.getRp_PayDate());
				String update1 = String
						.format("update base_student_site_up t set t.PAY_METHOD_CODE='1',t.stud_apply_payoff='1',PAY_ID='%2$s',t.STUD_APPLY_PAY_TIME=to_date('%3$s','yyyy-mm-dd hh24:mi:ss') where t.np_order='%1$s'",
								buyReturn.getR6_Order(),
								buyReturn.getRo_BankOrderId(),
								buyReturn.getRp_PayDate());
				String update2 = String
						.format("update BASE_NETPAY_PRE t set t.NP_PAY_STATUS='1' where t.np_order='%1$s'",
								buyReturn.getR6_Order(),
								buyReturn.getRo_BankOrderId());
				ArrayList<String> sqls = new ArrayList<String>();
				sqls.add(insert);
				sqls.add(update1);
				sqls.add(update2);
				int transExeSqls = dbUtil.transExeSqls(sqls);
				// if (transExeSqls > 0)
				// isPaySuccess = "y";
				// else
				// isPaySuccess = "s";
			} else {
				// 金额不匹配
				// isPaySuccess = "n";
				m.put("moneyerror", "1");
				m.put("moneyindb", siteUp.getTotalMoney());
			}
		}
	}

	public String bulkPre(){
		String sql=String.format("select * from base_stipend_unit t where t.city_code='%1$s'", this.getCOperUser().getCityCode());
		
		units=dbUtil.getObjList(sql, BaseStipendUnit.class);
		return "bulk_list";
	}

	public String bulkPreview(){
		String sql=String.format("select * from base_stipend_unit t where t.stipend_unit_code='%1$s'", unitCode);
		String countSql=String.format("select count(*) from base_student_site_up t left join base_student_info i on t.stud_exam_code=i.stud_exam_code where i.stipend_unit_code='%1$s' and t.stud_apply_payoff='0'  and t.examination_code=(select e.examination_code from base_examination e where e.nonce='1'  )", unitCode);
		String countStudentSql=String.format("select count(distinct t.stud_exam_code) from base_student_site_up t left join base_student_info i on t.stud_exam_code=i.stud_exam_code where i.stipend_unit_code='%1$s' and t.stud_apply_payoff='0'  and t.examination_code=(select e.examination_code from base_examination e where e.nonce='1'  )", unitCode);
		String totalMoneySql=String.format("select nvl(sum(t.stud_apply_money),0) from base_student_site_up t left join base_student_info i on t.stud_exam_code=i.stud_exam_code where i.stipend_unit_code='%1$s' and t.stud_apply_payoff='0'  and t.examination_code=(select e.examination_code from base_examination e where e.nonce='1'  )", unitCode);
		 
		units=dbUtil.getObjList(sql, BaseStipendUnit.class);
		subjectCount=dbUtil.getFirstCol(countSql);
		studentCount=dbUtil.getFirstCol(countStudentSql);
		totalMoney=StringTool.getMoneyNum(dbUtil.getFirstCol(totalMoneySql));
		
		return "bulk_particular";
	}

	public String bulkPay(){
		String sql=String.format("select * from base_stipend_unit t where t.stipend_unit_code='%1$s'", unitCode);
		String studentdsSql=String.format("select * from base_student_site_up t left join base_student_info i on t.stud_exam_code=i.stud_exam_code where i.stipend_unit_code='%1$s' and t.stud_apply_payoff='0' and t.examination_code=(select e.examination_code from base_examination e where e.nonce='1'  )", unitCode);
		String totalMoneySql=String.format("select sum(t.stud_apply_money) from base_student_site_up t left join base_student_info i on t.stud_exam_code=i.stud_exam_code where i.stipend_unit_code='%1$s' and t.stud_apply_payoff='0'  and t.examination_code=(select e.examination_code from base_examination e where e.nonce='1'  )", unitCode);
		
		List<BaseStudentSiteUp> students=dbUtil.getObjList(studentdsSql,BaseStudentSiteUp.class);
		ArrayList<String> sqls=new ArrayList<String>();
		Date now=new Date();
		String order = String.format("%1$s08%2$s%3$s%4$s",
				StringTool.getDate(now, "yyyy"),
				students.get(0).getExaminationCode().substring(4), 
				this.getCOperUser().getCityCode()+ StringTool.getDate(now, "xxHH-mm-ss"), 
				StringTool.getDate(now, "yyyyMMdd"));
		
		String totalMoneyX=StringTool.getMoneyNum(dbUtil.getFirstCol(totalMoneySql));
		if(totalMoney.equals(totalMoneyX)==false){
			this.PostJs(String.format("alert('需交费总金额发生变化（%2$s<>%3$s），请重新确认支付！');document.location='payStat_bulkPreview.do?unitCode=%1$s';",unitCode,totalMoneyX,totalMoney));
			return null;
		}
		
		for(BaseStudentSiteUp student:students){
			genSql(sqls,student,order);
		}
		
		int lines=dbUtil.transExeSqls(sqls);
		// 记录日志
		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "日常管理，日常管理-数据维护-助学单位支付");
		optLog.setLogOptSql(StringTool.join(sqls,";"));
		OperateLogTool.saveOptLog(optLog);
		if(lines==sqls.size()){
			this.PostJs(String.format("alert('支付成功！');document.location='payStat_bulkPre.do';",unitCode));
			return null;
		}else{
			this.PostJs(String.format("alert('支付失败！');document.location='payStat_bulkPreview.do?unitCode=%1$s';",unitCode));
			return null;
		}
//		units=dbUtil.getObjList(sql, BaseStipendUnit.class);
//		return "bulk_list";
	}
	
	void genSql(List<String> sqls,BaseStudentSiteUp student,String order){
//		String insert = String
//		.format("insert into base_netpay(NP_ORDER,STUD_EXAM_CODE,NP_BANK_ID,PAY_ID,NP_MONEY,NP_PAY_TIME) values('%1$s','%2$s','%3$s','%4$s',%5$s,to_date('%6$s','yyyy-mm-dd hh24:mi:ss'))",
//				buyReturn.getR6_Order(), student.getStudExamCode(),
//				buyReturn.getRb_BankId(),
//				buyReturn.getRo_BankOrderId(), totalMoney,
//				buyReturn.getRp_PayDate());
String update1 = String
		.format("update base_student_site_up t set t.PAY_METHOD_CODE='1',t.stud_apply_payoff='1',t.np_order='%1$s',PAY_ID='%2$s',t.STUD_APPLY_PAY_TIME=sysdate where t.stud_exam_code='%3$s'",
				order,
				"X-SNEAC",
				student.getStudExamCode());
//String update2 = String
//		.format("update BASE_NETPAY_PRE t set t.NP_PAY_STATUS='1' where t.np_order='%1$s'",
//				buyReturn.getR6_Order(),
//				buyReturn.getRo_BankOrderId());
sqls.add(update1);
		
	}
	/******************************* getter/setter **********************************/
	public DbUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<String[]> getBaseBreeauList() {
		return baseBreeauList;
	}

	public void setBaseBreeauList(List<String[]> baseBreeauList) {
		this.baseBreeauList = baseBreeauList;
	}

	public String getRegionaLism() {
		return regionaLism;
	}

	public void setRegionaLism(String regionaLism) {
		this.regionaLism = regionaLism;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<String[]> getPayInfoList() {
		return payInfoList;
	}

	public void setPayInfoList(List<String[]> payInfoList) {
		this.payInfoList = payInfoList;
	}

	public List<String[]> getPayInfoDateList() {
		return payInfoDateList;
	}

	public void setPayInfoDateList(List<String[]> payInfoDateList) {
		this.payInfoDateList = payInfoDateList;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public List<String[]> getPersonPayList() {
		return personPayList;
	}

	public void setPersonPayList(List<String[]> personPayList) {
		this.personPayList = personPayList;
	}

	public List<String[]> getPayInfoList2() {
		return payInfoList2;
	}

	public void setPayInfoList2(List<String[]> payInfoList2) {
		this.payInfoList2 = payInfoList2;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public List<String[]> getErrInfoList() {
		return errInfoList;
	}

	public void setErrInfoList(List<String[]> errInfoList) {
		this.errInfoList = errInfoList;
	}

	public String getDzTotalMoney() {
		return dzTotalMoney;
	}

	public void setDzTotalMoney(String dzTotalMoney) {
		this.dzTotalMoney = dzTotalMoney;
	}

	public String getSjTotalMoney() {
		return sjTotalMoney;
	}

	public void setSjTotalMoney(String sjTotalMoney) {
		this.sjTotalMoney = sjTotalMoney;
	}

	public String getTfTotalMoney() {
		return tfTotalMoney;
	}

	public void setTfTotalMoney(String tfTotalMoney) {
		this.tfTotalMoney = tfTotalMoney;
	}

	public String getRegionaLismValue() {
		return regionaLismValue;
	}

	public void setRegionaLismValue(String regionaLismValue) {
		this.regionaLismValue = regionaLismValue;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getPhyPath() {
		return phyPath;
	}

	public void setPhyPath(String phyPath) {
		this.phyPath = phyPath;
	}

	public String getPAY_RECORD_EXCEL() {
		return PAY_RECORD_EXCEL;
	}

	public String getXcTotalMoney() {
		return xcTotalMoney;
	}

	public void setXcTotalMoney(String xcTotalMoney) {
		this.xcTotalMoney = xcTotalMoney;
	}

	public String getExcelUrl() {
		return excelUrl;
	}

	public void setExcelUrl(String excelUrl) {
		this.excelUrl = excelUrl;
	}

	public List<BaseStipendUnit> getUnits() {
		return units;
	}

	public void setUnits(List<BaseStipendUnit> units) {
		this.units = units;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getSubjectCount() {
		return subjectCount;
	}

	public void setSubjectCount(String subjectCount) {
		this.subjectCount = subjectCount;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(String studentCount) {
		this.studentCount = studentCount;
	}
}