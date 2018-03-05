package com.yonyou.mes.prm.core.inspectiontask.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;

/**
 * @Description 巡检任务表头持久化
 * 
 * @author guojunf
 * @date 2018年2月7日
 * @version V1.0
 */
public class TaskHeadPersistent implements IVOPersistent {

	/**
	 * 巡检任务表头的数据库访问器
	 */
	private TaskHeadMapper dao;

	/**
	 * 构造器
	 * 
	 * @param dao
	 */
	public TaskHeadPersistent(TaskHeadMapper dao) {
		this.dao = dao;
	}

	@Override
	public void delete(List<String> list) {
		for (String id : list) {
			this.dao.deleteByPrimaryKey(id);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(List<?> list) {
		List<InspectionTaskHeadVO> voList = (List<InspectionTaskHeadVO>) list;
		// 只添加一条数据
		this.dao.insertSelective(voList.get(0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<?> list) {
		List<InspectionTaskHeadVO> voList = (List<InspectionTaskHeadVO>) list;
		this.dao.updateByPrimaryKey(voList.get(0));
	}

}
