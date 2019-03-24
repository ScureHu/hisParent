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
    public List<WardVO> findWard(){
        //获取病区
        List<Ward> listWard = wardDao.findward();
        List<WardVO> listWardVO = new ArrayList<>();
        for (Ward ward:listWard) {
            WardVO wardVO = new WardVO();
            wardVO.setWardCode(ward.getWardcode());
            wardVO.setWardName(ward.getWardName());
            listWardVO.add(wardVO);
        }
        return listWardVO;
    }
}
