package indi.ikun.spring.provider.service.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 日期转换工具
 * @author renqiankun
 */
@Slf4j
public class DateUtils {

    public static String format = "yyyy-MM-dd HH:mm:ss";

    /**
     * 昨天
     */
    public static Date getYesterdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 上个月第一天
     */
    public static Date getMonthStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    /**
     * 上月最后一天
     */
    public static Date getMonthEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 上周第一天
     */
    public static Date getWeekStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        calendar.add(Calendar.DATE, 2 - dayofweek - 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 上个周最后一天
     */
    public static Date getWeekEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getWeekStartDate(date));
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 今年第一天
     */
    public static Date getYearStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 今年最后一天
     */
    public static Date getYearEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getWeekStartDate(date));
        calendar.set(Calendar.MONTH,calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 日期转字符串
     *
     * @param:
     * 2020-07-01 00:00:00 yyyy-MM-dd 00:00:00
     * 2020-07-01 23:59:59 yyyy-MM-dd 23:59:59
     * 2020-07-01 23:59:59 yyyy年MM月dd日 23点59分59秒
     * 2020-07-01 23:59:59 yyyy-MM-dd 23:59:59:
     */
    public static String DateToString(Date date, String formatter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串转日期
     */
    public static Date StringToDate(String dateStr, String formatter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            log.error("======> " + dateStr + " 无法转换为" + formatter + "格式");
        }
        return date;
    }

    /**
     * 获取预设格式化日期
     * 昨天0点，昨天23点
     * 2020-07-01 00:00:00 yyyy-MM-dd 00:00:00
     * 2020-07-01 23:59:59 yyyy-MM-dd 23:59:59:
     */
    public static Date DateToDate(Date date, String formatter) {
        return StringToDate(DateToString(date, formatter), formatter);
    }


    /**
     * uuid
     **/
    public static String getUUID() {
        return String.valueOf(UUID.randomUUID()).replaceAll("-", "");
    }
}
