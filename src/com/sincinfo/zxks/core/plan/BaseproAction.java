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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.bean.ProSeq;
import com.sincinfo.zxks.bean.ProType;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.core.plan.examarrange.ExcelUtil;
import com.sincinfo.zxks.zxksdbs.BaseProDbService;

/**
 * @ClassName: LevelAction
 * @Description: 专业设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 15:45<br>
 * 
 */
public class BaseproAction extends WebActionSupport {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = -6217769699627471779L;

    private Page page = new Page();

    // 层次
    private BasePro basepro;

    // 用来传递查询结果
    private List<BasePro> baseproList;

    // 专业类型列表
    private List<ProType> protypelList;

    // 专业分类列表
    private List<ProSeq> proseqList;

    // 专业层次列表
    private List<PlanLevel> planlevelList;

    /**
     * 初始化
     */
    private void init() {
        if (basepro == null) {
            basepro = new BasePro();
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
        url.append(String.format("%1$s/plan/basepro_Show.do", request.getContextPath()));
        // url.append(String.format("?department.departmentGrade=%1$s",
        // department.getDepartmentGrade()));
        page.setPath(url.toString());
    }

    /**
     * 初始化查询条件的列表（根据用户级别）
     */
    private void initSelect() {
        BaseProDbService db = new BaseProDbService();
        this.protypelList = db.qryProTypeClasses();
        this.proseqList = db.qryProSeqClasses();
        this.planlevelList = db.qryPlanLevelClasses();
    }

    /**
     * 初始化查询条件的列表（根据用户级别）
     */
    private void initSctForOpert() {
        BaseProDbService db = new BaseProDbService();
        this.protypelList = db.qryProTypeClasses();
        this.proseqList = db.qryProSeqClasses();
        this.planlevelList = db.qryPlanLevelClasses();
    }

    /**
     * 查询层次列表
     * 
     * @return
     */
    public String Show() {
        init();
        BaseProDbService db = new BaseProDbService();
        this.baseproList = db.qry(null, page);
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
        BaseProDbService db = new BaseProDbService();
        boolean addFlag = db.save(basepro, 0);
        if (addFlag) {
            this.PostJs(String.format("alert('添加成功！');location.href='%1$s/plan/basepro_Show.do';", request
                    .getContextPath()));
        } else {
            this.PostJs(String.format("alert('添加失败！');location.href='%1$s/plan/basepro_Show.do';", request
                    .getContextPath()));
        }
    }

    /**
     * 进入修改页面
     * 
     * @return
     */
    public String EditPre() {
        initSctForOpert();
        BaseProDbService db = new BaseProDbService();
        this.basepro = db.qry(basepro.getProCode());
        return "Edit";
    }

    /**
     * 保存修改内容
     */
    public void Edit() {
        BaseProDbService db = new BaseProDbService();
        boolean editFlag = db.save(basepro, 1);
        if (editFlag) {
            this.PostJs(String.format("alert('修改成功！');location.href='%1$s/plan/basepro_Show.do';", request
                    .getContextPath()));
        } else {
            this.PostJs(String.format("alert('修改失败！');location.href='%1$s/plan/basepro_Show.do';", request
                    .getContextPath()));
        }
    }

    /**
     * 删除层次
     */
    public void Del() {
        BaseProDbService db = new BaseProDbService();
        boolean delFlag = db.del(basepro.getProCode());
        if (delFlag) {
            this.PostJs(String.format("alert('删除成功！');location.href='%1$s/plan/basepro_Show.do';", request
                    .getContextPath()));
        } else {
            this.PostJs("alert('删除失败！请重试。');location.back();");
        }
    }

    /**
     * 设置分页地址
     */
    private void setUrl() {
        StringBuilder url = new StringBuilder();
        url.append(String.format("location.href='%1$s/plan/basepro_Show.do';", request.getContextPath()));
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

        initSctForOpert();

        // 获取点击的层次信息
        BaseProDbService db = new BaseProDbService();

        // 查询对应层次下的所有职位列表
        this.baseproList = db.qry(basepro, page);

        // 加载权限列表
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
        BaseProDbService service = new BaseProDbService();

        // 查询专业列表
        List<BasePro> tmpProList = service.qryForExcel(basepro);
        
        if (tmpProList == null || tmpProList.size() == 0) {
            msg = "noData";
        } else {
            // 生成文件
            String excelFileName = "bST" + service.newFilename() + ".xls";
            String[] paths = service.getPaths();
            String subPath = service.getConfig("71");
            String excelPhyPath = paths[0] + subPath + System.getProperty("file.separator");
            String excelNetPath = paths[1] + subPath + "/" + excelFileName;
            // 合成真实的文件名
            String file = "";
            if (excelPhyPath.endsWith(System.getProperty("file.separator"))) {
                file = excelPhyPath + excelFileName;
            } else {
                file = excelPhyPath + System.getProperty("file.separator") + excelFileName;
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
                WritableCellFormat alignleft = excelUtil.getBodyformatAlignLeft();
                WritableCellFormat alignleftCenter = excelUtil.getBodyformatAlignLeftCenter();
                WritableCellFormat aligncenter = excelUtil.getBodyformatAlignCenter();
                WritableCellFormat demoleft = excelUtil.getDemoformat();
                String excelTile = "专业设置报表";
                BasePro tmpPro = null;
                for (int i = 0; i < tmpProList.size(); i++) {
                    if (i % pagesize == 0) {
                        // 新建sheet
                        sheet = book.createSheet("专业设置报表" + i / pagesize, i / pagesize);
                        label = new Label(0, 0, excelTile, haligncenter);
                        sheet.addCell(label);
                        sheet.mergeCells(0, 0, 6, 0);
    
                        label = new Label(0, 1, "序号", aligncenter);
                        sheet.addCell(label);
                        label = new Label(1, 1, "专业代码", aligncenter);
                        sheet.addCell(label);
                        label = new Label(2, 1, "专业名称", aligncenter);
                        sheet.addCell(label);
                        label = new Label(3, 1, "专业分类", aligncenter);
                        sheet.addCell(label);
                        label = new Label(4, 1, "专业类型", aligncenter);
                        sheet.addCell(label);
                        label = new Label(5, 1, "是否全国统考计划", aligncenter);
                        sheet.addCell(label);
                        label = new Label(6, 1, "状态", aligncenter);
                        sheet.addCell(label);
                        
                        rownum = 2;
                    }
                    
                    tmpPro = tmpProList.get(i);
                    label = new Label(0, rownum, String.valueOf(i + 1), alignleftCenter); // 序号
                    sheet.addCell(label);
                    label = new Label(1, rownum, tmpPro.getProCode(), alignleftCenter); // 专业代码
                    sheet.addCell(label);
                    label = new Label(2, rownum, tmpPro.getProName(), alignleftCenter); // 专业名称
                    sheet.addCell(label);
                    label = new Label(3, rownum, tmpPro.getProPartName(), alignleftCenter); // 专业分类
                    sheet.addCell(label);
                    label = new Label(4, rownum, tmpPro.getProTypeName(), alignleftCenter); // 专业类型
                    sheet.addCell(label);
                    label = new Label(5, rownum, "1".equals(tmpPro.getIsGb()) ? "是" : "否" , alignleftCenter); // 是否全国统考
                    sheet.addCell(label);
                    label = new Label(6, rownum, "1".equals(tmpPro.getIsUse()) ? "是" : "否", alignleftCenter); // 状态
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

    public BasePro getBasepro() {
        return basepro;
    }

    public void setBasepro(BasePro basepro) {
        this.basepro = basepro;
    }

    public List<BasePro> getBaseproList() {
        return baseproList;
    }

    public void setBaseproList(List<BasePro> baseproList) {
        this.baseproList = baseproList;
    }

    public List<ProType> getProtypelList() {
        return protypelList;
    }

    public void setProtypelList(List<ProType> protypelList) {
        this.protypelList = protypelList;
    }

    public List<ProSeq> getProseqList() {
        return proseqList;
    }

    public void setProseqList(List<ProSeq> proseqList) {
        this.proseqList = proseqList;
    }

    public List<PlanLevel> getPlanlevelList() {
        return planlevelList;
    }

    public void setPlanlevelList(List<PlanLevel> planlevelList) {
        this.planlevelList = planlevelList;
    }

}
