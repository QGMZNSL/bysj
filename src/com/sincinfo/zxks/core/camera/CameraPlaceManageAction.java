package com.sincinfo.zxks.core.camera;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseCameraPlace;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.GenericEntity;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

public class CameraPlaceManageAction extends WebActionSupport {
    private DbUtil du = new DbUtil();
    private Page page = new Page();

    private List<GenericEntity> citys;
    private List<GenericEntity> examAreas;
    private List<BaseCameraPlace> places;
    private BaseCameraPlace cameraPlace;
    private BaseCameraPlace place;
    private GenericEntity genericEntity;
    private String city;
    private String cameraPlaceCode;
    private String cameraPlaceName;
    private String examAreaCode;
    private String[] codes;
    private String codesStr;

    public String init() {

        return list();
    }

    private void loadMainData() {

        BaseUser optUser = this.getCOperUser();
        String _cityCode = optUser.getCityCode();
        String sql = null;
        if ("610000".equals(_cityCode)) { // 省用户登录
            // 查询全省地市列表
            sql = "select t.city_code id,t.city_code||'-'||t.city_name name from base_city t order by t.city_code";
            citys = du.getObjList(sql, GenericEntity.class);
            citys.add(0, new GenericEntity("", "全部"));
        } else if (!StringTool.isEmpty(_cityCode)) { // 地市用户登录
            // 查询地市列表
            sql = "select t.city_code id,t.city_code||'-'||t.city_name name from base_city t where t.city_code=? order by t.city_code";
            citys = du.getObjList(sql, GenericEntity.class, _cityCode);

            // 地市用户登录，默认地市代码
            city = _cityCode;
        }        
    }

    public String list() {
        loadMainData();

        StringBuilder url = new StringBuilder();
        url.append("/ZK_CORE/manager/camera/cpm_list.do");
        url.append("?city=");
        url.append(StringTool.trim(city));
        url.append("&cameraPlaceCode=");
        url.append(StringTool.trim(cameraPlaceCode));
        url.append("&cameraPlaceName=");
        url.append(StringTool.trim(cameraPlaceName));

        page.setPath(url.toString());

        StringBuilder sqlQuery = new StringBuilder();
        StringBuilder sqlCount = new StringBuilder();
        StringBuilder cond = new StringBuilder();

        sqlQuery.append(" select c.* ");
        sqlCount.append(" select count(c.camera_place_code) ");

        cond.append(" from base_camera_place c ");
        cond.append(" where 1=1 ");
        if (!StringTool.isEmpty(city)) {
            cond.append(" and c.city_code='" + city + "' ");
        }        
        if (!StringTool.isEmpty(cameraPlaceCode)) {
            cond.append(" and c.camera_place_code='" + cameraPlaceCode + "' ");
        }
        if (!StringTool.isEmpty(cameraPlaceName)) {
            cond.append(" and c.camera_place_name like '%" + cameraPlaceName + "%' ");
        }
        cond.append(" order by c.city_code asc, c.camera_place_code asc ");

        sqlQuery.append(cond);
        sqlCount.append(cond);

        String sqlPage = page.setPagecount(du.getNum(sqlCount.toString()), sqlQuery.toString());

        places = du.getObjList(sqlPage, BaseCameraPlace.class);
        
        // 查询该摄像点关联的考区
        String sql = null;
        for (int i = 0; i < places.size(); i++) {
            sql = "select a.exam_area_code as id,a.exam_area_code||'-'||a.exam_area_name as name from base_camera_place_exam_area t,base_exam_area a where t.exam_area_code=a.exam_area_code and t.camera_place_code=?";
            List<GenericEntity> ges = du.getObjList(sql, GenericEntity.class, places.get(i).getCameraPlaceCode());
            String tmp = "";
            for (int j = 0; j < ges.size(); j++) {
                tmp += ges.get(j).getName();
                if (j != ges.size() - 1) {
                    tmp += ",";
                }
            }
            places.get(i).setExamAreaName(tmp);
        }

        return "list";
    }

