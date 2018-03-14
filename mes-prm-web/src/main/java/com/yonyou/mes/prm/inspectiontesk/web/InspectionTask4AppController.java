package com.yonyou.mes.prm.inspectiontesk.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.yonyou.me.entity.MeSuperVO;
import com.yonyou.me.utils.dto.ExceptionResult;
import com.yonyou.me.utils.dto.Result;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;
import com.yonyou.mes.prm.core.inspectiontask.service.IInspectionTaskService;

/*
 * 巡检任务提供给巡检APP的服务
 */
@Controller
@RequestMapping(value = "/prm/taskapp")
public class InspectionTask4AppController {
	@Autowired
	private IInspectionTaskService service;

	/**
	 * 查询已下达和执行中的巡检任务
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
			this.writeJSON(response, rst);


		} catch (Exception ex) {
			result = ExceptionResult.process(ex);
		}
		
	}
	
	private void writeJSON(HttpServletResponse response, String json) throws Exception{
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			response.flushBuffer();
		
	}
}
