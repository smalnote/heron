package com.github.smalnote.heron.dist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleDataListener implements DataListener {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleDataListener.class);

    @Override
    public void dataChanged(byte[] data) {
        logger.debug("data changed: {}", new String(data));
    }
}
