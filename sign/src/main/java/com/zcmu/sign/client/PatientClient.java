package com.zcmu.sign.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Map;

@FeignClient("his-patient")
public interface PatientClient {

    @RequestMapping(value = "/patient/task",method = RequestMethod.GET)
    public Map<String,String> findAll();

}
