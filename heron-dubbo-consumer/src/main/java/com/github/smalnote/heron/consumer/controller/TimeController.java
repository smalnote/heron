package com.github.smalnote.heron.consumer.controller;

import com.github.smalnote.heron.api.TimeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/time")
public class TimeController {

    @DubboReference
    TimeService timeService;

    @GetMapping
    public LocalDateTime getDateTime() {
        return timeService.getDateTime();
    }

}
