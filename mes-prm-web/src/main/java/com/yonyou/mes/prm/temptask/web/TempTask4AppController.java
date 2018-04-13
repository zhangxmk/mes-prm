package com.yonyou.mes.prm.temptask.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yonyou.me.http.HttpClientUtil;
import com.yonyou.me.utils.dto.ExceptionResult;
import com.yonyou.me.utils.dto.Result;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanHeadVO;
import com.yonyou.mes.prm.core.inspectionplan.service.IInspectionPlanService;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBillVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBodyVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;
import com.yonyou.mes.prm.core.inspectionproject.service.IInspectionProjectService;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBillVO;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskBodyVO;
import com.yonyou.mes.prm.core.temptask.entity.TempTaskHeadVO;
import com.yonyou.mes.prm.core.temptask.service.ITempTaskService;

import java.sql.Timestamp;

@Controller
@RequestMapping(value = "/prm/temptaskapp")
public class TempTask4AppController 
{
	@Autowired
	private IInspectionPlanService plan_service;
	@Autowired
	private IInspectionProjectService project_service;
	@Autowired
	private ITempTaskService temp_service;
	
	/*@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void getPlanData(HttpServletRequest request, HttpServletResponse response)
	{
		String code = request.getParameter("code");
		List<InspectionPlanBillVO> list = new ArrayList<InspectionPlanBillVO>();
		
		try{
			//根据postid查询出所有的表头
			List<String> codes = new ArrayList<String>();
			codes.add(code);
			List<InspectionPlanHeadVO> heads= Arrays.asList(service.selectByCodes(codes));
			
			//用于存储所有表头的id
			List<String> ids = new ArrayList<String>();
			for(InspectionPlanHeadVO head:heads)
			{ids.add(head.getId());}
			
			//根据所有表头id查询出所有表体
			List<InspectionPlanBodyVO> bodys = Arrays.asList(service.selectByParentKeys(ids));
			
			for(InspectionPlanHeadVO head:heads)
			{
				String id = head.getId();
				
				List<InspectionPlanBodyVO> items = new ArrayList<InspectionPlanBodyVO>();
				
				//收集表体
				for(InspectionPlanBodyVO body:bodys)
				{
					if(body.getPk_plan().equals(id))
					{items.add(body);}
				}
				
				//设置表头表体
				InspectionPlanBillVO bill = new InspectionPlanBillVO();
				bill.setHead(head);
				bill.setChildren(InspectionPlanBodyVO.class, items);
				
				list.add(bill);
				
				bodys.remove(items);
			}
			
			//Gson gson = new Gson();
			Gson gson = new GsonBuilder().serializeNulls().create(); 
			String combin ="[";
			
			for(int i=0;i<list.size();i++)
			{
				if(i!=0)
				{combin+=",";}
				
				InspectionPlanBillVO bill = list.get(i);
				String head = gson.toJson(bill.getHead());
				String body = gson.toJson(bill.getChildren(InspectionPlanBodyVO.class));
				
				combin += head.substring(0, head.length()-1) +",\"bitem\":" + body + "}";
			}
			
			combin = "{\"data\":{\"list\":" + combin + "]},\"success\":true}";
			
			HttpClientUtil.writeJSON(response, combin);
			
		} 
		catch (Exception ex) {}
	}*/
	
