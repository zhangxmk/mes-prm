package com.yonyou.mes.prm.core.inspectionregion.entity;

import java.sql.Timestamp;

import com.yonyou.me.entity.MeSuperVO;



/**
 * mybatis方式
 */
public class InspectionRegionVO extends MeSuperVO {
	
	private String id;
	private Integer enablestate;
	private String pk_equip;
	private String pk_equip_name;
	private String pk_workshop;
	private String pk_workshop_name;
	private String pk_section;
	private String pk_section_name;
	private String name;
	private String code;
	private String tenantid;
	private String sysid;
	private String orgid;
	private String orgid_name;
	private java.sql.Timestamp ts;
	private Integer dr;
	private String creator;
	private String creator_name;
	private java.sql.Timestamp creationtime;
	private String modifier;
	private String modifier_name;
	private java.sql.Timestamp modifiedtime;
	public InspectionRegionVO() {
	}
    

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getEnablestate() {
		return enablestate;
	}


	public void setEnablestate(Integer enablestate) {
		this.enablestate = enablestate;
	}



	public String getPk_equip() {
		return this.pk_equip;
	}

	public void setPk_equip(String pk_equip) {
		this.pk_equip = pk_equip;
	}


	public String getPk_equip_name() {
		return this.pk_equip_name;
	}

	public void setPk_equip_name(String pk_equip_name) {
		this.pk_equip_name = pk_equip_name;
	}
	
	public String getPk_workshop() {
		return pk_workshop;
	}


	public void setPk_workshop(String pk_workshop) {
		this.pk_workshop = pk_workshop;
	}


	public String getPk_workshop_name() {
		return pk_workshop_name;
	}


	public void setPk_workshop_name(String pk_workshop_name) {
		this.pk_workshop_name = pk_workshop_name;
	}


	public String getPk_section() {
		return pk_section;
	}


	public void setPk_section(String pk_section) {
		this.pk_section = pk_section;
	}


	public String getPk_section_name() {
		return pk_section_name;
	}


	public void setPk_section_name(String pk_section_name) {
		this.pk_section_name = pk_section_name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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


	public java.sql.Timestamp getTs() {
		return this.ts;
	}

	public void setTs(java.sql.Timestamp ts) {
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


	public String getCreator_name() {
		return this.creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}


	public void setCreationtime(java.sql.Timestamp creationtime) {
		this.creationtime = creationtime;
	}


	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}


	public String getModifier_name() {
		return this.modifier_name;
	}

	public void setModifier_name(String modifier_name) {
		this.modifier_name = modifier_name;
	}


	public void setModifiedtime(java.sql.Timestamp modifiedtime) {
		this.modifiedtime = modifiedtime;
	}


	@Override
	public Timestamp getCreationtime() {
		return this.creationtime;
	}


	@Override
	public Timestamp getModifiedtime() {
		return this.modifiedtime;
	}


	@Override
	public String getOriginalid() {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		return "PRM_REGION";
	}


	@Override
	public void setOriginalid(String originalid) {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public String getParentid() {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public void setParentid(String parentid) {
		// TODO 自动生成的方法存根
		
	}

}