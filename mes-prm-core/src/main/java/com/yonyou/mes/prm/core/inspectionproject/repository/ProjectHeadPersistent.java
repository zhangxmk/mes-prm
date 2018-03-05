package com.yonyou.mes.prm.core.inspectionproject.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;

/**
 * @Description 巡检项目表头持久化
 * 
 * @author guojunf
 * @date 2018年2月7日
 * @version V1.0
 */
public class ProjectHeadPersistent implements IVOPersistent {

	/**
	 * 巡检项目表头的数据库访问器
	 */
	private ProjectHeadMapper dao;

	/**
	 * 构造器
	 * 
	 * @param dao
	 */
	public ProjectHeadPersistent(ProjectHeadMapper dao) {
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
		List<InspectionProjectHeadVO> voList = (List<InspectionProjectHeadVO>) list;
		// 只添加一条数据
		this.dao.insertSelective(voList.get(0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<?> list) {
		List<InspectionProjectHeadVO> voList = (List<InspectionProjectHeadVO>) list;
		this.dao.updateByPrimaryKey(voList.get(0));
	}

}