	/*@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void getPlanData(HttpServletRequest request, HttpServletResponse response)
	{
		String code = request.getParameter("er_project_code");
		List<InspectionPlanBillVO> list = new ArrayList<InspectionPlanBillVO>();
		
		try{
			//根据er_project_code查询出所有的表体
			List<InspectionPlanBodyVO> bodys= Arrays.asList(service.selectByBodyCode(code));
			
			//用于存储所有表体的pk_plan
			List<String> pk_plans = new ArrayList<String>();
			for(InspectionPlanBodyVO body:bodys)
			{pk_plans.add(body.getPk_plan());}
			
			//根据所有表头id查询出所有表体
			List<InspectionPlanHeadVO> heads = Arrays.asList(service.selectByIDs(pk_plans));
			
			for(InspectionPlanHeadVO head:heads)
			{
				String id = head.getId();
				
				List<InspectionPlanBodyVO> items = new ArrayList<InspectionPlanBodyVO>();
				
				//收集表体
				for(InspectionPlanBodyVO body:bodys)
				{
					if(body.getPk_plan().equals(id))
					{items.add(body);}
				}
				
				//设置表头表体
				InspectionPlanBillVO bill = new InspectionPlanBillVO();
				bill.setHead(head);
				bill.setChildren(InspectionPlanBodyVO.class, items);
				
				list.add(bill);
				
				bodys.remove(items);
			}
			
			//Gson gson = new Gson();
			Gson gson = new GsonBuilder().serializeNulls().create(); 
			String combin ="[";
			
			for(int i=0;i<list.size();i++)
			{
				if(i!=0)
				{combin+=",";}
				
				InspectionPlanBillVO bill = list.get(i);
				String head = gson.toJson(bill.getHead());
				String body = gson.toJson(bill.getChildren(InspectionPlanBodyVO.class));
				
				combin += head.substring(0, head.length()-1) +",\"bitem\":" + body + "}";
			}
			
			combin = "{\"data\":{\"list\":" + combin + "]},\"success\":true}";
			
			HttpClientUtil.writeJSON(response, combin);
			
		} 
		catch (Exception ex) {}
	}*/
	
	@RequestMapping(value = "/projectlist", method = RequestMethod.POST)
	public void getProjectData(HttpServletRequest request, HttpServletResponse response)
	{
		String code = request.getParameter("er_project_code");
		List<InspectionProjectBillVO> bill_list = new ArrayList<InspectionProjectBillVO>();
		
		try
		{
			List<InspectionProjectHeadVO> heads = Arrays.asList(project_service.selectByCode(code));
			
			List<String> ids = new ArrayList<String>();
			
			for(InspectionProjectHeadVO head:heads)
			{ids.add(head.getId());}
			
			List<InspectionProjectBodyVO> bodys = Arrays.asList(project_service.selectByParentKeys(ids));
			
			for(InspectionProjectHeadVO head:heads)
			{
				String id = head.getId();
				
				List<InspectionProjectBodyVO> items = new ArrayList<InspectionProjectBodyVO>();
				
				//收集表体
				for(InspectionProjectBodyVO body:bodys)
				{
					if(body.getCparentid().equals(id))
					{items.add(body);}
				}
				
				//设置表头表体
				InspectionProjectBillVO bill = new InspectionProjectBillVO();
				bill.setHead(head);
				bill.setChildren(InspectionProjectBodyVO.class, items);
				
				bill_list.add(bill);
				
				bodys.remove(items);
			}
			
			Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
			String combin ="[";
			
			for(int i=0;i<bill_list.size();i++)
			{
				if(i!=0)
				{combin+=",";}
				
				InspectionProjectBillVO bill = bill_list.get(i);
				String head = gson.toJson(bill.getHead());
				String body = gson.toJson(bill.getChildren(InspectionProjectBodyVO.class));
				
				combin += head.substring(0, head.length()-1) +",\"bitem\":" + body + "}";
			}
			
			combin = "{\"data\":{\"list\":" + combin + "]},\"success\":true}";
			
			HttpClientUtil.writeJSON(response, combin);
		}
		
		catch (Exception ex) {}
	}
	
