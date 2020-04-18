package com.github.smalnote.heron.designpattern.dynamicproxy;

import org.junit.jupiter.api.Test;

public class TestSubject {

    @Test
    public void shouldSuccessWhenUseSubjectProxy() {
        Subject subject = SubjectFactory.createSubject();
        subject.broadcast();
    }

}
