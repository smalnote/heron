package com.github.smalnote.heron.config;

import com.github.smalnote.heron.log.KafkaAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AsyncAppender;
import org.apache.logging.log4j.core.appender.db.ColumnMapping;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LogConfig {

    private JdbcConnectionSource jdbcConnectionSource;

    @Autowired
    public LogConfig(JdbcConnectionSource jdbcConnectionSource) {
        this.jdbcConnectionSource = jdbcConnectionSource;
    }

    @PostConstruct
    private void onStartUp() {
        // setUpJdbcLogger();
        setUpKafkaLogger();
    }

    private void setUpKafkaLogger() {
        KafkaAppender kafkaAppender = new KafkaAppender("kafkaAppender", null, null, true);
        kafkaAppender.start();

        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        config.addAppender(kafkaAppender);

        ((Logger) LogManager.getLogger("kafka")).addAppender(kafkaAppender);
    }

    private void setUpJdbcLogger() {
        ColumnMapping[] columnMappings = new ColumnMapping[4];
        columnMappings[0] =
                ColumnMapping.newBuilder().setName("timestamp").setType(java.util.Date.class).build();
        columnMappings[1] =
                ColumnMapping.newBuilder().setName("level").setPattern("%level").build();
        columnMappings[2] =
                ColumnMapping.newBuilder().setName("class").setPattern("%c").build();
        columnMappings[3] =
                ColumnMapping.newBuilder().setName("message").setPattern("%m").build();


        JdbcAppender jdbcAppender = JdbcAppender.newBuilder().setName("jdbcAppender").setTableName(
                "log").setColumnMappings(columnMappings).setIgnoreExceptions(
                true).setConnectionSource(jdbcConnectionSource).build();
        jdbcAppender.start();

        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        config.addAppender(jdbcAppender);

        AppenderRef[] jdbcAppenderRefs = new AppenderRef[1];
        jdbcAppenderRefs[0] = AppenderRef.createAppenderRef("jdbcAppender", null, null);
        AsyncAppender asyncJdbcAppender =
                AsyncAppender.newBuilder().setName("asyncJdbcAppender").setBlocking(
                        false).setIgnoreExceptions(true).setAppenderRefs(
                        jdbcAppenderRefs).setConfiguration(config).build();
        asyncJdbcAppender.start();
        config.addAppender(asyncJdbcAppender);

        ((Logger) LogManager.getLogger("jdbc")).addAppender(asyncJdbcAppender);
    }

}
