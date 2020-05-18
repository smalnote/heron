package com.github.smalnote.heron.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == HeronBean.class) {
            ((HeronBean) bean).getOperations().add("bean post processor's before initialization ");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == HeronBean.class) {
            ((HeronBean) bean).getOperations().add("bean post processor's after initialization ");
        }
        return bean;
    }
}
