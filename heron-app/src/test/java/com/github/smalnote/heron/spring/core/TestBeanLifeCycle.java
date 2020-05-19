package com.github.smalnote.heron.spring.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBeanLifeCycle {

    @Resource
    private HeronBean bean;

    @Resource
    private BeanFactory beanFactory;

    @Test
    public void testBeanLifeCycle() {
        for (String operation : bean.getOperations()) {
            System.out.println(operation);
        }
    }

    @Test
    public void shouldBeDifferentInstanceWhenBeansScopeIsPrototype() {
        PrototypeBean firstBean = beanFactory.getBean(PrototypeBean.class);
        PrototypeBean secondBean = beanFactory.getBean(PrototypeBean.class);
        assertNotEquals(firstBean.hashCode(), secondBean.hashCode());
    }

    @Test
    public void shoudBeSameInstanceWhenBeansScopeIsSingleton() {
        SingletonBean firstBean = beanFactory.getBean(SingletonBean.class);
        SingletonBean secondBean = beanFactory.getBean(SingletonBean.class);
        assertEquals(firstBean.hashCode(), secondBean.hashCode());
    }

    @Test
    public void shouldBePostProcessedAfterInitEvenFromBeanFactoryWhenBeanIsSingleton() {
        SingletonBean bean = beanFactory.getBean(SingletonBean.class);
        assertEquals(BeanLifeCycle.POST_PROCESSED_AFTER_INITIALIZATION, bean.getLifeCycle());
    }

    @Test
    public void shouldBePostProcessedAfterInitFromBeanFactoryWhenBeanIsPrototype() {
        // so that in this version, beanFactory is actually ApplicationContext
        PrototypeBean bean = beanFactory.getBean(PrototypeBean.class);
        assertEquals(BeanLifeCycle.POST_PROCESSED_AFTER_INITIALIZATION, bean.getLifeCycle());
    }

}
