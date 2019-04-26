package com.zcmu.sign.service;

import com.zcmu.sign.dao.SignDefaultDao;
import com.zcmu.sign.pojo.SignDefault;
import org.springframework.beans.factory.annotation.Autowired;
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
                signList.add(cb.equal(root.get("id").as(String.class),id));
                signList.add(cb.between(root.get("measure_time").as(Date.class),startTime,endTime));
                signList.add(cb.isNotEmpty(root.get("record_nurse_id")));
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

        cal.set(Calendar.HOUR_OF_DAY, 14);
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
            signDefault.setPatientName(patientsId.get(patientsId));
            signDefault.setMeasureTime(time1);
            saveSignlist.add(signDefault);

            signDefault.setId(String.valueOf(idWorker.nextId()));
            signDefault.setMeasureTime(time2);
            saveSignlist.add(signDefault);

            signDefault.setId(String.valueOf(idWorker.nextId()));
            signDefault.setMeasureTime(time3);
            saveSignlist.add(signDefault);

            signDefault.setId(String.valueOf(idWorker.nextId()));
            signDefault.setMeasureTime(time4);
            saveSignlist.add(signDefault);
        }
        signDefaultDao.saveAll(saveSignlist);
    }
}
