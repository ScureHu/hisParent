package com.zcmu.sign.controller;

import com.zcmu.sign.client.PatientClient;
import com.zcmu.sign.pojo.SignDefault;
import com.zcmu.sign.service.SignDefaultService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sign")
public class SignDefaultController {

    @Autowired
    private SignDefaultService signDefaultService;

    @Autowired
    private PatientClient patientClient;
   /**
     * 获取当前的患者当天的记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET )
    public Result getTodaySign(@PathVariable String id){
        List<SignDefault> singList = signDefaultService.getDateSign(id);
        List<Date> dateList = new ArrayList<>();
        for (SignDefault s :
                singList) {
            dateList.add(s.getMeasureTime());
        }
        return Result.success(dateList);
    }


    public void taskDateSign(){
        Map<String,String> map = patientClient.findAll();
        signDefaultService.taskDateSign(map);
    }
}
