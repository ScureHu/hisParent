package com.zcmu.sign.controller;

import com.zcmu.sign.client.PatientClient;
import com.zcmu.sign.pojo.SignDefault;
import com.zcmu.sign.service.SignDefaultService;
import entity.PageResult;
import entity.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import units.DateUtil;
import units.JwtUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sign")
@CrossOrigin
public class SignDefaultController {

    @Autowired
    private SignDefaultService signDefaultService;

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private JwtUtil jwtUtil;
   /**
     * 获取当前的患者当天的记录
     * @param patientId
     * @return
     */
    @RequestMapping(value = "/{patientId}" ,method = RequestMethod.GET )
    public Result getTodaySign(@PathVariable String patientId){
        List<SignDefault> singList = signDefaultService.getDateSign(patientId);
        List<Date> listTime = new ArrayList<>();
        for (SignDefault signDefault:singList) {
            listTime.add(signDefault.getMeasureTime());
        }
        return Result.success(listTime);
    }

    @RequestMapping(value = "/task" ,method = RequestMethod.GET )
    public void taskDateSign(){
        Map<String,String> map = patientClient.findAll();
        signDefaultService.taskDateSign(map);
    }

    /**
     * 从首页保存
     * @param signDefault
     * @return
     */
    @RequestMapping(value = "/saveSignHome",method = RequestMethod.POST)
    public Result saveSignHome(@RequestBody SignDefault signDefault, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        Claims claims = jwtUtil.parseJWT(token);
        signDefault.setRecordNurseId(claims.getId());
        signDefault.setSource("1");
        signDefaultService.saveSignHome(signDefault);
        return Result.success("保存成功!");
    }

    /**
     * 分页查询体征录入
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/findAllToday/{page}/{size}",method = RequestMethod.POST)
    public Result findAllToday(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page pageList = signDefaultService.findAllToday(searchMap,page,size);
        return Result.success(new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
    }

    /**
     * 更新体征数据
     * @param signDefault
     * @return
     */
    @RequestMapping(value = "/updateSign",method = RequestMethod.PUT)
    public Result updateSign(@RequestBody SignDefault signDefault,HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        Claims claims = jwtUtil.parseJWT(token);
        signDefault.setRecordNurseId(claims.getId());
        signDefault.setSource("2");
        signDefaultService.updateSign(signDefault);
        return Result.success("保存成功！");
    }
}
