package com.zcmu.careplan.service;

import com.zcmu.careplan.dao.NursingPlanDao;
import com.zcmu.careplan.dao.NursingPlanTemplateDao;
import com.zcmu.careplan.pojo.NursingPlan;
import com.zcmu.careplan.pojo.NursingPlanTemplate;
import com.zcmu.careplan.pojo.NursingPlanTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class NursingPlanService  {
    @Autowired
    private NursingPlanTemplateDao nursingPlanTemplateDao;

    @Autowired
    private NursingPlanDao nursingPlanDao;

    /**
     * 查询所有的护理计划模板
     * @return
     */
    public List<NursingPlanTemplateVO> findNurseTemplate() {
        List<NursingPlanTemplate> nursingPlanTemplateList = nursingPlanTemplateDao.findAllByIsValid("1");

        List<NursingPlanTemplateVO> nursingPlanTemplateVOList = new ArrayList<>();
        for (NursingPlanTemplate nursingPlanTemplate:nursingPlanTemplateList) {
            NursingPlanTemplateVO nursingPlanTemplateVO = new NursingPlanTemplateVO();
            nursingPlanTemplateVO.setId(nursingPlanTemplate.getId());
            nursingPlanTemplateVO.setName(nursingPlanTemplate.getName());
            nursingPlanTemplateVO.setContent(nursingPlanTemplate.getContent());
            String[] target = nursingPlanTemplate.getTarget().split("，");
            String[] nursingMeasure = nursingPlanTemplate.getNursingMeasure().split("，");
            nursingPlanTemplateVO.setTarget(Arrays.asList(target));
            nursingPlanTemplateVO.setNursingMeasure(Arrays.asList(nursingMeasure));
            nursingPlanTemplateVOList.add(nursingPlanTemplateVO);
        }
        return nursingPlanTemplateVOList;
    }

    /**
     *保存护理计划
     * @param nursingPlan
     */
    public void saveNursePlan(NursingPlan nursingPlan) {
        nursingPlanDao.save(nursingPlan);
    }

    /**
     * 查询所有的护理计划
     * @param patientId
     */
    public List<NursingPlan> findByNursePlanById(String patientId) {
        List<NursingPlan>  findByNursePlanByIdList = nursingPlanDao.findAllByPatientId(patientId);
        return findByNursePlanByIdList;
    }

    /**
     * 删除护理计划
     * @param id
     */
    public void deleteNursePlan(String id) {
        nursingPlanDao.deleteById(id);
    }
}
