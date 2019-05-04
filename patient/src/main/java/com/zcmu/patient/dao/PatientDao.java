package com.zcmu.patient.dao;

import com.zcmu.patient.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientDao extends JpaRepository<Patient,String>,JpaSpecificationExecutor<Patient> {

    @Query(value = "update zcmu_patient set status = '0' where uuid=?" ,nativeQuery = true)
    @Modifying
    void updateStatus(String uuid);

    /**
     * 根据病区查询所有的病人
     * @param wardCode
     * @return
     */
    List<Patient> findAllByWardcodeAndStatus(String wardCode,String status);
}
