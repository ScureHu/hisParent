package com.zcmu.order.dao;

import com.zcmu.order.pojo.RawOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RawOrderDao extends JpaRepository<RawOrder,String>, JpaSpecificationExecutor<RawOrder> {

    @Query(value = "from RawOrder WHERE patientId IN ?1 AND supplyCode IN ?2 AND status in ('0','1') AND endTime>?3")
    List<RawOrder> checkPatientsAndDrugUse(List<String> patientIds, List<String> drugUserIds, Date time);

    @Modifying
    @Query(value = "update zcmu_raw_order set status='1' where his_order_id=?",nativeQuery = true)
    void splitOrder(String hisOrderId);
}
