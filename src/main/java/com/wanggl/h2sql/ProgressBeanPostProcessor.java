package com.wanggl.h2sql;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Component("ProgressBeanPostProcessor")
public class ProgressBeanPostProcessor implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {

    private double total = 334;

    private AtomicInteger count = new AtomicInteger(0);

    String authority = Objects.requireNonNull(
            ProgressBeanPostProcessor.class.getClassLoader().getResource("")).getAuthority();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        count.incrementAndGet();
        try {
            PrintStream ps = new PrintStream(authority + "progress.txt");
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
        System.out.println("total:" + count.get());
    }

}