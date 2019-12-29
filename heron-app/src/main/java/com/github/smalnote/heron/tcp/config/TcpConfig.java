package com.github.smalnote.heron.tcp.config;

import lombok.Data;

@Data
public class TcpConfig {

    private Env env;
    private Mac mac;
    private String ip;
    private int port;

}
