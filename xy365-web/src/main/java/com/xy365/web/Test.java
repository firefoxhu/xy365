package com.xy365.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
// https://blog.csdn.net/noseparte/article/details/78678092
public class Test {
    //列如 伪代码不完整
    //@Autowired 一下 或者配置实例
    // private  UserDao   userDao;
    //  private   RoleDao  roleDao;
    // private  MchntService  mchntService;

    // @Scheduled(cron = "0 0 2 * * ?") //每天凌晨2点
    @Scheduled(cron = "*/5 * * * * ?") // 每五秒此一次
    public void test(){
        //TODO 1、通过 DAO  Service  api ....等服务的调用 来获取我们需要处理的数据（可能来自各个系统）  数据1  数据2  数据3  ....    u
        //TODO 2、对已拿到的数据进行处理得到结果
        //TODO 3、把结果保存或者发送某个地方
        System.out.println("赵建雅");
    }
}
