package com.github.smalnote.heron.iris;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IrisBeanConfiguration {

    private static final IrisContext irisContext = new IrisContext();

    @Bean
    public IrisContext getIrisContext() {
        return irisContext;
    }

    @Bean
    public Iris getIris() {
        return new Iris("iris");
    }
}
