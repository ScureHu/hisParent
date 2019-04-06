package com.zcmu.nurse.controller;

import com.zcmu.nurse.pojo.Nurse;
import com.zcmu.nurse.service.NurseService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import units.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,String> map){
        String nurseCode = map.get("nurseCode");
        String password = map.get("password");
        String wardCode = map.get("wardCode");
        Nurse loginNurse = nurseService.login(nurseCode, password, wardCode);
        if(loginNurse!=null){
            //生成token
            String token = jwtUtil.createJWT(loginNurse.getNurseCode(),loginNurse.getPassword(),loginNurse.getWardcode());
            Map loginMap = new HashMap();
            loginMap.put("token",token);
            loginMap.put("nurseName",loginNurse.getNurseName());
            //增加头像地址
            loginMap.put("avater","头像地址");
            return Result.success(loginMap,"登录成功");
        }else{
            return new Result(false,StatusCode.LOGINERROR,"用户名密码或者选择病区错误");
        }
    }
}
