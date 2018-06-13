package com.yonyou.mes.prm.core.inspectiontask.entity;

import java.sql.Timestamp;

import com.yonyou.me.entity.MeSuperVO;



/**
 * 巡检任务主表
 */
public class InspectionTaskHeadVO extends MeSuperVO{
	
	private String task_no;
	private String postid;
	private String postid_name;
	private String planid;
	private String planid_name;
	private String deptid;

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptid_name() {
		return deptid_name;
	}

	public void setDeptid_name(String deptid_name) {
		this.deptid_name = deptid_name;
	}

	private String deptid_name;
	private String team;
	private String team_name;
	private String shift;
	private String shift_name;
	private String userid;
	private String userid_name;
	private Integer billstatus;
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
	private Timestamp assign_time;
	private String pk_region_name;
	private String routeflag;
	public InspectionTaskHeadVO() {
	}
    

	public String getTask_no() {
		return this.task_no;
	}

	public void setTask_no(String task_no) {
		this.task_no = task_no;
	}

	public String getPostid() {
		return postid;
	}


	public void setPostid(String postid) {
		this.postid = postid;
	}


	public String getPostid_name() {
		return postid_name;
	}


	public void setPostid_name(String postid_name) {
		this.postid_name = postid_name;
	}


	public String getPlanid() {
		return planid;
	}


	public void setPlanid(String planid) {
		this.planid = planid;
	}


	public String getPlanid_name() {
		return planid_name;
	}


	public void setPlanid_name(String planid_name) {
		this.planid_name = planid_name;
	}
	
	public String getPk_region_name() {
		return this.pk_region_name;
	}

	public void setPk_region_name(String pk_region_name) {
		this.pk_region_name = pk_region_name;
	}
	
	public String getRouteflag() {
		return this.routeflag;
	}

	public void setRouteflag(String routeflag) {
		this.routeflag = routeflag;
	}
	
	public String getTeam() {
		return team;
	}


	public void setTeam(String team) {
		this.team = team;
	}


	public String getTeam_name() {
		return team_name;
	}


	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}


	public String getShift() {
		return shift;
	}


	public void setShift(String shift) {
		this.shift = shift;
	}


	public String getShift_name() {
		return shift_name;
	}


	public void setShift_name(String shift_name) {
		this.shift_name = shift_name;
	}
	
	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUserid_name() {
		return userid_name;
	}


	public void setUserid_name(String userid_name) {
		this.userid_name = userid_name;
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


	public Timestamp getAssign_time() {
		return this.assign_time;
	}

	public void setAssign_time(Timestamp assign_time) {
		this.assign_time = assign_time;
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
		// TODO 自动生成的方法存根
		return "PRM_TASK";
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


	public Integer getBillstatus() {
		return billstatus;
	}


	public void setBillstatus(Integer billstatus) {
		this.billstatus = billstatus;
	}

}