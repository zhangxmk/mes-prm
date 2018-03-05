package com.yonyou.mes.prm.core.inspectiontask.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;

/**
 * @Description 巡检任务表体持久化
 * 
 * @author guojunf
 * @date 2018年2月7日
 * @version V1.0
 */
public class TaskBodyPersistent implements IVOPersistent {

	/**
	 * 子实体的数据库访问器
	 */
	private TaskBodyMapper dao;

	/**
	 * 子实体的数据库操作构造函数
	 * 
	 * @param dao
	 *            子实体的数据库访问器
	 */
	public TaskBodyPersistent(TaskBodyMapper dao) {
		this.dao = dao;
	}

	@Override
	public void delete(List<String> list) {
		this.dao.batchDeleteByPrimaryKey(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(List<?> list) {
		List<InspectionTaskBodyVO> voList = (List<InspectionTaskBodyVO>) list;
		this.dao.batchInsert(voList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<?> list) {
		List<InspectionTaskBodyVO> voList = (List<InspectionTaskBodyVO>) list;
		this.dao.batchUpdate(voList);
	}

}
