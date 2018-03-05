package com.yonyou.mes.prm.core.inspectionplan.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanHeadVO;

/**
 * 
 * @author weijw
 *
 * 2018年2月7日
 */
public class InspectionPlanHeadPersistent implements IVOPersistent {

	private InspectionPlanHeadMapper dao;
	
	public InspectionPlanHeadPersistent(InspectionPlanHeadMapper dao){
		this.dao=dao;
	}
	
	@Override
	public void delete(List<String> list) {
		  for(String id :list) {
			  this.dao.deleteByPrimaryKey(id);
		  }
	}

	@Override
	public void insert(List<?> list) {
		List<InspectionPlanHeadVO> voList = (List<InspectionPlanHeadVO>) list;
	    //只添加一条数据
	    this.dao.insertSelective(voList.get(0));
	}

	@Override
	public void update(List<?> list) {
	    List<InspectionPlanHeadVO> voList = (List<InspectionPlanHeadVO>) list;
	    this.dao.updateByPrimaryKey(voList.get(0));
	}

}
