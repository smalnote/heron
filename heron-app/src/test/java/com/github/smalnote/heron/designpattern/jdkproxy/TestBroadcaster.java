package com.github.smalnote.heron.designpattern.jdkproxy;

import org.junit.Test;

public class TestBroadcaster {

    @Test
    public void shouldPrintAspectMessageWhenInvokeProxiedObject() {
        Broadcaster broadcaster = BroadcasterFactory.create();
        String message = broadcaster.broadcast();
        System.out.println(message);
    }

}
