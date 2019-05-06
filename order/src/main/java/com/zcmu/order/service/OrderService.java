package com.zcmu.order.service;

import com.zcmu.order.dao.OrderDao;
import com.zcmu.order.dao.RawOrderDao;
import com.zcmu.order.pojo.Order;
import com.zcmu.order.pojo.RawOrder;
import com.zcmu.order.pojo.SplitOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import units.Common;
import units.DateUtil;
import units.IdWorker;
import units.JwtUtil;
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

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 医嘱拆分
     * @param splitOrderVO
     * @param splitName
     */
    public int splitOrder(SplitOrderVO splitOrderVO, String splitName) {
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
        //累计一共拆分的条数
        int size = 0;

        //3.一个一个根据时间频率拆分今日和明日的
        for (RawOrder order:rawOrders) {
            String frequence = order.getFrequence();
            String[] split = frequence.split("|");
            List<Date> list = new ArrayList<>();
            List<Date> listByFreqTime = null;
            //如果今天是最后一天,且拆分的频率是1天的
            if(isToDay(order.getEndTime())&& split[0].equals("1")){
                switch (split[2]){
                    case "1":
                        listByFreqTime = getPlanTimeListByFreqTime("08,", order.getEndTime());
                        list.addAll(listByFreqTime);
                        break;
                    case "2":
                        listByFreqTime = getPlanTimeListByFreqTime("08,18", order.getEndTime());
                        list.addAll(listByFreqTime);
                        break;
                    case "3":
                        listByFreqTime = getPlanTimeListByFreqTime("08,14,18", order.getEndTime());
                        list.addAll(listByFreqTime);
                        break;
                }
            }else {
                //今天不是最后一天,拆分今天和明天的
                int today = isToday(order.getStartTime());
                StringBuilder sb = new StringBuilder();
                switch (split[0]){
                    case "1":
                        sb.append("1234567");
                        break;
                    case "2":
                        sb.append("246");
                        break;
                    case "3":
                        sb.append("36");
                        break;
                    case "4":
                        sb.append((today+4)/7);
                        break;
                    case "5":

                        break;
                    case "6":

                        break;
                    case "7":

                        break;
                }
                String timeToday =  null;
                switch (split[2]){
                    case "1":
                        timeToday = "08,";
                        break;
                    case "2":
                        timeToday= "08,18";
                        break;
                    case "3":
                        timeToday= "08,14,18";
                        break;
                }
                List<Date> planTimeListByFreqWeek = getPlanTimeListByFreqWeek(timeToday, sb.toString(),new Date());
                list.addAll(planTimeListByFreqWeek);
            }
            //拆分出来的拆分医嘱，过滤掉已经存在拆分医嘱
            List<Order> orders = new ArrayList<>();
            //加上拆分时间
            for (Date plandDate:list) {
                Order saveOrder = rawOrderToOrder(order);
                saveOrder.setSplitName(splitName);
                saveOrder.setPlanTime(plandDate);
                orders.add(saveOrder);
            }
            //要保存医嘱
            List<Order> saveOrders = new ArrayList<>();
            //根据医嘱号去查询是已经存在的医嘱
            List<Order> oldHisOrder = orderDao.findAllByHisOrderId(order.getHisOrderId());

            List<Order> newOrderSave = new ArrayList<>();
            //查当前拆分的医嘱是否已经拆分
            for (Order newSchedule : orders) {
                boolean isExist = false;
                for (Order splitedSchedule : oldHisOrder) {
                    if (newSchedule.getHisOrderId().equals(splitedSchedule.getHisOrderId())
                            && DateUtil.getTimeWithoutMS(newSchedule.getPlanTime()).getTime() == DateUtil.getTimeWithoutMS(splitedSchedule.getPlanTime()).getTime()) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    newOrderSave.add(newSchedule);
                }
            }
            //4.拆分好保存原始医嘱表
            if(newOrderSave.size()==0){
                return 0;
            }
            //保存所有的拆分医嘱
            size = size+newOrderSave.size();
            orderDao.saveAll(newOrderSave);
            //5.更新原始医嘱表的状态
            rawOrderDao.splitOrder(order.getHisOrderId());
        }
        //5.更新原始医嘱表的
        return size;
    }

    /**
     * 把单条原始医嘱转化为拆分医嘱
     * @param rawOrder
     * @return
     */
    public Order rawOrderToOrder(RawOrder rawOrder){
        Order order = new Order();
        order.setPlanId(String.valueOf(idWorker.nextId()));
        order.setPatientId(rawOrder.getPatientId());
        order.setHisOrderId(rawOrder.getHisOrderId());
        order.setStartTime(rawOrder.getStartTime());
        order.setEndTime(rawOrder.getEndTime());
        order.setSupplyCode(rawOrder.getSupplyCode());
        order.setDoctorName(rawOrder.getDoctorName());
        order.setDoseUnit(rawOrder.getDoseUnit());
        order.setFrequence(rawOrder.getFrequence());
        order.setStatus("1");
        order.setSplitTime(new Date());
        order.setDrugName(rawOrder.getDrugName());
        order.setRemark(rawOrder.getRemark());
        order.setDose(rawOrder.getDose());
        return order;
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

    /**
     * 根据患者id获取拆分的医嘱,未执行
     * @return
     */
    public List<Order> findAllByPatientId(String patientId) {
        List<Order> allByPatientIdAndStatus = orderDao.findAllByPatientIdAndStatus(patientId, "1");
        return allByPatientIdAndStatus;
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

    /**
     * 执行计划id
     * @param planId
     * @param nurseCode
     */
    public void executeOrder(String planId, String nurseCode, String nurseName) {
        orderDao.executeOrder(new Date(),nurseCode,nurseName,planId);
    }

    /**
     * 获取已经执行的医嘱
     * @param patientId
     * @return
     */
    public List<Order> executeList(String patientId) {
        return orderDao.findAllByPatientIdAndStatus(patientId,"0");
    }

    //======================#92132 长期医嘱按his时间更改预执行时间 end========================
}
