package com.github.smalnote.heron.designpattern.dynamicproxy;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HeronComponent {

    String value() default "";

}
