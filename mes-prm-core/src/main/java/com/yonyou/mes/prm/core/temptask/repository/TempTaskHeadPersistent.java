package com.yonyou.mes.prm.core.temptask.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskHeadVO;

public class TempTaskHeadPersistent implements IVOPersistent{

	  /**
	   * 示例实体的数据库访问器
	   */
	  private TempTaskHeadMapper dao;
	  
	  

	  /**
	   * 示例VO的数据库操作构造函数
	   * 
	   * @param dao 示例实体的数据库访问器
	   */
	  public TempTaskHeadPersistent(TempTaskHeadMapper dao) {
	    this.dao = dao;
	  }

	  @Override
	  public void delete(List<String> list) {
		  for(String id :list) {
			  this.dao.deleteByPrimaryKey(id);
		  }
	  }

	  @Override
	  public void insert(List<?> list) {
	    List<TempTaskHeadVO> voList = (List<TempTaskHeadVO>) list;
	    //只添加一条数据
	    this.dao.insertSelective(voList.get(0));
	  }

	  @Override
	  public void update(List<?> list) {
	    List<TempTaskHeadVO> voList = (List<TempTaskHeadVO>) list;
	    this.dao.updateByPrimaryKey(voList.get(0));
	    }

}
