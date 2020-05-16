package com.github.smalnote.heron.iris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties({IrisProperties.class})
public class IrisAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(IrisAutoConfiguration.class);

    @Autowired(required = false)
    private IrisProperties irisProperties;

    @PostConstruct
    public void setUp() {
        if (irisProperties != null) {
            LOG.debug("iris properties loaded with name: {} ", irisProperties.getName());
        }
    }


}
