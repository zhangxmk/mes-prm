package com.yonyou.mes.prm.temptask.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping(value = "/prm/temptaskapp")
public class TempTask4AppController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IInspectionPlanService plan_service;
	@Autowired
	private IInspectionProjectService project_service;
	@Autowired
	private ITempTaskService temp_service;

	@RequestMapping(value = "/projectlist", method = RequestMethod.POST)
	public void getProjectData(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("er_project_code");
		List<InspectionProjectBillVO> bill_list = new ArrayList<InspectionProjectBillVO>();

		try {
			List<InspectionProjectHeadVO> heads = Arrays.asList(project_service
					.selectByCode(code));

			if (heads.size() != 0) {
				List<String> ids = new ArrayList<String>();

				for (InspectionProjectHeadVO head : heads) {
					ids.add(head.getId());
				}

				List<InspectionProjectBodyVO> bodys = Arrays
						.asList(project_service.selectByParentKeys(ids));

				for (InspectionProjectHeadVO head : heads) {
					String id = head.getId();

					List<InspectionProjectBodyVO> items = new ArrayList<InspectionProjectBodyVO>();

					// 收集表体
					for (InspectionProjectBodyVO body : bodys) {
						if (body.getCparentid().equals(id)) {
							items.add(body);
						}
					}

					// 设置表头表体
					InspectionProjectBillVO bill = new InspectionProjectBillVO();
					bill.setHead(head);
					bill.setChildren(InspectionProjectBodyVO.class, items);

					bill_list.add(bill);

					bodys.remove(items);
				}
			}

			Gson gson = new GsonBuilder().serializeNulls()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String combin = "[";

			for (int i = 0; i < bill_list.size(); i++) {
				if (i != 0) {
					combin += ",";
				}

				InspectionProjectBillVO bill = bill_list.get(i);
				String head = gson.toJson(bill.getHead());
				String body = gson.toJson(bill
						.getChildren(InspectionProjectBodyVO.class));

				combin += head.substring(0, head.length() - 1) + ",\"bitem\":"
						+ body + "}";
			}

			combin = "{\"data\":{\"list\":" + combin + "]},\"success\":true}";

			HttpClientUtil.writeJSON(response, combin);
		}

		catch (Exception ex) {
			log.error(ex.getMessage());
			try {
				HttpClientUtil.writeJSON(response, ex.getMessage().toString());
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}
	}

	@RequestMapping(value = "/planlist", method = RequestMethod.POST)
	public void getPlanData(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		Result result = new Result();

		try {
			InspectionPlanHeadVO headVO = plan_service.selectByPrimaryKey(id);

			Map<String, InspectionPlanHeadVO> data = new HashMap<String, InspectionPlanHeadVO>();
			data.put("head", headVO);
			result.setData(data);

			Gson gson = new GsonBuilder().serializeNulls()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String rst = gson.toJson(result);
			HttpClientUtil.writeJSON(response, rst);
		}

		catch (Exception ex) {
			log.error(ex.getMessage());
			result = ExceptionResult.process(ex);
		}
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public void submit(HttpServletRequest request, HttpServletResponse response) {
		String param = request.getParameter("temptask");

		TempTaskHeadVO headVO = new TempTaskHeadVO();
		List<TempTaskBodyVO> body_list = new ArrayList<TempTaskBodyVO>();

		try 
		{
			JSONObject head = JSONObject.fromObject(param);
			 
//			String h_cdeptid = head.getString("cdeptid");
//			if(h_cdeptid!="null") 
//			{headVO.setCdeptid(h_cdeptid);}

			String h_cqcprojectid = head.getString("cqcprojectid");
			if (h_cqcprojectid != "null") 
			{headVO.setCqcprojectid(h_cqcprojectid);}

			String dtsource = head.getString("dtsource");
			if(dtsource!=null&&!dtsource.equals("null")){
				headVO.setDtsource(dtsource);
			}
			String deptid = head.getString("deptid");
			String deptid_name = head.getString("deptid_name");
			if(!deptid.equals("null")&&!deptid_name.equals("null")) {
				headVO.setCdeptid(deptid);
				headVO.setVdeptname(deptid_name);
			}
			
//			String h_vbillcode = head.getString("vbillcode");
//			if (h_vbillcode != "null") 
//			{headVO.setVbillcode(h_vbillcode);}
			
			String h_cstationid = head.getString("cstationid");
			if (h_cstationid != "null") 
			{headVO.setCstationid(h_cstationid);}
			
			String h_vstationname = head.getString("vstationname");
			if (h_vstationname != "null") 
			{headVO.setVstationname(h_vstationname);}
			
			String h_errdescribe = head.getString("errdescribe");
			if (h_errdescribe != "null") 
			{headVO.setErrdescribe(h_errdescribe);}

			String h_vqcprojectcode = head.getString("vqcprojectcode");
			if (h_vqcprojectcode != "null") 
			{headVO.setVqcprojectcode(h_vqcprojectcode);}

			String h_vqcprojectname = head.getString("vqcprojectname");
			if (h_vqcprojectname != "null") 
			{headVO.setVqcprojectname(h_vqcprojectname);}

			String h_tqctime = head.getString("tqctime");
			if (h_tqctime != "null") 
			{headVO.setTqctime(Timestamp.valueOf(h_tqctime));}

			String h_orgid = head.getString("orgid");
			if (h_orgid != "null") 
			{headVO.setOrgid(h_orgid);}

			String h_orgid_name = head.getString("orgid_name");
			if (h_orgid_name != "null") 
			{headVO.setOrgid_name(h_orgid_name);}

			headVO.setDr(0);

			String bitem = head.getString("bitems");
			JSONArray bodys = JSONArray.fromObject(bitem);

			for (int j = 0; j < bodys.size(); j++) 
			{
				JSONObject body = bodys.getJSONObject(j);

				TempTaskBodyVO bodyVO = new TempTaskBodyVO();

				String b_vprjcontent = body.getString("vprjcontent");
				if (b_vprjcontent != null) 
				{bodyVO.setVprjcontent(b_vprjcontent);}

				String b_vprjcriterion = body.getString("vprjcriterion");
				if (b_vprjcriterion != "null") 
				{bodyVO.setVprjcriterion(b_vprjcriterion);}

				String b_vqcvalue = body.getString("vqcvalue");
				if (b_vqcvalue != "null") 
				{bodyVO.setVqcvalue(b_vqcvalue);}

				String b_orgid = body.getString("orgid");
				if (b_orgid != "null") 
				{bodyVO.setOrgid(b_orgid);}

				String b_orgid_name = body.getString("orgid_name");
				if (b_orgid_name != "null") 
				{bodyVO.setOrgid_name(b_orgid_name);}
				
				String b_vrowno = body.getString("vrowno");
				if (b_vrowno != "null") 
				{bodyVO.setVrowno(b_vrowno);}
				
				String b_isqualified = body.getString("isqualified");
				if (b_isqualified != "null") 
				{bodyVO.setIsqualified(Integer.parseInt(b_isqualified));}

				bodyVO.setDr(0);

				body_list.add(bodyVO);
			}

			TempTaskBillVO billVO = new TempTaskBillVO();
			billVO.setHead(headVO);
			billVO.setChildren(TempTaskBodyVO.class, body_list);

			this.temp_service.add(billVO);

			String rst = "{\"success\":true}";
			HttpClientUtil.writeJSON(response, rst);
		}

		catch (Exception e) 
		{
			log.error(e.getMessage());
			try 
			{HttpClientUtil.writeJSON(response, e.getMessage());} 
			
			catch (Exception e1) 
			{e1.printStackTrace();}
		}
	}

	@RequestMapping(value = "/temptasklist", method = RequestMethod.POST)
	public void getTempTaskData(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> id = Arrays.asList(request.getParameterValues("param"));

		try {
			List<TempTaskBillVO> bill_list = Arrays.asList(temp_service
					.query(id));

			// Gson gson = new GsonBuilder().serializeNulls().create();
			Gson gson = new GsonBuilder().serializeNulls()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String combin = "[";

			for (int i = 0; i < bill_list.size(); i++) {
				if (i != 0) {
					combin += ",";
				}

				TempTaskBillVO bill = bill_list.get(i);
				String head = gson.toJson(bill.getHead());
				String body = gson.toJson(bill
						.getChildren(TempTaskBodyVO.class));

				combin += head.substring(0, head.length() - 1) + ",\"bitem\":"
						+ body + "}";
			}

			combin = "{\"data\":{\"list\":" + combin + "]},\"success\":true}";

			HttpClientUtil.writeJSON(response, combin);
		}

		catch (Exception e) {
			log.error(e.getMessage());
			try {
				HttpClientUtil.writeJSON(response, e.getMessage());
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}
}
