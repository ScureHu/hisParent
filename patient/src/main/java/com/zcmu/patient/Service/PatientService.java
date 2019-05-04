package com.zcmu.patient.Service;

import com.zcmu.patient.client.WardClient;
import com.zcmu.patient.controller.BaseExceptionHandler;
import com.zcmu.patient.dao.PatientDao;
import com.zcmu.patient.pojo.Patient;
import com.zcmu.patient.pojo.WardPatientBed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import units.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private WardClient wardClient;


    public Patient save(Patient patient) throws Exception{
        patient.setUuid(String.valueOf(idWorker.nextId()));
        patient.setStatus("1");

        patientDao.save(patient);
        WardPatientBed wardPatientBed = new WardPatientBed();
        wardPatientBed.setPatientId(patient.getUuid());
        wardPatientBed.setBedNo(Integer.parseInt(patient.getBedNo()));
        wardPatientBed.setWardcode(patient.getWardcode());
        wardClient.savePatient(wardPatientBed);
        return patient;
    }

    /**
     * 条件查询+分页
     * @param searchMap 条件
     * @param page 当前页
     * @param size 页大小
     * @return
     */
    public Page<Patient> findSearch(Map searchMap, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        Specification<Patient> specification = new Specification<Patient>() {
            @Override
            public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> patientList = new ArrayList<>();
                if(searchMap.get("nursingLevel") !=null && !"".equals(searchMap.get("nursingLevel")) ){
                    patientList.add(cb.equal(root.get("nursingLevel").as(String.class),searchMap.get("nursingLevel")));
                }
                if(searchMap.get("name") !=null && !"".equals(searchMap.get("name"))){
                    patientList.add(cb.like(root.get("name").as(String.class), (String) "%"+searchMap.get("name")+"%"));
                }
                if(searchMap.get("wardCode")!=null&& !"".equals(searchMap.get("name"))){
                    patientList.add(cb.equal(root.get("wardcode").as(String.class),searchMap.get("wardCode")));
                }
                patientList.add(cb.equal(root.get("status").as(String.class),"1"));
                return cb.and(patientList.toArray(new Predicate[patientList.size()]));
            }
        };
        Page<Patient> patients = patientDao.findAll(specification, pageable);
        return patients;
    }

    /**
     * 根据id查询当个人的信息
     * @param id
     * @return
     */
    public Patient findOne(String id) {
        Patient patient = patientDao.findById(id).get();
        return patient;
    }

    /**
     * 出院操作
     * @param patient
     */
    public void updatePatient(Patient patient) {
        patientDao.saveAndFlush(patient);
        patientDao.updateStatus(patient.getUuid());
        wardClient.deletePatientBed(patient.getUuid());
    }

    /**
     * 查询所有的hz数据只要uuid
     * @return
     */
    public List<Patient> findAll(String wardCode) {
        return patientDao.findAllByWardcodeAndStatus(wardCode,"1");
    }
}
