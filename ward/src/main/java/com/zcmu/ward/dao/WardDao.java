package com.zcmu.ward.dao;

import com.zcmu.ward.pojo.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardDao extends JpaRepository<Ward,String>,JpaSpecificationExecutor<Ward> {

}
