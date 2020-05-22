package com.github.smalnote.app.spring.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBeanWithBeanPostProcessor {

    @Resource
    private CustomBeanPostProcessor bean;

    @Test
    public void test() {

    }

}
