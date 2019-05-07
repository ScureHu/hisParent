package com.zcmu.careplan.controller;

import com.zcmu.careplan.client.NurseClient;
import com.zcmu.careplan.pojo.Nurse;
import com.zcmu.careplan.pojo.NursingPlan;
import com.zcmu.careplan.pojo.NursingPlanTemplateVO;
import com.zcmu.careplan.service.NursingPlanService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import units.IdWorker;
import units.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/nursingPlan")
@CrossOrigin
public class NursingPlanController {

    @Autowired
    private NursingPlanService nursingPlanService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private NurseClient nurseClient;
    /**
     * 获取所有的护理计划模板
     * @return
     */
    @RequestMapping(value = "/findAllTemplate",method = RequestMethod.GET)
    public Result findNurseTemplate(){
        List<NursingPlanTemplateVO> listNursingPlan = nursingPlanService.findNurseTemplate();
        return Result.success(listNursingPlan);
    }

    /**
     * 保存护理计划
     * @return
     */
    @RequestMapping(value = "/saveNursePlan/{patientId}",method = RequestMethod.POST)
    public Result saveNursePlan(@RequestBody NursingPlanTemplateVO nursingPlanTemplateVO, @PathVariable String patientId, HttpServletRequest request){
        NursingPlan nursingPlan = new NursingPlan();
        nursingPlan.setId(String.valueOf(idWorker.nextId()));
        nursingPlan.setRecordTime(nursingPlanTemplateVO.getHealthTime());

        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        String recordId = jwtUtil.parseJWT(token).getId();

        Nurse name = nurseClient.getName(recordId);
        nursingPlan.setRecorderName(name.getNurseName());
        nursingPlan.setRecorderId(name.getNurseCode());

        nursingPlan.setPatientId(patientId);
        nursingPlan.setTemplateName(nursingPlanTemplateVO.getName());

        List<String> nursingMeasure = nursingPlanTemplateVO.getNursingMeasure();
        StringBuilder nurs = new StringBuilder();
        for (int i = 0;i<nursingMeasure.size();i++){
            if(i == nursingMeasure.size() - 1 ) {
                nurs.append(nursingMeasure.get(i));
            }else {
                nurs.append(nursingMeasure.get(i)+",");
            }
        }

        List<String> target = nursingPlanTemplateVO.getTarget();
        StringBuilder targets = new StringBuilder();
        for (int i = 0;i<target.size();i++){
            if(i == target.size() - 1 ) {
                targets.append(target.get(i));
            }else {
                targets.append(target.get(i)+",");
            }
        }
        nursingPlan.setNursingMeasure(nurs.toString());
        nursingPlan.setTarget(targets.toString());

        nursingPlanService.saveNursePlan(nursingPlan);
        return Result.success("保存成功");
    }

    /**
     * 根据患者ID获取所有的护理计划
     * @return
     */
    @RequestMapping(value = "/{patientId}",method = RequestMethod.GET)
    public Result findByNursePlanById(@PathVariable String patientId){
        List<NursingPlan> nursingPlanList = nursingPlanService.findByNursePlanById(patientId);
        return Result.success(nursingPlanList);
    }

    /**
     * 删除护理计划
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteNursePlan(@PathVariable String id){
        nursingPlanService.deleteNursePlan(id);
        return Result.success("删除成功！");
    }
}
