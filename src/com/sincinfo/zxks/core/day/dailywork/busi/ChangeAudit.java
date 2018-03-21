/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.dailywork.busi.ChangePhoAudit.java<br>
 * @Description: 基本信息变更审核处理 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:13:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.dailywork.busi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sincinfo.zxks.bean.BaseStudinfoChange;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.zxksdbs.StudentMgrDbService;

/**
 * @ClassName: ChangePhoAudit
 * @Description: 基本信息变更审核处理 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:13:20 AM<br>
 * 
 */
public class ChangeAudit {
	private Log log;
	private StudentMgrDbService smds;

	public ChangeAudit(StudentMgrDbService smds) {
		this.log = new Log();
		this.smds = smds;
	}

	/**
	 * 变更申请审核
	 * 
	 * @return int[3] [0]-成功 [1]-失败 [2]-换人地市未审核
	 */
	public int[] doChangeAudit(String[] auditChangeIds, BaseUser optUser,
			String auditStatus, String unAuditReason) {
		// 照片（移动照片文件）
		DbUtil dbUtil = this.smds.getDbUtil();
		String[] paths = dbUtil.getPaths();
		String sep = System.getProperty("file.separator");
		String subPath_newTemp = dbUtil.getConfig("33");
		String subPath_stardand = dbUtil.getConfig("21");
		String subPath_backup = dbUtil.getConfig("34");

		// 处理文件目录
		String bakDirPath = paths[0] + subPath_backup;
		File bakDirFile = new File(bakDirPath);
		if (!bakDirFile.exists()) {
			bakDirFile.mkdirs();
		}

		// 组织文件路径
		String stardandFilePath = paths[0] + subPath_stardand + sep;
		String stardandFileP = paths[0] + "%1$s";
		String newTempFilePath = paths[0] + subPath_newTemp + sep;
		String backupFilePath = paths[0] + subPath_backup + sep;
		String fileNameMod = "%1$s.jpg"; // 使用准考证号进行格式化
		String bakFileNameMod = "%1$s_%2$s.jpg"; // 使用准考证号以及当前时间转为字符串后进行格式化
		String fileName = null;
		String bakFileName = null;
		File tempFile = null;
		File stardardFile_Old = null;
		File stardardFile = null;
		File bakFile = null;

		// 文件流对象
		FileInputStream fin = null;
		FileOutputStream fout = null;
		byte[] bytes = new byte[512];

		// 进行审核处理
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		int succCount = 0;
		int failCount = 0;
		int ipcCount = 0;
		boolean tmpFlag = false;
		for (String changeId : auditChangeIds) {
			// 获取变更申请对象
			BaseStudinfoChange sic = this.smds.qryStudinfoChange(changeId);
			tmpFlag = false;

			// 如果是换人，并且地市未审核的，直接跳过本次循环
			if ("1".equals(optUser.getUserRole()) && "1".equals(sic.getIsPeopleChange())
					&& "0".equals(sic.getChangeAuditStatus())) {
				ipcCount++;
				failCount++;
				continue;
			}

			// 先更新状态
			if (this.smds.auditInfoChangeApply(sic, optUser, auditStatus,
					unAuditReason)) {
				succCount++;
				tmpFlag = true;
			} else {
				failCount++;
			}
			if (tmpFlag && "9".equals(auditStatus)) {
				if ("7".equals(sic.getChangeTypeCode())) {
					// 移动基础照片文件，转移到备份目录
					fileName = String
							.format(fileNameMod, sic.getStudExamCode());
					bakFileName = String.format(bakFileNameMod, sic
							.getStudExamCode(), sdf.format(new Date()));
					tempFile = new File(newTempFilePath + fileName);
					stardardFile = new File(stardandFilePath + fileName);
					stardardFile_Old = new File(String.format(stardandFileP,
							sic.getOldInfo()));
					bakFile = new File(backupFilePath + bakFileName);

					// 移动临时文件到标准文件夹内
					try {
						if (!stardardFile_Old.exists()) {
							log.error(this.getClass(), "标准照片文件不存在！");
							new Exception("标准照片文件不存在！");
						}
						if (bakFile.exists()) {
							bakFile.delete();
							bakFile.createNewFile();
						} else {
							bakFile.createNewFile();
						}

						fin = new FileInputStream(stardardFile_Old);
						fout = new FileOutputStream(bakFile);
						int length = 512;
						int count = 0;
						while ((fin.read(bytes, count * length, length)) != -1) {
							fout.write(bytes);
						}
						fout.flush();
					} catch (FileNotFoundException e) {
						log.error(this.getClass(), "读取文件流失败！", e);
					} catch (IOException e) {
						log.error(this.getClass(), "读取文件流失败！", e);
					} finally {
						if (fin != null) {
							try {
								fin.close();
							} catch (IOException e) {
							}
						}
						if (fout != null) {
							try {
								fout.close();
							} catch (IOException e) {
							}
						}
					}

					// 移动临时照片文件至标准文件处
					try {
						if (!tempFile.exists()) {
							log.error(this.getClass(), "变更后的照片文件不存在！");
							new Exception("变更后的照片文件不存在！");
						}
						if (stardardFile.exists()) {
							stardardFile.delete();
							stardardFile.createNewFile();
						} else {
							stardardFile.createNewFile();
						}

						fin = new FileInputStream(tempFile);
						fout = new FileOutputStream(stardardFile);
						int length = 512;
						int count = 0;
						while ((fin.read(bytes, count * length, length)) != -1) {
							fout.write(bytes);
						}
					} catch (FileNotFoundException e) {
						log.error(this.getClass(), "读取文件流失败！", e);
					} catch (IOException e) {
						log.error(this.getClass(), "读取文件流失败！", e);
					} finally {
						if (fin != null) {
							try {
								fin.close();
							} catch (IOException e) {
							}
						}
						if (fout != null) {
							try {
								fout.close();
							} catch (IOException e) {
							}
						}
					}

					// 更新oldinfo信息
					if (!this.smds.updatePhoOldInfo(changeId, bakFileName)) {
						succCount--;
					}
				}
			} else {
				// 其他基础信息
			}
		}

		return new int[] { succCount, failCount, ipcCount };
	}

}
