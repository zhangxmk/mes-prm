package com.yonyou.mes.prm.inspectionregion.web;

import com.alibaba.fastjson.JSON;
import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.ref.info.RefClientPageInfo;
import com.yonyou.iuap.ref.model.RefViewModelVO;
import com.yonyou.iuap.ref.sdk.refmodel.model.AbstractGridRefModel;
import com.yonyou.me.utils.context.MeInvocationInfoProxy;
import com.yonyou.me.utils.exception.ExceptionUtils;
import com.yonyou.mes.prm.core.inspectionregion.entity.InspectionRegionVO;
import com.yonyou.mes.prm.core.inspectionregion.service.IInspectionRegionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * 区域参照
 *
 * @author se2mi
 */
@Controller
@RequestMapping({ "/prm/inspectregref" })
public class InspectionRegionRefController extends AbstractGridRefModel{

    @Autowired
    private IInspectionRegionService inspectRegionService;

    /**
     * 运行时日志记录
     */
    private static final Logger log = LoggerFactory
            .getLogger(InspectionRegionController.class);

    @Override
    public List<Map<String, String>> filterRefJSON(RefViewModelVO refViewModelVO) {
        return null;
    }

    @Override
    public RefViewModelVO getRefModelInfo(RefViewModelVO refViewModel) {
        RefViewModelVO refModel = super.getRefModelInfo(refViewModel);
        //
        refModel.setStrFieldCode(new String[] { "code", "name"});

        refModel.setStrHiddenFieldCode(new String[] { "id", "refpk"});

        refModel.setStrFieldName(new String[] { "项目编码", "项目名称"});

        refModel.setRootName("巡检区域");
        return refModel;
    }

    @Override
    public List<Map<String, String>> matchPKRefJSON(RefViewModelVO refViewModelVO) {
        return null;
    }

    @Override
    public List<Map<String, String>> matchBlurRefJSON(RefViewModelVO refModel) {
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        try {
            String tenantId = MeInvocationInfoProxy.getTenantid();
            String sysId = MeInvocationInfoProxy.getSysid();
            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("tenantId", tenantId);// 租户id
            condition.put("sysId", sysId);// 系统id
            String content = refModel.getContent();
            @SuppressWarnings("unchecked")
            Map<String, String> json = JSON.parseObject(content, Map.class);
            Map<String, String> params = new HashMap<String, String>();
            Set<Map.Entry<String, String>> set = json.entrySet();
            for (Map.Entry<String, String> param : set) {
                Object value = param.getValue();
                if (value != null && value != "") {
                    if (param.getKey().equals("code")) {
                        params.put("code", "%" + value + "%");
                    } else if (param.getKey().equals("name")) {
                        continue;
                    } else {
                        params.put(param.getKey(), "%" + value + "%");
                    }
                }
            }
            condition.put("params", params);

        } catch (Exception e) {
            log.error("获取数据异常：", e);
            ExceptionUtils.wrapBusinessException("获取数据异常");
        }
        return results;
    }

    @Override
    public Map<String, Object> getCommonRefData(@RequestBody RefViewModelVO refModel) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            int pageNum = refModel.getRefClientPageInfo().getCurrPageIndex() == 0 ? 1
                    : refModel.getRefClientPageInfo().getCurrPageIndex();

            int pageSize = 10; // refModel.getRefClientPageInfo().getPageSize();

            PageRequest request = buildPageRequest(pageNum, pageSize, null);
            // 获取过滤条件
            String clientParam = refModel.getClientParam();
            Map<String, Object> json = JSON.parseObject(clientParam, Map.class);
            // String searchParam = StringUtils.isEmpty(refModel.getContent()) ?
            // null : refModel.getContent();
            SearchParams param = buildSearchParam(json);
            Page<InspectionRegionVO> headpage = inspectRegionService
                    .selectAllByPage(request, param);
            List<InspectionRegionVO> headVOs = headpage.getContent();
            if (CollectionUtils.isNotEmpty(headVOs)) {
                List<Map<String, String>> list = buildRtnValsOfRef(headVOs);

                RefClientPageInfo refClientPageInfo = refModel
                        .getRefClientPageInfo();
                refClientPageInfo.setPageCount(headpage.getTotalPages());
                refClientPageInfo.setPageSize(pageSize);
                refClientPageInfo.setCurrPageIndex(pageNum - 1);
                refModel.setRefClientPageInfo(refClientPageInfo);

                results.put("dataList", list);
                results.put("refViewModel", refModel);
            }
        } catch (Exception e) {
            log.error("获取数据异常：", e);
            ExceptionUtils.wrapBusinessException("获取数据异常");
        }
        return results;
    }

    private PageRequest buildPageRequest(int pageNum, int pageSize,
                                         String sortColumn) {
        Sort sort = null;
        if (("auto".equalsIgnoreCase(sortColumn))
                || (StringUtils.isEmpty(sortColumn))) {
            sort = new Sort(Sort.Direction.ASC, "ts");
        } else {
            sort = new Sort(Sort.Direction.DESC, sortColumn);
        }
        return new PageRequest(pageNum - 1, pageSize, sort);
    }

    /**
     * 组装查询参数
     *
     * @param json
     * @return
     */
    private SearchParams buildSearchParam(Map<String, Object> json) {
        SearchParams param = new SearchParams();

        Map<String, Object> results = new HashMap<String, Object>();
        String tenantid = MeInvocationInfoProxy.getTenantid();
        String sysid = MeInvocationInfoProxy.getSysid();
        if (tenantid != null) {
            results.put("tenantid", tenantid);
            results.put("sysid", sysid);
        }
        if (json != null && json.containsKey("status")) {
            results.put("status", json.get("status"));
        }
        param.setSearchMap(results);
        return param;
    }

    /**
     * 过滤完的数据组装
     *
     * @param headVOs
     * @return
     */
    private List<Map<String, String>> buildRtnValsOfRef(
            List<InspectionRegionVO> headVOs) {

        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        if ((headVOs != null) && (!headVOs.isEmpty())) {
            for (InspectionRegionVO entity : headVOs) {
                Map<String, String> refDataMap = new HashMap<String, String>();

                refDataMap.put("id", entity.getId());
                refDataMap.put("refpk", entity.getId());
                refDataMap.put("code", entity.getCode());
                refDataMap.put("name", entity.getName());


                results.add(refDataMap);
            }
        }
        return results;
    }
}
