package com.zcmu.ward.controller;

import com.zcmu.ward.pojo.WardPatientBed;
import com.zcmu.ward.service.WardPatientBedService;
import com.zcmu.ward.service.WardService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ward")
public class WardController {

    @Autowired
    private WardService wardService;

    @Autowired
    private WardPatientBedService patientBedService;
    /**
     * 查询所有的病区
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return Result.success(wardService.findWard(),"查询成功！");
    }

    /**
     * 根据wardcode查还剩余的床号
     * @param wardcode
     * @return
     */
    @RequestMapping(value = "/bed/{wardcode}",method = RequestMethod.GET)
    public Result existWardBed(@PathVariable String wardcode){
       List<Integer> existBedCode = wardService.existWardBed(wardcode);
       return Result.success(existBedCode);
    }

    /**
     * 保存病床号
     * @param wardPatientBed
     * @return
     */
    @RequestMapping(value = "/bed",method = RequestMethod.POST)
    public Result savePatient(@RequestBody WardPatientBed wardPatientBed){
        patientBedService.savePatient(wardPatientBed);
        return Result.success("保存成功！");
    }

    /**
     * 删除病床号
     * @return
     */
    @RequestMapping(value = "/bed/{patientId}",method = RequestMethod.DELETE)
    public Result deletePatientBed(@PathVariable String patientId){
        patientBedService.deletePatientBed(patientId);
        return Result.success("删除成功！");
    }
}
