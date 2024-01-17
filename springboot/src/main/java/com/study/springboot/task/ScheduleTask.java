package com.study.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 胡代伟
 * @description 定时任务
 * @date 2024-01-17 9:56
 */
//1、加入spring容器管理
@Component
public class ScheduleTask {
    //2、指定执行的时间
    //3、主启动类上开启对定时任务的支撑
    //@Scheduled(cron = "0/5 * * * * 0-7",initialDelay = 2000) //'initialDelay' not supported for cron triggers
    //@Scheduled(cron = "0/5 * * * * 0-7",fixedDelay = 2000) //Exactly one of the 'cron', 'fixedDelay(String)', or 'fixedRate(String)' attributes is required
    //@Scheduled(cron = "0/5 * * * * 0-7",fixedRate = 1000) //Exactly one of the 'cron', 'fixedDelay(String)', or 'fixedRate(String)' attributes is required
    //@Scheduled(fixedRate = 1000,initialDelay = 5000,fixedDelay = 1000) //Exactly one of the 'cron', 'fixedDelay(String)', or 'fixedRate(String)' attributes is required
    //@Scheduled(fixedRate = 1000,initialDelay = 5000) //启动5秒后执行
    //@Scheduled(fixedRate = 1000,fixedDelay = 1000) //Exactly one of the 'cron', 'fixedDelay(String)', or 'fixedRate(String)' attributes is required
    //@Scheduled(fixedDelay = 1000) //1秒执行一次
    @Scheduled(cron = "0/5 * * * * 0-7") //5秒执行一次
    private void test() {
        System.out.println("方法执行了");
    }
}
