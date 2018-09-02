package com.item.eshop.util;
import  java.util.Date;
import  java.util.Calendar;
public class DateUtil {
    //判断当前时间是否为礼拜一
    public static boolean isRightWeek(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.get(calendar.DAY_OF_WEEK)==2)
            return true;
        else
            return false;
    }
    //
    public static Date getMonAndSun(Date date,int diff){
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_WEEK,diff);
        return cal.getTime();
    }
    public static int getWeekDate(int year,int month,int day){
        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month-1,day);

        return calendar.get(Calendar.DAY_OF_WEEK)-1;
    }
    //获取该时间所处月份的前月的第一天
    public static Date getBeforeMonthFirstDay(Date date){
        Calendar   cal_1=Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);
        return cal_1.getTime();

    }
    //获取该时间所处月份的前月的最后一天
    public static Date getBeforeMonthLastDay(Date date){
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, -1);
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cale.getTime();

    }
    //获取当前月的第一天
    public static Date getMonthFirstDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);
        return c.getTime();

    }
    //获取当前月的最后一天
    public static Date getMonthLastDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }
    //
    public static Date getYearFirstDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR),0,1);
        return c.getTime();
    }

    //
    public static Date getYearLastDay(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR),11,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }
    //获取该时间所处年份的上一年的第一天
    public static  Date getBeforeYearFirstDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR)-1,0,1);
        return c.getTime();
    }
    //获取该时间所出年份的上一年的最后一天
    public static Date getBeforeYearLastDay(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR)-1,11,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean sameDate(Date d1, Date d2) {
        if(null == d1 || null == d2)
            return false;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        return  cal1.getTime().equals(cal2.getTime());
    }

}
