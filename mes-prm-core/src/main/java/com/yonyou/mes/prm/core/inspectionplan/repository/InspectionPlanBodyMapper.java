package com.yonyou.mes.prm.core.inspectionplan.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBodyVO;


@MyBatisRepository
public interface InspectionPlanBodyMapper {
    int deleteByPrimaryKey(String id);

    int insert(InspectionPlanBodyVO record);

    int insertSelective(InspectionPlanBodyVO record);

    InspectionPlanBodyVO selectByPrimaryKey(String id);

    InspectionPlanBodyVO[] selectByParentKey(String id);
	
    int updateByPrimaryKeySelective(InspectionPlanBodyVO record);

    int updateByPrimaryKey(InspectionPlanBodyVO record);
    
    PageResult<InspectionPlanBodyVO> selectAllByPage(@Param("page") PageRequest pageRequest,
            @Param("search") SearchParams searchParams);

    void batchInsert(List<InspectionPlanBodyVO> addList);

    void batchUpdate(List<InspectionPlanBodyVO> updateList);

    void batchDeleteByPrimaryKey(List<String> list);

}
