package com.wanggl.h2sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;

@Component("ProgressBeanPostProcessor")
public class ProgressBeanPostProcessor implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ProgressBeanPostProcessor.class);

    private double total = 329;

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        count.incrementAndGet();
        try {
            PrintStream ps = new PrintStream("./log/progress.txt");
            ps.print((int) (count.get() / total * 100));
            ps.flush();
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
        //最终应该设置的total值
        logger.info("total:{}", count.get());
    }

}
