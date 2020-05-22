package com.github.smalnote.app.designpattern.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;

public class SubjectFactory {
    private static final Enhancer enhancer = new Enhancer();

    static {
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(new SubjectInterceptor());
    }

    private SubjectFactory() {
    }

    public static Subject createSubject() {
        return (Subject) enhancer.create();
    }

    public static Subject createSubject(String prefix) {
        return (Subject) enhancer.create(new Class[]{String.class}, new Object[]{prefix});
    }

}
