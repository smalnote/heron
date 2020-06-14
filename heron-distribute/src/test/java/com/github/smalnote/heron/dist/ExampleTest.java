package com.github.smalnote.heron.dist;

import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void testKeep() {
    }

    @Test
    public void usingZooKeeper() {
        CreateMode[] values = CreateMode.values();
        assertEquals(7, values.length);
    }

}
