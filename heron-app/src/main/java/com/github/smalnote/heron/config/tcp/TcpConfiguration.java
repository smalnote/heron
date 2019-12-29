package com.github.smalnote.heron.config.tcp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ConditionalOnProperty(value = "tcp.enabled", havingValue = "true")
public class TcpConfiguration {
    private static final Logger LOG = LogManager.getLogger();
    private static final Map<ConfigKey, Config> CONFIGS = new ConcurrentHashMap<>();
    @Autowired
    private ConfigProperties configProperties;

    @PostConstruct
    private void setUp() {
        configProperties.getConfigs().forEach(config -> CONFIGS.put(config.getKey(), config));
        LOG.debug("{} tcp configs loaded ", CONFIGS.size());
    }

    public Config get(ConfigKey key) {
        return CONFIGS.get(key);
    }

}
