package com.yonyou.mes.prm.core.inspectionplan.entity;

import java.sql.Timestamp;

import com.yonyou.me.entity.MeSuperVO;



/**
 * mybatis方式
 */
public class InspectionPlanBodyVO extends MeSuperVO{
	
	private String pk_er_project;
	private String er_project_code;
	private String er_project_name;
	private String pk_plan;
	private String id;
	private String tenantid;
	private String sysid;
	private String orgid;
	private String orgid_name;
	private Timestamp ts;
	private Integer dr;
	private String creator;
	private Timestamp creationtime;
	private String modifier;
	private Timestamp modifiedtime;
	private String creator_name;
	private String modifier_name;
	public InspectionPlanBodyVO() {
	}
    

	public String getPk_er_project() {
		return this.pk_er_project;
	}

	public void setPk_er_project(String pk_er_project) {
		this.pk_er_project = pk_er_project;
	}


	public String getEr_project_code() {
		return this.er_project_code;
	}

	public void setEr_project_code(String er_project_code) {
		this.er_project_code = er_project_code;
	}


	public String getEr_project_name() {
		return this.er_project_name;
	}

	public void setEr_project_name(String er_project_name) {
		this.er_project_name = er_project_name;
	}


	public String getPk_plan() {
		return this.pk_plan;
	}

	public void setPk_plan(String pk_plan) {
		this.pk_plan = pk_plan;
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


	@Override
	public String getName() {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public void setName(String name) {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public String getCode() {
		// TODO 自动生成的方法存根
		return null;
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
		return "PRM_PLAN_B";
	}


	@Override
	public void setCode(String code) {
		// TODO 自动生成的方法存根
		
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