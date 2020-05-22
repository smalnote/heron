package com.github.smalnote.app.designpattern.dynamicproxy;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HeronComponent {

    String value() default "";

}
