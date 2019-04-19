package com.zcmu.ward.dao;

import com.zcmu.ward.pojo.WardPatientBed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardPatientBedDao extends JpaRepository<WardPatientBed,String>,JpaSpecificationExecutor<WardPatientBed> {

    List<WardPatientBed> findByWardcodeAndStatus(String wardcode, String status);

    @Query(value = "update zcmu_ward_patient set status = '0' where patient_id=?",nativeQuery = true)
    @Modifying
    void deleteByPatientId(String patientId);
}
