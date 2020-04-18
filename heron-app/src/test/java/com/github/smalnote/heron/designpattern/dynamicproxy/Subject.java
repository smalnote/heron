package com.github.smalnote.heron.designpattern.dynamicproxy;

@HeronComponent("corona")
public class Subject {

    public void boardcast() {
        System.out.println("boardcasting... ");
    }

}
