package com.yonyou.mes.prm.core.inspectionregion.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.me.entity.AbstractMeBillVO;
import com.yonyou.me.entity.EnableStateVO;
import com.yonyou.me.utils.exception.ExceptionUtils;
import com.yonyou.me.utils.repository.BillPersistent;
import com.yonyou.me.utils.service.EnableStateServiceImpl;
import com.yonyou.me.utils.service.IBaseQueryBS;
import com.yonyou.me.utils.service.IEnableStateService;
import com.yonyou.me.utils.service.bill.BillSaveService;
import com.yonyou.me.utils.service.vo.VODeleteService;
import com.yonyou.mes.prm.core.inspectionregion.entity.InspectionRegionBillVO;
import com.yonyou.mes.prm.core.inspectionregion.entity.InspectionRegionVO;
import com.yonyou.mes.prm.core.inspectionregion.repository.InspectionRegionMapper;
import com.yonyou.mes.prm.core.inspectionregion.repository.InspectionRegionPersistent;
import com.yonyou.mes.prm.core.inspectionregion.service.IInspectionRegionService;

/**
 * @author wangkem
 * 2018年2月6日
 * @description 巡检区域服务实现类
 */

@Service
public class InspectionRegionServiceImpl implements IInspectionRegionService {

    // 主表mapper
    @Autowired
    InspectionRegionMapper headMapper;

    @Autowired
    IBaseQueryBS dao;

		/*// 子表mapper
		@Autowired
		MeasurePointTypeBodyMapper bodyMapper;
*/

    //VODeleteService vodeleteservice;

    /**
     * 分页查询方法
     *
     * @param pageRequest
     * @param searchParams
     * @return
     */
    public Page<InspectionRegionVO> selectAllByPage(
            PageRequest pageRequest, SearchParams searchParams) {
        return headMapper.selectAllByPage(pageRequest, searchParams).getPage();
    }


    /**
     * 查询表体信息
     *
     * @return
     */
		 /* @Override
		  public Page<MeasurePointTypeBodyVO> queryBodyByPage(PageRequest pageRequest, SearchParams searchParams) {

		    return bodyMapper.selectAllByPage(pageRequest, searchParams).getPage();
		  }*/

    /**
     * 新增保存
     *
     * @param vo
     */
    @Transactional
    public InspectionRegionBillVO add(InspectionRegionBillVO vo) {
        if (vo == null) {
            ExceptionUtils.wrapBusinessException("传入数据为空");
        }
        BillPersistent dao = this.createBillDao();
        BillSaveService service = new BillSaveService(dao);

        // 保存前后规则
        // service.addBeforeRule(rule);
        // service.addAfterRule(rule);
        // 设置填充字段
        // TODO
        if (checkUnique(vo)) {
            ExceptionUtils.wrapBusinessException("区域编码、名称存在重复，请检查。");
        }
        List<?> list = service.save(new AbstractMeBillVO[]{vo});
        InspectionRegionBillVO retVo = (InspectionRegionBillVO) list.get(0);

        return retVo;
    }

    /**
     * 修改保存
     *
     * @param vo
     */
    @Transactional
    public InspectionRegionBillVO update(InspectionRegionBillVO vo) {
        if (vo == null) {
            ExceptionUtils.wrapBusinessException("传入数据为空");
        }
        BillPersistent dao = this.createBillDao();
        BillSaveService service = new BillSaveService(dao);

        // 保存前后规则
        // service.addBeforeRule(rule);
        // service.addAfterRule(rule);
        // 设置填充字段
        // TODO

        List<?> list = service.save(new AbstractMeBillVO[]{vo});
        InspectionRegionBillVO retVo = (InspectionRegionBillVO) list.get(0);

        return retVo;
    }

    private boolean checkUnique(InspectionRegionBillVO vo) {
        try {
            List<InspectionRegionVO> regvo = dao.queryVOsBySql("select * from PRM_REGION where dr=0 and orgid='" + vo.getHead().getOrgid() + "'" +
                    " and code='" + vo.getHead().getCode() + "' and name='" + vo.getHead().getName() + "'", InspectionRegionVO.class);
            if (regvo != null && regvo.size() > 0) {
                return true;
            }
        } catch (Exception e) {
            ExceptionUtils.wrapException(e);
        }

        return false;
    }

    /**
     * 批量删除
     *
     * @param list
     */
    @Transactional
    public void batchDeleteByPrimaryKey(InspectionRegionVO[] vos) {
        InspectionRegionPersistent headPersistent = new InspectionRegionPersistent(
                this.headMapper);
        VODeleteService vodeleteservice = new VODeleteService(headPersistent);
//			vodeleteservice.addBeforeRule(rule);
//			vodeleteservice.addAfterRule(rule);
        vodeleteservice.delete(vos);
    }

    /**
     * 查询单据，包括表头和表体
     */
		/*@Override
		public InspectionRegionBillVO[] query(List<String> ids) {
			if (null == ids || ids.size() == 0) {
				ExceptionUtils.wrapBusinessException("查询主键不能为空！" + ids);
			}
			List<InspectionRegionBillVO> list = new ArrayList<InspectionRegionBillVO>();
			
			InspectionRegionVO[] heads = this.headMapper.selectByIDs(ids);
			for(InspectionRegionVO head:heads){
				String id = head.getId();
				//MeasurePointTypeBodyVO[] items = this.bodyMapper.selectByParentKey(id);
				
				InspectionRegionBillVO bill = new InspectionRegionBillVO();
				bill.setHead(head);
				//bill.setChildren(MeasurePointTypeBodyVO.class, Arrays.asList(items));
				
				list.add(bill);
			}
			
			return list.toArray(new InspectionRegionBillVO[0]);
		}*/
    private BillPersistent createBillDao() {
        // 创建单据保存器
        BillPersistent billdao = new BillPersistent();
        // 注册主表数据库操作
        InspectionRegionPersistent headPersistent = new InspectionRegionPersistent(
                this.headMapper);
        billdao.regiter(InspectionRegionVO.class, headPersistent);
			/*// 注册子表数据库操作
			MeasurePointTypeBodyPersistent bodyPersistent = new MeasurePointTypeBodyPersistent(
					this.bodyMapper);
			billdao.regiter(MeasurePointTypeBodyVO.class, bodyPersistent);*/

        return billdao;
    }


    @Override
    public InspectionRegionBillVO[] query(List<String> ids) {
        // TODO 自动生成的方法存根
        return null;
    }


    @Override
    public void batchDisableByPrimaryKey(InspectionRegionVO[] vos) {
        List<EnableStateVO> statelist = new ArrayList<>();
        for (InspectionRegionVO def : vos) {
            EnableStateVO vo = new EnableStateVO();
            vo.setId(def.getId());
            vo.setTs(def.getTs());
            vo.setTableName(def.getTableName());
            statelist.add(vo);
        }
        IEnableStateService serv = new EnableStateServiceImpl();
        serv.unableState(statelist);

    }


    @Override
    public void batchEnableByPrimaryKey(InspectionRegionVO[] vos) {
        List<EnableStateVO> statelist = new ArrayList<>();
        for (InspectionRegionVO def : vos) {
            EnableStateVO vo = new EnableStateVO();
            vo.setId(def.getId());
            vo.setTs(def.getTs());
            vo.setTableName(def.getTableName());
            statelist.add(vo);
        }
        IEnableStateService serv = new EnableStateServiceImpl();
        serv.enableState(statelist);

    }

}
