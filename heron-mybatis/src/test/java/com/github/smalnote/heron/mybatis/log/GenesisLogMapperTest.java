package com.github.smalnote.heron.mybatis.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GenesisLogMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(GenesisLogMapperTest.class);

    @Resource
    GenesisLogMapper logMapper;

    @Test
    public void shouldRetrunGeneratedIdWhenInsert() {
        GenesisLog log = GenesisLog.builder()
                .level("DEBUG")
                .logger(GenesisLogMapperTest.class.getSimpleName())
                .message("test select key of mybatis")
                .customMsg("")
                .exception("")
                .build();
        int rowAffected = logMapper.insert(log);
        assertEquals(1, rowAffected);

        logger.debug("inserted log's event_date: {}", log.getEventDate());
    }

    @Test
    public void shouldReturnGeneratedKeysWhenInsertMultipleRows() {

        GenesisLog log = GenesisLog.builder()
                .level("DEBUG")
                .logger(GenesisLogMapperTest.class.getSimpleName())
                .message("test select key of mybatis")
                .customMsg("")
                .exception("")
                .build();
        List<GenesisLog> logs = Collections.unmodifiableList(Arrays.asList(log.toBuilder().build(), log.toBuilder().build(), log.toBuilder().build()));
        int rowAffected = logMapper.insertBatch(logs);
        assertEquals(logs.size(), rowAffected);
        logs.forEach(l -> logger.debug("inserted log's event_id is {}", l.getEventId()));

    }

    @Test
    @Transactional
    public void shouldSelectAssociatedLog() {
        // TODO not work yet
        GenesisLog insertLog = GenesisLog.builder()
                .level("DEBUG")
                .logger(GenesisLogMapperTest.class.getSimpleName())
                .message("test select key of mybatis")
                .customMsg("")
                .exception("")
                .build();
        int rowAffected = logMapper.insert(insertLog);
        assertEquals(1, rowAffected);
        GenesisLogWithItself log = logMapper.selectAssociationLog(insertLog.getEventId());
        assertNotNull(log);
        GenesisLogWithItself mySelf = log.getMySelf();
        assertNotNull(mySelf);
        assertEquals(mySelf.getEventId(), log.getEventId());
    }

}
