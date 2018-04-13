package com.yonyou.mes.prm.core.inspectionproject.repository;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@MyBatisRepository
public interface ProjectHeadMapper {
	
	//单个增删改查
	int insert(InspectionProjectHeadVO record);

	int insertSelective(InspectionProjectHeadVO record);
	
	int updateByPrimaryKeySelective(InspectionProjectHeadVO record);

    int updateByPrimaryKey(InspectionProjectHeadVO record);
    
    InspectionProjectHeadVO[] selectByIDs(List<String> ids);

    int deleteByPrimaryKey(String pk);

    InspectionProjectHeadVO selectByPrimaryKey(String pk);
    
    
	PageResult<InspectionProjectHeadVO> selectAllByPage(@Param("page") PageRequest pageRequest, @Param("search") SearchParams searchParams);
    
   //批量操作
    void batchInsert(List<InspectionProjectHeadVO> addList);

    void batchUpdate(List<InspectionProjectHeadVO> updateList);

    void batchDeleteByPrimaryKey(List<InspectionProjectHeadVO> list);
    
    InspectionProjectHeadVO[] selectByCode(String code);
}
