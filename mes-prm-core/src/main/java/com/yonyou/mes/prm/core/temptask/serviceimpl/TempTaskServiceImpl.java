package com.yonyou.mes.prm.core.temptask.serviceimpl;

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
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBillVO;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBodyVO;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskHeadVO;
import com.yonyou.mes.prm.core.temptask.repository.TempTaskBodyMapper;
import com.yonyou.mes.prm.core.temptask.repository.TempTaskBodyPersistent;
import com.yonyou.mes.prm.core.temptask.repository.TempTaskHeadMapper;
import com.yonyou.mes.prm.core.temptask.repository.TempTaskHeadPersistent;
import com.yonyou.mes.prm.core.temptask.service.ITempTaskService;

@Service
public class TempTaskServiceImpl implements ITempTaskService {

	// 主表mapper
	@Autowired
	TempTaskHeadMapper headMapper;

	// 子表mapper
	@Autowired
	TempTaskBodyMapper bodyMapper;

	/**
	 * 分页查询方法
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<TempTaskHeadVO> selectAllByPage(
			PageRequest pageRequest, SearchParams searchParams) {
		return headMapper.selectAllByPage(pageRequest, searchParams).getPage(); 
	}

	
	  /**
	   * 查询表体信息
	   * 
	   * @return
	   */
	  @Override
	  public Page<TempTaskBodyVO> queryBodyByPage(PageRequest pageRequest, SearchParams searchParams) {
	    return bodyMapper.selectAllByPage(pageRequest, searchParams).getPage(); 
	  }
	/**
	 * 新增保存
	 * 
	 * @param vo
	 */
	@Transactional
	public TempTaskBillVO add(TempTaskBillVO vo) {
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
		TempTaskBillVO retVo = (TempTaskBillVO) list.get(0);

		return retVo;
	}

	/**
	 * 修改保存
	 * 
	 * @param vo
	 */
	@Transactional
	public TempTaskBillVO update(TempTaskBillVO vo) {
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
		TempTaskBillVO retVo = (TempTaskBillVO) list.get(0);

		return retVo;
	}

	/**
	 * 批量删除
	 * 
	 * @param list
	 */
	public void batchDeleteByPrimaryKey(TempTaskBillVO[] vos) {
		BillPersistent dao = this.createBillDao();
		BillDeleteService service = new BillDeleteService(dao);

		service.delete(vos);
	}

	/**
	 * 查询单据，包括表头和表体
	 */
	@Override
	public TempTaskBillVO[] query(List<String> ids) {
		if (null == ids || ids.size() == 0) {
			ExceptionUtils.wrapBusinessException("查询主键不能为空！" + ids);
		}
		List<TempTaskBillVO> list = new ArrayList<TempTaskBillVO>();
		
		TempTaskHeadVO[] heads = this.headMapper.selectByIDs(ids);
		for(TempTaskHeadVO head:heads){
			String id = head.getId();
			TempTaskBodyVO[] items = this.bodyMapper.selectByParentKey(id);
			
			TempTaskBillVO bill = new TempTaskBillVO();
			bill.setHead(head);
			bill.setChildren(TempTaskBodyVO.class, Arrays.asList(items));
			
			list.add(bill);
		}
		
		return list.toArray(new TempTaskBillVO[0]);
	}

	private BillPersistent createBillDao() {
		// 创建单据保存器
		BillPersistent billdao = new BillPersistent();
		// 注册主表数据库操作
		TempTaskHeadPersistent headPersistent = new TempTaskHeadPersistent(
				this.headMapper);
		billdao.regiter(TempTaskHeadVO.class, headPersistent);
		// 注册子表数据库操作
		TempTaskBodyPersistent bodyPersistent = new TempTaskBodyPersistent(
				this.bodyMapper); 
		billdao.regiter(TempTaskBodyVO.class, bodyPersistent);

		return billdao;
	}
	
	public void batchInsertHead(List<TempTaskHeadVO> addList)
	{
		headMapper.batchInsert(addList);
	}
	
	public void batchInsertBody(List<TempTaskBodyVO> addList)
	{
		bodyMapper.batchInsert(addList);
	}
}
