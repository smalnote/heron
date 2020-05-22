package com.github.smalnote.app.designpattern.jdkproxy;

public class Subject implements Broadcaster {
    @Override
    public String broadcast() {
        return "this is a broadcasting message";
    }
}
