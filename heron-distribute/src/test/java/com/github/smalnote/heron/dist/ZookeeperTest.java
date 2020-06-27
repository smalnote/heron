package com.github.smalnote.heron.dist;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class ZookeeperTest {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperTest.class);

    @Test
    public void testKeep() {
    }

    @Test
    public void usingZooKeeper() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new RetryOneTime(1000));
        client.start();
        Subscriber subscriber = new Subscriber(client, "/smalnode", new ConsoleDataListener());
        subscriber.subscribe();
        new CountDownLatch(1).await();
    }

    @Test
    public void subscribe() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new RetryOneTime(1000));
        client.start();
        NodeCache nodeCache = new NodeCache(client, "/smalnode");
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                byte[] bytes = client.getData().watched().forPath("/smalnode");
                logger.debug("/smalnode data changed: {}", new String(bytes));
            }
        });
        nodeCache.start();
        new CountDownLatch(1).await();
    }

    private static final class Subscriber implements CuratorWatcher {

        private CuratorFramework client;
        private String path;
        private DataListener listener;

        public Subscriber(CuratorFramework client, String path, DataListener listener) {
            this.client = client;
            this.path = path;
            this.listener = listener;
        }

        @Override
        public void process(WatchedEvent event) throws Exception {
            if (event.getType() == Watcher.Event.EventType.NodeDataChanged) {
                byte[] bytes = client.getData().watched().forPath(path);
                listener.dataChanged(bytes);
            }
            if (event.getType() != Watcher.Event.EventType.NodeDeleted && event.getType() != Watcher.Event.EventType.None) {
                client.getData().usingWatcher(this).forPath(path);
            }
        }

        public void subscribe() throws Exception {
            client.getData().usingWatcher(this).forPath(path);
        }
    }

}
