package com.yonyou.mes.prm.core.inspectionregion.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.inspectionregion.entity.InspectionRegionVO;


@MyBatisRepository
public interface InspectionRegionMapper {
    int deleteByPrimaryKey(String id);

    int insert(InspectionRegionVO record);

    int insertSelective(InspectionRegionVO record);

    InspectionRegionVO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InspectionRegionVO record);

    int updateByPrimaryKey(InspectionRegionVO record);

    PageResult<InspectionRegionVO> selectAllByPage(@Param("page") PageRequest pageRequest,
            @Param("search") SearchParams searchParams);

    void batchInsert(List<InspectionRegionVO> addList);

    void batchUpdate(List<InspectionRegionVO> updateList);

    void batchDeleteByPrimaryKey(List<InspectionRegionVO> list);

}
