package com.zcmu.patient.controller;

import com.zcmu.patient.Service.PatientService;
import com.zcmu.patient.client.WardClient;
import com.zcmu.patient.pojo.Patient;
import com.zcmu.patient.pojo.WardPatientBed;
import entity.PageResult;
import entity.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import units.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 入院评估单
     * @return
     */
    @RequestMapping(value = "/sheet",method = RequestMethod.POST)
    public Result sheet(@RequestBody Patient patient,HttpServletRequest request) throws Exception {

        String wardCode = jwtUtil.getJwtWardCode(request);
        patient.setWardcode(wardCode);
        Patient save = patientService.save(patient);

        return Result.success("入院成功！请在患者列表中查询！");
    }

    /**
     * 条件查询所有的患者
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result searchPatient(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size,HttpServletRequest request){
        String wardCode = jwtUtil.getJwtWardCode(request);
        searchMap.put("wardCode",wardCode);
        Page pageList = patientService.findSearch(searchMap,page,size);
        return Result.success(new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
    }

    /**
     * 查询单个病人信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findOnePatient(@PathVariable String id){
        Patient patient = patientService.findOne(id);
        return Result.success(patient,"查询成功！");
    }

    /**
     * 出院评估单
     * @return
     */
    @RequestMapping(value = "/outSheet",method = RequestMethod.POST)
    public Result outSheet(@RequestBody Patient patient){
        patientService.updatePatient(patient);
        return Result.success("出院成功！");
    }


    /**
     * 查询所有在的本病区的
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(HttpServletRequest request){
        String wardCode = jwtUtil.getJwtWardCode(request);
        List<Patient> patientList = patientService.findAll(wardCode);
        return Result.success(patientList);
    }
}
