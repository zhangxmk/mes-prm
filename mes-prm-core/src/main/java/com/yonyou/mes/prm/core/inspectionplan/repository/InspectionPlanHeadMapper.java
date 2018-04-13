package com.yonyou.mes.prm.core.inspectionplan.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanHeadVO;


@MyBatisRepository
public interface InspectionPlanHeadMapper {
    int deleteByPrimaryKey(String id);

    int insert(InspectionPlanHeadVO record);
    
    int insertSelective(InspectionPlanHeadVO record);

    InspectionPlanHeadVO selectByPrimaryKey(String id);

    InspectionPlanHeadVO[] selectByIDs(List<String> ids);
    
    int updateByPrimaryKeySelective(InspectionPlanHeadVO record);

    int updateByPrimaryKey(InspectionPlanHeadVO record);

    PageResult<InspectionPlanHeadVO> selectAllByPage(@Param("page") PageRequest pageRequest,
            @Param("search") SearchParams searchParams);
    
    PageResult<InspectionPlanHeadVO> getModalDataByPage(@Param("page") PageRequest pageRequest,
            @Param("search") SearchParams searchParams);

    void batchInsert(List<InspectionPlanHeadVO> addList);

    void batchUpdate(List<InspectionPlanHeadVO> updateList);

    void batchDeleteByPrimaryKey(List<InspectionPlanHeadVO> list);
    
    InspectionPlanHeadVO[] selectByCodes(List<String> codes);
}
