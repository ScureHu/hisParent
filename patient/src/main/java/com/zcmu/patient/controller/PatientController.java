package com.zcmu.patient.controller;

import entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {
    /**
     *
     * @return
     */
    @RequestMapping(value = "/sheet",method = RequestMethod.POST)
    public Result sheet(){
        return null;
    }
}
