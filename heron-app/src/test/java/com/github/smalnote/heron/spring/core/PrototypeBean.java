package com.github.smalnote.heron.spring.core;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean extends AbstractLifeCycleBean {
}
