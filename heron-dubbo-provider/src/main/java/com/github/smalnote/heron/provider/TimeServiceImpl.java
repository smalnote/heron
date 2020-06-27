package com.github.smalnote.heron.provider;

import com.github.smalnote.heron.api.TimeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@DubboService
public class TimeServiceImpl implements TimeService {
    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}
