package com.github.smalnote.heron.spring.transaction;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
    private static final String insertLogSql = "INSERT INTO genesis_log_t(event_date, level, logger, message, custom_msg, exception) VALUES (?, ?, ?, ?, '', '')";
    private final JdbcTemplate jdbcTemplate;

    public LogService(JdbcTemplate jdbcTemplate) {
        logger.debug("instantiating transactional log service... ");
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertLog(GenesisLog log) {
        PreparedStatementCreatorFactory cf = new PreparedStatementCreatorFactory(insertLogSql);
        cf.setGeneratedKeysColumnNames("event_id");
        PreparedStatementCreator creator = cf.newPreparedStatementCreator(new Object[]{log.getEventDate(), log.getLevel(), log.getLogger(), log.getMessage()});
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return jdbcTemplate.update(creator, keyHolder);
    }

    @PostConstruct
    public void postConstruct() {
        logger.debug("log service's properties are set ");
    }


}
