package com.yonyou.mes.prm.core.inspectionplan.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.yonyou.me.entity.MeSuperVO;
import com.yonyou.me.utils.annotation.Validation;



/**
 * mybatis方式
 * 巡检方案
 */
public class InspectionPlanHeadVO extends MeSuperVO{
	
	private String pk_dept;
	private String pk_dept_name;
	@Validation(description="巡检方案编码",unique = true)
	private String code;
	@Validation(description="巡检方案名称",unique = true)
	private String name;
	private String pk_region;
	private String pk_region_name;
	private Integer enablestate;
	private Integer defplan;
	private String id;
	private String tenantid;
	private String sysid;
	private String orgid;
	private String orgid_name;
	private Timestamp ts;
	private Integer dr;
	private String creator;
	private Timestamp creationtime;
	private Timestamp invalidate;
	private String modifier;
	private Timestamp modifiedtime;
	private String creator_name;
	private String modifier_name;
	private String pk_post;
	private String pk_post_name;
	private BigDecimal cycle;
	private String routeflag;
	private String iversion;
	private String vnote;
	public InspectionPlanHeadVO() {
	}
    

	public String getPk_dept() {
		return this.pk_dept;
	}

	public void setPk_dept(String pk_dept) {
		this.pk_dept = pk_dept;
	}


	public String getPk_dept_name() {
		return this.pk_dept_name;
	}

	public void setPk_dept_name(String pk_dept_name) {
		this.pk_dept_name = pk_dept_name;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPk_region() {
		return this.pk_region;
	}

	public void setPk_region(String pk_region) {
		this.pk_region = pk_region;
	}


	public String getPk_region_name() {
		return this.pk_region_name;
	}

	public void setPk_region_name(String pk_region_name) {
		this.pk_region_name = pk_region_name;
	}


	public Integer getEnablestate() {
		return this.enablestate;
	}

	public void setEnablestate(Integer enablestate) {
		this.enablestate = enablestate;
	}

	
	public Integer getDefplan() {
		return this.defplan;
	}

	public void setDefplan(Integer defplan) {
		this.defplan = defplan;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTenantid() {
		return this.tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}


	public String getSysid() {
		return this.sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}


	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}


	public String getOrgid_name() {
		return this.orgid_name;
	}

	public void setOrgid_name(String orgid_name) {
		this.orgid_name = orgid_name;
	}


	public Timestamp getTs() {
		return this.ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}


	public Integer getDr() {
		return this.dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}


	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}


	public Timestamp getCreationtime() {
		return this.creationtime;
	}

	public void setCreationtime(Timestamp creationtime) {
		this.creationtime = creationtime;
	}

	public Timestamp getInvalidate() {
		return this.invalidate;
	}

	public void setInvalidate(Timestamp invalidate) {
		this.invalidate = invalidate;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}


	public Timestamp getModifiedtime() {
		return this.modifiedtime;
	}

	public void setModifiedtime(Timestamp modifiedtime) {
		this.modifiedtime = modifiedtime;
	}


	public String getCreator_name() {
		return this.creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}


	public String getModifier_name() {
		return this.modifier_name;
	}

	public void setModifier_name(String modifier_name) {
		this.modifier_name = modifier_name;
	}


	public String getPk_post() {
		return this.pk_post;
	}

	public void setPk_post(String pk_post) {
		this.pk_post = pk_post;
	}


	public String getPk_post_name() {
		return this.pk_post_name;
	}

	public void setPk_post_name(String pk_post_name) {
		this.pk_post_name = pk_post_name;
	}


	public BigDecimal getCycle() {
		return this.cycle;
	}

	public void setCycle(BigDecimal cycle) {
		this.cycle = cycle;
	}


	public String getRouteflag() {
		return this.routeflag;
	}

	public void setRouteflag(String routeflag) {
		this.routeflag = routeflag;
	}


	public String getIversion() {
		return this.iversion;
	}

	public void setIversion(String iversion) {
		this.iversion = iversion;
	}


	public String getVnote() {
		return this.vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
	}



	@Override
	public String getOriginalid() {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public String getParentid() {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public String getTableName() {
		return "PRM_PLAN";
	}


	@Override
	public void setOriginalid(String originalid) {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void setParentid(String parentid) {
		// TODO 自动生成的方法存根
		
	}
}