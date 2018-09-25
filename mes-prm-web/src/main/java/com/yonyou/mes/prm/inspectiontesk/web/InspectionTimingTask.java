package com.yonyou.mes.prm.inspectiontesk.web;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yonyou.me.constance.StatusConst;
import com.yonyou.me.entity.MeSuperVO;
import com.yonyou.me.utils.context.LoginUtil;
import com.yonyou.me.utils.service.IBaseQueryBS;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBillVO;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanBodyVO;
import com.yonyou.mes.prm.core.inspectionplan.entity.InspectionPlanHeadVO;
import com.yonyou.mes.prm.core.inspectionplan.service.IInspectionPlanService;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBillVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectBodyVO;
import com.yonyou.mes.prm.core.inspectionproject.entity.InspectionProjectHeadVO;
import com.yonyou.mes.prm.core.inspectionproject.service.IInspectionProjectService;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBillVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskBodyVO;
import com.yonyou.mes.prm.core.inspectiontask.entity.InspectionTaskHeadVO;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.utils.PropertyUtil;
import com.yonyou.me.http.RestUtils;
import com.yonyou.mes.prm.core.inspectiontask.service.IInspectionTaskService;

/**
 * 巡检任务后台任务处理
 *
 * @author weijw
 * <p>
 * 2018年3月12日
 */
@RestController
@RequestMapping(value = "/prm/timingtask/restWithSign")
public class InspectionTimingTask {
    private Logger logger = LoggerFactory.getLogger(InspectionTimingTask.class);
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private IInspectionPlanService service;
    @Autowired
    private IInspectionProjectService projservice;
    @Autowired
    private IInspectionTaskService taskService;
    @Autowired
    private IBaseQueryBS qrysrv;

    @RequestMapping(value = "/createtask", method = RequestMethod.POST)
    public @ResponseBody
    Object pageString(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject data) throws Exception {
        JSONObject postData = JSONObject.fromObject(data);
        JSONObject dataBody = postData.getJSONObject("data");


        String planid = "";
        if (dataBody != null && !dataBody.isNullObject()) {
            if (dataBody.has("planid")) {
                planid = dataBody.getString("planid");
            }
        }
        String id = planid;
        String tasklogid = postData.getString("tasklogid");

        response.setCharacterEncoding("utf-8");
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "true");
        map.put("resultValue", "任务执行中！");
        map.put("sendMsgContent", "任务执行中！");
        map.put("asynchronized", "true");// 是否异步。如果异步的话，则显示任务执行中

        threadPoolTaskExecutor.execute(new Runnable() {
                                           @Override
                                           public void run() {
                                               try {
                                                   executeTask(id);
                                                   callBackResult(tasklogid, "true", "任务执行成功！");
                                               } catch (Exception e) {
                                                   callBackResult(tasklogid, "false", e.getMessage());
                                               }
                                           }
                                       }
        );

