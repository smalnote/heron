package com.github.smalnote.heron.iris;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "iris")
public class IrisProperties {

	private String name;

}
