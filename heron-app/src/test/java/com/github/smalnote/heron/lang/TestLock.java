package com.github.smalnote.heron.lang;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    @Test
    public void synchronizedMethodWOStaticShouldUseDifferentObjectMonitor() throws InterruptedException {
        StaticLock staticLock = new StaticLock();
        new Thread(() -> {
            try {
                StaticLock.doStatic();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }).start();
        staticLock.doNonStatic();
        // synchronized static method lock class while non-static synchronized
        // method lock class instance
    }

    @Test
    public void synchronizedShouldBeReentrantLock() {
        StaticLock staticLock = new StaticLock();
        staticLock.doPrint(2);
    }

    @Test
    public void retrantLockShouldBeReentrantable() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        System.out.println("first lock");
        reentrantLock.lock();
        System.out.println("second lock");
        reentrantLock.unlock();
        reentrantLock.unlock();
    }

    @Test
    public void retrantLockWithCondition() {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Condition condition = reentrantLock.newCondition();
    }

}

class StaticLock {

    public static synchronized void doStatic() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(2000);
        System.out.println("static synchronized ");
    }

    public synchronized void doNonStatic() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(300);
        System.out.println("non-static synchronized ");
    }

    public synchronized void doPrint(int n) {
        System.out.println("i am in synchronized ");
        n--;
        if (n > 0) {
            doPrint(n);
        }
    }

}
