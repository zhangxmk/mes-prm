package com.yonyou.mes.prm.temptask.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.iweb.entity.DataTable;
import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.me.base.BaseController;
import com.yonyou.me.constance.EntityConst;
import com.yonyou.me.entity.MeSuperVO;
import com.yonyou.me.entity.VOUtil;
import com.yonyou.me.utils.dto.BaseDTO;
import com.yonyou.me.utils.dto.ExceptionResult;
import com.yonyou.me.utils.dto.PageVO;
import com.yonyou.me.utils.dto.Result;
import com.yonyou.me.utils.dto.ViewArea;
import com.yonyou.me.utils.exception.ExceptionUtils;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBillVO;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBodyVO;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskHeadVO;
import com.yonyou.mes.prm.core.temptask.service.ITempTaskService;

/**
 * controller层
 */
@RestController
@RequestMapping(value = "/er/temptask")
public class TempTaskController extends BaseController {

	@Autowired
	private ITempTaskService service;

	/**
	 * classMap和nameMap分别保存Json子段名到VO类、类中所有字段的映射
	 */
	private final Map<String, Class<?>> classMap = new HashMap<String, Class<?>>() {
		{
			put(EntityConst.HEAD, TempTaskHeadVO.class);
			put(EntityConst.BODY, TempTaskBodyVO.class);
		}
	};

	private final Map<String, String[]> nameMap = new HashMap<String, String[]>() {
		{
			put(EntityConst.HEAD,
					VOUtil.AllClassFields(TempTaskHeadVO.class));
			put(EntityConst.BODY,
					VOUtil.AllClassFields(TempTaskBodyVO.class));
		}
	};

