package com.github.smalnote.app.lang;

public class TestEmpty {

    public void test() {
        ThreadLocal<String> s = ThreadLocal.withInitial(() -> "A");
        s.set("B");
    }

}
