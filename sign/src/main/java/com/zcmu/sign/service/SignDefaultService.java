package com.zcmu.sign.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SignDefaultService {
   @Autowired
    private SignDefaultDao signDefaultDao;

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
                signList.add(cb.isNotEmpty(root.get("record_nurse_id")).as(String.class));
                return cb.and(signList.toArray(new Predicate[signList.size()]));
            }
        };
        List<SignDefault> signList = signDefaultDao.findAll(specification);
        return signList;
    }
}
