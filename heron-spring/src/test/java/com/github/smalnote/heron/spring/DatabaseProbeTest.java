package com.github.smalnote.heron.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseProbeTest {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldSuccessWhenSelect() {
        String sql = "SELECT current_time";
        Date date = jdbcTemplate.queryForObject(sql, Date.class);
        assertNotNull(date);
    }

}
