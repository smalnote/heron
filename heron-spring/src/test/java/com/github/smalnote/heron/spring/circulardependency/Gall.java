package com.github.smalnote.heron.spring.circulardependency;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gall {

    @Getter
    private Cho cho;

    public Gall() {
        System.out.println("initiating gall...");
    }

    @Autowired
    public void setCho(Cho cho) {
        this.cho = cho;
        System.out.println("gall: when inject dependency cho, the circular dependency gall of the injected cho is " + cho.getGall());
    }

}
