package com.zcmu.order.service;

import com.zcmu.order.dao.RawOrderDao;
import com.zcmu.order.pojo.RawOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import units.IdWorker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RawOrderService {

    @Autowired
    private RawOrderDao rawOrderDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 原始医嘱的保存
     * @param rawOrder
     */
    public void saveRawOrder(RawOrder rawOrder) {
        rawOrder.setHisOrderId(String.valueOf(idWorker.nextId()));
        Date endTime = rawOrder.getEndTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(endTime);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 59);
        rawOrder.setEndTime(cal.getTime());
        rawOrder.setStatus("0");
        rawOrderDao.save(rawOrder);
    }

    /**
     *
     * @param patientId
     * @return
     */
    public List<RawOrder> getRawOrderByPatientId(String patientId) {
        //List<RawOrder> listRawOrder = rawOrderDao.findByPatientId(patientId);
        return null;
    }
}
