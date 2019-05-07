package com.zcmu.careplan.controller;

import com.zcmu.careplan.client.NurseClient;
import com.zcmu.careplan.pojo.HealthPlan;
import com.zcmu.careplan.pojo.Nurse;
import com.zcmu.careplan.service.HealthPlanService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import units.IdWorker;
import units.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/healthPlan")
@CrossOrigin
public class HealthPlanController {
    @Autowired
    private HealthPlanService healthPlanService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private NurseClient nurseClient;
    /**
     * 保存单个用户id的健康教育
     * @param healthPlan
     * @return
     */
    @RequestMapping(value = "/{pateintId}",method = RequestMethod.POST)
    public Result savehealthPlan(@RequestBody HealthPlan healthPlan, @PathVariable String pateintId, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        String recordId = jwtUtil.parseJWT(token).getId();

        Nurse name = nurseClient.getName(recordId);
        healthPlan.setPatientId(pateintId);
        healthPlan.setRecord_Time(new Date());
        healthPlan.setId(String.valueOf(idWorker.nextId()));
        healthPlan.setRecorderId(recordId);
        healthPlan.setRecorderName(name.getNurseName());
        healthPlanService.savehealthPlan(healthPlan);
        return Result.success("保存成功");
    }

    /**
     * 根据患者Id查询所有的病人信息
     * @param pateintId
     * @return
     */
    @RequestMapping(value = "/{pateintId}",method = RequestMethod.GET)
    public Result findByIdHealthPlan(@PathVariable String pateintId){
        List<HealthPlan> healthPlanList = healthPlanService.findByIdHealthPlan(pateintId);
        return Result.success(healthPlanList);
    }

    /**
     * 删除护理计划
     * @param id
     * @return
     */
    @RequestMapping(value = "/health/{id}",method = RequestMethod.DELETE)
    public Result deletehealth(@PathVariable String id) {
        healthPlanService.deletehealth(id);
        return Result.success("删除成功！");
    }

}
