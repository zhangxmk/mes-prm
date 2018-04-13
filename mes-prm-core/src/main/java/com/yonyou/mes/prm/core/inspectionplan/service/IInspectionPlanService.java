package com.yonyou.mes.prm.core.inspectionplan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBillVO;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBodyVO;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanHeadVO;

/**
 * 巡检方案service
 * @author weijw
 *
 * 2018年2月7日
 */
public interface IInspectionPlanService {
	/**
	 * 分页查询方法
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<InspectionPlanHeadVO> selectAllByPage(PageRequest pageRequest,
			SearchParams searchParams);
	
	/**
	 * 历史版本分页查询方法
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<InspectionPlanHeadVO> getModalDataByPage(PageRequest pageRequest,
			SearchParams searchParams);

	/**
	 * 查询表体信息
	 * 
	 * @return
	 */
	public Page<InspectionPlanBodyVO> queryBodyByPage(PageRequest pageRequest,
			SearchParams searchParams);

	/**
	 * 新增
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionPlanBillVO add(InspectionPlanBillVO vo);

	/**
	 * 更新
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionPlanBillVO update(InspectionPlanBillVO vo);
	
	/**
	 * 废除旧版本
	 * 
	 * @param vo
	 */
	@Transactional
	public void disableoldplan(InspectionPlanHeadVO vo);
	
	/**
	 * 版本变更
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionPlanBillVO vchange(InspectionPlanBillVO vo);

	/**
	 * 批量删除
	 * 
	 * @param list
	 */
	@Transactional
	public void batchDeleteByPrimaryKey(InspectionPlanBillVO[] vos);
	
	/**
	 * 批量停用
	 * 
	 * @param list
	 */
	@Transactional
	public void batchDisableByPrimaryKey(List<InspectionPlanHeadVO> list);
	
	/**
	 * 批量启用
	 * 
	 * @param list
	 */
	@Transactional
	public void batchEnableByPrimaryKey(List<InspectionPlanHeadVO> list);

	/**
	 * 根据主表主键查询主子数据
	 * 
	 * @param id
	 * @return
	 */
	public InspectionPlanBillVO[] query(List<String> ids);
	
	/**
	 * 根据巡检方案编码查询表头数据
	 * 
	 * @param code
	 * @return
	 */
	public InspectionPlanHeadVO[] selectByCodes(List<String> codes);
	
	/**
	 * 根据表头ids查询表体数据
	 * 
	 * @param ids
	 * @return
	 */
	public InspectionPlanBodyVO[] selectByParentKeys(List<String> ids);
	
	/**
	 * 根据项目编码查询表体数据
	 * 
	 * @param code
	 * @return
	 */
	public InspectionPlanBodyVO[] selectByBodyCode(String code);
	
	/**
	 * 根据主表ids查询表头数据
	 * 
	 * @param List<id>
	 * @return
	 */
	public InspectionPlanHeadVO[] selectByIDs(List<String> ids);
	
	/**
	 * 根据主表id查询表头数据
	 * 
	 * @param List<id>
	 * @return
	 */
	public InspectionPlanHeadVO selectByPrimaryKey(String id);
}
