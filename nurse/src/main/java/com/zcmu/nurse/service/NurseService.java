package com.zcmu.nurse.service;

import com.zcmu.nurse.dao.NurseDao;
import com.zcmu.nurse.pojo.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService {
    @Autowired
    private NurseDao nurseDao;

    /**
     * 登录验证
     * @param nurseCode 护士账号
     * @param password 密码
     * @param wardCode 病区
     */
    public Nurse login(String nurseCode, String password, String wardCode) {
        Nurse loginNurse = nurseDao.findByNurseCode(nurseCode);
        if(loginNurse!=null && password.equals(loginNurse.getPassword()) && wardCode.equals(loginNurse.getWardcode())){
            return loginNurse;
        }else{
            return null;
        }
    }
}
