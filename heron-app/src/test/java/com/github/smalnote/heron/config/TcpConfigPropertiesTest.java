package com.github.smalnote.heron.config;

import com.github.smalnote.heron.tcp.config.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TcpConfigPropertiesTest {

    static final Logger LOG = LoggerFactory.getLogger(TcpConfigProperties.class);

    @Autowired
    private TcpConfigProperties tcpConfigProperties;

    @Test
    public void shouldHaveTcpConfig() {
        TcpConfig devSh01 = tcpConfigProperties.get(new TcpConfigKey(Env.DEV, Mac.SH01));
        assertThat(devSh01, notNullValue());
        assertThat(devSh01.getIp(), is("192.168.0.1"));
        assertThat(devSh01.getPort(), is(3306));
        LOG.debug(devSh01.toString());
    }

}