package com.github.smalnote.heron.spring.transaction;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenesisLog {

    private long eventId;
    private LocalDateTime eventDate;
    private String level;
    private String logger;
    private String message;

}
