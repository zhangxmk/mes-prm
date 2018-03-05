package com.yonyou.mes.prm.core.temptask.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBodyVO;

public class TempTaskBodyPersistent implements IVOPersistent{

	  /**
	   * 子实体的数据库访问器
	   */
	  private TempTaskBodyMapper dao;

	  /**
	   * 子实体的数据库操作构造函数
	   * 
	   * @param dao 子实体的数据库访问器
	   */
	  public TempTaskBodyPersistent(TempTaskBodyMapper dao) {
	    this.dao = dao;
	  }

	  @Override
	  public void delete(List<String> list) {
	    this.dao.batchDeleteByPrimaryKey(list);
	  }

	  @Override
	  public void insert(List<?> list) {
	    List<TempTaskBodyVO> voList = (List<TempTaskBodyVO>) list;
	    this.dao.batchInsert(voList);
	  }

	  @Override
	  public void update(List<?> list) {
	    List<TempTaskBodyVO> voList = (List<TempTaskBodyVO>) list;
	    this.dao.batchUpdate(voList);
	  }

}
