package com.github.smalnote.heron.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

class UserTest {

    @Test
    void builderInitWithZeroValues() {
        User user = User.builder(true).password("123456").build();
        assertThat(user.getUsername(), is(""));
        assertThat(user.getBlance(), is(BigDecimal.ZERO));
        assertThat(user.getPostNo(), is(0L));
        assertThat(user.getPassword(), is("123456"));
    }

    @Test
    void builder() {
        User user = User.builder().username("user").build();
        assertThat(user.getUsername(), is("user"));
        assertThat(user.getPassword(), nullValue());
    }
}