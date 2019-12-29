package com.github.smalnote.heron.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenesisLog {

    private long eventId;
    private Timestamp eventDate;
    private String level;
    private String logger;
    private String message;
    private String customMsg;
    private String exception;

}
