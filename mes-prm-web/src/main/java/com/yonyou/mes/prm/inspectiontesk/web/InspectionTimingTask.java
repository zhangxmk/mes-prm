package com.yonyou.mes.prm.inspectiontesk.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.utils.PropertyUtil;
import com.yonyou.me.http.RestUtils;

/**
 * 巡检任务后台任务处理
 * @author weijw
 *
 * 2018年3月12日
 */
@RestController
@RequestMapping(value = "/prm/timingtask")
public class InspectionTimingTask {
	@Value("${base.url}")  
	private String baseurl;
	
	@RequestMapping(value = "/createtask", method = RequestMethod.POST)
	public @ResponseBody Object pageString(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject data) {
		JSONObject postData = JSONObject.fromObject(data);
	    JSONObject dataBody = postData.getJSONObject("data");

	    final String param1;
	    if (dataBody != null && !dataBody.isNullObject()) {
	        if (dataBody.has("planid")) {
	            param1 = dataBody.getString("planid");
	        }
	    }
	    final String tasklogid = postData.getString("tasklogid");
	    
	    response.setCharacterEncoding("utf-8");
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("success", "true");
	    map.put("resultValue", "任务执行中！");
	    map.put("sendMsgContent", "任务执行中！");
	    map.put("asynchronized", "true");// 是否异步。如果异步的话，则显示任务执行中

	
	            try {
	                executeTask();
	                callBackResult(tasklogid, "true", "任务执行成功！");
	            } catch (Exception e) {
	                callBackResult(tasklogid, "false", e.getMessage());
	            }

	    return map;		
	}
	
	private void executeTask() throws Exception {
	    Thread.sleep(1000);
	}

	/**
	 * 回调返回任务执行结果
	 * 
	 * @param tasklogid
	 *            任务ID
	 * @param success
	 *            是否成功：true代表成功，其他代表失败
	 * @param msg
	 *            具体消息内容
	 */
	private void callBackResult(String tasklogid, String success, String msg) {
	    String url = baseurl
	            + "/iuap-saas-dispatch-service/taskcallback/updateTaskLog";
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("id", tasklogid);
	    map.put("success", success);
	    map.put("resultValue", msg);
	    Map<String, String> result = RestUtils.getInstance().doPost(url, map, Map.class);
	    System.out.println(result);
	}

}
