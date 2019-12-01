package com.github.smalnote.heron;

import com.github.smalnote.heron.config.HeronProperties;
import com.github.smalnote.iris.EnableIris;
import com.github.smalnote.iris.Iris;
import com.github.smalnote.iris.IrisContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableIris
@EnableScheduling
@EnableKafka
public class HeronApp {

    private static final Logger LOG = LogManager.getLogger();

    public static final String KAFKA_TOPIC_LOGGER = "KAFKA_TOPIC_LOGGER";

    @Autowired
    private Iris iris;

    @Autowired
    private IrisContext irisContext;


    public static void main(String[] args) {
        SpringApplication.run(HeronApp.class);
    }

    @PostConstruct
    private void setUp() {
        LOG.info(iris.getName());
        LOG.info(irisContext.NAME);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Autowired
    private void printAppId(HeronProperties props) {
        LOG.info(props.getAppId());
    }
}
