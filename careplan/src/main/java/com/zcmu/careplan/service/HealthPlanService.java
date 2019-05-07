package com.zcmu.careplan.service;

import com.zcmu.careplan.dao.HealthPlanDao;
import com.zcmu.careplan.pojo.HealthPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class HealthPlanService {

    @Autowired
    private HealthPlanDao healthPlanDao;
    /**
     * 保存健康教育
     * @param healthPlan
     */
    public void savehealthPlan(HealthPlan healthPlan) {
        healthPlanDao.save(healthPlan);
    }

    /**
     * 根据患者id查询所有的健康教育
     * @param pateintId
     * @return
     */
    public List<HealthPlan> findByIdHealthPlan(String pateintId) {
        List<HealthPlan> patientId = healthPlanDao.findAllByPatientId(pateintId);
        return patientId;
    }

    /**
     * 删除护理计划
     * @param id
     */
    public void deletehealth(String id) {
        healthPlanDao.deleteById(id);
    }
}
