package com.github.smalnote.heron.lang;

public class TestThreadExecute {

    public static void main(String[] args) {

        System.out.println("Main thread started");
        new Thread(() -> {
            System.out.println("Second thread started");
            try {
                Thread.sleep(2000);  // wait two seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println("Second thread (almost) finished");
        }).start();
        System.out.println("Main thread (almost) finished");
    }
}
