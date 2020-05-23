package com.github.smalnote.spring.wke;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class WkeService {

    public Object call(String message) {
        return message;
    }

}
