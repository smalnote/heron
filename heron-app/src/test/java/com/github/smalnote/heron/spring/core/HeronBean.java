package com.github.smalnote.heron.spring.core;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.LinkedList;
import java.util.List;

@Component
public class HeronBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private List<String> operations;

    public HeronBean() {
        this.operations = new LinkedList<>();
        operations.add("instantiate ");
    }

    @Autowired
    public void setProperty(BeanFactory beanFactory) {
        operations.add("populate properties ");
    }

    @Override
    public void setBeanName(@Nullable String beanName) {
        operations.add("set bean name: " + beanName);
    }

    @Override
    public void setBeanFactory(@Nullable BeanFactory beanFactory) throws BeansException {
        operations.add("set bean factory ");
    }


    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        operations.add("set application context ");
    }

    @Override
    public void afterPropertiesSet() {
        operations.add("initializing bean's after properties set ");
    }

    @PostConstruct
    public void postConstruct() {
        operations.add("postConstruct ");
    }

    @Override
    public void destroy() {
        String op = "disposable bean's destroy ";
        operations.add(op);
        System.out.println(op);
    }

    @PreDestroy
    public void preDestroy() {
        String op = "preDestroy ";
        operations.add(op);
        System.out.println(op);
    }

    public List<String> getOperations() {
        return operations;
    }
}