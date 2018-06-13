package com.xmr.token;


import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {  
    private final Logger logger = Logger.getLogger(this.getClass());
      
    @Scheduled(cron="0/2 0/1 * * * ?") //每分钟执行一次,这是cron表达式
    public void statusCheck() {      
        logger.info("开始……");
        //statusTask.healthCheck();  
       // logger.info("每分钟执行一次。结束。");
    }    
  
    //@Scheduled(fixedRate=20000)
    public void testTasks() {      
        logger.info("每20秒执行一次。开始……");  
        //statusTask.healthCheck();  
        logger.info("每20秒执行一次。结束。");  
    }    
}  