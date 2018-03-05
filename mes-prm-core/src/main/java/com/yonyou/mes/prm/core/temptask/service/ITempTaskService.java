package com.yonyou.mes.prm.core.temptask.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBillVO;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBodyVO;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskHeadVO;

public interface ITempTaskService {

	/**
	 * 分页查询方法
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<TempTaskHeadVO> selectAllByPage(
			PageRequest pageRequest, SearchParams searchParams);

	/**
	 * 查询表体信息
	 * 
	 * @return
	 */
	public Page<TempTaskBodyVO> queryBodyByPage(
			PageRequest pageRequest,SearchParams searchParams);

	/**
	 * 新增
	 * 
	 * @param vo
	 */
	@Transactional
	public TempTaskBillVO add(TempTaskBillVO vo);

	/**
	 * 更新
	 * 
	 * @param vo
	 */
	@Transactional
	public TempTaskBillVO update(TempTaskBillVO vo);

	/**
	 * 批量删除
	 * 
	 * @param list
	 */
	public void batchDeleteByPrimaryKey(TempTaskBillVO[] vos);

	/**
	 * 根据主表主键查询主子数据
	 * 
	 * @param id
	 * @return
	 */
	public TempTaskBillVO[] query(List<String> ids);
}
