package com.yonyou.mes.prm.core.inspectiontask.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.utils.exception.ExceptionUtils;
import com.yonyou.me.utils.repository.BillPersistent;
import com.yonyou.me.utils.service.bill.BillDeleteService;
import com.yonyou.me.utils.service.bill.BillSaveService;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBillVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.TaskForTableVO;
import com.yonyou.mes.prm.core.inspectiontask.repository.TaskBodyMapper;
import com.yonyou.mes.prm.core.inspectiontask.repository.TaskBodyPersistent;
import com.yonyou.mes.prm.core.inspectiontask.repository.TaskHeadMapper;
import com.yonyou.mes.prm.core.inspectiontask.repository.TaskHeadPersistent;
import com.yonyou.mes.prm.core.inspectiontask.service.IInspectionTaskService;

@Service
public class InspectionTaskServiceImpl implements IInspectionTaskService {
	// 主表mapper
	@Autowired
	TaskHeadMapper headMapper;

	// 子表mapper
	@Autowired
	TaskBodyMapper bodyMapper;
	
	@Override
	public Page<InspectionTaskHeadVO> selectAllByPage(PageRequest pageRequest,
			SearchParams searchParams) {
		return headMapper.selectAllByPage(pageRequest, searchParams).getPage();
	}
	
	@Override
	public Page<TaskForTableVO> selectForStaTable(PageRequest pageRequest,
			SearchParams searchParams) {
		return headMapper.selectForStaTable(pageRequest, searchParams).getPage();
	}

	@Override
	public Page<InspectionTaskBodyVO> queryBodyByPage(PageRequest pageRequest,
			SearchParams searchParams) {
		return bodyMapper.selectAllByPage(pageRequest, searchParams).getPage();
		
	}
	
	@Override
	public Page<InspectionTaskBodyVO> queryProjectAndContent(String planid) {
		return bodyMapper.queryProjectAndContent(planid).getPage();
	}

	@Override
	@Transactional
	public InspectionTaskBillVO add(InspectionTaskBillVO vo) {
		if (vo == null) {
			ExceptionUtils.wrapBusinessException("传入数据为空");
		}
		BillPersistent dao = this.createBillDao();
		BillSaveService service = new BillSaveService(dao);

		// 保存前后规则
		// service.addBeforeRule(rule);
		// service.addAfterRule(rule);
		// 设置填充字段
		// TODO

		List<?> list = service.save(new AbstractMeBillVO[] { vo });
		InspectionTaskBillVO retVo = (InspectionTaskBillVO) list.get(0);

		return retVo;
	}

	@Override
	@Transactional
	public InspectionTaskBillVO update(InspectionTaskBillVO vo) {
		if (vo == null) {
			ExceptionUtils.wrapBusinessException("传入数据为空");
		}
		BillPersistent dao = this.createBillDao();
		BillSaveService service = new BillSaveService(dao);

		// 保存前后规则
		// service.addBeforeRule(rule);
		// service.addAfterRule(rule);
		// 设置填充字段
		// TODO

		List<?> list = service.save(new AbstractMeBillVO[] { vo });
		InspectionTaskBillVO retVo = (InspectionTaskBillVO) list.get(0);

		return retVo;
	}

	@Override
	@Transactional
	public void batchDeleteByPrimaryKey(InspectionTaskBillVO[] vos) {
		BillPersistent dao = this.createBillDao();
		BillDeleteService service = new BillDeleteService(dao);

		service.delete(vos);

	}

	@Override
	public InspectionTaskBillVO[] query(List<String> ids) {
		if (null == ids || ids.size() == 0) {
			ExceptionUtils.wrapBusinessException("查询主键不能为空！" + ids);
		}
		List<InspectionTaskBillVO> list = new ArrayList<InspectionTaskBillVO>();

		InspectionTaskHeadVO[] heads = this.headMapper.selectByIDs(ids);
		for (InspectionTaskHeadVO head : heads) {
			String id = head.getId();
			InspectionTaskBodyVO[] items = this.bodyMapper
					.selectByParentKey(id);

			InspectionTaskBillVO bill = new InspectionTaskBillVO();
			bill.setHead(head);
			bill.setChildren(InspectionTaskBodyVO.class,
					Arrays.asList(items));

			list.add(bill);
		}

		return list.toArray(new InspectionTaskBillVO[0]);
	}

	private BillPersistent createBillDao() {
		// 创建单据保存器
		BillPersistent billdao = new BillPersistent();
		// 注册主表数据库操作
		TaskHeadPersistent headPersistent = new TaskHeadPersistent(
				this.headMapper);
		billdao.regiter(InspectionTaskHeadVO.class, headPersistent);
		// 注册子表数据库操作
		TaskBodyPersistent bodyPersistent = new TaskBodyPersistent(
				this.bodyMapper);
		billdao.regiter(InspectionTaskBodyVO.class, bodyPersistent);

		return billdao;
	}

	@Override
	public InspectionTaskBodyVO[] queryTaskDetails(String postid) {
		return this.bodyMapper.queryTaskDetail4App(postid);
	}

	@Override
	public InspectionTaskBillVO createTaskBill(String planid) {
		//TODO
		return null;
	}



	@Override
	public Page<InspectionTaskHeadVO> queryTask(String postid) {
		return this.headMapper.queryTask(postid).getPage();
	}
	
	@Override
	public InspectionTaskBodyVO[] queryTaskDetailsByID(List<String> id) {
		return this.bodyMapper.queryTaskDetailsByID(id);
	}
	
	@Override
	public void batchUpdateByPrimaryKeySelective(List<InspectionTaskBodyVO> updateList) {
		this.bodyMapper.batchUpdateByPrimaryKeySelective(updateList);
	}
	
	@Override
	public void updateHead(InspectionTaskHeadVO vo)  {
		this.headMapper.updateByPrimaryKeySelective(vo);
	}
}
