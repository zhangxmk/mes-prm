package com.yonyou.mes.prm.inspectiontesk.web;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.utils.exception.ExceptionUtils;
import com.yonyou.me.utils.lock.VOLockOperaor;
import com.yonyou.me.utils.repository.BillPersistent;
import com.yonyou.me.utils.repository.VOPersistent;
import com.yonyou.me.utils.service.IBaseQueryBS;
import com.yonyou.me.utils.service.bill.BillSaveService;
import com.yonyou.me.utils.service.vo.VOSaveService;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBodyVO;
import com.yonyou.mes.prm.core.inspectiontask.repository.TaskBodyMapper;
import com.yonyou.mes.prm.core.inspectiontask.repository.TaskBodyPersistent;
import com.yonyou.mes.prm.core.inspectiontask.repository.TaskHeadPersistent;

import nc.vo.pub.VOStatus;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yonyou.me.entity.MeSuperVO;
import com.yonyou.me.http.HttpClientUtil;
import com.yonyou.me.utils.dto.ExceptionResult;
import com.yonyou.me.utils.dto.Result;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBillVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;
import com.yonyou.mes.prm.core.inspectiontask.service.IInspectionTaskService;

/*
 * 巡检任务提供给巡检APP的服务
 */
@Controller
@RequestMapping(value = "/prm/taskapp")
public class InspectionTask4AppController {
    @Autowired
    private IInspectionTaskService service;

    @Autowired
    private IBaseQueryBS qrybs;

    // 子表mapper
    @Autowired
    TaskBodyMapper bodyMapper;


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

        try {
            //根据postid查询出所有的表头
            Page<InspectionTaskHeadVO> heads_page = service.queryTask(postid);

            List<InspectionTaskHeadVO> heads = heads_page.getContent();

            if (heads.size() != 0) {
                //用于存储所有表头的id
                List<String> ids = new ArrayList<String>();
                //用于存储所有表头的
                //List<String> planids = new ArrayList<String>();

                for (InspectionTaskHeadVO head : heads) {
                    ids.add(head.getId());
					/*if(head.getPlanid()!=null)
					planids.add(head.getPlanid());*/
                }
                //根据所有表头id查询出所有表体
                List<InspectionTaskBodyVO> bodys = Arrays.asList(service.queryTaskDetailsByID(ids));
//                bodys = bodys.stream().filter(e->e.getProject_status()!=3).collect(Collectors.toList());
                for (InspectionTaskHeadVO head : heads) {
                    String id = head.getId();

                    List<InspectionTaskBodyVO> items = new ArrayList<InspectionTaskBodyVO>();
                    Set<String> prjids = new HashSet<>();
                    //收集表体
                    for (InspectionTaskBodyVO body : bodys) {
                        if (body.getPk_task().equals(id)) {
                            items.add(body);
                        }
                        prjids.add(body.getProjectid());
                    }
                    List<InspectionProjectBodyVO> prjbodys = qrybs.queryByCond("prm_project_b", " cparentid in (" +
                            inSql(prjids.toArray(new String[0])) + ")", null, InspectionProjectBodyVO.class);
                    Map<String, InspectionProjectBodyVO> prjmap = new HashMap<>();
                    for (InspectionProjectBodyVO b :
                            prjbodys) {
                        prjmap.put(b.getCparentid() + b.getCrowno(), b);
                    }
                    for (InspectionTaskBodyVO tvo :
                            bodys) {
                        tvo.setFvaluetype(prjmap.get(tvo.getProjectid()+tvo.getCrowno()).getFvaluetype());
//                        tvo.setFvaluetype(1);
                    }

                    //设置表头表体
                    InspectionTaskBillVO bill = new InspectionTaskBillVO();
                    bill.setHead(head);
                    bill.setChildren(InspectionTaskBodyVO.class, items);

                    list.add(bill);

                    bodys.remove(items);
                }
            }
            //Gson gson = new Gson();
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String combin = "[";

            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    combin += ",";
                }

