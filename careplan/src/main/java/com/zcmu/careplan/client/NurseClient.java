package com.zcmu.careplan.client;


import com.zcmu.careplan.pojo.Nurse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * bq客户端
 */
@FeignClient("his-nurse")
public interface NurseClient {

    @RequestMapping(value = "/nurse/{usercode}",method = RequestMethod.GET)
    Nurse getName(@PathVariable(value = "usercode") String usercode);
}