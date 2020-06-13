package com.github.smalnote.heron.spring.circulardependency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChoGallTest {

    @Resource
    Gall gall;

    @Resource
    Cho cho;

    @Test
    public void shouldCreateBeanEvenHaveCircularDependency() {
        assertNotNull(gall);
        assertNotNull(cho);
    }

}
