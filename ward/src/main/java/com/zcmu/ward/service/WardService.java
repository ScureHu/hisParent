package com.zcmu.ward.service;

import com.zcmu.ward.dao.WardDao;
import com.zcmu.ward.pojo.Ward;
import com.zcmu.ward.pojo.WardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardService {
    @Autowired
    private WardDao wardDao;

    /**
     * 查询所有病区
     * @return
     */
    public List<Ward> findWard(){
        return wardDao.findAll();
    }
}
