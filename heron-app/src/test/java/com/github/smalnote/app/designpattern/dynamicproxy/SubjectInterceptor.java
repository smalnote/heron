package com.github.smalnote.app.designpattern.dynamicproxy;

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
            Object v = methodProxy.invokeSuper(o, objects);
            if (v instanceof String) {
                String vv = (String) v;
                v = "enhanced " + vv;
            }
            return v;
        }
    }
}
