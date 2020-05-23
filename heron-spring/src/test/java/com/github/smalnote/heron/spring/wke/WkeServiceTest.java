package com.github.smalnote.heron.spring.wke;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WkeServiceTest {

    @Resource
    WkeService wkeService;

    @Test
    public void call() {
        wkeService.call("a test call");
    }
}