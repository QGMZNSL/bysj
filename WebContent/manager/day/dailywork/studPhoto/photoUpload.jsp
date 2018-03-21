<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.io.*"%>
<%@ page import="com.sincinfo.zxks.core.day.dailywork.busi.PhotoModify"%>
<%@ page import="com.sincinfo.zxks.common.Constants"%>
<%@ page import="com.sincinfo.zxks.common.util.StringTool"%>
<%@ page import="com.sincinfo.zxks.common.db.DbUtil"%>
<%@ page import="com.sincinfo.zxks.bean.BaseUser"%>
<%@page import="com.sincinfo.zxks.zxksdbs.StudentMgrDbService"%>
<%
	// 取值
	Object phoObj = session
			.getAttribute(PhotoModify.STUD_MGR_PHOTO_MODIFY);
	PhotoModify phoMod = (PhotoModify) phoObj;

	if ( phoMod != null) {
		// 构建路径
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String filePath = paths[0]
				+ System.getProperty("file.separator")
				+ dbUtil.getConfig("33")
				+ System.getProperty("file.separator");
		String fileName = String.format("%1$s.jpg", phoMod.getStudent()
				.getStudExamCode());
		String fileFullPath = filePath + fileName;

		// 过滤路径
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 保存文件
		BufferedInputStream inputStream = new BufferedInputStream(
				request.getInputStream());
		FileOutputStream outputStream = new FileOutputStream(new File(
				fileFullPath));
		byte[] bytes = new byte[1024];
		int v;
		while ((v = inputStream.read(bytes)) > 0) {
			outputStream.write(bytes, 0, v);
		}
		outputStream.close();
		inputStream.close();

		// 更新数据库对应字段
		BaseUser optUser = (BaseUser) session
				.getAttribute(Constants.ZK_OPERATOR);
		StudentMgrDbService smds = new StudentMgrDbService();
		if (smds.saveStudInfoChange(phoMod.getStudent()
				.getStudExamCode(), phoMod.getChangeTypeCode(), StringTool.trim(phoMod
				.getStudent().getStudPhotoFile1()), fileName, phoMod
				.getModifyReason(), phoMod.getProveMaterial(), optUser
				.getUserName(), phoMod.getFillinBy())) {
			// 数据库更新成功
		} else {
			// 数据库更新失败
			throw new Exception("error ：with db connection!");
		}
	} else {
		throw new Exception("error ：with out parameter!");
	}
%>
