<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.io.*"%>

<%@ page import="com.sincinfo.zxks.common.Constants"%>
<%@ page import="com.sincinfo.zxks.common.log.OperatLog"%>
<%@ page import="com.sincinfo.zxks.common.util.StringTool"%>
<%@ page import="com.sincinfo.zxks.common.db.DbUtil"%>
<%@ page import="com.sincinfo.zxks.bean.BaseUser"%>
<%@ page import="com.sincinfo.zxks.zxksdbs.PhotoGatherDbService"%>

<%@ page import="com.sincinfo.zxks.check.collectPicturesCheck"%>
<%@ page import="com.sincinfo.zxks.bean.imageInfo"%>
<%
	// 取值
	String preApplyCode = session.getAttribute("PRE_APPLY_CODE_FOR_PHOTO").toString();
	if ( !"".equals(StringTool.trim(preApplyCode))) {
		// 构建路径
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String filePath = paths[0] + System.getProperty("file.separator") + dbUtil.getConfig("21") + System.getProperty("file.separator");
		String fileName = String.format("%1$s.jpg", preApplyCode);
		String fileFullPath = filePath + fileName;
		
		// 过滤路径
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		// 保存文件
		BufferedInputStream inputStream = new BufferedInputStream(
				request.getInputStream());
		FileOutputStream outputStream = new FileOutputStream(
				new File(fileFullPath));
		byte[] bytes = new byte[1024];
		int v;
		while ((v = inputStream.read(bytes)) > 0) {
			outputStream.write(bytes, 0, v);
		}
		
		collectPicturesCheck cpc= new collectPicturesCheck();
		imageInfo ii = cpc.Imgchange(fileFullPath);
		cpc.LoadImageToServer(fileFullPath,0);
 		PhotoGatherDbService pgds = new PhotoGatherDbService();
 		int i = pgds.checkImg(fileFullPath);
 		if(i!=0 || i!=4){
 		i = cpc.LoadImageToServer1(fileFullPath,i);
 		}
 		
		outputStream.close();
		inputStream.close();

		// 构造操作日志
		Object userObj = session.getAttribute(Constants.ZK_OPERATOR);
		BaseUser optUser = (BaseUser) userObj;
		OperatLog optLog = new OperatLog(optUser.getUserName(), request
				.getRemoteAddr(), session.getAttribute(Constants.TREE_NODE_ID)
				.toString(), request.getContextPath(), OperatLog.DB_UPDATE, "", "摄像点，照片采集");
		
		// 更新数据库对应字段
		if (i==0 && pgds.saveStudPhoto(preApplyCode, fileName, StringTool.trim(optUser.getCameraPlaceCode()), optLog)) {
			// 数据库更新成功
		} else {
			// 数据库更新失败
			throw new Exception("error ：with db connection!");
		}
	} else {
		throw new Exception("error ：with out parameter!");
	}
%>
