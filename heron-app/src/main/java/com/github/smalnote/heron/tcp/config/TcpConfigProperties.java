package com.github.smalnote.heron.tcp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ConfigurationProperties("tcp")
public class TcpConfigProperties {

    static final Logger LOG = LoggerFactory.getLogger(TcpConfigProperties.class);

    private List<TcpConfig> configs;

    private final static Map<TcpConfigKey, TcpConfig> CONFIGS = new ConcurrentHashMap<>();

    @PostConstruct
    private void setUp() {
        assert configs != null;
        for (TcpConfig config : configs) {
            CONFIGS.put(new TcpConfigKey(config.getEnv(), config.getMac()), config);
        }
        LOG.debug("{} tcp configs loaded ", CONFIGS.size());
    }

    public TcpConfig get(TcpConfigKey key) {
        assert key != null;
        return CONFIGS.get(key);
    }

}