        return map;
    }

    private void executeTask(String planid) throws Exception {

        logger.error("任务执行开始");

        new LoginUtil().login_iuapAP("{'username':'zyj','password':'123qwe'}");

        List<InspectionPlanHeadVO> plans = qrysrv.queryVOsBySql("select * from prm_plan where dr=0 and defplan=1 and enablestate=1", InspectionPlanHeadVO.class);
        List<String> planids = new ArrayList<>();
        for (InspectionPlanHeadVO head :
                plans) {
            planids.add(head.getId());

        }
        InspectionPlanBillVO[] billvos = service.query(planids);
        Map<String,InspectionPlanBillVO> pbill = new HashMap<>();
        for (InspectionPlanBillVO billVO  :
             billvos) {
            pbill.put(billVO.getHead().getId(),billVO);
        }
        String idphase = " and planid in ("+getIn(planids)+")";
        String sql = "select *" +
                "from prm_task a, (select" +
                "                    planid," +
                "                    max(creationtime) maxtime" +
                "                  from prm_task" +
                "                  where dr = 0 "+idphase +
                "                  group by planid" +
                "" +
                "                 ) T" +
                " where a.planid = T.planid and a.dr = 0 and a.creationtime = T.maxtime";
        List<InspectionTaskHeadVO> headlist = qrysrv.queryVOsBySql(sql, InspectionTaskHeadVO.class);
        Map<String,InspectionTaskHeadVO> tmap = convert2map(headlist);
        for (InspectionPlanHeadVO phead :
                plans) {
            if (tmap.containsKey(phead.getId())) {
                Date now = new Date();
                Date origin = new Date(now.getTime()-(1000*60*60*phead.getCycle().longValue())-1000*60*30);
//                LocalTime now = LocalTime.now(TimeZone.getDefault().toZoneId());
//                now = now.minusHours(phead.getCycle().longValue());

                boolean cancreate = tmap.get(phead.getId()).getCreationtime().before(origin);
//                cancreate = true;
                if(cancreate){
                    if(tmap.get(phead.getId()).getBillstatus()==1||tmap.get(phead.getId()).getBillstatus()==2){
                        InspectionTaskHeadVO thead = tmap.get(phead.getId());
                        thead.setStatus(StatusConst.update);
                        thead.setBillstatus(4);
                        taskService.updateHead(thead);
                    }
                    InspectionPlanBillVO bill = pbill.get(phead.getId());
                    InspectionTaskBillVO nbill = new InspectionTaskBillVO();

                    InspectionPlanHeadVO head = (InspectionPlanHeadVO) bill.getHead();
                    List<MeSuperVO> bodys = bill.getChildren(InspectionPlanBodyVO.class);

                    InspectionTaskHeadVO nhead = new InspectionTaskHeadVO();
                    nhead.setOrgid(head.getOrgid());
                    nhead.setOrgid_name(head.getOrgid_name());
                    nhead.setDeptid(head.getPk_dept());
                    nhead.setDeptid_name(head.getPk_dept_name());
                    nhead.setProcessid(head.getPk_process());
                    nhead.setProcessid_name(head.getPk_process_name());
                    nhead.setReleased_time(new Timestamp(now.getTime()-1000*60*30));
                    nhead.setBillstatus(1);
                    nhead.setPlanid(head.getId());
                    nhead.setPlanid_name(head.getName());
                    nhead.setPostid(head.getPk_post());
                    nhead.setPostid_name(head.getPk_post_name());
                    nhead.setSysid(head.getSysid());
                    nhead.setTenantid(head.getTenantid());
                    nhead.setStatus(2);

                    List<InspectionTaskBodyVO> nlist = new ArrayList<>();
                    for (MeSuperVO spvo :
                            bodys) {
                        InspectionPlanBodyVO bodyvo = (InspectionPlanBodyVO) spvo;
                        List<String> bids = new ArrayList<>();
                        bids.add(bodyvo.getPk_er_project());
                        InspectionProjectBillVO[] projbills = projservice.query(bids);
                        InspectionProjectBillVO projbill = projbills[0];
                        List<MeSuperVO> pbodys = projbill.getChildren(InspectionProjectBodyVO.class);

                        for (MeSuperVO psuper :
                                pbodys) {
                            InspectionProjectBodyVO pbody = (InspectionProjectBodyVO) psuper;
                            InspectionTaskBodyVO tbvo = new InspectionTaskBodyVO();
                            tbvo.setSysid(phead.getSysid());
                            tbvo.setTenantid(phead.getTenantid());
                            tbvo.setProjectid(phead.getId());
                            tbvo.setProjectid_code(pbody.getCode());
                            tbvo.setProjectid_name(pbody.getName());
                            tbvo.setPlan_order(bodyvo.getPlan_order());
                            tbvo.setProject_content(pbody.getCprjcontent_name());//设备name
                            tbvo.setProject_contentid(pbody.getCprjcontent());//设备
                            tbvo.setJudge_standard(pbody.getCjudstd());
                            tbvo.setPrjcontent(pbody.getPrjcontent());//项目内容
                            tbvo.setProject_status(1);//下达
                            tbvo.setStatus(2);
                            nlist.add(tbvo);

                        }


                    }
                    nbill.setHead(nhead);
                    nbill.setChildren(InspectionTaskBodyVO.class, nlist);
                    taskService.add(nbill);
                }

            }else{
                InspectionPlanBillVO bill = pbill.get(phead.getId());
                InspectionTaskBillVO nbill = new InspectionTaskBillVO();

                InspectionPlanHeadVO head = (InspectionPlanHeadVO) bill.getHead();
                List<MeSuperVO> bodys = bill.getChildren(InspectionPlanBodyVO.class);
                Date now = new Date();

                InspectionTaskHeadVO nhead = new InspectionTaskHeadVO();
                nhead.setOrgid(head.getOrgid());
                nhead.setOrgid_name(head.getOrgid_name());
                nhead.setDeptid(head.getPk_dept());
                nhead.setDeptid_name(head.getPk_dept_name());
                nhead.setProcessid(head.getPk_process());
                nhead.setProcessid_name(head.getPk_process_name());
                nhead.setBillstatus(1);
                nhead.setPlanid(head.getId());
                nhead.setPlanid_name(head.getName());
                nhead.setPostid(head.getPk_post());
                nhead.setPostid_name(head.getPk_post_name());
                nhead.setSysid(head.getSysid());
                nhead.setTenantid(head.getTenantid());
                nhead.setReleased_time(new Timestamp(now.getTime()-1000*60*30));
                nhead.setStatus(2);

                List<InspectionTaskBodyVO> nlist = new ArrayList<>();
                for (MeSuperVO spvo :
                        bodys) {
                    InspectionPlanBodyVO bodyvo = (InspectionPlanBodyVO) spvo;
                    List<String> bids = new ArrayList<>();
                    bids.add(bodyvo.getPk_er_project());
                    InspectionProjectBillVO[] projbills = projservice.query(bids);
                    InspectionProjectBillVO projbill = projbills[0];
                    List<MeSuperVO> pbodys = projbill.getChildren(InspectionProjectBodyVO.class);

                    for (MeSuperVO psuper :
                            pbodys) {
                        InspectionProjectBodyVO pbody = (InspectionProjectBodyVO) psuper;
                        InspectionTaskBodyVO tbvo = new InspectionTaskBodyVO();
                        tbvo.setSysid(phead.getSysid());
                        tbvo.setTenantid(phead.getTenantid());
                        tbvo.setProjectid(phead.getId());
                        tbvo.setProjectid_code(pbody.getCode());
                        tbvo.setProjectid_name(pbody.getName());
                        tbvo.setPlan_order(bodyvo.getPlan_order());
                        tbvo.setProject_content(pbody.getCprjcontent_name());//设备name
                        tbvo.setProject_contentid(pbody.getCprjcontent());//设备
                        tbvo.setJudge_standard(pbody.getCjudstd());
                        tbvo.setPrjcontent(pbody.getPrjcontent());//项目内容
                        tbvo.setProject_status(1);//下达
                        tbvo.setStatus(2);
                        nlist.add(tbvo);

                    }


                }
                nbill.setHead(nhead);
                nbill.setChildren(InspectionTaskBodyVO.class, nlist);
                taskService.add(nbill);
            }
        }


//        String[] ids = new String[]{planid};
//        InspectionPlanBillVO[] billvos = service.query(Arrays.asList(ids));
//        List<InspectionTaskBillVO> newbills = new ArrayList<>();
//        for (InspectionPlanBillVO bill :
//                billvos) {
//            InspectionTaskBillVO nbill = new InspectionTaskBillVO();
//
//            InspectionPlanHeadVO head = (InspectionPlanHeadVO) bill.getHead();
//            List<MeSuperVO> bodys = bill.getChildren(InspectionPlanBodyVO.class);
//
//            InspectionTaskHeadVO nhead = new InspectionTaskHeadVO();
//            nhead.setOrgid(head.getOrgid());
//            nhead.setOrgid_name(head.getOrgid_name());
//            nhead.setDeptid(head.getPk_dept());
//            nhead.setDeptid_name(head.getPk_dept_name());
//            nhead.setPlanid(head.getId());
//            nhead.setPlanid_name(head.getName());
//            nhead.setPostid(head.getPk_post());
//            nhead.setPostid_name(head.getPk_post_name());
//            nhead.setSysid(head.getSysid());
//            nhead.setTenantid(head.getTenantid());
//            nhead.setStatus(2);
//
//            List<InspectionTaskBodyVO> nlist = new ArrayList<>();
//            for (MeSuperVO spvo :
//                    bodys) {
//                InspectionPlanBodyVO bodyvo = (InspectionPlanBodyVO) spvo;
//                List<String> bids = new ArrayList<>();
//                bids.add(bodyvo.getPk_er_project());
//                InspectionProjectBillVO[] projbills = projservice.query(bids);
//                InspectionProjectBillVO projbill = projbills[0];
//                InspectionProjectHeadVO phead = (InspectionProjectHeadVO) projbill.getHead();
//                List<MeSuperVO> pbodys = projbill.getChildren(InspectionProjectBodyVO.class);
//
//                for (MeSuperVO psuper :
//                        pbodys) {
//                    InspectionProjectBodyVO pbody = (InspectionProjectBodyVO) psuper;
//                    InspectionTaskBodyVO tbvo = new InspectionTaskBodyVO();
//                    tbvo.setSysid(phead.getSysid());
//                    tbvo.setTenantid(phead.getTenantid());
//                    tbvo.setProjectid(phead.getId());
//                    tbvo.setProjectid_code(phead.getCode());
//                    tbvo.setProjectid_name(phead.getName());
//                    tbvo.setPlan_order(bodyvo.getPlan_order());
//                    tbvo.setProject_content(pbody.getCprjcontent());
//                    tbvo.setJudge_standard(pbody.getCjudstd());
//                    tbvo.setProject_status(1);//下达
//                    tbvo.setStatus(2);
//                    nlist.add(tbvo);
//
//                }
//
//
//            }
//            nbill.setHead(nhead);
//            nbill.setChildren(InspectionTaskBodyVO.class, nlist);
//            taskService.add(nbill);
//        }
    }

    private Map<String,InspectionTaskHeadVO> convert2map(List<InspectionTaskHeadVO> headlist) {
        Map<String,InspectionTaskHeadVO> res = new HashMap<>();
        for (InspectionTaskHeadVO head :
                headlist) {
            res.put(head.getPlanid(), head);
        }
        return res;
    }

    private String getIn(List<String> planids) {
        String res = "";
        for (String id:planids){
            res=res+"'"+id+"',";
        }
        res = res.substring(0,res.length()-1);
        return res;
    }

    /**
     * 回调返回任务执行结果
     *
     * @param tasklogid 任务ID
     * @param success   是否成功：true代表成功，其他代表失败
     * @param msg       具体消息内容
     */
    private void callBackResult(String tasklogid, String success, String msg) {
        String url = PropertyUtil.getPropertyByKey("base.url")
                + "/iuap-saas-dispatch-service/taskcallback/updateTaskLog";
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", tasklogid);
        map.put("success", success);
        map.put("resultValue", msg);
        com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
//        obj.put("dept",)
        Map<String, String> result = RestUtils.getInstance().doPostWithSign(url, map, Map.class);
//	    System.out.println(result);
        logger.error(result.toString());
        logger.error("任务执行结束");
    }

}
