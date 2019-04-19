package com.zcmu.order.service;

import com.zcmu.order.dao.RawOrderDao;
import com.zcmu.order.pojo.RawOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RawOrderService {

    @Autowired
    private RawOrderDao rawOrderDao;

    public void saveRawOrder(RawOrder rawOrder) {
    }

    /**
     *
     * @param patientId
     * @return
     */
    public List<RawOrder> getRawOrderByPatientId(String patientId) {
        List<RawOrder> listRawOrder = rawOrderDao.findByPatientId(patientId);
        return listRawOrder;
    }
}