    public String modifyPre() {
        loadMainData();

        // 查询摄像点
        String sql = "select * from base_camera_place where camera_place_code=?";
        place = du.getObject(sql, BaseCameraPlace.class, cameraPlaceCode);
        if (place == null) {
            this.GoBack("将要修改的摄像点不存在！");
            return null;
        }
        BaseUser optUser = this.getCOperUser();
        if (!"610000".equals(optUser.getCityCode()) && !place.getCityCode().equals(optUser.getCityCode())) {
            this.GoBack("您没有权限修改此摄像点！");
            return null;
        }
        
        // 查询摄像点可选的考区
        sql = "select t.exam_area_code id,t.exam_area_code||'-'||t.exam_area_name name from base_exam_area t where t.is_use=1 and t.city_code=?  order by t.city_code,t.exam_area_code";
        examAreas = du.getObjList(sql, GenericEntity.class, place.getCityCode());

        // 查询摄像点所属地市
        genericEntity = du.getObject(
                "select city_code as id, city_code||'-'||city_name as name from base_city where city_code=?",
                GenericEntity.class, place.getCityCode());

        // 查询已选择的摄像点
        List<GenericEntity> list = du.getObjList(
                "select exam_area_code as id,'' from base_camera_place_exam_area where camera_place_code=?",
                GenericEntity.class, place.getCameraPlaceCode());
        codesStr = "";
        // 将已选摄像点id进行处理，转换成字符串
        for (int i = 0; i < list.size(); i++) {
            codesStr += "," + list.get(i).getId();
            if (i == list.size() - 1) {
                codesStr += ",";
            }
        }

        return "modifyPre";
    }

    public String info() {
        modifyPre();
        return "info";
    }

