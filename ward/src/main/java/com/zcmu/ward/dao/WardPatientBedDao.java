package com.zcmu.ward.dao;

import com.zcmu.ward.pojo.WardPatientBed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WardPatientBedDao extends JpaRepository<WardPatientBed,String>,JpaSpecificationExecutor<WardPatientBed> {
}
