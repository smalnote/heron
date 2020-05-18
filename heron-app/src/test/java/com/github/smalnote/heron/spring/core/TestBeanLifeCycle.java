package com.github.smalnote.heron.spring.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBeanLifeCycle {

    @Resource
    private HeronBean bean;

    @Test
    public void testBeanLifeCycle() {
        for (String operation : bean.getOperations()) {
            System.out.println(operation);
        }
    }

}
