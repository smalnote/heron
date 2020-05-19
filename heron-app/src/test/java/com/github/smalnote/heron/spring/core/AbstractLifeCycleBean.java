package com.github.smalnote.heron.spring.core;

import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractLifeCycleBean implements InitializingBean {

    private BeanLifeCycle lifeCycle;

    public AbstractLifeCycleBean() {
        this.lifeCycle = BeanLifeCycle.INSTANTIATED;
    }

    @Override
    public void afterPropertiesSet() {
        this.lifeCycle = BeanLifeCycle.PROPERTIES_SET;
    }

    public BeanLifeCycle getLifeCycle() {
        return lifeCycle;
    }

    public void setLyfeCycle(BeanLifeCycle lyfeCycle) {
        this.lifeCycle = lyfeCycle;
    }
}
