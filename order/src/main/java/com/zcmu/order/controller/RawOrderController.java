package com.zcmu.order.controller;

import com.zcmu.order.pojo.RawOrder;
import com.zcmu.order.service.RawOrderService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rawOrder")
@CrossOrigin
public class RawOrderController {

    @Autowired
    private RawOrderService rawOrderService;

    /**
     * 批量添加原始医嘱
     * @param rawOrder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result saveRawOrder(@RequestBody RawOrder rawOrder){
        rawOrderService.saveRawOrder(rawOrder);
        return Result.success("医嘱插入成功!");
    }

    /**
     * 根据患者Id拿出
     * @return
     */
    @RequestMapping(value = "/{patientId}",method = RequestMethod.GET)
    public Result getRawOrderByPatientId(@PathVariable String patientId){
        List<RawOrder> listRawOrder = rawOrderService.getRawOrderByPatientId(patientId);
        return Result.success(listRawOrder);
    }
}
