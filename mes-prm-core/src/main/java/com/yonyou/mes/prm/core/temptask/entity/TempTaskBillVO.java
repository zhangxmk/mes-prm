package com.yonyou.mes.prm.core.temptask.entity;

import com.yonyou.me.constance.EntityConst;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.entity.ApproveMeta;
import com.yonyou.me.entity.BillCodeMeta;

public class TempTaskBillVO extends AbstractMeBillVO{

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
		this.addFK(TempTaskBodyVO.class, TempTaskBodyVO.PARENTID);
		
	}

	@Override
	protected void setVOClazz() {
		// TODO 自动生成的方法存根
		this.addArea(EntityConst.HEAD, TempTaskHeadVO.class);
		this.addArea(EntityConst.BODY, TempTaskBodyVO.class);
		
	}

}
