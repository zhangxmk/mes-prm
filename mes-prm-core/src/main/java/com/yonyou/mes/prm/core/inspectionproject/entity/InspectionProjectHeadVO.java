package com.yonyou.mes.prm.core.inspectionproject.entity;

import java.sql.Timestamp;

import com.yonyou.me.entity.MeSuperVO;

public class InspectionProjectHeadVO extends MeSuperVO {

	private String cregion;
	private String cregion_name;
	private String code;
	private String name;
	private String cequip;
	private String cequip_name;
	private String pk_dept;

	public String getPk_dept() {
		return pk_dept;
	}

	public void setPk_dept(String pk_dept) {
		this.pk_dept = pk_dept;
	}

	public String getPk_dept_name() {
		return pk_dept_name;
	}

	public void setPk_dept_name(String pk_dept_name) {
		this.pk_dept_name = pk_dept_name;
	}

	private String pk_dept_name;
	private Integer enablestate;
	private String id;
	private String tenantid;
	private String sysid;
	private String orgid;
	private String orgid_name;
	private Timestamp ts;
	private Integer dr;
	private String creator;
	private String creator_name;
	private Timestamp creationtime;
	private String modifier;
	private String modifier_name;
	private Timestamp modifiedtime;
	private String pk_process;
	private String pk_process_name;
	
	public InspectionProjectHeadVO() {
	}
	
	public String getPk_process() {
		return pk_process;
	}

	public void setPk_process(String pk_process) {
		this.pk_process = pk_process;
	}

	public String getPk_process_name() {
		return pk_process_name;
	}

	public void setPk_process_name(String pk_process_name) {
		this.pk_process_name = pk_process_name;
	}
	
	public String getCregion() {
		return this.cregion;
	}

	public void setCregion(String cregion) {
		this.cregion = cregion;
	}

	public String getCregion_name() {
		return this.cregion_name;
	}

	public void setCregion_name(String cregion_name) {
		this.cregion_name = cregion_name;
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

	public String getCequip() {
		return this.cequip;
	}

	public void setCequip(String cequip) {
		this.cequip = cequip;
	}

	public String getCequip_name() {
		return this.cequip_name;
	}

	public void setCequip_name(String cequip_name) {
		this.cequip_name = cequip_name;
	}

	public Integer getEnablestate() {
		return this.enablestate;
	}

	public void setEnablestate(Integer enablestate) {
		this.enablestate = enablestate;
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
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}

	public String getModifier_name() {
		return modifier_name;
	}

	public void setModifier_name(String modifier_name) {
		this.modifier_name = modifier_name;
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
		return "PRM_PROJECT";
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