package com.zcmu.nurse.dao;

import com.zcmu.nurse.pojo.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NurseDao extends JpaRepository<Nurse,String>,JpaSpecificationExecutor<Nurse> {
    /**
     * 根据账号来查找Nurse
     * @param nurseCode
     * @return
     */
    Nurse findByNurseCode(String nurseCode);
}
