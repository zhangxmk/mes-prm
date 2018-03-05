package com.yonyou.mes.prm.core.inspectionplan.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBodyVO;

public class InspectionPlanBodyPersistent implements IVOPersistent {

	private InspectionPlanBodyMapper dao;

	public InspectionPlanBodyPersistent(InspectionPlanBodyMapper dao) {
		this.dao = dao;
	}

	@Override
	public void delete(List<String> list) {
		this.dao.batchDeleteByPrimaryKey(list);

	}

	@Override
	public void insert(List<?> list) {
		List<InspectionPlanBodyVO> voList = (List<InspectionPlanBodyVO>) list;
		this.dao.batchInsert(voList);
	}

	@Override
	public void update(List<?> list) {
		List<InspectionPlanBodyVO> voList = (List<InspectionPlanBodyVO>) list;
		this.dao.batchUpdate(voList);

	}

}
