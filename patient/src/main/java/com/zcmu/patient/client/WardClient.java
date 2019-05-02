package com.zcmu.patient.client;

import com.zcmu.patient.pojo.WardPatientBed;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * bq客户端
 */
@FeignClient("his-ward")
public interface WardClient {

    @RequestMapping(value = "/ward/bed",method = RequestMethod.POST)
    public Result savePatient(@RequestBody WardPatientBed wardPatientBed);

}
