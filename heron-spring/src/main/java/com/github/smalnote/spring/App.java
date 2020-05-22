package com.github.smalnote.spring;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class App {

    private final Log logger = LogFactory.getLog(App.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);
    }

    @PostConstruct
    public void postConstruct() {
        if (logger.isInfoEnabled()) {
            logger.info("after properties set ");
        }
    }


}
