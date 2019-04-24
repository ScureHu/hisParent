package com.zcmu.sign.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
public class SignDefaultController {
     @Autowired
    private SignDefaultService signDefaultService;
   /**
     * 获取当前的患者当天的记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET )
    public Result getTodaySign(@PathVariable String id){
        List<SignDefault> singList = signDefaultService.getDateSign(id);
        List<Date> dateList = new ArrayList<>();
        for (SignDefault s :
                singList) {
            dateList.add(s.getMeasureTime());
        }
        return Result.success(dateList);
    }
}
