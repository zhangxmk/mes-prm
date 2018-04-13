package com.yonyou.mes.prm.core.inspectionplan.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.entity.EnableStateVO;
import com.yonyou.me.entity.MeSuperVO;
import com.yonyou.me.utils.exception.ExceptionUtils;
import com.yonyou.me.utils.repository.BillPersistent;
import com.yonyou.me.utils.service.EnableStateServiceImpl;
import com.yonyou.me.utils.service.IEnableStateService;
import com.yonyou.me.utils.service.bill.BillDeleteService;
import com.yonyou.me.utils.service.bill.BillSaveService;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBillVO;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBodyVO;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanHeadVO;
import com.yonyou.mes.prm.core.inspectionplan.repository.InspectionPlanBodyMapper;
import com.yonyou.mes.prm.core.inspectionplan.repository.InspectionPlanBodyPersistent;
import com.yonyou.mes.prm.core.inspectionplan.repository.InspectionPlanHeadMapper;
import com.yonyou.mes.prm.core.inspectionplan.repository.InspectionPlanHeadPersistent;
import com.yonyou.mes.prm.core.inspectionplan.service.IInspectionPlanService;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;

@Service
public class InspectionPlanServiceImpl implements IInspectionPlanService {
	// 主表mapper
	@Autowired
	InspectionPlanHeadMapper headMapper;

	// 子表mapper
	@Autowired
	InspectionPlanBodyMapper bodyMapper;
	
	@Override
	public Page<InspectionPlanHeadVO> selectAllByPage(PageRequest pageRequest,
			SearchParams searchParams) {
		return headMapper.selectAllByPage(pageRequest, searchParams).getPage();
	}

	@Override
	public Page<InspectionPlanBodyVO> queryBodyByPage(PageRequest pageRequest,
			SearchParams searchParams) {
		return bodyMapper.selectAllByPage(pageRequest, searchParams).getPage();
		
	}

	@Override
	@Transactional
	public InspectionPlanBillVO add(InspectionPlanBillVO vo) {
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
		InspectionPlanBillVO retVo = (InspectionPlanBillVO) list.get(0);

		return retVo;
	}

	@Override
	@Transactional
	public InspectionPlanBillVO update(InspectionPlanBillVO vo) {
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
		InspectionPlanBillVO retVo = (InspectionPlanBillVO) list.get(0);

		return retVo;
	}

	@Override
	@Transactional
	public void batchDeleteByPrimaryKey(InspectionPlanBillVO[] vos) {
		BillPersistent dao = this.createBillDao();
		BillDeleteService service = new BillDeleteService(dao);

		service.delete(vos);

	}

	@Override
	public InspectionPlanBillVO[] query(List<String> ids) {
		if (null == ids || ids.size() == 0) {
			ExceptionUtils.wrapBusinessException("查询主键不能为空！" + ids);
		}
		List<InspectionPlanBillVO> list = new ArrayList<InspectionPlanBillVO>();

		InspectionPlanHeadVO[] heads = this.headMapper.selectByIDs(ids);
		for (InspectionPlanHeadVO head : heads) {
			String id = head.getId();
			InspectionPlanBodyVO[] items = this.bodyMapper
					.selectByParentKey(id);

			InspectionPlanBillVO bill = new InspectionPlanBillVO();
			bill.setHead(head);
			bill.setChildren(InspectionPlanBodyVO.class,
					Arrays.asList(items));

			list.add(bill);
		}

		return list.toArray(new InspectionPlanBillVO[0]);
	}

	private BillPersistent createBillDao() {
		// 创建单据保存器
		BillPersistent billdao = new BillPersistent();
		// 注册主表数据库操作
		InspectionPlanHeadPersistent headPersistent = new InspectionPlanHeadPersistent(
				this.headMapper);
		billdao.regiter(InspectionPlanHeadVO.class, headPersistent);
		// 注册子表数据库操作
		InspectionPlanBodyPersistent bodyPersistent = new InspectionPlanBodyPersistent(
				this.bodyMapper);
		billdao.regiter(InspectionPlanBodyVO.class, bodyPersistent);

		
		return billdao;
	}

	@Override
	public void batchDisableByPrimaryKey(List<InspectionPlanHeadVO> list) {
		// TODO 自动生成的方法存根
		headMapper.batchUpdate(list);
	}

	@Override
	public void batchEnableByPrimaryKey(List<InspectionPlanHeadVO> list) {
		// TODO 自动生成的方法存根
		headMapper.batchUpdate(list);
	}

	@Override
	public InspectionPlanBillVO vchange(InspectionPlanBillVO vo) {
		// TODO 自动生成的方法存根
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
		
		InspectionPlanHeadVO headVO = (InspectionPlanHeadVO)vo.getHead();
		headVO.setId(null);
		Double d = Double.valueOf(headVO.getIversion());
		d = d + 0.1;
		headVO.setIversion(d.toString().substring(0,3));
		vo.setHead(headVO);
		List<MeSuperVO> bodyList = vo.getChildren(InspectionPlanBodyVO.class);
		List<InspectionPlanBodyVO> bList = new ArrayList<InspectionPlanBodyVO>();
		for(MeSuperVO bodyvo:bodyList){
			InspectionPlanBodyVO bvo = (InspectionPlanBodyVO)bodyvo;
			bvo.setId(null);
			bList.add(bvo);
		}
		vo.setChildren(InspectionPlanBodyVO.class,bList);
		List<?> list = service.save(new AbstractMeBillVO[] { vo });
		InspectionPlanBillVO retVo = (InspectionPlanBillVO) list.get(0);

		return retVo;
	}

	@Override
	public void disableoldplan(InspectionPlanHeadVO vo) {
		// TODO 自动生成的方法存根
		vo.setDefplan(0);
		vo.setModifiedtime(new Timestamp(new Date().getTime()));
		headMapper.updateByPrimaryKey(vo);
	}

	@Override
	public Page<InspectionPlanHeadVO> getModalDataByPage(
			PageRequest pageRequest, SearchParams searchParams) {
		// TODO 自动生成的方法存根
		return headMapper.getModalDataByPage(pageRequest, searchParams).getPage();
	}
	
	@Override
	public InspectionPlanHeadVO[] selectByCodes(List<String> codes) {
		return this.headMapper.selectByCodes(codes);
	}
	
	@Override
	public InspectionPlanBodyVO[] selectByParentKeys(List<String> codes) {
		return this.bodyMapper.selectByParentKeys(codes);
	}
	
	@Override
	public InspectionPlanBodyVO[] selectByBodyCode(String code) {
		return this.bodyMapper.selectByBodyCode(code);
	}
	
	@Override
	public InspectionPlanHeadVO[] selectByIDs(List<String> ids){
		return this.headMapper.selectByIDs(ids);
	}
	
	@Override
	public InspectionPlanHeadVO selectByPrimaryKey(String id){
		return this.headMapper.selectByPrimaryKey(id);
	}
}
