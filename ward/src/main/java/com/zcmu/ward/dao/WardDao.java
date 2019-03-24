package com.zcmu.ward.dao;

import com.zcmu.ward.pojo.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardDao extends JpaRepository<Ward,String>,JpaSpecificationExecutor<Ward> {
    /**
     * 查找所有的病区
     */
    @Query(value = "SELECT wardcode,ward_name,bed_no,parent_id,status FROM zcmu_ward GROUP BY wardcode",nativeQuery = true )
    public List<Ward> findward();
}
