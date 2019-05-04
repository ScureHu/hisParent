package units;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DateUtil {

    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static SimpleDateFormat dateFormat     = new SimpleDateFormat("dd-MM-yyyy");
    public static final String[]    DATE_FORMATS   = new String[] { "yyyyMMdd", "yyyy", "yyyy-MM", "yyyy-MM-dd",
            "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm", "yyyyMMddHH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-ddHHmmss",
            "yyyy-MM-dd HHmmss", "yyyy-MM-dd HH:mm:ss.S", "yyyyMMddHH:mm", "yyyy/MM/dd HH:mm:ss" };

    public static String getToday() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(today);
    }

    public static String getDateDMY(Date date) {
        return (date == null) ? "" : dateFormat.format(date);
    }

    public static String getDateTimeDMYHms(Date date) {
        return (date == null) ? "" : dateTimeFormat.format(date);
    }

    /***************************************************************************
     * 将传入的日期转化为"yyyy-MM-dd"形式的字符串
     *
     * @author Alan he <alan.he@jongogroup.com>
     * @param dt
     * @return String
     */
    public static String formatDate(Date dt) {
        if (dt == null) return null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(dt);
    }

    public static String formatDate(String format, Date dt) {
        if (dt == null) return null;
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(dt);
    }

    public static String formatDateYMDHMS(Date dt) {
        if (dt == null) return "";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(dt);
    }

    public static String formatDateYMDHM(Date dt) {
        SimpleDateFormat fmt = new SimpleDateFormat("yy年M月d日 HH:mm");
        return fmt.format(dt);
    }

    public static String formatDateYMDHMe(Date dt) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return fmt.format(dt);
    }


    @SuppressWarnings("static-access")
    public static String getPeriodDate(int intervalDay) {
        Calendar cal = Calendar.getInstance();
        // 1-intervalDay表示包含今天的间隔
        cal.add(cal.DAY_OF_MONTH, 1 - intervalDay);
        Date date = cal.getTime();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(date);
    }

    /**
     * 得到传入日期n天后的日期,如果传入日期为null，则表示当前日期n天后的日期
     *
     * @author Alan he <alan.he@jongogroup.com>
     * @param days 可以为任何整数，负数表示前days天，正数表示后days天
     * @return Date
     */
    public static Date getAddDayDate(Date dt, int days) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + days);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 给定的时间再加上指定小时数
     *
     * @author Alex Zhang
     * @param dt
     * @param hours
     * @return
     */
    public static Date getAddHourDate(Date dt, int hours) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.HOUR, hours);

        return cal.getTime();
    }

    /**
     * 给定的时间再加上指定秒数
     *
     * @param dt
     * @param seconds
     * @return
     */
    public static Date getAddSecondDate(Date dt, int seconds) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.SECOND, seconds);

        return cal.getTime();
    }

    /***************************************************************************
     * 某天的开始时刻
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /***************************************************************************
     * 某天的截止时刻
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /***************************************************************************
     * 某天的特定时刻
     *
     * @param date
     * @return
     */
    public static Date getDaySomeTime(Date date, int hour, int min, int second, int mill) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, mill);
        return cal.getTime();
    }

    /***************************************************************************
     * java.util.Date装换为java.sql.Date
     *
     * @author Alan he <alan.he@jongogroup.com>
     * @param dt
     * @return java.sql.Date
     */
    public static java.sql.Date toSqlDate(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    /***************************************************************************
     * 得到传入日期所在月的开始时间，如果传入日期为null，则表示当前日期n天后的日期
     *
     * @author Alan he <alan.he@jongogroup.com> 2007-7-25
     * @param dt
     * @return Date
     */
    public static Date getMonthBeginTime(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /***************************************************************************
     * 得到传入日期所在月的结束时间，如果传入日期为null，则表示当前日期n天后的日期
     *
     * @author Alan he <alan.he@jongogroup.com> 2007-7-25
     * @param dt
     * @return Date
     */
    public static Date getMonthEndTime(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }

    public static Timestamp dateToTimestamp(Date dt) {
        if (dt == null) return new Timestamp(System.currentTimeMillis());
        else return new Timestamp(dt.getTime());
    }

    /***************************************************************************
     * 获得传入日期的年\月\日,已整型数组方式返回
     *
     * @author Alan he <alan.he@jongogroup.com> 2007-7-26
     * @param dt
     * @return int[]
     */
    public static int[] getTimeArray(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        int[] timeArray = new int[3];
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        timeArray[0] = cal.get(Calendar.YEAR);
        timeArray[1] = cal.get(Calendar.MONTH) + 1;
        timeArray[2] = cal.get(Calendar.DAY_OF_MONTH);
        return timeArray;
    }

    public static int[] timeArray(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        int[] timeArray = new int[5];
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        timeArray[0] = cal.get(Calendar.YEAR);
        timeArray[1] = cal.get(Calendar.MONTH) + 1;
        timeArray[2] = cal.get(Calendar.DAY_OF_MONTH);
        timeArray[3] = cal.get(Calendar.HOUR_OF_DAY);
        timeArray[4] = cal.get(Calendar.MINUTE);
        return timeArray;
    }

    /***************************************************************************
     * 根据年月日得到Date类型时间
     *
     * @author Alan he <alan.he@jongogroup.com>
     * @param year
     * @param month
     * @param day
     * @return Date
     */
    public static Date getTime(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        if (year != null) cal.set(Calendar.YEAR, year);
        if (month != null) cal.set(Calendar.MONTH, month - 1);
        if (day != null) cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }


    /**
     * @author kim <kim.qiu@jongogroup.com> 通过格式化时间得到字符串
     * @param parrern 格式化字符串 例如：yyyy-MM-dd
     * @param date 时间 例如：new Date()
     * @return 出错返回 ""
     */
    public static String getStringFromPattern(String parrern, Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat(parrern);
        try {
            return fmt.format(date);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 计算两个日期间相隔的小时
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getDayBetween(java.util.Date d1, java.util.Date d2) {
        // return (int)(d1.getTime()-d2.getTime())/(1000*60*60*24);
        Date[] d = new Date[2];
        d[0] = d1;
        d[1] = d2;
        Calendar[] cal = new Calendar[2];
        for (int i = 0; i < cal.length; i++) {
            cal[i] = Calendar.getInstance();
            cal[i].setTime(d[i]);
            // cal[i].set(Calendar.HOUR_OF_DAY,0);
            cal[i].set(Calendar.MINUTE, 0);
            cal[i].set(Calendar.SECOND, 0);
        }
        long m = cal[0].getTime().getTime();
        long n = cal[1].getTime().getTime();
        return (int) ((m - n) / 3600000);
    }

    /**
     * 计算两个日期间相隔的天数
     *
     * @return
     */
    public static int getDateBetween(Date t1, Date t2) {
        Date t3;
        // 保证第二个时间一定大于第一个时间
        if (t1.getTime() > t2.getTime()) {
            t3 = t2;
            t2 = t1;
            t1 = t3;
        }
        return (int) ((t2.getTime() - t1.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算两个日期间相隔的天数(日期相减)
     *
     * @return
     */
    public static int getDateBetweenByDate(Date t1, Date t2) {
        Date t3;
        // 保证第二个时间一定大于第一个时间
        if (t1.getTime() > t2.getTime()) {
            t3 = t2;
            t2 = t1;
            t1 = t3;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(t1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        t1 = c1.getTime();

        Calendar c2 = Calendar.getInstance();
        c2.setTime(t2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        t2 = c2.getTime();

        return (int) ((t2.getTime() - t1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static double getTimesBetween(java.util.Date d1, java.util.Date d2) {
        Date[] d = new Date[2];
        d[0] = d1;
        d[1] = d2;
        Calendar[] cal = new Calendar[2];
        for (int i = 0; i < cal.length; i++) {
            cal[i] = Calendar.getInstance();
            cal[i].setTime(d[i]);
            cal[i].set(Calendar.HOUR_OF_DAY, 0);
            cal[i].set(Calendar.MINUTE, 0);
            cal[i].set(Calendar.SECOND, 0);
            cal[i].set(Calendar.MILLISECOND, 0);
        }
        long m = cal[0].getTime().getTime();
        long n = cal[1].getTime().getTime();
        return (double) ((m - n));
    }



    /**
     * 计算年龄
     *
     * @param brithday
     * @return
     */
    public static String getAge(Date brithday) {
        if (brithday == null) return null;
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(brithday);
            Calendar birthday = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));// month从0开始
            Calendar now = Calendar.getInstance();
            int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
            int month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
            int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
            // 按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。
            if (day < 0) {
                month -= 1;
                now.add(Calendar.MONTH, -1);// 得到上一个月，用来得到上个月的天数。
                day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (month < 0) {
                month = (month + 12) % 12;
                year--;
            }
            return year > 0 ? (year + 1) + "岁" : (month > 0 ? month + "个月" : "")
                    + (day == 0 ? (year == 0 && month == 0 ? "0天" : "") : day + "天");
        }
        catch (Exception e) {
            return "未知";
        }
    }

    /**
     * 取得两个时间之间的天数，可能是负数(第二个时间的日期小于第一个时间的日期)。如果两个时间在同一天，则返回1
     *
     * @param d1 第一个时间
     * @param d2 第二个时间
     * @return
     * @author Derek
     * @version 1.0 2009-10-14
     */
    public static int getDaysBetween(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        int days = (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / 86400000);
        if (days == 0) return 1;
        else return days;
    }

    public static Date getNowWithoutMS() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getNowWithoutSecond() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getTimeWithoutMS(Date inputTime) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(inputTime);
        c1.set(Calendar.MILLISECOND, 0);
        return c1.getTime();
    }

    public static Date getTimeWithoutSecond(Date inputTime) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(inputTime);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        return c1.getTime();
    }

    public static boolean isTimeEquals(Date d1, Date d2) {
        if (d1 == null || d2 == null) return false;
        return Math.abs(d1.getTime() - d2.getTime()) < 50;
    }

    /**
     * 根据分钟间隔数,返回时间点列表,从0点 -- 0点
     *
     * @param min 分钟间隔数
     * @return
     */
    public static List<Date> getDateListByMin(int min) {
        List<Date> dateList = new ArrayList<Date>();
        int minOfDay = 60 * 24;
        int timeLine = minOfDay / min;
        for (int i = 0; i <= timeLine; i++) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            c.add(Calendar.MINUTE, i * min);
            dateList.add(c.getTime());
        }
        return dateList;
    }

    /**
     * 判断d1的时分秒是否在d2的时分秒之后
     *
     * @param d1
     * @param d2
     * @return 如果d1的时分秒 < d2的时分秒,则true,否则false
     */
    public static boolean afterHourMinSecond(Date d1, Date d2) {
        if (d1 == null || d2 == null) return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        c1.set(Calendar.YEAR, 0);
        c1.set(Calendar.MONTH, 0);
        c1.set(Calendar.DAY_OF_YEAR, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        c2.set(Calendar.YEAR, 0);
        c2.set(Calendar.MONTH, 0);
        c2.set(Calendar.DAY_OF_YEAR, 0);
        return c1.after(c2);
    }

    /**
     * 判断d1的时分秒是否在d2的时分秒之前
     *
     * @param d1
     * @param d2
     * @return 如果d1的时分秒 > d2的时分秒,则true,否则false
     */
    public static boolean beforeHourMinSecond(Date d1, Date d2) {
        if (d1 == null || d2 == null) return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        c1.set(Calendar.YEAR, 0);
        c1.set(Calendar.MONTH, 0);
        c1.set(Calendar.DAY_OF_YEAR, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        c2.set(Calendar.YEAR, 0);
        c2.set(Calendar.MONTH, 0);
        c2.set(Calendar.DAY_OF_YEAR, 0);
        return c1.before(c2);
    }

    /**
     * 取1900年
     *
     * @return
     */
    public static Date getDefaultTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1900);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getMinMaxTime2(Date inputDate) {
        if (inputDate == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);
        if (c.get(Calendar.YEAR) < 1900) {
            return null;
        }
        if (c.get(Calendar.YEAR) > 2078) {
            Calendar cl = Calendar.getInstance();
            cl.set(Calendar.YEAR, 2078);
            cl.set(Calendar.MONTH, 1);
            cl.set(Calendar.DAY_OF_MONTH, 1);
            cl.set(Calendar.MINUTE, 0);
            cl.set(Calendar.SECOND, 0);
            cl.set(Calendar.MILLISECOND, 0);
            return cl.getTime();
        }
        return inputDate;
    }

    /**
     * 检查时间,如果是最小值,返回1900年,如果是最大值,返回2078年,如果是空,返回空,正常直接返回
     *
     * @param inputDate
     * @return
     */
    public static Date getMinMaxTime(Date inputDate) {
        if (inputDate == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);
        if (c.get(Calendar.YEAR) < 1900) {
            return getDefaultTime();
        }
        if (c.get(Calendar.YEAR) > 2078) {
            Calendar cl = Calendar.getInstance();
            cl.set(Calendar.YEAR, 2078);
            cl.set(Calendar.MONTH, 1);
            cl.set(Calendar.DAY_OF_MONTH, 1);
            cl.set(Calendar.MINUTE, 0);
            cl.set(Calendar.SECOND, 0);
            cl.set(Calendar.MILLISECOND, 0);
            return cl.getTime();
        }
        return inputDate;
    }

    /**
     * 取2078年
     *
     * @return
     */
    public static Date getMaxTime() {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.YEAR, 2078);
        cl.set(Calendar.MONTH, 1);
        cl.set(Calendar.DAY_OF_MONTH, 1);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        cl.set(Calendar.MILLISECOND, 0);
        return cl.getTime();
    }

    /**
     * 取指定日期处理后的结果
     * @param base	基准日期
     * @param addDay	相对天数：1，明天；0，今天；-1 昨天
     * @param newHour	返回时间的小时数
     * @param newMin	返回时间的分钟数
     * @param newSec	返回时间的秒数
     * @param newMillSec	返回时间的毫秒数
     * @return
     */
    public static Calendar getNewCalendar(Date base, int addDay,
                                          int newHour, int newMin, int newSec, int newMillSec) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(base);
        cal.add(Calendar.DAY_OF_YEAR, addDay);
        cal.set(Calendar.HOUR_OF_DAY, newHour);
        cal.set(Calendar.MINUTE, newMin);
        cal.set(Calendar.SECOND, newSec);
        cal.set(Calendar.MILLISECOND, newMillSec);

        return cal;
    }

}
