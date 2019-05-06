package com.zcmu.order.controller;

import com.zcmu.order.client.NurseClient;
import com.zcmu.order.pojo.Nurse;
import com.zcmu.order.pojo.Order;
import com.zcmu.order.pojo.SplitOrderVO;
import com.zcmu.order.service.OrderService;
import entity.Result;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import units.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private NurseClient nurseClient;

    /**
     * 医嘱拆分
     * @param splitOrderVO
     * @param request
     * @return
     */
    @RequestMapping(value = "/split" ,method = RequestMethod.POST)
    public Result splitOrder(@RequestBody SplitOrderVO splitOrderVO, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        Nurse nurse = nurseClient.getName(jwtUtil.parseJWT(token).getId());
        //1.根据患者的id去查询存在的1,2,3,4的用药方式，在用的，未拆分的
        int i = orderService.splitOrder(splitOrderVO, nurse.getNurseName());
        if(i>0){
            return Result.success(i,"一共拆分出"+i+"医嘱");
        }else{
            return Result.success("当前选择的患者无医嘱需拆分");
        }
    }

    /**
     * 根据患者去拆分明细
     * @return
     */
    @RequestMapping(value = "/noExecute/{patientId}" ,method = RequestMethod.GET)
    public Result noExecute(@PathVariable String patientId){
        List<Order> orderList = orderService.findAllByPatientId(patientId);
        return Result.success(orderList);
    }

    /**
     * 执行医嘱
     * @param planId 执行计划id
     * @return
     */
    @RequestMapping(value = "/executeOrder/{planId}" ,method = RequestMethod.GET)
    public Result executeOrder(@PathVariable String planId,HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        Nurse nurse = nurseClient.getName(jwtUtil.parseJWT(token).getId());

        orderService.executeOrder(planId,nurse.getNurseCode(),nurse.getNurseName());
        return Result.success(planId+"医嘱执行成功！");
    }

    /**
     * 获取已经执行的医嘱
     * @param patientId
     * @return
     */
    @RequestMapping(value = "/execute/{patientId}" ,method = RequestMethod.GET)
    public Result executeOrder(@PathVariable String patientId){
        List<Order> executeList = orderService.executeList(patientId);
        return Result.success(executeList);
    }
}
