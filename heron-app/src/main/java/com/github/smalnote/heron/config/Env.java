package com.github.smalnote.heron.config;

public enum  Env {

    DEV("DEV"),
    ST("ST"),
    UAT("UAT"),
    PRD("PRD"),
    ;

    private String env;
    Env(String env) {
        this.env = env;
    }
}
