package com.yonyou.mes.prm.core.inspectionplan.entity;

import com.yonyou.me.constance.EntityConst;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.entity.ApproveMeta;
import com.yonyou.me.entity.BillCodeMeta;

/**
 * 巡检方案aggvo
 * @author weijw
 *
 * 2018年2月7日
 */
public class InspectionPlanBillVO  extends AbstractMeBillVO{

	@Override
	public ApproveMeta getApproveMeta() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public BillCodeMeta getBillCodeMeta() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	protected void initChildrenFK() {
		this.addFK(InspectionPlanBodyVO.class, "pk_plan");
		
	}

	@Override
	protected void setVOClazz() {
		this.addArea(EntityConst.HEAD, InspectionPlanHeadVO.class);
		this.addArea(EntityConst.BODY, InspectionPlanBodyVO.class);
	}

}
