package com.yonyou.mes.prm.statable.web;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.me.base.BaseController;
import com.yonyou.me.utils.dto.ExceptionResult;
import com.yonyou.me.utils.dto.Result;
import com.yonyou.mes.prm.core.inspectiontask.entity.TaskForTableVO;
import com.yonyou.mes.prm.core.inspectiontask.service.IInspectionTaskService;


@RestController
@RequestMapping(value = "/prm/statable")
public class StaTableController extends BaseController{
	
	
	@Autowired
	private IInspectionTaskService service;
	
	@RequestMapping(value = "/exportdata", method = RequestMethod.POST)
	
	public @ResponseBody Object exportData(PageRequest pageRequest, @RequestParam String ids,HttpServletRequest request, HttpServletResponse response) throws WriteException, IOException {
		
		Result result = new Result();
		OutputStream os = null;
		WritableWorkbook workbook = null;
		try {
			SearchParams searchParams = new SearchParams();
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(ids);
			Object deptid =  json.get("deptid");
			Object team =  json.get("team");
			Object shift =  json.get("shift");
			Object postid =  json.get("postid");
			Object deptid_name =  json.get("deptid_name");
			Object team_name  =  json.get("team_name");
			Object shift_name  =  json.get("shift_name");
			Object postid_name  =  json.get("postid_name");
			Object starttime =  json.get("starttime");
			Object finishtime =  json.get("finishtime");
			Object groupstr =  json.get("groupstr");
			Object orderby =  json.get("orderby");
			
			Map<String,Object> searchmap = new HashMap<String, Object>();
			searchmap.put("deptid", deptid);
			searchmap.put("team", team);
			searchmap.put("shift", shift);
			searchmap.put("postid", postid);
			searchmap.put("starttime", starttime);
			searchmap.put("finishtime", finishtime);
			searchmap.put("groupstr", groupstr);
			searchmap.put("orderby", orderby);
			searchParams.setSearchMap(searchmap);
			
			Page<TaskForTableVO> pageVOs = service.selectForStaTable(
					null, searchParams);
			List<TaskForTableVO> list = pageVOs.getContent();
			
			//数据导出容器
			List<List<Object>> resultList = new ArrayList<List<Object>>();
			
			//标题
			List<Object> listTitle = getTitle();
			resultList.add(listTitle);
			
			//内容
			for(TaskForTableVO vo :list){
				List<Object> tempList = new LinkedList<Object>();
				if(deptid_name!=null){
					tempList.add(deptid_name);
				}else{
					tempList.add(vo.getDeptid_name());
				}
				if(team_name!=null){
					tempList.add(team_name);
				}else{
					tempList.add(vo.getTeam_name());
				}
				if(shift_name!=null){
					tempList.add(shift_name);
				}else{
					tempList.add(vo.getShift_name());
				}
				if(postid_name!=null){
					tempList.add(postid_name);
				}else{
					tempList.add(vo.getPostid_name());
				}
				Double plancount = Double.valueOf(vo.getPlancount());
				Double unfinishcount = Double.valueOf(vo.getUnfinishcount());
				Double rate = (plancount-unfinishcount)/plancount;
				DecimalFormat df = new DecimalFormat("0.00%");
				
				tempList.add(plancount);
				tempList.add(unfinishcount);
				tempList.add(df.format(rate));
				
				resultList.add(tempList);
			}
			String sheetName = "巡检完成情况统计表";
			String fileName = "巡检完成情况统计表";
			fileName = new String(fileName.getBytes("GB2312"), "ISO8859-1"); // 导出的文字编码
			WritableCellFormat midTileCenterFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 10,
				       WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE)); 
			midTileCenterFormat.setBorder(Border.ALL, BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			midTileCenterFormat.setAlignment(Alignment.CENTRE);
			//内容居中
			WritableCellFormat midContentFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 10,
				       WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE)); 
			midContentFormat.setBorder(Border.ALL, BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			midContentFormat.setAlignment(Alignment.CENTRE);
			
			WritableCellFormat midTileCenterFormatForHead = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 10,
				       WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE)); 
			midTileCenterFormatForHead.setAlignment(Alignment.CENTRE);
			
			WritableCellFormat midContentFormatForHead = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 10,
				       WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE)); 
			midContentFormatForHead.setAlignment(Alignment.CENTRE);
			//导出数据
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment;filename="+fileName+".xls");
			os = response.getOutputStream();
			workbook = Workbook.createWorkbook(os);
			WritableSheet ws = workbook.createSheet(sheetName, 0);//创建可写工作表
			ws.getSettings().setDefaultColumnWidth(14);
			
			//查询参数信息
			ws.addCell(CellFactory(0,0,"车间",midTileCenterFormatForHead));
			ws.addCell(CellFactory(3,0,"班组",midTileCenterFormatForHead));
			ws.addCell(CellFactory(0,1,"班次",midTileCenterFormatForHead));
			ws.addCell(CellFactory(3,1,"岗位",midTileCenterFormatForHead));
			ws.addCell(CellFactory(0,2,"起止日期",midTileCenterFormatForHead));
			if(deptid_name!=null){
				ws.addCell(CellFactory(1,0,deptid_name,midContentFormatForHead));
			}
			if(team_name!=null){
				ws.addCell(CellFactory(4,0,team_name,midContentFormatForHead));
			}
			if(shift_name!=null){
				ws.addCell(CellFactory(1,1,shift_name,midContentFormatForHead));
			}
			if(postid_name!=null){
				ws.addCell(CellFactory(4,1,postid_name,midContentFormatForHead));
			}
			if(starttime!=null&&finishtime!=null){
				String date = ((String)starttime).substring(0, 10)+"-"+((String)finishtime).substring(0, 10);
				ws.addCell(CellFactory(1,2,date,midContentFormatForHead));
			}

			
			if(null != resultList && resultList.size()>0){
				for(int i=0;i<resultList.size();i++){
					List<Object> rowList = resultList.get(i);
					for(int j=0;j<rowList.size();j++){
						if(i==0){
							ws.addCell(CellFactory(j,i+3,rowList.get(j),midTileCenterFormat));
						}else{
							ws.addCell(CellFactory(j,i+3,rowList.get(j),midContentFormat));
						}
					}
				}
			}
			workbook.write();
			
		} catch (Exception ex) {
			result = ExceptionResult.process(ex);
			
		}finally {
			if(null != workbook){
				workbook.close();
			}
			if (os != null) {
				os.flush();
				os.close();
			}
		}
		return result;
	}
	
	private List<Object> getTitle(){
		List<Object> listTitle = new LinkedList<Object>();
		listTitle.add("车间");
		listTitle.add("班组");
		listTitle.add("班次");
		listTitle.add("岗位");
		listTitle.add("计划次数");
		listTitle.add("未完成次数");
		listTitle.add("完成率");
		return listTitle;
		
	}
	/**
	 * 根据数据类型不同，建立相应的对象
	* @param i
	* @param j
	* @param columnVal
	* @param columnFormat
	* @return
	* @throws Exception
	 */
	private static WritableCell CellFactory(int i,int j,Object columnVal,WritableCellFormat columnFormat) throws Exception{
		WritableCell returnCell = null;
		
		if(null == columnVal){
			returnCell = new Label(i,j,"",columnFormat);
		}else{
			if(columnVal.getClass().equals(Long.class)){
				returnCell = new jxl.write.Number(i,j,Long.parseLong(columnVal.toString()),columnFormat);
			}else if(columnVal.getClass().equals(Integer.class)){
				returnCell = new jxl.write.Number(i,j,Integer.parseInt(columnVal.toString()),columnFormat);
			}else if(columnVal.getClass().equals(Double.class)){
				returnCell = new jxl.write.Number(i,j,Double.parseDouble(columnVal.toString()),columnFormat);
			}else if(columnVal.getClass().equals(Float.class)){
				returnCell = new jxl.write.Number(i,j,Float.parseFloat(columnVal.toString()),columnFormat);
			}else if(columnVal.getClass().equals(String.class)){
				returnCell = new Label(i,j,columnVal.toString(),columnFormat);
			}else if(columnVal.getClass().equals(Date.class)){
				returnCell = new jxl.write.DateTime(i,j,(Date)columnVal,columnFormat);
			}else if(columnVal.getClass().equals(BigDecimal.class)){
				returnCell = new jxl.write.Number(i,j,((BigDecimal)columnVal).doubleValue(),columnFormat);
			}else if(columnVal.getClass().equals(Timestamp.class)){
				returnCell = new jxl.write.DateTime(i,j,((Timestamp)columnVal),columnFormat);
			}else{
				returnCell = new Label(i,j,"",columnFormat);
				System.out.println("columnVal.type="+columnVal.getClass());
			}
		}
		return returnCell;
	}
	

}
