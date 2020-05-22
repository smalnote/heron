package com.github.smalnote.app.config.tcp;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "tcp")
@Getter
public class ConfigProperties {

    private List<Config> configs = new LinkedList<>();

}
