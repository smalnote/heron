package com.github.smalnote.spring.wke;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WkeServiceTest {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void call() {
        WkeService wkeService = applicationContext.getBean(WkeService.class);
        wkeService.call("a test call");
    }
}