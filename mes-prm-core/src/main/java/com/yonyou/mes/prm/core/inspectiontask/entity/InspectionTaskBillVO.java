package com.yonyou.mes.prm.core.inspectiontask.entity;

import com.yonyou.me.constance.EntityConst;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.entity.ApproveMeta;
import com.yonyou.me.entity.BillCodeMeta;

/**
 * 巡检任务aggvo
 * @author weijw
 *
 * 2018年3月1日
 */
public class InspectionTaskBillVO  extends AbstractMeBillVO{

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
		this.addFK(InspectionTaskBodyVO.class, "pk_task");
		
	}

	@Override
	protected void setVOClazz() {
		this.addArea(EntityConst.HEAD, InspectionTaskHeadVO.class);
		this.addArea(EntityConst.BODY, InspectionTaskBodyVO.class);
	}

}
