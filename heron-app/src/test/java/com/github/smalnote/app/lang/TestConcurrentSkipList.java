package com.github.smalnote.app.lang;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class TestConcurrentSkipList {

    @Test
    public void shouldBeThreadSafeList() throws ExecutionException, InterruptedException {
        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        LinkedList<Future<?>> futures = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Future<?> submit = executorService.submit(new OperateMapTask(map));
            futures.add(submit);
        }

        for (Future<?> future : futures) {
            future.get();
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(String.format("[%s: %s] ", entry.getKey(), entry.getValue()));
        }

    }

    private static final class OperateMapTask implements Runnable {
        private ConcurrentMap<? super String, ? super String> map;

        public OperateMapTask(ConcurrentMap<? super String, ? super String> map) {
            if (map == null) {
                throw new NullPointerException();
            }
            this.map = map;
        }

        @Override
        public void run() {
            Random r = new Random();
            for (int i = 0; i < 100; i++) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    String s = String.valueOf(c);
                    if (r.nextBoolean()) {
                        map.put(s, s);
                    } else {
                        map.remove(s);
                    }
                }
            }

        }
    }

}
