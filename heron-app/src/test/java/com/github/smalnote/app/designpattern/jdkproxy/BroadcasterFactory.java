package com.github.smalnote.app.designpattern.jdkproxy;

import java.lang.reflect.Proxy;

public class BroadcasterFactory {

    public static Broadcaster create() {
        Object o = Proxy.newProxyInstance(Subject.class.getClassLoader(), Subject.class.getInterfaces(), new BroadcasterHandler(new Subject()));
        return (Broadcaster) o;
    }

}