    public String modify() {
        loadMainData();

        // 查询摄像点
        String sql = "select * from base_camera_place where camera_place_code=?";
        BaseCameraPlace t_place = du.getObject(sql, BaseCameraPlace.class, place.getCameraPlaceCode());
        if (t_place == null) {
            this.GoBack("将要修改的摄像点不存在！");
            return null;
        }
        BaseUser optUser = this.getCOperUser();
        if (!"610000".equals(optUser.getCityCode()) && !t_place.getCityCode().equals(optUser.getCityCode())) {
            this.GoBack("您没有权限修改此摄像点！");
            return null;
        }

        ArrayList<String> sqls = new ArrayList<String>();
        // 生成摄像点的更新语句
        sql = String
                .format(
                        "update base_camera_place set camera_place_name='%2$s',city_code='%3$s',camera_place_address='%4$s',camera_place_link_man='%5$s',camera_place_link_telephon='%6$s',by_bus='%7$s',is_use=%8$s,MAX_PEOPLE=%9$s where camera_PLACE_CODE='%1$s'",
                        place.getCameraPlaceCode(), place.getCameraPlaceName(), place.getCityCode(), place
                                .getCameraPlaceAddress(), place.getCameraPlaceLinkMan(), place
                                .getCameraPlaceLinkTelephon(), place.getByBus(), place.getIsUse(), place.getMaxPeople());
        sqls.add(sql);
        // 更新摄像点考区关系表，先删除，再重新添加
        sql = String.format("delete from base_camera_place_exam_area where camera_place_code='%1$s'", place.getCameraPlaceCode());
        sqls.add(sql);
        for (int i = 0; i < codes.length; i++) {
            sql = String.format("insert into base_camera_place_exam_area (camera_place_code,exam_area_code) values ('%1$s','%2$s')",place.getCameraPlaceCode(), codes[i]);
            sqls.add(sql);
        }
        
        if (du.transExeSqls(sqls) > 0) {
            // 记录日志
            OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "摄像点，摄像点管理-修改摄像点");
            optLog.setLogOptSql(sql);
            OperateLogTool.saveOptLog(optLog);
            this.PostJs("alert('保存成功！');document.location='cpm_init.do?city=" + place.getCityCode() + "';");
        } else
            this.GoBack("保存失败！");
        return null;
    }

    public String insertPre() {
        loadMainData();

        if (StringTool.isEmpty(city)) {
            this.GoBack("请选择市区名称！");
            return null;
        }

        String sql = null;
        
        // 查询地市
        sql = "select city_code as id, city_code||'-'||city_name as name from base_city where city_code=?";
        genericEntity = du.getObject(sql, GenericEntity.class, city);
        
        // 查询摄像点可选的考区
        sql = "select t.exam_area_code id,t.exam_area_code||'-'||t.exam_area_name name from base_exam_area t where t.is_use=1 and t.city_code=?  order by t.city_code,t.exam_area_code";
        examAreas = du.getObjList(sql, GenericEntity.class, city);

        return "insertPre";
    }

    public String insert() {
        loadMainData();

        String sql = null;

        sql = "select * from base_camera_place where camera_place_code=?";
        List<BaseCameraPlace> _places = du.getObjList(sql, BaseCameraPlace.class, place.getCameraPlaceCode());
        if (_places.size() > 0) {
            this.GoBack("保存失败！指定的摄像点编号已经存在。");
            return null;
        }

        if (codes != null && codes.length > 0) {
            ArrayList<String> sqls = new ArrayList<String>();

            // 插入摄像点数据
            sql = String
                    .format(
                            "insert into base_camera_place (camera_PLACE_CODE,camera_place_name,city_code,camera_place_address,camera_place_link_man,camera_place_link_telephon,by_bus,is_use,MAX_PEOPLE) values('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s',%9$s)",
                            place.getCameraPlaceCode(), place.getCameraPlaceName(), place.getCityCode(), place
                                    .getCameraPlaceAddress(), place.getCameraPlaceLinkMan(), place
                                    .getCameraPlaceLinkTelephon(), place.getByBus(), place.getIsUse(), place
                                    .getMaxPeople());
            sqls.add(sql);

            // 插入摄像点考区关系表数据
            String tmp = null;
            for (int i = 0; i < codes.length; i++) {
                tmp = codes[i];
                sql = "insert into BASE_CAMERA_PLACE_EXAM_AREA(CAMERA_PLACE_CODE,EXAM_AREA_CODE) values ('%1$s','%2$s')";
                sqls.add(String.format(sql, place.getCameraPlaceCode(), tmp));
            }

            int i = du.transExeSqls(sqls);
            if (i > 0) {
                // 记录日志
                OperatLog optLog = this.getOptLog(OperatLog.DB_INSERT, "摄像点，摄像点管理-添加摄像点");
                optLog.setLogOptSql(sql);
                OperateLogTool.saveOptLog(optLog);
                this.PostJs("alert('保存成功！');document.location='cpm_init.do?city=" + place.getCityCode() + "';");
                return null;
            } else {
                this.GoBack("保存失败！");
                return null;
            }
        }
        return null;
    }

    public List<GenericEntity> getCitys() {
        return citys;
    }

    public void setCitys(List<GenericEntity> citys) {
        this.citys = citys;
    }

    public String getCameraPlaceCode() {
        return cameraPlaceCode;
    }

    public void setCameraPlaceCode(String cameraPlaceCode) {
        this.cameraPlaceCode = cameraPlaceCode;
    }

    public String getCameraPlaceName() {
        return cameraPlaceName;
    }

    public void setCameraPlaceName(String cameraPlaceName) {
        this.cameraPlaceName = cameraPlaceName;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<BaseCameraPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<BaseCameraPlace> places) {
        this.places = places;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BaseCameraPlace getPlace() {
        return place;
    }

    public void setPlace(BaseCameraPlace place) {
        this.place = place;
    }

    public List<GenericEntity> getExamAreas() {
        return examAreas;
    }

    public void setExamAreas(List<GenericEntity> examAreas) {
        this.examAreas = examAreas;
    }

    public String getExamAreaCode() {
        return examAreaCode;
    }

    public void setExamAreaCode(String examAreaCode) {
        this.examAreaCode = examAreaCode;
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }

    public BaseCameraPlace getCameraPlace() {
        return cameraPlace;
    }

    public void setCameraPlace(BaseCameraPlace cameraPlace) {
        this.cameraPlace = cameraPlace;
    }

    public GenericEntity getGenericEntity() {
        return genericEntity;
    }

    public void setGenericEntity(GenericEntity genericEntity) {
        this.genericEntity = genericEntity;
    }

    public String getCodesStr() {
        return codesStr;
    }

    public void setCodesStr(String codesStr) {
        this.codesStr = codesStr;
    }

}
