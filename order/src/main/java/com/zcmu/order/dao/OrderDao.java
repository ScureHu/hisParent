package com.zcmu.order.dao;

import com.zcmu.order.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderDao extends JpaRepository<Order,String>, JpaSpecificationExecutor<Order> {

    List<Order> findAllByHisOrderId(String hisOrderId);

    List<Order> findAllByPatientIdAndStatus(String patient,String status);

    @Modifying
    @Query(value = "update zcmu_order set status='0',execute_time=?,execute_id=?,execute_name=? where plan_id=?",nativeQuery = true)
    void executeOrder(Date date, String nurseCode, String nurseName,String planId);

}
