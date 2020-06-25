package com.github.smalnote.heron.mybatis.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GenesisLog {

    private long eventId;
    private LocalDateTime eventDate;
    private String level;
    private String logger;
    private String message;
    private String customMsg;
    private String exception;

}