                InspectionTaskBillVO bill = list.get(i);
                String head = gson.toJson(bill.getHead());
                String body = gson.toJson(bill.getChildren(InspectionTaskBodyVO.class));


                combin += head.substring(0, head.length() - 1) + ",\"bitem\":" + body + "}";
            }

            combin = "{\"data\":{\"list\":" + combin + "]},\"success\":true}";

            HttpClientUtil.writeJSON(response, combin);

        } catch (Exception ex) {
            try {
                HttpClientUtil.writeJSON(response, ex.getMessage().toString());
            } catch (Exception e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/checkunique", method = RequestMethod.POST)
    public void checkUnique(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String id = request.getParameter("projectbid");
            String userid = request.getParameter("userid");
            String username = request.getParameter("username");

            List<InspectionTaskBodyVO> list = qrybs.queryVOsBySql("select * from prm_task_b where id='" + id + "'",
                    InspectionTaskBodyVO.class);
            if (list == null || list.size() == 0) {
                ExceptionUtils.wrapBusinessException("数据异常");
            }
            InspectionTaskBodyVO body = list.get(0);
            if(body.getFactuser()==null){
                body.setStatus(VOStatus.UPDATED);
                body.setFactuser(userid);
                body.setFactuser_name(username);

                // 创建单据保存器
                // 注册子表数据库操作
                TaskBodyPersistent bodyPersistent = new TaskBodyPersistent(
                        this.bodyMapper);
                VOSaveService service = new VOSaveService(bodyPersistent);
                service.save(new InspectionTaskBodyVO[]{body});
                String rst = "{\"success\":true}";
                HttpClientUtil.writeJSON(response, rst);
            }else{
                if(!body.getFactuser().equals(userid)){
                    String rst = "{\"success\":false,\"errorinfo\":\"当前巡检项目已被认领\"}";
                    HttpClientUtil.writeJSON(response, rst);
                }else{
                    String rst = "{\"success\":true}";
                    HttpClientUtil.writeJSON(response, rst);
                }
            }


        } catch (Exception e) {
            HttpClientUtil.writeJSON(response, e.getMessage());
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody Object submit(@RequestBody String param) throws Exception {
    	Result result = new Result();
        try {
            //String param = request.getParameter("param");
            JSONObject head = JSONObject.fromObject(param);
            JSONArray bodys = JSONArray.fromObject(head.getString("bitems"));
            HashMap<String, InspectionTaskBodyVO> hmap = new HashMap<String, InspectionTaskBodyVO>();
            String pk_task = "";

            for (int i = 0; i < bodys.size(); i++) {
                JSONObject body = bodys.getJSONObject(i);

                if (i == 0) {
                    pk_task = body.getString("pk_task");
                }

                InspectionTaskBodyVO bodyVO = new InspectionTaskBodyVO();

                bodyVO.setId(body.getString("id"));

                String assign_time = body.getString("assign_time");
                if (assign_time != null && assign_time != "null") {
                    bodyVO.setComplete_time(Timestamp.valueOf(assign_time));
                }

                String rdbvalue = body.getString("rdbvalue");
                if (rdbvalue != null && rdbvalue != "null") {
                    bodyVO.setRdbvalue(rdbvalue);
                }

                String errdescribe = body.getString("errdescribe");
                if (errdescribe != null && errdescribe != "null") {
                    bodyVO.setErrdescribe(errdescribe);
                }

                Integer polling_result = body.getInt("polling_result");
                if (polling_result != null && polling_result != null) {
                    bodyVO.setPolling_result(polling_result);
                }

                String polling_value = body.getString("polling_value");
                if (polling_value != null && polling_value != "null") {
                    bodyVO.setPolling_value(polling_value);
                }

                String pic_url = body.getString("pic_url");
                if (pic_url != null && pic_url != "null") {
                    bodyVO.setPolling_value(pic_url);
                }

                String fact_order = body.getString("fact_order");
                if (fact_order != null && fact_order != "null") {
                    bodyVO.setFact_order(Integer.parseInt(fact_order));
                }
                
                String factuser = body.getString("factuser");
                if (factuser != null && factuser != "null") {
                    bodyVO.setFactuser(factuser);
                }
                
                String factuser_name = body.getString("factuser_name");
                if (factuser_name != null && factuser_name != "null") {
                    bodyVO.setFactuser_name(factuser_name);
                }

                hmap.put(body.getString("id"), bodyVO);
            }

            List<String> list_pk_task = new ArrayList<String>();
            list_pk_task.add(pk_task);

            //查出表头的所有子表
            List<InspectionTaskBodyVO> body_list = Arrays.asList(service.queryTaskDetailsByID(list_pk_task));

            //finish表示表头的项目状态，1：下达:2：执行中、3：完成:4：未完成
            int billstatus = 1;
            boolean finish = true;

            for (InspectionTaskBodyVO body : body_list) {
                String id = body.getId();

                if (hmap.containsKey(id)) {
                    InspectionTaskBodyVO bodyVO = hmap.get(id);

                    Timestamp assign_time = bodyVO.getComplete_time();
                    if (assign_time != null) {
                        body.setComplete_time(assign_time);
                    }

                    String rdbvalue = bodyVO.getRdbvalue();
                    if (rdbvalue != null && rdbvalue != "null") {
                        body.setRdbvalue(rdbvalue);
                    }

                    String errdescribe = bodyVO.getErrdescribe();
                    if (errdescribe != null && errdescribe != "null") {
                        body.setErrdescribe(errdescribe);
                    }

                    Integer polling_result = bodyVO.getPolling_result();
                    if (polling_result != null && polling_result != null) {
                        body.setPolling_result(polling_result);
                    }

                    String polling_value = bodyVO.getPolling_value();
                    if (polling_value != null && polling_value != "null") {
                        body.setPolling_value(polling_value);
                    }

                    String pic_url = bodyVO.getPic_url();
                    if (pic_url != null && pic_url != "null") {
                        body.setPolling_value(pic_url);
                    }

                    Integer fact_order = bodyVO.getFact_order();
                    if (fact_order != null) {
                        body.setFact_order(fact_order);
                    }
                    
                    String factuser = bodyVO.getFactuser();
                    if (factuser != null && factuser != "null") {
                        body.setFactuser(factuser);
                    }
                    
                    String factuser_name = bodyVO.getFactuser_name();
                    if (factuser_name != null && factuser_name != "null") {
                        body.setFactuser_name(factuser_name);
                    }

                    body.setProject_status(3);
                    body.setStatus(1);
                }

                //完成状态不可能为完成
                if (body.getProject_status() == null || body.getProject_status() == 1) {
                    finish = false;
                } else {
                    billstatus = 2;
                }
            }


            InspectionTaskHeadVO headVO = service.queryHeadById(pk_task);
            if (finish == true) {
                billstatus = 3;
                headVO.setAssign_time(new Timestamp(System.currentTimeMillis()));
            }
            if (headVO.getBillstatus() != billstatus) {
                headVO.setBillstatus(billstatus);
            }

            headVO.setShift(head.getString("shift"));
            headVO.setShift_name(head.getString("shift_name"));
            headVO.setTeam(head.getString("team"));
            headVO.setTeam_name(head.getString("team_name"));
            headVO.setStatus(1);

            InspectionTaskBillVO billVO = new InspectionTaskBillVO();
            billVO.setHead(headVO);
            billVO.setChildren(InspectionTaskBodyVO.class, body_list);

            service.update(billVO);

            String rst = "{\"success\":true}";
            result.setData(billVO);
            //HttpClientUtil.writeJSON(response, rst);
        } catch (Exception e) {
        	result = ExceptionResult.process(e);
//            try {
//                HttpClientUtil.writeJSON(response, e.getMessage().toString());
//            } catch (Exception ex) {
//                ExceptionUtils.wrapException(ex);
//            }
        }
        return result ;
    }

    private String inSql(String... ids) {
        String s = null;
        if (ids.length > 0) {
            s = "'" + String.join("','", ids) + "'";
        }
        return s;
    }
}