	@RequestMapping(value = "/planlist", method = RequestMethod.POST)
	public void getPlanData(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		Result result = new Result();
		
		try
		{
			InspectionPlanHeadVO headVO = plan_service.selectByPrimaryKey(id);
			
			Map<String, InspectionPlanHeadVO> data = new HashMap<String, InspectionPlanHeadVO>();
			data.put("head", headVO);
			result.setData(data);
			
			Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
			String rst = gson.toJson(result);
			HttpClientUtil.writeJSON(response, rst);
		}
		
		catch (Exception ex) 
		{result = ExceptionResult.process(ex);}
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public void submit(HttpServletRequest request, HttpServletResponse response)
	{
		String param = request.getParameter("temptask");
		
		TempTaskHeadVO headVO = new TempTaskHeadVO();
		List<TempTaskBodyVO> body_list = new ArrayList<TempTaskBodyVO>();
		
		try
		{
			JSONObject head = JSONObject.fromObject(param);
				
			//String str_head = obj.getString("temptask");
			//JSONObject head = JSONObject.fromObject(str_head);
				
			/*String h_id = (obj.getString("id"));
			if(h_id!="null")
			{headVO.setId(h_id);}
				
			String h_cdeptid = (obj.getString("cdeptid"));
			if(h_cdeptid!="null")
			{headVO.setCdeptid(h_cdeptid);}
				
			String h_vdeptname = (obj.getString("vdeptname"));
			if(h_vdeptname!="null")
			{headVO.setVdeptname(h_vdeptname);}*/
				
			String h_cqcprojectid = head.getString("cqcprojectid");
			if(h_cqcprojectid!="null")
			{headVO.setCqcprojectid(h_cqcprojectid);}
				
			String h_vqcprojectcode = head.getString("vqcprojectcode");
			if(h_vqcprojectcode!="null")
			{headVO.setVqcprojectcode(h_vqcprojectcode);}
				
			String h_vqcprojectname = head.getString("vqcprojectname");
			if(h_vqcprojectname!="null")
			{headVO.setVqcprojectname(h_vqcprojectname);}
				
			/*String h_cstationid = obj.getString("cstationid");
			if(h_cstationid!="null")
			{headVO.setCstationid(h_cstationid);}
				
			String h_vstationname = obj.getString("vstationname");
			if(h_vstationname!="null")
			{headVO.setVstationname(h_vstationname);}*/
				
			String h_tqctime = head.getString("tqctime");
			if(h_tqctime!="null")
			{headVO.setTqctime(Timestamp.valueOf(h_tqctime));}
				
			/*String h_tenantid = obj.getString("tenantid");
			if(h_tenantid!="null")
			{headVO.setTenantid(h_tenantid);}
				
			String h_sysid = obj.getString("sysid");
			if(h_sysid!="null")
			{headVO.setSysid(h_sysid);}*/
				
			String h_orgid = head.getString("orgid");
			if(h_orgid!="null")
			{headVO.setOrgid(h_orgid);}
				
			String h_orgid_name = head.getString("orgid_name");
			if(h_orgid_name!="null")
			{headVO.setOrgid_name(h_orgid_name);}
				
			headVO.setDr(0);
				
			/*String h_ts = obj.getString("ts");
			if(h_ts!="null")
			{headVO.setTs(Timestamp.valueOf(h_ts));}
				
			String h_creator = obj.getString("creator_name");
			if(h_creator!="null")
			{headVO.setCreator(h_creator);}
				
			String h_creator_name = obj.getString("creator_name");
			if(h_creator_name!="null")
			{headVO.setCreator_name(h_creator_name);}
				
			String h_creationtime = obj.getString("creationtime");
			if(h_creationtime!="null")
			{headVO.setCreationtime(Timestamp.valueOf(h_creationtime));}
				
			String h_modifier = obj.getString("modifier");
			if(h_modifier!="null")
			{headVO.setModifier(h_modifier);}
			
			String h_modifier_name = obj.getString("modifier_name");
			if(h_modifier_name!="null")
			{headVO.setModifier_name(h_modifier_name);}
				
			String h_modifiedtime = obj.getString("modifiedtime");
			if(h_modifiedtime!="null")
			{headVO.setModifiedtime(Timestamp.valueOf(h_modifiedtime));}*/
			
			String bitem = head.getString("bitems");
			JSONArray bodys = JSONArray.fromObject(bitem);
			
			for(int j=0;j<bodys.size();j++)
			{
				JSONObject body = bodys.getJSONObject(j);
				
				TempTaskBodyVO bodyVO = new TempTaskBodyVO();
				
				/*String b_id = body.getString("id");
				if(b_id!="null")
				{bodyVO.setId(body.getString("id"));}
					
				String b_pk_temptask = body.getString("pk_temptask");
				if(b_pk_temptask!="null")
				{bodyVO.setPk_temptask(body.getString("pk_temptask"));}*/
					
				String b_vprjcontent = body.getString("vprjcontent");
				if(b_vprjcontent!=null)
				{bodyVO.setVprjcontent(b_vprjcontent);}
					
				String b_vprjcriterion = body.getString("vprjcriterion");
				if(b_vprjcriterion!="null")
				{bodyVO.setVprjcriterion(b_vprjcriterion);}
					
				String b_vqcvalue = body.getString("vqcvalue");
				if(b_vqcvalue!="null")
				{bodyVO.setVqcvalue(b_vqcvalue);}
					
				/*String b_tenantid = body.getString("tenantid");
				if(b_tenantid!="null")
				{bodyVO.setTenantid(b_tenantid);}
					
				String b_sysid = body.getString("sysid");
				if(b_sysid!="null")
				{bodyVO.setSysid(b_sysid);}*/
					
				String b_orgid = body.getString("orgid");
				if(b_orgid!="null")
				{bodyVO.setOrgid(b_orgid);}
					
				String b_orgid_name = body.getString("orgid_name");
				if(b_orgid_name!="null")
				{bodyVO.setOrgid_name(b_orgid_name);}
					
				bodyVO.setDr(0);
					
				/*String b_ts = body.getString("ts");
				if(b_ts!="null")
				{bodyVO.setTs(Timestamp.valueOf(b_ts));}
					
				String b_creator = body.getString("creator");
				if(b_creator!="null")
				{bodyVO.setCreator(b_creator);}
					
				String b_creator_name = body.getString("creator_name");
				if(b_creator_name!="null")
				{bodyVO.setCreator_name(b_creator_name);}
					
				String b_creationtime = body.getString("creationtime");
				if(b_creationtime!="null")
				{bodyVO.setCreationtime(Timestamp.valueOf(b_creationtime));}
					
				String b_modifier = body.getString("modifier");
				if(b_modifier!="null")
				{bodyVO.setModifier(b_modifier);}
					
				String b_modifier_name = body.getString("modifier_name");
				if(b_modifier_name!="null")
				{bodyVO.setModifier_name(b_modifier_name);}
					
				String b_modifiedtime = body.getString("modifiedtime");
				if(b_modifiedtime!="null")
				{bodyVO.setModifiedtime(Timestamp.valueOf(b_modifiedtime));}*/
				
				body_list.add(bodyVO);
			}
		
			TempTaskBillVO billVO = new TempTaskBillVO();
			billVO.setHead(headVO);
			billVO.setChildren(TempTaskBodyVO.class, body_list);
			
			this.temp_service.add(billVO);
			
			String rst = "{\"success\":true}";
			HttpClientUtil.writeJSON(response, rst);
		}
		
		catch (Exception e) {}
	}
	
	@RequestMapping(value = "/temptasklist", method = RequestMethod.POST)
	public void getTempTaskData(HttpServletRequest request, HttpServletResponse response)
	{
		List<String> id = Arrays.asList(request.getParameterValues("param"));
		
		try
		{
			List<TempTaskBillVO> bill_list = Arrays.asList(temp_service.query(id));
			
			//Gson gson = new GsonBuilder().serializeNulls().create(); 
			Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
			String combin ="[";
			
			for(int i=0;i<bill_list.size();i++)
			{
				if(i!=0)
				{combin+=",";}
				
				TempTaskBillVO bill = bill_list.get(i);
				String head = gson.toJson(bill.getHead());
				String body = gson.toJson(bill.getChildren(TempTaskBodyVO.class));
				
				combin += head.substring(0, head.length()-1) +",\"bitem\":" + body + "}";
			}
			
			combin = "{\"data\":{\"list\":" + combin + "]},\"success\":true}";
			
			HttpClientUtil.writeJSON(response, combin);
		}
		
		catch (Exception e) {}
	}
}
