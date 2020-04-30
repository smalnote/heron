package com.github.smalnote.heron.lang;

import org.junit.Test;

public class TestMultiThread {

    @Test
    public void shouldBeRunningWhenBeNotifiedAfterWait() {
        Subject subject = new Subject();
        for (int i = 0; i < 5; i++) {
            new Thread(new Receiver(subject)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Sender(subject)).start();
        }
    }

}

class Sender implements Runnable {

    private Subject subject;

    Sender(Subject subject) {
        this.subject = subject;
    }


    @Override
    public void run() {
        System.out.println(String.format("%d: i am about to send message ", Thread.currentThread().getId()));
        subject.boardcast(String.format("message from %d ", Thread.currentThread().getId()));
    }
}

class Receiver implements Runnable {
    private Subject subject;

    Receiver(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void run() {
        String message = subject.listen();
        System.out.println(String.format("%d received: %s ", Thread.currentThread().getId(), message));
    }
}

class Subject {

    private String message;
    private boolean listened = false;

    public synchronized String listen() {
        while (!listened) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String tmpMessage = message;
        listened = false;
        message = null;
        return tmpMessage;
    }

    public synchronized void boardcast(String message) {
        while (listened) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.message = message;
        this.listened = true;
        notify();
    }

}
