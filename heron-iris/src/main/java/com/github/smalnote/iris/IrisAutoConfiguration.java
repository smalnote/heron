package com.github.smalnote.iris;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Iris.class)
@EnableConfigurationProperties(IrisProperties.class)
public class IrisAutoConfiguration {
	
	@Autowired
	private IrisProperties irisProperties;
 
    @Bean
    @ConditionalOnMissingBean
    public Iris iris() {
        return new Iris(irisProperties.getModuleName());
    }

}
