package com.zcmu.careplan.dao;

import com.zcmu.careplan.pojo.NursingPlanTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NursingPlanTemplateDao extends JpaRepository<NursingPlanTemplate,String>, JpaSpecificationExecutor<NursingPlanTemplate> {
}
