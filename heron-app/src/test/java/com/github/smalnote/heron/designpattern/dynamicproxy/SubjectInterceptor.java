package com.github.smalnote.heron.designpattern.dynamicproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SubjectInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        HeronComponent annotation = o.getClass().getAnnotation(HeronComponent.class);
        if (annotation == null) {
            return methodProxy.invokeSuper(o, objects);
        } else {
            String value = annotation.value();
            System.out.println(String.format("%s's %s before execute... ", value, method.getName()));
            Object v = methodProxy.invokeSuper(o, objects);
            System.out.println(String.format("%s's %s after executed! ", value, method.getName()));
            return v;
        }
    }
}
