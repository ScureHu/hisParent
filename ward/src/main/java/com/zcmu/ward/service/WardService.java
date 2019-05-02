package com.zcmu.ward.service;

import com.zcmu.ward.dao.WardDao;
import com.zcmu.ward.dao.WardPatientBedDao;
import com.zcmu.ward.pojo.Ward;
import com.zcmu.ward.pojo.WardPatientBed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WardService {
    @Autowired
    private WardDao wardDao;

    @Autowired
    private WardPatientBedDao patientBedDao;

    /**
     * 查询所有病区
     * @return
     */
    public List<Ward> findWard(){
        return wardDao.findAll();
    }

    /**
     * 剩余的床位
     * @param wardcode
     * @return
     */
    public List<Integer> existWardBed(String wardcode) {

        List<WardPatientBed> patientList= patientBedDao.findByWardcodeAndStatus(wardcode,"1");
        List<Integer> bedList = new ArrayList<>();
        for (WardPatientBed p: patientList) {
            bedList.add(p.getBedNo());
        }

        Ward ward = wardDao.getOne(wardcode);
        List<Integer> exisbedList = new ArrayList<>();
        for (int i =0;i<ward.getBedSum();i++){
            exisbedList.add(i+1);
        }
        exisbedList.removeAll(bedList);
        return exisbedList;
    }
}
