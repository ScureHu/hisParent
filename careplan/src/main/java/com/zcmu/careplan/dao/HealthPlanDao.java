package com.zcmu.careplan.dao;

import com.zcmu.careplan.pojo.HealthPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface HealthPlanDao extends JpaRepository<HealthPlan,String>, JpaSpecificationExecutor<HealthPlan> {
    List<HealthPlan> findAllByPatientId(String patientId);
}
