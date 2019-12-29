package com.github.smalnote.heron.config;

import com.github.smalnote.heron.log.WkeLogMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;


// @Component
public class LogTask {

    private static final Logger JDBC_LOGGER = LogManager.getLogger("jdbc");
    private static final Logger KFK_LOGGER = LogManager.getLogger("kafka");

    // @Scheduled(fixedRate = 20000)
    private void livenessProbe() {
        JDBC_LOGGER.info("Heron app is living... ");
    }

    @Scheduled(fixedRate = 15000)
    private void kakfaMessage() {
        // KFK_LOGGER.info("This is a test message send to kafka logger. ");
        WkeLogMessage message = new WkeLogMessage();
        message.setWkeCod("XXYYYZZZ");
        message.setErrCod("XX00001");
        message.setErrMsg("This a message set in WkeLogMessage! ");
        KFK_LOGGER.info(message);
    }

}
