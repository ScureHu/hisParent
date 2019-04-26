package com.zcmu.sign.dao;

import com.zcmu.sign.pojo.SignDefault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SignDefaultDao extends JpaRepository<SignDefault,String>, JpaSpecificationExecutor<SignDefault> {
}
