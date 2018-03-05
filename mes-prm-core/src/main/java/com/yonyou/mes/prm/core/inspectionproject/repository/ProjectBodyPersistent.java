package com.yonyou.mes.prm.core.inspectionproject.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBodyVO;

/**
 * @Description 巡检项目表体持久化
 * 
 * @author guojunf
 * @date 2018年2月7日
 * @version V1.0
 */
public class ProjectBodyPersistent implements IVOPersistent {

	/**
	 * 子实体的数据库访问器
	 */
	private ProjectBodyMapper dao;

	/**
	 * 子实体的数据库操作构造函数
	 * 
	 * @param dao
	 *            子实体的数据库访问器
	 */
	public ProjectBodyPersistent(ProjectBodyMapper dao) {
		this.dao = dao;
	}

	@Override
	public void delete(List<String> list) {
		this.dao.batchDeleteByPrimaryKey(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(List<?> list) {
		List<InspectionProjectBodyVO> voList = (List<InspectionProjectBodyVO>) list;
		this.dao.batchInsert(voList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<?> list) {
		List<InspectionProjectBodyVO> voList = (List<InspectionProjectBodyVO>) list;
		this.dao.batchUpdate(voList);
	}

}
