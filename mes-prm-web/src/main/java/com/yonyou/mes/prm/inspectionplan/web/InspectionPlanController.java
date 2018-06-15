package com.yonyou.mes.prm.inspectionplan.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.me.base.BaseController;
import com.yonyou.me.constance.EntityConst;
import com.yonyou.me.entity.MeSuperVO;
import com.yonyou.me.entity.VOUtil;
import com.yonyou.me.utils.dto.BaseDTO;
import com.yonyou.me.utils.dto.ExceptionResult;
import com.yonyou.me.utils.dto.IDValueConvert;
import com.yonyou.me.utils.dto.PageVO;
import com.yonyou.me.utils.dto.Result;
import com.yonyou.me.utils.dto.ViewArea;
import com.yonyou.me.utils.exception.ExceptionUtils;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBillVO;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBodyVO;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanHeadVO;
import com.yonyou.mes.prm.core.inspectionplan.service.IInspectionPlanService;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBillVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBodyVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;
import com.yonyou.mes.prm.core.inspectionproject.service.IInspectionProjectService;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBillVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;
import com.yonyou.mes.prm.core.inspectiontask.service.IInspectionTaskService;

/**
 * 巡检方案 controller
 *
 * @author weijw
 * <p>
 * 2018年2月7日
 */

@RestController
@RequestMapping(value = "/prm/inspectionplan")
public class InspectionPlanController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IInspectionPlanService service;

    @Autowired
    private IInspectionProjectService projservice;

    @Autowired
    private IInspectionTaskService taskService;

    /**
     * 表头前端档案id与显示名称的映射
     */
    private static Map<String, String> headDisplayID2Name = new HashMap<String, String>() {
        {
            put("pk_dept", "pk_dept_name");// 部门
            put("pk_region", "pk_region_name");// 巡检区域
            put("pk_post", "pk_post_name");// 岗位
            put("orgid", "orgid_name");//工厂
        }
    };
    /**
     * 表体前端档案id与显示名称的映射
     */
    private static Map<String, String> bodyDisplayID2Name = new HashMap<String, String>() {
        {
            put("pk_er_project", "er_project_code");// 项目编码

        }
    };
    /**
     * classMap和nameMap分别保存Json子段名到VO类、类中所有字段的映射
     */
    private final Map<String, Class<?>> classMap = new HashMap<String, Class<?>>() {
        {
            put(EntityConst.HEAD, InspectionPlanHeadVO.class);
            put(EntityConst.BODY, InspectionPlanBodyVO.class);
        }
    };

    private final Map<String, String[]> nameMap = new HashMap<String, String[]>() {
        {
            put(EntityConst.HEAD,
                    VOUtil.AllClassFields(InspectionPlanHeadVO.class));
            put(EntityConst.BODY,
                    VOUtil.AllClassFields(InspectionPlanBodyVO.class));
        }
    };

    /**
     * 查询表头
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    Object pageString(@RequestBody PageVO pageVO) {

        Result result = new Result();
        try {
            PageRequest pageRequest = pageVO.getPageRequest();
            SearchParams searchParams = pageVO.getSearchParams();
            if (pageRequest == null || searchParams == null) {
                ExceptionUtils.wrapBusinessException("当前参数数据有误");
            }

            Page<InspectionPlanHeadVO> pageVOs = service.selectAllByPage(
                    pageVO.getPageRequest(), pageVO.getSearchParams());
            Map<String, Page<?>> voMap = new HashMap<String, Page<?>>();
            voMap.put(EntityConst.HEAD, pageVOs);

            Map<String, ViewArea> data = this.convertPageVO2DTO(classMap,
                    voMap, nameMap);

            Map<String, MeSuperVO[]> voIndex = this.convertToVOMap(voMap);
            // 补充名称和精度
            this.afterProcess(data, voIndex);
            result.setData(data);
        } catch (Exception ex) {
        	log.error(ex.getMessage());
            result = ExceptionResult.process(ex);
        }
        return result;
    }

    /**
     * 查询表头
     */
    @RequestMapping(value = "/push2task", method = RequestMethod.POST)
    public @ResponseBody
    Object push2Task(@RequestBody PageVO pageVO) {

        Result result = new Result();
        try {
            PageRequest pageRequest = pageVO.getPageRequest();
            SearchParams searchParams = pageVO.getSearchParams();
            if (pageRequest == null || searchParams == null) {
                ExceptionUtils.wrapBusinessException("当前参数数据有误");
            }
            String[] ids = ((ArrayList<String>)searchParams.getSearchMap().get("planids")).toArray(new String[0]);
            InspectionPlanBillVO[] billvos = service.query(Arrays.asList(ids));
            List<InspectionTaskBillVO> newbills = new ArrayList<>();
            for (InspectionPlanBillVO bill :
                    billvos) {
                InspectionTaskBillVO nbill = new InspectionTaskBillVO();

                InspectionPlanHeadVO head = (InspectionPlanHeadVO) bill.getHead();
                List<MeSuperVO> bodys = bill.getChildren(InspectionPlanBodyVO.class);

                InspectionTaskHeadVO nhead = new InspectionTaskHeadVO();
                nhead.setOrgid(head.getOrgid());
                nhead.setOrgid_name(head.getOrgid_name());
                nhead.setDeptid(head.getPk_dept());
                nhead.setDeptid_name(head.getPk_dept_name());
                nhead.setPlanid(head.getId());
                nhead.setPlanid_name(head.getName());
                nhead.setPostid(head.getPk_post());
                nhead.setPostid_name(head.getPk_post_name());
                nhead.setSysid(head.getSysid());
                nhead.setTenantid(head.getTenantid());
                nhead.setStatus(2);
                nhead.setReleased_time(new Timestamp(new Date().getTime()));

                List<InspectionTaskBodyVO> nlist = new ArrayList<>();
                for (MeSuperVO spvo:
                    bodys ) {
                    InspectionPlanBodyVO bodyvo = (InspectionPlanBodyVO) spvo;
                    List<String> bids = new ArrayList<>();
                    bids.add(bodyvo.getPk_er_project());
                    InspectionProjectBillVO[] projbills = projservice.query(bids);
                    InspectionProjectBillVO projbill =  projbills[0];
                    InspectionProjectHeadVO phead = (InspectionProjectHeadVO) projbill.getHead();
                    List<MeSuperVO> pbodys = projbill.getChildren(InspectionProjectBodyVO.class);

                    for (MeSuperVO psuper :
                            pbodys) {
                        InspectionProjectBodyVO pbody = (InspectionProjectBodyVO) psuper;
                        InspectionTaskBodyVO tbvo = new InspectionTaskBodyVO();
                        tbvo.setSysid(phead.getSysid());
                        tbvo.setTenantid(phead.getTenantid());
                        tbvo.setProjectid(phead.getId());
                        tbvo.setProjectid_code(phead.getCode());
                        tbvo.setProjectid_name(phead.getName());
                        tbvo.setPlan_order(bodyvo.getPlan_order());
                        tbvo.setCrowno(pbody.getCrowno());
                        tbvo.setProject_content(pbody.getCprjcontent());
                        tbvo.setJudge_standard(pbody.getCjudstd());
                        tbvo.setProject_status(1);//下达
                        tbvo.setStatus(2);
                        nlist.add(tbvo);

                    }


                }
                nbill.setHead(nhead);
                nbill.setChildren(InspectionTaskBodyVO.class,nlist);
                taskService.add(nbill);
            }


            result.setSuccess(true);
        } catch (Exception ex) {
        	log.error(ex.getMessage());
            result = ExceptionResult.process(ex);
        }
        return result;
    }

    /**
     * 根据表头查询表体数据
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryBodyByParentid", method = RequestMethod.POST)
    public @ResponseBody
    Object queryBodyByParentid(@RequestBody PageVO page) {

        // 创建返回信息
        Result result = new Result();

        try {
            PageRequest pageRequest = page.getPageRequest();
            SearchParams searchParams = page.getSearchParams();
            if (pageRequest == null || searchParams == null) {
                ExceptionUtils.wrapBusinessException("当前参数数据有误");
            }
            // 查询表体数据
            Page<InspectionPlanBodyVO> pageVOs = service.queryBodyByPage(
                    pageRequest, searchParams);
            Map<String, Page<?>> voMap = new HashMap<String, Page<?>>();
            voMap.put(EntityConst.BODY, pageVOs);

            Map<String, ViewArea> data = this.convertPageVO2DTO(classMap,
                    voMap, nameMap);

            Map<String, MeSuperVO[]> voIndex = this.convertToVOMap(voMap);
            this.afterProcess(data, voIndex);
            result.setData(data);
        } catch (Exception ex) {
        	log.error(ex.getMessage());
            result = ExceptionResult.process(ex);
        }
        return result;
    }

    /**
     * 新增保存
     *
     * @param sysDictTypeDataTable
     * @param response
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody BaseDTO dto) {
        Result result = new Result();
        try {
            // 1.前台数据转化成实体vo
            List<InspectionPlanBillVO> list = this.dtoToVO(dto);
            if (list == null || list.size() == 0) {
                throw new Exception("传入数据为空");
            }
            InspectionPlanBillVO vo = list.get(0);
            // 2.调用保存接口
            InspectionPlanBillVO resultData = this.service.add(vo);
            // 3.保存结果转化成返回值结构
            result = this.voToDTO(resultData);
        } catch (Exception e) {
        	log.error(e.getMessage());
            result = ExceptionResult.process(e);
        }

        return result;
    }

    /**
     * 修改保存
     *
     * @param sysDictTypeDataTable
     * @param response
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody BaseDTO dto) {
        Result result = new Result();
        try {
            // 1.前台数据转化成实体vo
            List<InspectionPlanBillVO> list = this.dtoToVO(dto);
            if (list == null || list.size() == 0) {
                throw new Exception("传入数据为空");
            }
            InspectionPlanBillVO vo = list.get(0);
            // 2.调用保存接口
            InspectionPlanBillVO resultData = this.service.update(vo);
            // 3.保存结果转化成返回值结构
            result = this.voToDTO(resultData);
        } catch (Exception e) {
        	log.error(e.getMessage());
            result = ExceptionResult.process(e);
        }

        return result;
    }

    /**
     * 变更版本
     *
     * @param sysDictTypeDataTable
     * @param response
     * @return
     */
    @RequestMapping(value = "/vchange", method = RequestMethod.POST)
    @ResponseBody
    public Object vchange(@RequestBody BaseDTO dto) {
        Result result = new Result();
        try {
            // 1.前台数据转化成实体vo
            List<InspectionPlanBillVO> list = this.dtoToVO(dto);
            if (list == null || list.size() == 0) {
                throw new Exception("传入数据为空");
            }
            InspectionPlanBillVO vo = list.get(0);
            // 2.调用版本变更接口
            InspectionPlanBillVO resultData = this.service.vchange(vo);
            // 3.保存结果转化成返回值结构
            result = this.voToDTO(resultData);
        } catch (Exception e) {
        	log.error(e.getMessage());
            result = ExceptionResult.process(e);
        }

        return result;
    }

    /**
     * 废除旧版本
     *
     * @param sysDictTypeDataTable
     * @param response
     * @return
     */
    @RequestMapping(value = "/disableoldplan", method = RequestMethod.POST)
    @ResponseBody
    public Object disableoldplan(@RequestBody BaseDTO dto) {
        Result result = new Result();
        try {
            // 1.前台数据转化成实体vo
            List<InspectionPlanBillVO> list = this.dtoToVO(dto);
            if (list == null || list.size() == 0) {
                throw new Exception("传入数据为空");
            }
            InspectionPlanBillVO vo = list.get(0);

            List<String> ids = new ArrayList<String>();
            ids.add(((InspectionPlanHeadVO) vo.getHead()).getId());
            // 根据表头id查询主子表
            InspectionPlanBillVO[] billvos = this.service.query(ids);

            // 2.调用废除旧版本接口
            this.service.disableoldplan((InspectionPlanHeadVO) billvos[0].getHead());
        } catch (Exception e) {
        	log.error(e.getMessage());
            result = ExceptionResult.process(e);
        }

        return result;
    }

    /**
     * datatable 多行删除实现。
     *
     * @param sysDictTypeDataTable
     * @param response
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public @ResponseBody
    Result delete(@RequestBody BaseDTO dto) {
        Result result = new Result();
        try {
            // 获取表头
            Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
            classMap.put(EntityConst.HEAD, InspectionPlanHeadVO.class);

            Map<String, MeSuperVO[]> voMap = this.convertDTO2VO(classMap, dto);
            if (MapUtils.isEmpty(voMap)) {
                ExceptionUtils.wrapBusinessException("没有数据！");
            }
            InspectionPlanHeadVO[] headvos = (InspectionPlanHeadVO[]) voMap
                    .get(EntityConst.HEAD);
            if (headvos == null || headvos.length == 0) {
                ExceptionUtils.wrapBusinessException("表头数据为空，无法保存！");
            }

            List<String> ids = new ArrayList<String>();
            // 记录界面数据id和ts映射
            Map<String, Timestamp> tsMap = new HashMap<String, Timestamp>();

            for (InspectionPlanHeadVO headvo : headvos) {
                ids.add(headvo.getId());
                tsMap.put(headvo.getId(), headvo.getTs());
            }
            // 根据表头id查询主子表
            InspectionPlanBillVO[] billvos = this.service.query(ids);

            for (InspectionPlanBillVO billvo : billvos) {
                // 前台ts赋值，用于校验ts
                if (ids.contains(billvo.getHead().getId())) {
                    billvo.getHead().setTs(tsMap.get(billvo.getHead().getId()));
                } else {
                    ExceptionUtils.wrapBusinessException("刪除對象不存在");
                }
            }

            service.batchDeleteByPrimaryKey(billvos);
        } catch (Exception ex) {
        	log.error(ex.getMessage());
            // 将异常转换为返回信息，并且记入后台日志
            result = ExceptionResult.process(ex);
        }
        return result;
    }

    /**
     * 多行停用
     *
     * @param sysDictTypeDataTable
     * @param response
     * @return
     */
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public Object disable(@RequestBody BaseDTO dto) {
        Result result = new Result();
        try {
            // 获取表头
            Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
            classMap.put(EntityConst.HEAD, InspectionPlanHeadVO.class);

            Map<String, MeSuperVO[]> voMap = this.convertDTO2VO(classMap, dto);
            if (MapUtils.isEmpty(voMap)) {
                ExceptionUtils.wrapBusinessException("没有数据！");
            }
            InspectionPlanHeadVO[] headvos = (InspectionPlanHeadVO[]) voMap
                    .get(EntityConst.HEAD);
            if (headvos == null || headvos.length == 0) {
                ExceptionUtils.wrapBusinessException("表头数据为空，无法停用！");
            }

            List<String> ids = new ArrayList<String>();

            for (InspectionPlanHeadVO headvo : headvos) {
                ids.add(headvo.getId());
            }
            // 根据表头id查询主子表
            InspectionPlanBillVO[] vos = this.service.query(ids);

            List<InspectionPlanHeadVO> list = new ArrayList<InspectionPlanHeadVO>();

            for (InspectionPlanBillVO vo : vos) {
                InspectionPlanHeadVO headVO = (InspectionPlanHeadVO) vo.getHead();
                headVO.setEnablestate(0);
                headVO.setInvalidate(new Timestamp(new Date().getTime()));
                headVO.setModifiedtime(new Timestamp(new Date().getTime()));
                list.add(headVO);
            }

            // 2.调用批量停用接口
            this.service.batchDisableByPrimaryKey(list);

        } catch (Exception e) {
        	log.error(e.getMessage());
            result = ExceptionResult.process(e);
        }
        return result;
    }

    /**
     * 多行启用
     *
     * @param sysDictTypeDataTable
     * @param response
     * @return
     */
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public Object enable(@RequestBody BaseDTO dto) {
        Result result = new Result();
        try {
            // 获取表头
            Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
            classMap.put(EntityConst.HEAD, InspectionPlanHeadVO.class);

            Map<String, MeSuperVO[]> voMap = this.convertDTO2VO(classMap, dto);
            if (MapUtils.isEmpty(voMap)) {
                ExceptionUtils.wrapBusinessException("没有数据！");
            }
            InspectionPlanHeadVO[] headvos = (InspectionPlanHeadVO[]) voMap
                    .get(EntityConst.HEAD);
            if (headvos == null || headvos.length == 0) {
                ExceptionUtils.wrapBusinessException("表头数据为空，无法启用！");
            }

            List<String> ids = new ArrayList<String>();

            for (InspectionPlanHeadVO headvo : headvos) {
                ids.add(headvo.getId());
            }
            // 根据表头id查询主子表
            InspectionPlanBillVO[] vos = this.service.query(ids);

            List<InspectionPlanHeadVO> list = new ArrayList<InspectionPlanHeadVO>();

            for (InspectionPlanBillVO vo : vos) {
                InspectionPlanHeadVO headVO = (InspectionPlanHeadVO) vo.getHead();
                headVO.setEnablestate(1);
                headVO.setInvalidate(null);
                headVO.setModifiedtime(new Timestamp(new Date().getTime()));
                list.add(headVO);
            }

            // 2.调用批量启用接口
            this.service.batchEnableByPrimaryKey(list);
        } catch (Exception e) {
        	log.error(e.getMessage());
            result = ExceptionResult.process(e);
        }
        return result;
    }

    private void copyDisplayName(BaseDTO dto, Map<String, MeSuperVO[]> voMap) {
        Map<String, ViewArea> data = dto.getData();

        Map<String, IDValueConvert> idValueMap = this.getIdValueMap();

        // 调用公共规则复制档案主键对应的显示名称
        this.copyDisplayName(data, voMap, idValueMap);
    }

    private Map<String, IDValueConvert> getIdValueMap() {
        Map<String, IDValueConvert> idValueMap = new HashMap<String, IDValueConvert>();

        // 创建档案主键与档案名称字段的映射关系
        IDValueConvert convert = new IDValueConvert();
        for (String key : headDisplayID2Name.keySet()) {
            convert.add(key, headDisplayID2Name.get(key));
        }

        IDValueConvert bodyconvert = new IDValueConvert();
        for (String key : bodyDisplayID2Name.keySet()) {
            bodyconvert.add(key, bodyDisplayID2Name.get(key));
        }

        // 注册前端区域的主键名称的翻译器
        idValueMap.put(EntityConst.HEAD, convert);
        idValueMap.put(EntityConst.BODY, bodyconvert);

        return idValueMap;
    }

    /**
     * dto -> vo，若有多个表头则只取一条
     */
    private List<InspectionPlanBillVO> dtoToVO(BaseDTO dto) {
        // 获取表头表体
        Map<String, MeSuperVO[]> voMap = this.convertDTO2VO(classMap, dto);
        if (MapUtils.isEmpty(voMap)) {
            ExceptionUtils.wrapBusinessException("没有数据！");
        }
        // 补全档案名称
        this.copyDisplayName(dto, voMap);

        InspectionPlanHeadVO[] headvos = (InspectionPlanHeadVO[]) voMap
                .get(EntityConst.HEAD);
        if (headvos == null || headvos.length == 0) {
            ExceptionUtils.wrapBusinessException("表头数据为空，无法保存！");
        }
        MeSuperVO[] bodyvos = voMap.get(EntityConst.BODY);

        InspectionPlanBillVO vos = new InspectionPlanBillVO();

        vos.setHead(headvos[0]);
        if (null != bodyvos && bodyvos.length > 0) {
            List<MeSuperVO> list = java.util.Arrays.asList(bodyvos);
            vos.setChildren(InspectionPlanBodyVO.class, list);
        }

        return new ArrayList<InspectionPlanBillVO>() {
            {
                add(vos);
            }
        };

    }

    /**
     * 业务中的vo转换为传送给前端的dto，处理映射关系
     *
     * @return 发送给前端的Result对象
     */
    private Result voToDTO(InspectionPlanBillVO bill) {
        Result result = new Result();
        // 创建Json字段名到VO对象的映射
        Map<String, MeSuperVO[]> voMap = new HashMap<>();
        List<MeSuperVO> heads = new ArrayList<>(), bodies = new ArrayList<>();

        heads.add(bill.getHead());
        if (bill.getChildren(InspectionPlanBodyVO.class) != null) {
            bodies.addAll(bill.getChildren(InspectionPlanBodyVO.class));
        }
        voMap.put(EntityConst.HEAD, heads.toArray(new MeSuperVO[0]));
        voMap.put(EntityConst.BODY, bodies.toArray(new MeSuperVO[0]));
        // 将查询的实体VO数据按照前端页面需要的属性名转换为前端数据结构
        Map<String, ViewArea> data = this.convertVO2DTO(classMap, voMap,
                nameMap);
        // 处理显示名称和精度（档案可能不需要，直接去掉）
        this.afterProcess(data, voMap);

        result.setData(data);
        return result;
    }

    private void afterProcess(Map<String, ViewArea> data,
                              Map<String, MeSuperVO[]> voMap) {
        // 处理前端页面数值精度
        this.setPrecision(data);

        // 处理前端枚举和档案的显示名称
        this.setDisplayName(data, voMap);
    }

    private void setPrecision(Map<String, ViewArea> data) {
        // 设置每个区域需要处理精度的数值字段
        Map<String, String[]> precisionMap = new HashMap<String, String[]>();
        String[] names = this
                .getDecimalTypeAttributes(InspectionPlanHeadVO.class);
        precisionMap.put(EntityConst.HEAD, names);

        // 调用公共规则处理精度
        this.setPrecision(data, precisionMap);
    }

    private void setDisplayName(Map<String, ViewArea> data,
                                Map<String, MeSuperVO[]> voMap) {

        Map<String, IDValueConvert> idValueMap = this.getIdValueMap();

        // 调用公共规则设置档案主键的显示名称
        this.setDisplayName(data, voMap, idValueMap);

//		// 调用公共规则设置非冗余档案主键的显示名称
//		DocMeta[] hmetas = new DocMeta[1];
//		hmetas[0] = new DocMeta("currtypeid", BaseDocType.Currency);
//
//		Map<String, DocMeta[]> docmap = new HashMap<String, DocMeta[]>();
//		docmap.put("head", hmetas);
//
//		IDocIDConvertor docConvertor = new BaseDocIDConvertor();
//		DocIDConvertor convertor = new DocIDConvertor(docConvertor);
//		convertor.setDisplay(data, docmap);
    }

}
