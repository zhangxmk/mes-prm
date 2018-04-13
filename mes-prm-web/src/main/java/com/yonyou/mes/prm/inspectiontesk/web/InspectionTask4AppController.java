package com.yonyou.mes.prm.inspectiontesk.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
import com.yonyou.me.entity.MeSuperVO;
import com.yonyou.me.http.HttpClientUtil;
import com.yonyou.me.utils.dto.ExceptionResult;
import com.yonyou.me.utils.dto.Result;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBillVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;
import com.yonyou.mes.prm.core.inspectiontask.service.IInspectionTaskService;

import org.springframework.data.domain.Page;

/*
 * 巡检任务提供给巡检APP的服务
 */
@Controller
@RequestMapping(value = "/prm/taskapp")
public class InspectionTask4AppController {
	@Autowired   
	private IInspectionTaskService service;

	/**
	 * 根据岗位查询已下达和执行中的巡检任务明细
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void getTaskDatas(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		String postid = request.getParameter("postid");

		try {
			InspectionTaskBodyVO[] list = service.queryTaskDetails(postid);
			Map<String, MeSuperVO[]> data = new HashMap<String, MeSuperVO[]>();
			data.put("list", list);
			result.setData(data);
			
			Gson gson = new Gson();
			String rst = gson.toJson(result);
			HttpClientUtil.writeJSON(response, rst);


		} catch (Exception ex) {
			result = ExceptionResult.process(ex);
		}
		
	}

	@RequestMapping(value = "/tasklist", method = RequestMethod.POST)
	public void getTaskHeadDatas(HttpServletRequest request, HttpServletResponse response) {
		String postid = request.getParameter("postid");
		List<InspectionTaskBillVO> list = new ArrayList<InspectionTaskBillVO>();
		
		try{
			//根据postid查询出所有的表头
			Page<InspectionTaskHeadVO> heads_page= service.queryTask(postid);
			
			List<InspectionTaskHeadVO> heads=heads_page.getContent();
			//用于存储所有表头的id
			List<String> ids = new ArrayList<String>();
			//用于存储所有表头的
			//List<String> planids = new ArrayList<String>();
			
			for(InspectionTaskHeadVO head:heads)
			{
				ids.add(head.getId());
				/*if(head.getPlanid()!=null)
				planids.add(head.getPlanid());*/
			}
			//根据所有表头id查询出所有表体
			List<InspectionTaskBodyVO> bodys = Arrays.asList(service.queryTaskDetailsByID(ids));
			
			for(InspectionTaskHeadVO head:heads)
			{
				String id = head.getId();
				
				List<InspectionTaskBodyVO> items = new ArrayList<InspectionTaskBodyVO>();
				
				//收集表体
				for(InspectionTaskBodyVO body:bodys)
				{
					if(body.getPk_task().equals(id))
					{items.add(body);}
				}
				
				//设置表头表体
				InspectionTaskBillVO bill = new InspectionTaskBillVO();
				bill.setHead(head);
				bill.setChildren(InspectionTaskBodyVO.class, items);
				
				list.add(bill);
				
				bodys.remove(items);
			}
			
			//Gson gson = new Gson();
			Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
			String combin ="[";
			
			for(int i=0;i<list.size();i++)
			{
				if(i!=0)
				{combin+=",";}
				
				InspectionTaskBillVO bill = list.get(i);
				String head = gson.toJson(bill.getHead());
				String body = gson.toJson(bill.getChildren(InspectionTaskBodyVO.class));
				
				combin += head.substring(0, head.length()-1) +",\"bitem\":" + body + "}";
			}
			
			combin = "{\"data\":{\"list\":" + combin + "]},\"success\":true}";
			
			HttpClientUtil.writeJSON(response, combin);
			
		} 
		catch (Exception ex) {}
	}
	
	/*@RequestMapping(value = "/submit2", method = RequestMethod.POST)
	public void submit2(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String[] param = request.getParameterValues("param");
		
		String id = request.getParameter("id");
		String polling_value = request.getParameter("polling_value");
		String pk_task = request.getParameter("pk_task");
		
		List<String> list_pk_task = new ArrayList<String>();
		list_pk_task.add(pk_task);
		
		try
		{
			service.submit(id,polling_value);
			
			List<InspectionTaskBodyVO> bodys = Arrays.asList(service.queryTaskDetailsByID(list_pk_task));
			
			//finish表示表头的项目状态，1：下达:2：执行中、3：完成:4：未完成
			int billstatus = 1;
			boolean finish = true;
			
			for(InspectionTaskBodyVO body:bodys)
			{
				//完成状态不可能为完成
				if(body.getProject_status() == null || body.getProject_status() == 1)
				{ finish = false; }
				
				else{billstatus=2;}
				
				//已确认没有全部完成并且有完成的,确认为执行中
				if(finish == false && billstatus == 2)
				{break;}
			}
		
			if(finish == true)
			{billstatus = 3;}
			
			if(billstatus != 1)
			{
				InspectionTaskHeadVO head = new InspectionTaskHeadVO();
				head.setBillstatus(billstatus);
				head.setId(pk_task);
				
				service.updateHead(head);
			}
			
			String rst = "{\"success\":true}";
			HttpClientUtil.writeJSON(response, rst);
		}
		catch (Exception ex) 
		{}
	}*/
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public void submit(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String param = request.getParameter("param");
		JSONArray bodys = JSONArray.fromObject(param);
		
		String pk_task="";
		
		List<InspectionTaskBodyVO> list = new ArrayList<InspectionTaskBodyVO>();
		
		//构建VO用于更新
		for(int i=0;i<bodys.size();i++)
		{
			//String str = param[i];
			
			//JSONObject obj = JSONObject.fromObject();
			
			JSONObject body = bodys.getJSONObject(i);
			
			if(i==0)
			{pk_task = body.getString("pk_task");}
			
			String id = body.getString("id");
			String polling_value = body.getString("polling_value");
			
			InspectionTaskBodyVO bodyVO = new InspectionTaskBodyVO();
			bodyVO.setId(id);
			bodyVO.setPolling_value(polling_value);
			bodyVO.setProject_status(2);
			
			list.add(bodyVO);
		}
		
		try
		{
			service.batchUpdateByPrimaryKeySelective(list);
			
			List<String> list_pk_task = new ArrayList<String>();
			list_pk_task.add(pk_task);
			
			//查出表头的所有子表
			List<InspectionTaskBodyVO> body_list = Arrays.asList(service.queryTaskDetailsByID(list_pk_task));
			
			//finish表示表头的项目状态，1：下达:2：执行中、3：完成:4：未完成
			int billstatus = 1;
			boolean finish = true;
			
			for(InspectionTaskBodyVO body:body_list)
			{
				//完成状态不可能为完成
				if(body.getProject_status() == null || body.getProject_status() == 1)
				{ finish = false; }
				
				else{billstatus=2;}
				
				//已确认没有全部完成并且有完成的,确认为执行中
				if(finish == false && billstatus == 2)
				{break;}
			}
		
			if(finish == true)
			{billstatus = 3;}
			
			if(billstatus != 1)
			{
				InspectionTaskHeadVO head = new InspectionTaskHeadVO();
				head.setBillstatus(billstatus);
				head.setId(pk_task);
				
				service.updateHead(head);
			}
			
			String rst = "{\"success\":true}";
			HttpClientUtil.writeJSON(response, rst);
		}
		
		catch(Exception e){}
	}
}
