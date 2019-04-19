package com.zcmu.ward.service;

import com.zcmu.ward.dao.WardPatientBedDao;
import com.zcmu.ward.pojo.WardPatientBed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import units.IdWorker;

@Service
@Transactional
public class WardPatientBedService {

    @Autowired
    private WardPatientBedDao patientBedDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存病床号
     * @param wardPatientBed
     */
    public void savePatient(WardPatientBed wardPatientBed) {
        wardPatientBed.setId(String.valueOf(idWorker.nextId()));
        wardPatientBed.setStatus("1");
        patientBedDao.save(wardPatientBed);
    }

    /**
     * 删除病床号
     * @param patientId
     */
    public void deletePatientBed(String patientId) {
        patientBedDao.deleteByPatientId(patientId);
    }
}
