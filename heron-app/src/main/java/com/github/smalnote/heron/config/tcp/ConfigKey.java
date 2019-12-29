package com.github.smalnote.heron.config.tcp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfigKey {
    private Env env;
    private Mac mac;
}
