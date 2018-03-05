package com.yonyou.mes.prm.core.inspectionregion.repository;

import java.util.List;

import com.yonyou.me.utils.repository.IVOPersistent;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;
import com.yonyou.mes.prm.core.inspectionregion.entity.InspectionRegionVO;


/**
 * 
 * @description 巡检区域的数据库操作
 *
 * @author wangkem
 * 2018年2月7日
 */
public class InspectionRegionPersistent implements IVOPersistent {

  /**
   * 巡检区域的数据库访问器
   */
  private InspectionRegionMapper dao;

  /**
   * 巡检区域VO的数据库操作构造函数
   * 
   * @param dao 巡检区域的数据库访问器
   */
  public InspectionRegionPersistent(InspectionRegionMapper dao) {
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
    List<InspectionRegionVO> voList = (List<InspectionRegionVO>) list;
    //只添加一条数据
    this.dao.insertSelective(voList.get(0));
  }

  @Override
  public void update(List<?> list) {
    List<InspectionRegionVO> voList = (List<InspectionRegionVO>) list;
    this.dao.updateByPrimaryKey(voList.get(0));
    }

}
