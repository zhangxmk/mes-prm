package com.yonyou.mes.prm.core.inspectionproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBillVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBodyVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;

/**
 * @Description 巡检项目service接口
 * 
 * @author guojunf
 * @version 1.0
 * @date 2018年2月6日
 */
public interface IInspectionProjectService {
	/**
	 * 分页查询方法
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<InspectionProjectHeadVO> selectAllByPage(PageRequest pageRequest,
			SearchParams searchParams);

	/**
	 * 查询表体信息
	 * 
	 * @return
	 */
	public Page<InspectionProjectBodyVO> queryBodyByPage(PageRequest pageRequest,
			SearchParams searchParams);

	/**
	 * 新增
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionProjectBillVO add(InspectionProjectBillVO vo);

	/**
	 * 更新
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionProjectBillVO update(InspectionProjectBillVO vo);

	/**
	 * 批量删除
	 * 
	 * @param list
	 */
	public void batchDeleteByPrimaryKey(InspectionProjectBillVO[] vos);

	/**
	 * 根据主表主键查询主子数据
	 * 
	 * @param id
	 * @return
	 */
	public InspectionProjectBillVO[] query(List<String> ids);
}
