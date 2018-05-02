package com.yonyou.mes.prm.core.temptask.entity;

import java.sql.Timestamp;

import com.yonyou.me.entity.MeSuperVO;



/**
 * mybatis方式
 */
public class TempTaskHeadVO extends MeSuperVO{
	
	private String id;
	private String cdeptid;
	private String vdeptname;
	private String cqcprojectid;
	private String vqcprojectcode;
	private String vqcprojectname;
	private String vbillcode;
	private String cstationid;
	private String vstationname;
	private Timestamp tqctime;
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
	public TempTaskHeadVO() {
	}
    

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getCdeptid() {
		return this.cdeptid;
	}

	public void setCdeptid(String cdeptid) {
		this.cdeptid = cdeptid;
	}


	public String getVdeptname() {
		return this.vdeptname;
	}

	public void setVdeptname(String vdeptname) {
		this.vdeptname = vdeptname;
	}


	public String getCqcprojectid() {
		return this.cqcprojectid;
	}

	public void setCqcprojectid(String cqcprojectid) {
		this.cqcprojectid = cqcprojectid;
	}


	public String getVqcprojectcode() {
		return this.vqcprojectcode;
	}

	public void setVqcprojectcode(String vqcprojectcode) {
		this.vqcprojectcode = vqcprojectcode;
	}


	public String getVqcprojectname() {
		return this.vqcprojectname;
	}

	public void setVqcprojectname(String vqcprojectname) {
		this.vqcprojectname = vqcprojectname;
	}


	public String getCstationid() {
		return this.cstationid;
	}

	public void setCstationid(String cstationid) {
		this.cstationid = cstationid;
	}


	public String getVstationname() {
		return this.vstationname;
	}

	public void setVstationname(String vstationname) {
		this.vstationname = vstationname;
	}


	public Timestamp getTqctime() {
		return this.tqctime;
	}

	public void setTqctime(Timestamp tqctime) {
		this.tqctime = tqctime;
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


//	public Timestamp getTs() {
//		return this.ts;
//	}
//
//	public void setTs(Timestamp ts) {
//		this.ts = ts;
//	}


	public String getVbillcode() {
		return vbillcode;
	}

	public void setVbillcode(String vbillcode) {
		this.vbillcode = vbillcode;
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


//	public Timestamp getCreationtime() {
//		return this.creationtime;
//	}
//
//	public void setCreationtime(Timestamp creationtime) {
//		this.creationtime = creationtime;
//	}


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


//	public Timestamp getModifiedtime() {
//		return this.modifiedtime;
//	}
//
//	public void setModifiedtime(Timestamp modifiedtime) {
//		this.modifiedtime = modifiedtime;
//	}


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
		return this.creationtime;
	}


	@Override
	public Timestamp getModifiedtime() {
		// TODO 自动生成的方法存根
		return this.modifiedtime;
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
		// TODO 自动生成的方法存根
		return "prm_temptask";
	}


	@Override
	public Timestamp getTs() {
		// TODO 自动生成的方法存根
		return this.ts;
	}


	@Override
	public void setCode(String code) {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void setCreationtime(Timestamp creationtime) {
		// TODO 自动生成的方法存根
		this.creationtime = creationtime;
		
	}


	@Override
	public void setModifiedtime(Timestamp modifiedtime) {
		// TODO 自动生成的方法存根
		this.modifiedtime = modifiedtime;
	}


	@Override
	public void setOriginalid(String originalid) {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void setParentid(String parentid) {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void setTs(Timestamp ts) {
		// TODO 自动生成的方法存根
		this.ts = ts;
	}

}