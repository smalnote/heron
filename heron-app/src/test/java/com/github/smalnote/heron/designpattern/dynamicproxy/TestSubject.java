package com.github.smalnote.heron.designpattern.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class TestSubject {

    ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldSuccessWhenUseSubjectProxy() {
        Subject subject = SubjectFactory.createSubject();
        String result = subject.broadcast();
        assertEquals("enhanced broadcasting", result);
    }

    @Test
    public void shouldNotProxyFinalMethod() {
        Subject subject = SubjectFactory.createSubject();
        String result = subject.finalBroadcast();
        assertEquals("final broadcasting", result);
    }

    @Test
    public void shouldNotProxyStaticMethod() {
        assertEquals("static broadcasting", Subject.staticBroadcast());
    }

    @Test
    public void shouldThrowExceptionWhenProxyFinalClass() {
        thrown.expect(IllegalArgumentException.class);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FinalSubject.class);
        enhancer.setCallback(new SubjectInterceptor());
    }

    @Test
    public void shouldHaveParameterizedProxiedObject() {
        Subject subject = SubjectFactory.createSubject("parameterized ");
        String result = subject.broadcast();
        assertEquals("enhanced parameterized broadcasting", result);
    }

}
