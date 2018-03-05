package com.yonyou.mes.prm.core.inspectionproject.entity;

import com.yonyou.me.entity.MeSuperVO;

/**
 * mybatis方式
 */
public class InspectionProjectBodyVO extends MeSuperVO {
	
	public static final String PARENTID = "cparentid";

	private String cprjcontent;
	private String cjudstd;
	private Integer fvaluetype;
	private String bautocollect;
	private String cdatalabel;
	private String cdatalabel_name;
	private String brequired;
	private String cparentid;
	private String crowno;
	private String id;
	private String tenantid;
	private String sysid;
	private String orgid;
	private String orgid_name;
	private java.sql.Timestamp ts;
	private Integer dr;
	private String creator;
	private java.sql.Timestamp creationtime;
	private String modifier;
	private java.sql.Timestamp modifiedtime;
	private String creator_name;
	private String modifier_name;

	public InspectionProjectBodyVO() {
	}

	public String getCprjcontent() {
		return this.cprjcontent;
	}

	public void setCprjcontent(String cprjcontent) {
		this.cprjcontent = cprjcontent;
	}

	public String getCjudstd() {
		return this.cjudstd;
	}

	public void setCjudstd(String cjudstd) {
		this.cjudstd = cjudstd;
	}

	public Integer getFvaluetype() {
		return this.fvaluetype;
	}

	public void setFvaluetype(Integer fvaluetype) {
		this.fvaluetype = fvaluetype;
	}

	public String getBautocollect() {
		return this.bautocollect;
	}

	public void setBautocollect(String bautocollect) {
		this.bautocollect = bautocollect;
	}

	public String getCdatalabel() {
		return this.cdatalabel;
	}

	public void setCdatalabel(String cdatalabel) {
		this.cdatalabel = cdatalabel;
	}

	public String getCdatalabel_name() {
		return this.cdatalabel_name;
	}

	public void setCdatalabel_name(String cdatalabel_name) {
		this.cdatalabel_name = cdatalabel_name;
	}

	public String getBrequired() {
		return this.brequired;
	}

	public void setBrequired(String brequired) {
		this.brequired = brequired;
	}

	public String getCparentid() {
		return this.cparentid;
	}

	public void setCparentid(String cparentid) {
		this.cparentid = cparentid;
	}

	public String getCrowno() {
		return this.crowno;
	}

	public void setCrowno(String crowno) {
		this.crowno = crowno;
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

	public java.sql.Timestamp getCreationtime() {
		return this.creationtime;
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

	public java.sql.Timestamp getModifiedtime() {
		return this.modifiedtime;
	}

	public void setModifiedtime(java.sql.Timestamp modifiedtime) {
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
		return "PRM_PROJECT_B";
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