package com.yonyou.mes.prm.core.temptask.entity;

import com.yonyou.me.constance.EntityConst;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.entity.ApproveMeta;
import com.yonyou.me.entity.BillCodeMeta;
import com.yonyou.me.utils.busitype.MesBusitypeConst;

public class TempTaskBillVO extends AbstractMeBillVO{

	/**
	 * 临时巡检任务
	 */
	public static final String BUSITYPECODE_TEMP_TASK = "temp_task";
	public static final String BUSITYPENAME_TEMP_TASK = "临时巡检任务";

	//private static final BillCodeMeta BillCodeMeta = new BillCodeMeta(BUSITYPECODE_TEMP_TASK, "vbillcode");

	@Override
	public ApproveMeta getApproveMeta() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public BillCodeMeta getBillCodeMeta() {
		//return BillCodeMeta;
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
