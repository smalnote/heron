package com.github.smalnote.heron.spring.circulardependency;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cho {

    @Getter
    private Gall gall;

    public Cho() {
        System.out.println("initiating cho...");
    }

    @Autowired
    public void setGall(Gall gall) {
        this.gall = gall;
        System.out.println("cho: when inject dependency gall, the circular dependency cho of the injected gall is " + gall.getCho());
    }

}
