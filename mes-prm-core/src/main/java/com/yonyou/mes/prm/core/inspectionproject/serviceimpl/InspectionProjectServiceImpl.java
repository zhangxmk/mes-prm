package com.yonyou.mes.prm.core.inspectionproject.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yonyou.me.entity.EnableStateVO;
import com.yonyou.me.utils.service.EnableStateServiceImpl;
import com.yonyou.me.utils.service.IEnableStateService;
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
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBillVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBodyVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;
import com.yonyou.mes.prm.core.inspectionproject.repository.ProjectBodyMapper;
import com.yonyou.mes.prm.core.inspectionproject.repository.ProjectBodyPersistent;
import com.yonyou.mes.prm.core.inspectionproject.repository.ProjectHeadMapper;
import com.yonyou.mes.prm.core.inspectionproject.repository.ProjectHeadPersistent;
import com.yonyou.mes.prm.core.inspectionproject.service.IInspectionProjectService;

/**
 * @Description 巡检项目服务实现类
 *
 * @author guojunf
 * @version 1.0
 * @date 2018年2月6日
 */
@Service
public class InspectionProjectServiceImpl implements IInspectionProjectService {

	// 主表mapper
	@Autowired
	ProjectHeadMapper headMapper;

	// 子表mapper
	@Autowired
	ProjectBodyMapper bodyMapper;

	/**
	 * 分页查询方法
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<InspectionProjectHeadVO> selectAllByPage(
			PageRequest pageRequest, SearchParams searchParams) {
		return headMapper.selectAllByPage(pageRequest, searchParams).getPage();
	}

	/**
	 * 查询表体信息
	 * 
	 * @return
	 */
	@Override
	public Page<InspectionProjectBodyVO> queryBodyByPage(
			PageRequest pageRequest, SearchParams searchParams) {

		return bodyMapper.selectAllByPage(pageRequest, searchParams).getPage();
	}

	/**
	 * 新增保存
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionProjectBillVO add(InspectionProjectBillVO vo) {
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
		InspectionProjectBillVO retVo = (InspectionProjectBillVO) list.get(0);

		return retVo;
	}

	/**
	 * 修改保存
	 * 
	 * @param vo
	 */
	@Transactional
	public InspectionProjectBillVO update(InspectionProjectBillVO vo) {
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
		InspectionProjectBillVO retVo = (InspectionProjectBillVO) list.get(0);

		return retVo;
	}

	/**
	 * 批量删除
	 * 
	 * @param vos
	 */
	public void batchDeleteByPrimaryKey(InspectionProjectBillVO[] vos) {
		BillPersistent dao = this.createBillDao();
		BillDeleteService service = new BillDeleteService(dao);

		service.delete(vos);
	}

	/**
	 * 查询单据，包括表头和表体
	 */
	@Override
	public InspectionProjectBillVO[] query(List<String> ids) {
		if (null == ids || ids.size() == 0) {
			ExceptionUtils.wrapBusinessException("查询主键不能为空！" + ids);
		}
		List<InspectionProjectBillVO> list = new ArrayList<InspectionProjectBillVO>();

		InspectionProjectHeadVO[] heads = this.headMapper.selectByIDs(ids);
		for (InspectionProjectHeadVO head : heads) {
			String id = head.getId();
			InspectionProjectBodyVO[] items = this.bodyMapper
					.selectByParentKey(id);

			InspectionProjectBillVO bill = new InspectionProjectBillVO();
			bill.setHead(head);
			bill.setChildren(InspectionProjectBodyVO.class,
					Arrays.asList(items));

			list.add(bill);
		}

		return list.toArray(new InspectionProjectBillVO[0]);
	}

	@Override
	public void batchEnableByPrimaryKey(InspectionProjectHeadVO[] vos) {
		List<EnableStateVO> statelist = new ArrayList<>();
		for (InspectionProjectHeadVO def:vos){
			EnableStateVO vo = new EnableStateVO();
			vo.setId(def.getId());
			vo.setTs(def.getTs());
			vo.setTableName(def.getTableName());
			statelist.add(vo);
		}
		IEnableStateService serv = new EnableStateServiceImpl();
		serv.enableState(statelist);
	}

	@Override
	public void batchDisableByPrimaryKey(InspectionProjectHeadVO[] vos) {
		List<EnableStateVO> statelist = new ArrayList<>();
		for (InspectionProjectHeadVO def:vos){
			EnableStateVO vo = new EnableStateVO();
			vo.setId(def.getId());
			vo.setTs(def.getTs());
			vo.setTableName(def.getTableName());
			statelist.add(vo);
		}
		IEnableStateService serv = new EnableStateServiceImpl();
		serv.unableState(statelist);
	}

	private BillPersistent createBillDao() {
		// 创建单据保存器
		BillPersistent billdao = new BillPersistent();
		// 注册主表数据库操作
		ProjectHeadPersistent headPersistent = new ProjectHeadPersistent(
				this.headMapper);
		billdao.regiter(InspectionProjectHeadVO.class, headPersistent);
		// 注册子表数据库操作
		ProjectBodyPersistent bodyPersistent = new ProjectBodyPersistent(
				this.bodyMapper);
		billdao.regiter(InspectionProjectBodyVO.class, bodyPersistent);

		return billdao;
	}

	public InspectionProjectHeadVO[] selectByCode(String code)
	{
		return headMapper.selectByCode(code);
	}
	
	public InspectionProjectBodyVO[] selectByParentKeys(List<String> ids)
	{
		return bodyMapper.selectByParentKeys(ids);
	}
}
