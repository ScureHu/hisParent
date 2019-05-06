package com.zcmu.sign.service;

import com.zcmu.sign.dao.SignDefaultDao;
import com.zcmu.sign.pojo.SignDefault;
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
import java.util.*;

@Service
@Transactional
public class SignDefaultService {

    @Autowired
    private SignDefaultDao signDefaultDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 获取当前患者今天还需要录入的数据
     * @param id
     * @return
     */
    public List<SignDefault> getDateSign(String id) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startTime = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 59);
        Date endTime = cal.getTime();

        Specification<SignDefault> specification = new Specification<SignDefault>() {
            @Override
            public Predicate toPredicate(Root<SignDefault> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Date date = new Date();
                List<Predicate> signList = new ArrayList<>();
                signList.add(cb.equal(root.get("patientId").as(String.class),id));
                signList.add(cb.between(root.get("measureTime").as(Date.class),startTime,endTime));
                signList.add(cb.isNull(root.get("recordNurseId").as(String.class)));
                return cb.and(signList.toArray(new Predicate[signList.size()]));
            }
        };
        List<SignDefault> signList = signDefaultDao.findAll(specification);
        return signList;
    }


    public void taskDateSign(Map<String,String> patientsId){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 5);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date time1 = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date time2 = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date time3 = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, 22);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date time4 = cal.getTime();

        //每个人生成4个时间点的数据
        List<SignDefault> saveSignlist = new ArrayList<>();
        for (String patientId:patientsId.keySet()) {
            SignDefault signDefault = new SignDefault();
            signDefault.setId(String.valueOf(idWorker.nextId()));
            signDefault.setPatientId(patientId);
            signDefault.setPatientName(patientsId.get(patientId));
            signDefault.setMeasureTime(time1);
            saveSignlist.add(signDefault);

            SignDefault signDefault1 = new SignDefault();
            signDefault1.setId(String.valueOf(idWorker.nextId()));
            signDefault1.setPatientId(patientId);
            signDefault1.setPatientName(patientsId.get(patientId));
            signDefault1.setMeasureTime(time2);
            saveSignlist.add(signDefault1);

            SignDefault signDefault2 = new SignDefault();
            signDefault2.setId(String.valueOf(idWorker.nextId()));
            signDefault2.setPatientId(patientId);
            signDefault2.setPatientName(patientsId.get(patientId));
            signDefault2.setMeasureTime(time3);
            saveSignlist.add(signDefault2);

            SignDefault signDefault3 = new SignDefault();
            signDefault3.setId(String.valueOf(idWorker.nextId()));
            signDefault3.setPatientId(patientId);
            signDefault3.setPatientName(patientsId.get(patientId));
            signDefault3.setMeasureTime(time4);
            saveSignlist.add(signDefault3);
        }
        signDefaultDao.saveAll(saveSignlist);
    }

    /**
     * 从首页保存
     * @param signDefault
     */
    public void saveSignHome(SignDefault signDefault) {
        SignDefault oldSignDefalut = signDefaultDao.findByMeasureTimeAndPatientId(signDefault.getMeasureTime(), signDefault.getPatientId());
        oldSignDefalut.setSource(signDefault.getSource());
        oldSignDefalut.setRecordNurseId(signDefault.getRecordNurseId());
        oldSignDefalut.setR(signDefault.getR());
        oldSignDefalut.setBp(signDefault.getBp());
        oldSignDefalut.setP(signDefault.getP());
        oldSignDefalut.setT(signDefault.getT());
        oldSignDefalut.setRecordTime(new Date());
        signDefaultDao.saveAndFlush(oldSignDefalut);
    }

    /**
     * 分页查询体征
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<SignDefault> findAllToday(Map searchMap, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startTime = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 59);
        Date endTime = cal.getTime();
        Specification<SignDefault> specification = new Specification<SignDefault>(){

            @Override
            public Predicate toPredicate(Root<SignDefault> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> SignDefaultList = new ArrayList<>();
                if(searchMap.get("patientName") !=null && !"".equals(searchMap.get("patientName")) ){
                    SignDefaultList.add(cb.like(root.get("patientName").as(String.class),"%"+searchMap.get("patientName")+"%"));
                }
                SignDefaultList.add(cb.between(root.get("measureTime").as(Date.class),startTime,endTime));
                return cb.and(SignDefaultList.toArray(new Predicate[SignDefaultList.size()]));
            }
        };
        Page<SignDefault> SignDefaults = signDefaultDao.findAll(specification, pageable);
        return SignDefaults;
    }

    /**
     * 更新数据
     * @param signDefault
     */
    public void updateSign(SignDefault signDefault) {
        signDefault.setRecordTime(new Date());
        signDefaultDao.save(signDefault);
    }
}
