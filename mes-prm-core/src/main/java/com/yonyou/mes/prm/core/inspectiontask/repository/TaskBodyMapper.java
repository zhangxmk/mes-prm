package com.yonyou.mes.prm.core.inspectiontask.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;


@MyBatisRepository
public interface TaskBodyMapper {
    int deleteByPrimaryKey(String id);

    int insert(InspectionTaskBodyVO record);

    int insertSelective(InspectionTaskBodyVO record);

    InspectionTaskBodyVO selectByPrimaryKey(String id);
    
    InspectionTaskBodyVO[] selectByParentKey(String id);
    
    PageResult<InspectionTaskBodyVO> queryProjectAndContent(String planid);

    int updateByPrimaryKeySelective(InspectionTaskBodyVO record);

    int updateByPrimaryKey(InspectionTaskBodyVO record);

    PageResult<InspectionTaskBodyVO> selectAllByPage(@Param("page") PageRequest pageRequest,
            @Param("search") SearchParams searchParams);

    void batchInsert(List<InspectionTaskBodyVO> addList);

    void batchUpdate(List<InspectionTaskBodyVO> updateList);

    void batchDeleteByPrimaryKey(List<String> list);
    
    InspectionTaskBodyVO[] queryTaskDetail4App(String postid);

    InspectionTaskBodyVO[] queryTaskDetailsByID(List<String> ids);
    
    void batchUpdateByPrimaryKeySelective(List<InspectionTaskBodyVO> updateList);
 }
