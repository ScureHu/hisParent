package com.zcmu.careplan.dao;

import com.zcmu.careplan.pojo.NursingPlanTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NursingPlanTemplateDao extends JpaRepository<NursingPlanTemplate,String>, JpaSpecificationExecutor<NursingPlanTemplate> {

    List<NursingPlanTemplate> findAllByIsValid(String valid);
}
