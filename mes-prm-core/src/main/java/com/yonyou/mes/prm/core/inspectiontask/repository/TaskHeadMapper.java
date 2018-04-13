package com.yonyou.mes.prm.core.inspectiontask.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;


@MyBatisRepository
public interface TaskHeadMapper {
    int deleteByPrimaryKey(String id);

    int insert(InspectionTaskHeadVO record);

    int insertSelective(InspectionTaskHeadVO record);

    InspectionTaskHeadVO selectByPrimaryKey(String id);

    InspectionTaskHeadVO[] selectByIDs(List<String> ids);

    int updateByPrimaryKeySelective(InspectionTaskHeadVO record);

    int updateByPrimaryKey(InspectionTaskHeadVO record);

    PageResult<InspectionTaskHeadVO> selectAllByPage(@Param("page") PageRequest pageRequest,
            @Param("search") SearchParams searchParams);

    void batchInsert(List<InspectionTaskHeadVO> addList);

    void batchUpdate(List<InspectionTaskHeadVO> updateList);

    void batchDeleteByPrimaryKey(List<String> list);

    PageResult<InspectionTaskHeadVO> queryTask(String postid);
}
