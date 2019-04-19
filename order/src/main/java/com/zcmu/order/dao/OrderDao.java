package com.zcmu.order.dao;

import com.zcmu.order.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDao extends JpaRepository<Order,String>, JpaSpecificationExecutor<Order> {
}
