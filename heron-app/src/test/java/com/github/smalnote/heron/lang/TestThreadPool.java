package com.github.smalnote.heron.lang;

import org.junit.Test;

import java.util.concurrent.*;

public class TestThreadPool {

    @Test
    public void shouldWaitUntilConsumerIsAvailable() {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);
        Executor executor = new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, queue,
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 20; i++) {
            executor.execute(new SleepTask(i, 2000));
        }
    }

}

class SleepTask implements Runnable {

    private int sleepMs;
    private int id;

    public SleepTask(int id, int sleepMs) {
        this.sleepMs = sleepMs;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println(String.format("%d: start... ", id));
            TimeUnit.MILLISECONDS.sleep(sleepMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
