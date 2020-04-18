package com.github.smalnote.heron.designpattern.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;

public class SubjectFactory {

    private SubjectFactory() {
    }

    public static Subject createSubject() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(new SubjectInterceptor());
        return (Subject) enhancer.create();
    }

}
