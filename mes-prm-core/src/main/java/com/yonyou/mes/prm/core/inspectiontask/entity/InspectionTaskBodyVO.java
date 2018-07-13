package com.yonyou.mes.prm.core.inspectiontask.entity;

import java.sql.Timestamp;

import com.yonyou.me.entity.MeSuperVO;



/**
 * mybatis方式
 */
public class InspectionTaskBodyVO extends MeSuperVO{
	
	private String pk_task;
	private Integer plan_order;
	private String projectid;
	private String projectid_code;
	private String projectid_name;
	private Integer fact_order;
	private Integer crowno;
	private Integer project_status;
	private String project_content;
	private String project_contentid;
	private String judge_standard;
	private String polling_value;
	private Integer polling_result;
	private String pic_url;
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
	private Timestamp complete_time;
	private String errdescribe;
	private	Integer fvaluetype;
	private String factuser;
	private String factuser_name;

	public String getFactuser() {
		return factuser;
	}

	public void setFactuser(String factuser) {
		this.factuser = factuser;
	}

	public String getFactuser_name() {
		return factuser_name;
	}

	public void setFactuser_name(String factuser_name) {
		this.factuser_name = factuser_name;
	}

	public String getRdbvalue() {
		return rdbvalue;
	}

	public void setRdbvalue(String rdbvalue) {
		this.rdbvalue = rdbvalue;
	}

	private String rdbvalue;
	public InspectionTaskBodyVO() {
	}
    

	public String getPk_task() {
		return this.pk_task;
	}

	public void setPk_task(String pk_task) {
		this.pk_task = pk_task;
	}


	public Integer getPlan_order() {
		return this.plan_order;
	}

	public void setPlan_order(Integer plan_order) {
		this.plan_order = plan_order;
	}


	public String getProjectid() {
		return projectid;
	}


	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}


	public String getProjectid_name() {
		return projectid_name;
	}


	public void setProjectid_name(String projectid_name) {
		this.projectid_name = projectid_name;
	}


	public Integer getProject_status() {
		return project_status;
	}


	public void setProject_status(Integer project_status) {
		this.project_status = project_status;
	}


	public Integer getFact_order() {
		return this.fact_order;
	}

	public void setFact_order(Integer fact_order) {
		this.fact_order = fact_order;
	}
	
	public Integer getCrowno() {
		return this.crowno;
	}

	public void setCrowno(Integer crowno) {
		this.crowno = crowno;
	}

	public String getProject_content() {
		return this.project_content;
	}

	public void setProject_content(String project_content) {
		this.project_content = project_content;
	}
	
	public String getProject_contentid() {
		return this.project_contentid;
	}

	public void setProject_contentid(String project_contentid) {
		this.project_contentid = project_contentid;
	}


	public String getJudge_standard() {
		return this.judge_standard;
	}

	public void setJudge_standard(String judge_standard) {
		this.judge_standard = judge_standard;
	}


	public String getPolling_value() {
		return this.polling_value;
	}

	public void setPolling_value(String polling_value) {
		this.polling_value = polling_value;
	}


	public String getPic_url() {
		return this.pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
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


	public Timestamp getComplete_time() {
		return this.complete_time;
	}

	public void setComplete_time(Timestamp complete_time) {
		this.complete_time = complete_time;
	}

	public Integer getFvaluetype() {
		return this.fvaluetype;
	}

	public void setFvaluetype(Integer fvaluetype) {
		this.fvaluetype = fvaluetype;
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
		return "PRM_TASK_B";
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


	public String getProjectid_code() {
		return projectid_code;
	}


	public void setProjectid_code(String projectid_code) {
		this.projectid_code = projectid_code;
	}


	public Integer getPolling_result() {
		return polling_result;
	}


	public void setPolling_result(Integer polling_result) {
		this.polling_result = polling_result;
	}

	/**
	 * @return errdescribe
	 */
	public String getErrdescribe() {
		return errdescribe;
	}

	/**
	 * @param errdescribe 要设置的 errdescribe
	 */
	public void setErrdescribe(String errdescribe) {
		this.errdescribe = errdescribe;
	}


}