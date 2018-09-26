package com.wpisen.trace.server;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 摘要：服务入口启动类
 * @author Greet.guo
 * @version 1.0
 * @Date 2017年8月8日
 */
@SpringBootApplication
@EnableScheduling
public class TraceServiceMain {
	private static Logger logger = LoggerFactory.getLogger(TraceServiceMain.class);

    public static void main(String[] args) throws Exception {
    	SpringApplication ctx = new SpringApplication(TraceServiceMain.class);
        ctx.setShowBanner(false);
        ctx.setWebEnvironment(true);
        Set<Object> set = new HashSet<Object>();
        set.add("classpath*:config/spring*.xml");
        ctx.setSources(set);
        ctx.run(args);
        logger.warn("Trace->TraceServiceMain start !!!!");
    }
}
