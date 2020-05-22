package com.github.smalnote.app.config.tcp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
class TcpConfigurationTest {

    @Autowired
    private TcpConfiguration tcpConfiguration;

    @Test
    public void shouldHaveConfig() {
        ConfigKey key = new ConfigKey(Env.DEV, Mac.SZ01);
        Config config = tcpConfiguration.get(key);
        assertNotNull(config);
        assertEquals("192.168.0.1", config.getIp());
        assertEquals(80, config.getPort());
    }

}