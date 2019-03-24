package com.zcmu.ward.controller;

import com.zcmu.ward.service.WardService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/ward")
public class WardController {

    @Autowired
    private WardService wardService;
    /**
     * 查询所有的病区信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return Result.success(wardService.findWard(),"查询成功！");
    }
}
