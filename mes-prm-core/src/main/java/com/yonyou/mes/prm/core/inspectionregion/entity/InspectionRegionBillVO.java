package com.yonyou.mes.prm.core.inspectionregion.entity;

import com.yonyou.me.constance.EntityConst;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.entity.ApproveMeta;
import com.yonyou.me.entity.BillCodeMeta;


public class InspectionRegionBillVO extends AbstractMeBillVO {
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
		// TODO 自动生成的方法存根
		
	}

	@Override
	protected void setVOClazz() {
		this.addArea(EntityConst.HEAD, InspectionRegionVO.class);
		//this.addArea(EntityConst.BODY, MeasurePointTypeBodyVO.class);

	}

}
