package com.yonyou.mes.prm.core.inspectionproject.entity;

import com.yonyou.me.constance.EntityConst;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.entity.ApproveMeta;
import com.yonyou.me.entity.BillCodeMeta;


public class InspectionProjectBillVO extends AbstractMeBillVO {
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
		this.addFK(InspectionProjectBodyVO.class, InspectionProjectBodyVO.PARENTID);
	}

	@Override
	protected void setVOClazz() {
		this.addArea(EntityConst.HEAD, InspectionProjectHeadVO.class);
		this.addArea(EntityConst.BODY, InspectionProjectBodyVO.class);

	}

}
