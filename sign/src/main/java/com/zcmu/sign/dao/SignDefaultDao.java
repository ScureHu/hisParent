package com.zcmu.sign.dao;

import com.zcmu.sign.pojo.SignDefault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface SignDefaultDao extends JpaRepository<SignDefault,String>, JpaSpecificationExecutor<SignDefault> {

    SignDefault findByMeasureTimeAndPatientId(Date time,String patientId);
}
