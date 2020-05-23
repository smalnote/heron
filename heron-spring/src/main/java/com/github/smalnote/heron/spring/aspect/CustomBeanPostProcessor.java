package com.github.smalnote.heron.spring.aspect;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(@Nullable Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) throws BeansException {
        return bean;
    }

    protected void breakPostProcess() {
        throw new UnsupportedOperationException("just for break ");
    }
}
