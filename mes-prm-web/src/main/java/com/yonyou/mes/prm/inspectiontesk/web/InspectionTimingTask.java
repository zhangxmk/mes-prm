package com.yonyou.mes.prm.inspectiontesk.web;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 巡检任务后台任务处理
 * @author weijw
 *
 * 2018年3月12日
 */
@RestController
@RequestMapping(value = "/prm/timingtask")
public class InspectionTimingTask {
	@RequestMapping(value = "/inserttask", method = RequestMethod.POST)
	public @ResponseBody Object pageString(@RequestBody JSONObject data) {
		
		
		return data;
		
	}
}
