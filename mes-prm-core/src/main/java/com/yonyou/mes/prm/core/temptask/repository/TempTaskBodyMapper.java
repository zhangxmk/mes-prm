package com.yonyou.mes.prm.core.temptask.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBodyVO;


@MyBatisRepository
public interface TempTaskBodyMapper {
    int deleteByPrimaryKey(String id);

    int insert(TempTaskBodyVO record);

    int insertSelective(TempTaskBodyVO record);

    TempTaskBodyVO selectByPrimaryKey(String id);
    
    TempTaskBodyVO[] selectByParentKey(String id);
    
    int updateByPrimaryKeySelective(TempTaskBodyVO record);

    int updateByPrimaryKey(TempTaskBodyVO record);

    PageResult<TempTaskBodyVO> selectAllByPage(@Param("page") PageRequest pageRequest,
            @Param("search")SearchParams searchParams);

    void batchInsert(List<TempTaskBodyVO> addList);

    void batchUpdate(List<TempTaskBodyVO> updateList);

    void batchDeleteByPrimaryKey(List<String> list);

}
