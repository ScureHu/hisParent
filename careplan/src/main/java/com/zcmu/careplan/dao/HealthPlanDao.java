package com.zcmu.careplan.dao;

import com.zcmu.careplan.pojo.HealthPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HealthPlanDao extends JpaRepository<HealthPlan,String>, JpaSpecificationExecutor<HealthPlan> {
}
