package com.zcmu.order.controller;

import com.zcmu.order.client.NurseClient;
import com.zcmu.order.pojo.Nurse;
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

    @RequestMapping(value = "/split" ,method = RequestMethod.POST)
    public Result splitOrder(@RequestBody SplitOrderVO splitOrderVO, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        Nurse nurse = nurseClient.getName(jwtUtil.parseJWT(token).getId());
        //1.根据患者的id去查询存在的1,2,3,4的用药方式，在用的，未拆分的
        orderService.splitOrder(splitOrderVO,nurse.getNurseName());
        //2.组合成一个大的原始医嘱,记录下拆分的原始医嘱的条数
        //3.一个一个根据时间频率拆分今日和明日的
        //4.拆分好保存原始医嘱表
        //5.更新原始医嘱表的
        return null;
    }
}
