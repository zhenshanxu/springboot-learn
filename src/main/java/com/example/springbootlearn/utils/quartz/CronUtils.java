package com.example.springbootlearn.utils.quartz;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project springboot-learn
 * @Description cron 时间表达式
 * @Author xuzhenshan
 * @Date 2023/12/27 18:45:07
 * @Version 1.0
 */
public class CronUtils {

    /**
     * 获取 cron 时间格式
     *
     * @param cronExpression 定时任务执行时间
     * @param execute        定时任务执行次数
     * @param week           按周执行
     * @return
     */
    public static String getCronExpression(String cronExpression, int execute, String week) {
        String cron = null;
        switch (execute) {
            case 0: // 仅执行一次
                cron = onlyOnce(cronExpression);
                break;
            case 1: // 每天执行
                cronExpression = cronExpression.split(" ")[1];
                cron = everyDay(cronExpression);
                break;
            case 2: // 每周执行
                cronExpression = cronExpression.split(" ")[1];
                cron = everyWeek(cronExpression, week);
                break;
            case 3: // 每月执行
                cron = everyMonth(cronExpression);
                break;
            case 4: // 每年执行
                cron = everyYear(cronExpression);
            default:
                break;
        }
        return cron;
    }

    /**
     * 仅一次
     *
     * @param dateStr
     * @return
     */
    public static String onlyOnce(String dateStr) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return localDateTime.format(DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy"));
    }

    /**
     * 每天
     *
     * @param timeStr
     * @return
     */
    public static String everyDay(String timeStr) {
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return time.format(DateTimeFormatter.ofPattern("ss mm HH * * ?"));
    }

    /**
     * 每周
     *
     * @param timeStr
     * @param week
     * @return
     */
    public static String everyWeek(String timeStr, String week) {
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return time.format(DateTimeFormatter.ofPattern("ss mm HH ? * " + week));
    }

    /**
     * 每月
     *
     * @param dateStr
     * @return
     */
    public static String everyMonth(String dateStr) {
        LocalDateTime time = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return time.format(DateTimeFormatter.ofPattern("ss mm HH dd * ?"));
    }

    /**
     * 每年
     *
     * @param dateStr 2022-03-31 09:51:05
     * @return
     */
    public static String everyYear(String dateStr) {
        LocalDateTime time = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return time.format(DateTimeFormatter.ofPattern("ss mm HH dd MM *"));
    }

}
