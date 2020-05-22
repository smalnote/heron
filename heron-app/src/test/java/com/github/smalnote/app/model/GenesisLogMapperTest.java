package com.github.smalnote.app.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
class GenesisLogMapperTest {

    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private GenesisLogMapper mapper;

    @Test
    public void shouldHaveOneRecord() throws SQLException {
        GenesisLog log = GenesisLog.builder().eventId(1).build();
        GenesisLog first = mapper.select(log, false);
        assertNotNull(first);
        assertEquals(1, first.getEventId());
        LOG.debug(first);
    }

    @Test
    public void shouldHaveNoRecord() throws SQLException {
        GenesisLog log = GenesisLog.builder().eventId(1).build();
        GenesisLog first = mapper.select(log, true);
        assertNull(first);
    }
}