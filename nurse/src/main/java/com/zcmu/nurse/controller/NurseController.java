package com.zcmu.nurse.controller;

import com.zcmu.nurse.pojo.Nurse;
import com.zcmu.nurse.service.NurseService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
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
            loginMap.put("avater","http://img1.touxiang.cn/uploads/20131125/25-091231_300.jpg");
            return Result.success(loginMap,"登录成功");
        }else{
            return new Result(false,StatusCode.LOGINERROR,"用户名密码或者选择病区错误");
        }
    }
    /**
     * 获取用户的登录信息
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Result info(String token){
        Claims claims = jwtUtil.parseJWT(token);
        String nurseCode = claims.getId();
        String password = claims.getSubject();
        String wardCode = (String) claims.get("wardCode");
        Nurse loginNurse = nurseService.login(nurseCode, password, wardCode);
        Map loginMap = new HashMap();
        loginMap.put("token",token);
        loginMap.put("nurseName",loginNurse.getNurseName());
        //增加头像地址
        loginMap.put("avatar","http://img1.touxiang.cn/uploads/20131125/25-091231_300.jpg");
        loginMap.put("roles","nurse");
        return Result.success(loginMap,"登录成功");
    }
    /**
     * 获取用户的登录信息
     */
    @RequestMapping(value = "/{usercode}",method = RequestMethod.GET)
    public Nurse getName(@PathVariable(value = "usercode") String usercode){
        return nurseService.findByUsrCode(usercode);
    }
}
