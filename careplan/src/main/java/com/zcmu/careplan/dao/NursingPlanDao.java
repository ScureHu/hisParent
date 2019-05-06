package com.zcmu.careplan.dao;

import com.zcmu.careplan.pojo.NursingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NursingPlanDao extends JpaRepository<NursingPlan,String>, JpaSpecificationExecutor<NursingPlan> {
}
