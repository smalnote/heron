package com.github.smalnote.app.lang;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLockCondition {

    @Test
    public void testCondition() {
        Registry registry = new Registry();
        SendTask sendTask = new SendTask(registry);
        PrintTask printTask = new PrintTask(registry);
        sendTask.start();
        printTask.start();
    }

}

class SendTask extends Thread {

    private Registry registry;

    public SendTask(Registry registry) {
        this.registry = registry;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            this.registry.broadcast(String.format("message %d ", i));
        }
    }
}

class PrintTask extends Thread {
    private Registry registry;

    public PrintTask(Registry registry) {
        this.registry = registry;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String message = registry.listenHigh();
            System.out.println(message);
        }
    }
}

class Registry {

    private final ReentrantLock lock;
    private Condition high;
    private volatile boolean hasMessage;
    private volatile String message;

    public Registry() {
        this.lock = new ReentrantLock(true);
        this.high = lock.newCondition();
    }

    public void broadcast(String message) {
        try {
            lock.lock();
            while (hasMessage) {
                high.await();
            }
            hasMessage = true;
            this.message = message;

            high.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

    }

    public String listenHigh() {
        try {
            lock.lock();

            while (!hasMessage) {
                high.await();
            }
            hasMessage = false;
            String tmpMessage = message;
            this.message = null;

            high.signal();
            return tmpMessage;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
        return null;
    }

}
