package com.github.smalnote.heron.designpattern.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BroadcasterHandler implements InvocationHandler {

    private Broadcaster broadcaster;

    public BroadcasterHandler(Broadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before handle... ");
        Object o = method.invoke(broadcaster, args);
        System.out.println("after handled! ");
        return o;
    }
}
