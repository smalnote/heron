package com.github.smalnote.heron.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TcpConfigKey {

    private Env env;
    private Mac mac;

}
