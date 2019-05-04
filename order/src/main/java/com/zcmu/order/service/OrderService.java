package com.zcmu.order.service;

import com.zcmu.order.dao.OrderDao;
import com.zcmu.order.dao.RawOrderDao;
import com.zcmu.order.pojo.RawOrder;
import com.zcmu.order.pojo.SplitOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import units.Common;
import units.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RawOrderDao rawOrderDao;

    /**
     * 医嘱拆分
     * @param splitOrderVO
     */
    public int splitOrder(SplitOrderVO splitOrderVO) {
        //1.根据患者的id去查询存在的1,2,3,4的用药方式，在用的，未拆分的,结束时间要大于今天
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date time = cal.getTime();
        List<RawOrder> rawOrders = rawOrderDao.checkPatientsAndDrugUse(splitOrderVO.getPatientIds(), splitOrderVO.getDrugUseIds(),time);
        //2.如果不存在拆分的药物则返回零条
        //2.组合成一个大的原始医嘱,记录下拆分的原始医嘱的条数
        if(rawOrders.size()==0){
            return 0;
        }
        //3.一个一个根据时间频率拆分今日和明日的
        for (RawOrder order:rawOrders) {
            //如果今天是最后一天
            if(isToDay(order.getEndTime())){
                order.getFrequence();
            }else {

            }
        }
        //4.拆分好保存原始医嘱表
        //5.更新原始医嘱表的
        return 0;
    }

    /**
     * 判断是不是今天
     * @param time
     * @return
     */
    public static boolean isToDay(Date time){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return time.compareTo(cal.getTime())==0?true:false;
    }


    //======================#92132 长期医嘱按his时间更改预执行时间 start========================
    /**
     * 根据freqTime拆分当天时间
     * @param freqTime
     * 		   拆分频繁
     * @param day
     * 		   拆分时间
     * @return
     */
    private List<Date> getPlanTimeListByFreqTime(String freqTime,Date day){
        //如果频率时间为空，则返回为空
        if("".equals(freqTime)){
            return new ArrayList<Date>();
        }

        List<Date> listTime = new ArrayList<Date>();
        //拆分时间频率,例如(08,09,14,),拆分为字符串数组
        String[] splitTime = freqTime.split(",");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(day);
        //根据频率赋值时间
        for (int i = 0; i < splitTime.length; i++) {
            c1.set(Calendar.HOUR_OF_DAY, 0);
            c1.set(Calendar.MINUTE, 0);
            c1.set(Calendar.SECOND, 0);
            c1.set(Calendar.MILLISECOND, 0);
            c1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTime[i]));
            listTime.add(c1.getTime());
        }
        return listTime;
    }
    /**
     * 根据freqTime和freqDay拆分时间
     * @param freqTime
     * 		  每日拆分时间，不为空
     * @param freqDay
     * 		  每周拆分时间，不为空
     * @param beginTime
     * 		  开始时间
     * @return
     */
    private List<Date> getPlanTimeListByFreqWeek(String freqTime,String freqDay,Date beginTime){
        char[] charWeek = freqDay.toCharArray();

        List<Date> returnList = new ArrayList<Date>();
        //获取每周的需要执行的日期

        List<Integer> weekDay = new ArrayList<Integer>();
        for (int i = 0; i < charWeek.length; i++) {
            weekDay.add(charWeek[i]-48);
        }
        //根据开始时间判断今天是星期几
        int today = isToday(beginTime);
        //下一次是星期几
        int nextDay = weekDay.get(getWeekNextDay(weekDay, today));
        //结束时间是明天23点59分59秒
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        c1.add(Calendar.DAY_OF_YEAR, 1);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);
        c1.set(Calendar.MILLISECOND, 999);
        Date end = c1.getTime();
        Date result = beginTime;
        while (result.getTime() <= end.getTime()) {
            if (weekDay.contains(isToday(result))) {
                List<Date> list = getPlanTimeListByFreqTime(freqTime, result);
                returnList.addAll(list);
                result = DateUtil.getAddDayDate(result,
                        nextDay - today > 0 ? nextDay - today : nextDay - today + 7);
                nextDay = weekDay.get(getWeekNextDay(weekDay, isToday(result)));
            } else {
                result = DateUtil.getAddDayDate(result,
                        nextDay - today > 0 ? nextDay - today : nextDay - today + 7);
            }
        }
        if(!Common.isEmpty(returnList)){
            return returnList ;
        }
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, DateUtil.getAddSecondDate(returnList.get(i), 1));
        }
        return returnList;
    }
    /**
     * 根据今天的星期几，判断下一次是星期几
     * @param weekDay
     * @param toDay
     * @return 数组的下标
     */
    private int getWeekNextDay(List<Integer> weekDay,int toDay){
        for (int i = 0; i < weekDay.size(); i++) {
            if(weekDay.get(i)>toDay){
                return i;
            }
        }
        return 0;
    }

    /**
     * 判断是星期几
     * @param time
     * @return
     */
    private int isToday(Date time){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(time);
        int i = c1.get(Calendar.DAY_OF_WEEK);
        if(i==1){
            return 7;
        }
        return i-1;
    }
    /**
     * zdm为空，每天执行
     * @param beginTime
     * 		  计划开始时间
     * @param freqTime
     * 		 每天的频率
     * @return
     */
    private List<Date> getPlanTimeListByEverDay(Date beginTime,String freqTime){

        List<Date> returnList = new ArrayList<Date>();

        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        c1.add(Calendar.DAY_OF_YEAR, 1);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);
        c1.set(Calendar.MILLISECOND, 999);
        Date end = c1.getTime();
        Date result = beginTime;
        while (result.getTime() <= end.getTime()) {
            List<Date> list = getPlanTimeListByFreqTime(freqTime, result);
            returnList.addAll(list);
            result = DateUtil.getAddDayDate(result,1);
        }
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, DateUtil.getAddSecondDate(returnList.get(i), 1));
        }
        return returnList;

    }
    //======================#92132 长期医嘱按his时间更改预执行时间 end========================
}
