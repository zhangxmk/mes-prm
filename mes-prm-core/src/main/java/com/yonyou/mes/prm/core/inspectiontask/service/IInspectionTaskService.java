package com.yonyou.mes.prm.core.inspectiontask.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBillVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.TaskForTableVO;

/**
 * 巡检任务service
 * @author weijw
 *
 * 2018年3月1日
 */
public interface IInspectionTaskService {
	/**
	 * 分页查询方法
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<InspectionTaskHeadVO> selectAllByPage(PageRequest pageRequest,
			SearchParams searchParams);
	
	/**
	 * 巡检完成情况统计表数据
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<TaskForTableVO> selectForStaTable(PageRequest pageRequest,
			SearchParams searchParams);

	/**
	 * 查询表体信息
	 * 
	 * @return
	 */
	public Page<InspectionTaskBodyVO> queryBodyByPage(PageRequest pageRequest,
			SearchParams searchParams);

	
	/**
	 * 查询巡检项目及内容
	 * 
	 * @return
	 */
	public Page<InspectionTaskBodyVO> queryProjectAndContent(String planid);
	
	
	/**
	 * 新增
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionTaskBillVO add(InspectionTaskBillVO vo);

	/**
	 * 更新
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionTaskBillVO update(InspectionTaskBillVO vo);

	/**
	 * 批量删除
	 * 
	 * @param list
	 */
	@Transactional
	public void batchDeleteByPrimaryKey(InspectionTaskBillVO[] vos);

	/**
	 * 根据主表主键查询主子数据
	 * 
	 * @param id
	 * @return
	 */
	public InspectionTaskBillVO[] query(List<String> ids);
	
	/**
	 * 根据岗位Id查询任务中的项目明细
	 * 
	 * @param id
	 * @return
	 */
	public InspectionTaskBodyVO[] queryTaskDetails(String postid);
	
	/**

	 * 
	 * @param planid
	 * @return
	 */
	public InspectionTaskBillVO createTaskBill(String planid);
	/*
	 * 根据岗位Id查询任务中的表头
	 * 
	 * @param id
	 * @return
	 */
	public Page<InspectionTaskHeadVO> queryTask(String postid);
	
	
	/**
	 * 根据主表主键查询任务中的项目明细
	 * 
	 * @param id
	 * @return
	 */
	public InspectionTaskBodyVO[] queryTaskDetailsByID(List<String> ids);
	
	
	/**
	 * 根据表头id查表头
	 */
	public InspectionTaskHeadVO queryHeadById(String id);
	
	/**
	 * 更新标头
	 * 
	 * @param vo
	 * @return
	 */
	public void updateHead(InspectionTaskHeadVO vo);

}
