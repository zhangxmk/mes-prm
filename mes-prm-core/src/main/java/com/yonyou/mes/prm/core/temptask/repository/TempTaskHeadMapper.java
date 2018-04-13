 package com.yonyou.mes.prm.core.temptask.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskHeadVO;


@MyBatisRepository
public interface TempTaskHeadMapper {
    int deleteByPrimaryKey(String id);

    int insert(TempTaskHeadVO record);

    int insertSelective(TempTaskHeadVO record);

    TempTaskHeadVO selectByPrimaryKey(String id);
    
    TempTaskHeadVO[] selectByIDs(List<String> ids);
    
    int updateByPrimaryKeySelective(TempTaskHeadVO record);

    int updateByPrimaryKey(TempTaskHeadVO record);

    PageResult<TempTaskHeadVO> selectAllByPage(@Param("page") PageRequest pageRequest,
            @Param("search") SearchParams searchParams);

    void batchInsert(List<TempTaskHeadVO> addList);

    void batchUpdate(List<TempTaskHeadVO> updateList);

    void batchDeleteByPrimaryKey(List<TempTaskHeadVO> list);

}
