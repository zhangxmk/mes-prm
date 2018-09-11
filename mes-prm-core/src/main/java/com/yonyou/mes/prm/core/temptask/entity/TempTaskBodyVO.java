package com.yonyou.mes.prm.core.temptask.entity;

import java.sql.Timestamp;

import com.yonyou.me.entity.MeSuperVO;

/**
 * mybatis方式
 */
public class TempTaskBodyVO extends MeSuperVO {

	public static final String PARENTID = "pk_temptask";

	private String id;
	private String vrowno;
	private String vprjcontent;
	private String vprjcriterion;
	private String vqcvalue;
	private String rdbvalue;
	private String errdescribe;

	public String getErrdescribe() {
		return errdescribe;
	}

	public void setErrdescribe(String errdescribe) {
		this.errdescribe = errdescribe;
	}
	
	public String getRdbvalue() {
		return rdbvalue;
	}

	public void setRdbvalue(String rdbvalue) {
		this.rdbvalue = rdbvalue;
	}

	public Integer getIsqualified() {
		return isqualified;
	}

	public void setIsqualified(Integer isqualified) {
		this.isqualified = isqualified;
	}

	private Integer isqualified;
	private String pk_temptask;
	private String tenantid;
	private String sysid;
	private String orgid;
	private String orgid_name;
	private Integer dr;
	private Timestamp ts;
	private String creator;
	private String creator_name;
	private String modifier;
	private String modifier_name;

	public TempTaskBodyVO() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVrowno() {
		return this.vrowno;
	}

	public void setVrowno(String vrowno) {
		this.vrowno = vrowno;
	}

	public String getVprjcontent() {
		return this.vprjcontent;
	}

	public void setVprjcontent(String vprjcontent) {
		this.vprjcontent = vprjcontent;
	}

	public String getVprjcriterion() {
		return this.vprjcriterion;
	}

	public void setVprjcriterion(String vprjcriterion) {
		this.vprjcriterion = vprjcriterion;
	}

	public String getVqcvalue() {
		return this.vqcvalue;
	}

	public void setVqcvalue(String vqcvalue) {
		this.vqcvalue = vqcvalue;
	}

	public String getPk_temptask() {
		return this.pk_temptask;
	}

	public void setPk_temptask(String pk_temptask) {
		this.pk_temptask = pk_temptask;
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
	public Timestamp getCreationtime() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Timestamp getModifiedtime() {
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
		return "prm_temptask_b";
	}

	@Override
	public Timestamp getTs() {
		return ts;
	}

	@Override
	public void setCode(String code) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void setCreationtime(Timestamp creationtime) {
		// TODO 自动生成的方法存根
	}

	@Override
	public void setModifiedtime(Timestamp modifiedtime) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void setOriginalid(String originalid) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void setParentid(String parentid) {

	}

	@Override
	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

}