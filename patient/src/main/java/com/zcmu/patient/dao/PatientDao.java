package com.zcmu.patient.dao;

import com.zcmu.patient.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PatientDao extends JpaRepository<Patient,String>,JpaSpecificationExecutor<Patient> {
}
