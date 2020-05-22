package com.github.smalnote.app.designpattern.dynamicproxy;

@HeronComponent("corona")
public class Subject {

    private String prefix;

    public Subject() {
        this.prefix = "";
    }

    public Subject(String prefix) {
        if (prefix == null)
            throw new NullPointerException();
        this.prefix = prefix;
    }

    public static String staticBroadcast() {
        return "static broadcasting";
    }

    public String broadcast() {
        return prefix + "broadcasting";
    }

    public final String finalBroadcast() {
        return prefix + "final broadcasting";
    }

}
