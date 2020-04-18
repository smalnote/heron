package com.github.smalnote.heron.designpattern.dynamicproxy;

@HeronComponent("corona")
public class Subject {

    public void broadcast() {
        System.out.println("broadcasting... ");
    }

}
