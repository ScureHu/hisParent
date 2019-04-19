package com.zcmu.order.dao;

import com.zcmu.order.pojo.RawOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RawOrderDao extends JpaRepository<RawOrder,String>, JpaSpecificationExecutor<RawOrder> {

    List<RawOrder> findByPatientIdAndS(String patientId);
}