	/**
	 * 查询表头
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Object pageString(@RequestBody PageVO pageVO) {

		Result result = new Result();
		try {
			PageRequest pageRequest = pageVO.getPageRequest();
			SearchParams searchParams = pageVO.getSearchParams();
			if (pageRequest == null || searchParams == null) {
				ExceptionUtils.wrapBusinessException("当前参数数据有误");
			}

			Page<TempTaskHeadVO> pageVOs = service.selectAllByPage(
					pageVO.getPageRequest(), pageVO.getSearchParams());
			Map<String, Page<?>> voMap = new HashMap<String, Page<?>>();
			voMap.put(EntityConst.HEAD, pageVOs);

			Map<String, ViewArea> data = this.convertPageVO2DTO(classMap,
					voMap, nameMap);

			Map<String, MeSuperVO[]> voIndex = this.convertToVOMap(voMap);
			// 补充名称和精度
			// this.afterProcess(data, voIndex);
			result.setData(data);
		} catch (Exception ex) {
			result = ExceptionResult.process(ex);
		}
		return result;
	}

	/**
	 * 根据表头查询表体数据
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/queryBodyByParentid", method = RequestMethod.POST)
	  public @ResponseBody Object queryBodyByParentid(@RequestBody PageVO page) {

	    // 创建返回信息
	    Result result = new Result();

	    try {
	      PageRequest pageRequest = page.getPageRequest();
	      SearchParams searchParams = page.getSearchParams();
	      if (pageRequest == null || searchParams == null) {
	        ExceptionUtils.wrapBusinessException("当前参数数据有误");
	      }
	      //查询表体数据
	      Page<TempTaskBodyVO> pageVOs = service.queryBodyByPage(pageRequest, searchParams);
	      Map<String, Page<?>> voMap = new HashMap<String, Page<?>>();
	      voMap.put(EntityConst.BODY, pageVOs);

	      Map<String, ViewArea> data = this.convertPageVO2DTO(classMap, voMap, nameMap);

	      Map<String, MeSuperVO[]> voIndex = this.convertToVOMap(voMap);
//	      this.afterProcess(data, voIndex);
	      result.setData(data);
	    } catch (Exception ex) {
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
			List<TempTaskBillVO> list = this.dtoToVO(dto);
			if (list == null || list.size() == 0) {
				throw new Exception("传入数据为空");
			}
			TempTaskBillVO vo = list.get(0);
			// 2.调用保存接口
			TempTaskBillVO resultData = this.service.add(vo);
			// 3.保存结果转化成返回值结构
			result = this.voToDTO(resultData);
		} catch (Exception e) {
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
			List<TempTaskBillVO> list = this.dtoToVO(dto);
			if (list == null || list.size() == 0) {
				throw new Exception("传入数据为空");
			}
			TempTaskBillVO vo = list.get(0);
			// 2.调用保存接口
			TempTaskBillVO resultData = this.service.update(vo);
			// 3.保存结果转化成返回值结构
			result = this.voToDTO(resultData);
		} catch (Exception e) {
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
	public @ResponseBody Result delete(@RequestBody BaseDTO dto) {
		Result result = new Result();
		try {
			// 获取表头
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put(EntityConst.HEAD, TempTaskHeadVO.class);

			Map<String, MeSuperVO[]> voMap = this.convertDTO2VO(classMap, dto);
			if (MapUtils.isEmpty(voMap)) {
				ExceptionUtils.wrapBusinessException("没有数据！");
			}
			TempTaskHeadVO[] headvos = (TempTaskHeadVO[]) voMap
					.get(EntityConst.HEAD);
			if (headvos == null || headvos.length == 0) {
				ExceptionUtils.wrapBusinessException("表头数据为空，无法保存！");
			}

			List<String> ids = new ArrayList<String>();
			// 记录界面数据id和ts映射
			Map<String, Timestamp> tsMap = new HashMap<String, Timestamp>();

			for (TempTaskHeadVO headvo : headvos) {
				ids.add(headvo.getId());
				tsMap.put(headvo.getId(), headvo.getTs());
			}
			// 根据表头id查询主子表
			TempTaskBillVO[] billvos = this.service.query(ids);

			for (TempTaskBillVO billvo : billvos) {
				// 前台ts赋值，用于校验ts
				if (ids.contains(billvo.getHead().getId())) {
					billvo.getHead().setTs(tsMap.get(billvo.getHead().getId()));
				} else {
					ExceptionUtils.wrapBusinessException("刪除對象不存在");
				}
			}

			service.batchDeleteByPrimaryKey(billvos);
		} catch (Exception ex) {
			// 将异常转换为返回信息，并且记入后台日志
			result = ExceptionResult.process(ex);
		}
		return result;
	}

	/**
	 * 获取datatable的查询过滤条件
	 * 
	 * @param sysDictTypeDataTable
	 * @param prefix
	 * @return
	 */
	private Map<String, Object> createSearchParamsStartingWith(
			DataTable dataTable, String prefix) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> dtParam = dataTable.getParams();
		Set<Map.Entry<String, Object>> set = dtParam.entrySet();
		for (Map.Entry<String, Object> entry : set) {
			String key = entry.getKey();
			if (key.startsWith(prefix)) {
				String unprefixed = key.substring(prefix.length());
				params.put(unprefixed, entry.getValue().toString());
			}
		}
		return params;
	}

	/**
	 * dto -> vo，若有多个表头则只取一条
	 */
	private List<TempTaskBillVO> dtoToVO(BaseDTO dto) {
		// 获取表头表体
		Map<String, MeSuperVO[]> voMap = this.convertDTO2VO(classMap, dto);
		if (MapUtils.isEmpty(voMap)) {
			ExceptionUtils.wrapBusinessException("没有数据！");
		}
		// // 补全档案名称
		// this.copyDisplayName(dto, voMap);
		TempTaskHeadVO[] headvos = (TempTaskHeadVO[]) voMap
				.get(EntityConst.HEAD);
		if (headvos == null || headvos.length == 0) {
			ExceptionUtils.wrapBusinessException("表头数据为空，无法保存！");
		}
		MeSuperVO[] bodyvos = voMap.get(EntityConst.BODY);

		TempTaskBillVO vos = new TempTaskBillVO();

		vos.setHead(headvos[0]);
		if (null != bodyvos && bodyvos.length > 0) {
			List<MeSuperVO> list = java.util.Arrays.asList(bodyvos);
			vos.setChildren(TempTaskBodyVO.class, list);
		}

		return new ArrayList<TempTaskBillVO>() {
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
	private Result voToDTO(TempTaskBillVO bill) {
		Result result = new Result();
		// 创建Json字段名到VO对象的映射
		Map<String, MeSuperVO[]> voMap = new HashMap<>();
		List<MeSuperVO> heads = new ArrayList<>(), bodies = new ArrayList<>();

		heads.add(bill.getHead());
		bodies.addAll(bill.getChildren(TempTaskBodyVO.class));

		voMap.put(EntityConst.HEAD, heads.toArray(new MeSuperVO[0]));
		voMap.put(EntityConst.BODY, bodies.toArray(new MeSuperVO[0]));
		// 将查询的实体VO数据按照前端页面需要的属性名转换为前端数据结构
		Map<String, ViewArea> data = this.convertVO2DTO(classMap, voMap,
				nameMap);
		// // 处理显示名称和精度（档案可能不需要，直接去掉）
		// this.afterProcess(data, voMap);

		result.setData(data);
		return result;
	}

	// /**
	// * 设置客户端数据精度
	// * @param data
	// */
	// private void setPrecision(Map<String, ViewArea> data) {
	// // 设置每个区域需要处理精度的数值字段
	// Map<String, String[]> precisionMap = new HashMap<>();
	// precisionMap.put(BondInterestBillConstant.INTERESTBILL_HEAD,
	// BondInterestBillConstant.getHeadField());
	// precisionMap.put(BondInterestBillConstant.INTERESTBILL_BODY,
	// BondInterestBillConstant.getBodyField());
	// this.setPrecision(data, precisionMap);
	// }
	//
	// /**
	// * 设置客户端数据显示名
	// * @param data
	// * @param voMap
	// */
	// private void setDisplayName(Map<String, ViewArea> data, Map<String,
	// TmcSuperVO[]> voMap) {
	// Map<String, IdValueConvert> idValueMap = getDisplayNameMap();
	// // 调用公共规则设置档案主键的显示名称
	// this.setDisplayName(data, voMap, idValueMap);
	// }
	//
	// /**
	// * 获取显示名转换器（冗余字段）
	// * @return 每个需要转换的字段对应的转换器
	// */
	// private Map<String, IdValueConvert> getDisplayNameMap() {
	// Map<String, IdValueConvert> idValueMap = new HashMap<>();
	// idValueMap.put(BondInterestBillConstant.INTERESTBILL_HEAD,
	// BondInterestBillConstant.getHeadRefConvert());
	// return idValueMap;
	// }

}
