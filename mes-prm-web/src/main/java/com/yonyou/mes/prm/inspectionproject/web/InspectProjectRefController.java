package com.yonyou.mes.prm.inspectionproject.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.ref.info.RefClientPageInfo;
import com.yonyou.iuap.ref.model.RefViewModelVO;
import com.yonyou.iuap.ref.sdk.refmodel.model.AbstractGridRefModel;
import com.yonyou.me.utils.context.MeInvocationInfoProxy;
import com.yonyou.me.utils.exception.ExceptionUtils;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;
import com.yonyou.mes.prm.core.inspectionproject.service.IInspectionProjectService;


/**
 * 巡检项目参照
 * 
 * @author guojunf
 *
 */
@Controller
@RequestMapping({ "/prm/inspectprjref" })
public class InspectProjectRefController extends AbstractGridRefModel {
	@Autowired
	private IInspectionProjectService inspectProjectService;
	/**
	 * 运行时日志记录
	 */
	private static final Logger log = LoggerFactory
			.getLogger(InspectProjectRefController.class);

	@Override
	public List<Map<String, String>> filterRefJSON(RefViewModelVO refModel) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getCommonRefData(
			@RequestBody RefViewModelVO refModel) {
		Map<String, Object> results = new HashMap<String, Object>();
		try {
			int pageNum = refModel.getRefClientPageInfo().getCurrPageIndex() == 0 ? 1
					: refModel.getRefClientPageInfo().getCurrPageIndex();

			int pageSize = refModel.getRefClientPageInfo().getPageSize();

			PageRequest request = buildPageRequest(pageNum, pageSize, null);
			// 获取过滤条件
			String clientParam = refModel.getClientParam();
			Map<String, Object> json = JSON.parseObject(clientParam, Map.class);
			// String searchParam = StringUtils.isEmpty(refModel.getContent()) ?
			// null : refModel.getContent();
			SearchParams param = buildSearchParam(json);
			Page<InspectionProjectHeadVO> headpage = inspectProjectService
					.selectAllByPage(request, param);
			List<InspectionProjectHeadVO> headVOs = headpage.getContent();
			if (CollectionUtils.isNotEmpty(headVOs)) {
				List<Map<String, String>> list = buildRtnValsOfRef(headVOs);

				RefClientPageInfo refClientPageInfo = refModel
						.getRefClientPageInfo();
				refClientPageInfo.setPageCount(headpage.getTotalPages());
				refClientPageInfo.setPageSize(pageSize);
				refClientPageInfo.setCurrPageIndex(pageNum);
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

	/**
	 * 分页
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortColumn
	 * @return
	 */
	private PageRequest buildPageRequest(int pageNum, int pageSize,
			String sortColumn) {
		Sort sort = null;
		if (("auto".equalsIgnoreCase(sortColumn))
				|| (StringUtils.isEmpty(sortColumn))) {
			sort = new Sort(Sort.Direction.ASC, new String[] { "ts" });
		} else {
			sort = new Sort(Sort.Direction.DESC, new String[] { sortColumn });
		}
		return new PageRequest(pageNum - 1, pageSize, sort);
	}

	/**
	 * 组装查询参数
	 * 
	 * @param searchParam
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
		if (json.containsKey("status")) {
			results.put("status", json.get("status"));
		}
		param.setSearchMap(results);
		return param;
	}

	/**
	 * 过滤完的数据组装
	 * 
	 * @param peoplelist
	 * @return
	 */
	private List<Map<String, String>> buildRtnValsOfRef(
			List<InspectionProjectHeadVO> headVOs) {

		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		if ((headVOs != null) && (!headVOs.isEmpty())) {
			for (InspectionProjectHeadVO entity : headVOs) {
				Map<String, String> refDataMap = new HashMap<String, String>();

				refDataMap.put("id", entity.getId());
				refDataMap.put("refpk", entity.getId());
				refDataMap.put("code", entity.getCode());
				refDataMap.put("name", entity.getName());
				refDataMap.put("pk_org", entity.getOrgid());
				refDataMap.put("pk_orgname", entity.getOrgid_name());

				results.add(refDataMap);
			}
		}
		return results;
	}

	@Override
	public List<Map<String, String>> matchBlurRefJSON(
			@RequestBody RefViewModelVO refModel) {
		// List<Map<String, String>> results = new ArrayList<Map<String,
		// String>>();
		// try {
		// String tenantId = MeInvocationInfoProxy.getTenantid();
		// String sysId = MeInvocationInfoProxy.getSysid();
		// Map<String, Object> condition = new HashMap<String, Object>();
		// condition.put("tenantId", tenantId);// 租户id
		// condition.put("sysId", sysId);// 系统id
		// String content = refModel.getContent();
		// @SuppressWarnings("unchecked")
		// Map<String, String> json = JSON.parseObject(content, Map.class);
		// Map<String, String> params = new HashMap<String, String>();
		// Set<Entry<String, String>> set = json.entrySet();
		// for (Entry<String, String> param : set) {
		// Object value = param.getValue();
		// if (value != null && value != "") {
		// if (param.getKey().equals("code")) {
		// params.put("code", "%" + value + "%");
		// } else if (param.getKey().equals("name")) {
		// continue;
		// } else {
		// params.put(param.getKey(), "%" + value + "%");
		// }
		// }
		// }
		// condition.put("params", params);
		// List<InspectionProjectHeadVO> rtnVal = inspectProjectService
		// .getByNameOrCode(condition);
		// results = buildRtnValsOfRef(rtnVal);
		// } catch (Exception e) {
		// log.error("获取数据异常：", e);
		// ExceptionUtils.wrapBusinessException("获取数据异常");
		// }
		// return results;

		return null;
	}

	@Override
	public List<Map<String, String>> matchPKRefJSON(RefViewModelVO refModel) {
		return null;
	}

}
