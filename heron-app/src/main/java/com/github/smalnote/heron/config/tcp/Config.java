package com.github.smalnote.heron.config.tcp;

import lombok.Data;

@Data
public class Config {

    private Env env;
    private Mac mac;
    private String ip;
    private int port;

    public ConfigKey getKey() {
        return new ConfigKey(env, mac);
    }

}
