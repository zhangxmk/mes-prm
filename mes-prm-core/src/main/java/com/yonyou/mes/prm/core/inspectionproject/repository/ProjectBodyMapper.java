package com.yonyou.mes.prm.core.inspectionproject.repository;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;
import com.yonyou.iuap.persistence.mybatis.anotation.MyBatisRepository;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBodyVO;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@MyBatisRepository
public interface ProjectBodyMapper {

	// 单个增删改查
	int insert(InspectionProjectBodyVO record);

	int insertSelective(InspectionProjectBodyVO record);

	int updateByPrimaryKeySelective(InspectionProjectBodyVO record);

	int updateByPrimaryKey(InspectionProjectBodyVO record);

	int deleteByPrimaryKey(String pk);

	InspectionProjectBodyVO selectByPrimaryKey(String pk);
	
	InspectionProjectBodyVO[] selectByParentKey(String id);

	PageResult<InspectionProjectBodyVO> selectAllByPage(
			@Param("page") PageRequest pageRequest,
			@Param("search") SearchParams searchParams);

	// 批量操作
	void batchInsert(List<InspectionProjectBodyVO> addList);

	void batchUpdate(List<InspectionProjectBodyVO> updateList);

	void batchDeleteByPrimaryKey(List<String> list);

}
