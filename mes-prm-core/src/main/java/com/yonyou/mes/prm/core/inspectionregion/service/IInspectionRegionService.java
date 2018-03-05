package com.yonyou.mes.prm.core.inspectionregion.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.mes.prm.core.inspectionregion.entity.InspectionRegionBillVO;
import com.yonyou.mes.prm.core.inspectionregion.entity.InspectionRegionVO;

/**
 * 
 * @description 巡检区域服务接口
 *
 * @author wangkem
 * 2018年2月7日
 */

public interface IInspectionRegionService {

	/**
	 * 分页查询方法
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<InspectionRegionVO> selectAllByPage(
			PageRequest pageRequest, SearchParams searchParams);

	/**
	 * 查询表体信息
	 * 
	 * @return
	 *//*
	public Page<InspectionRegionVO> queryBodyByPage(
			PageRequest pageRequest,SearchParams searchParams);*/

	/**
	 * 新增
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionRegionBillVO add(InspectionRegionBillVO vo);

	/**
	 * 更新
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionRegionBillVO update(InspectionRegionBillVO vo);

	/**
	 * 批量删除
	 * 
	 * @param list
	 */
	@Transactional
	public void batchDeleteByPrimaryKey(InspectionRegionVO[] vos);

	/**
	 * 根据主表主键查询主子数据
	 * 
	 * @param id
	 * @return
	 */
	public InspectionRegionBillVO[] query(List<String> ids);
}
