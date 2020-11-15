package com.github.smalnote.heron.dist;


import org.apache.zookeeper.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistServer {
    private static final String connectString = "127.0.0.1:2181";
    private static final int sessionTimeout = 2000;
    private static final String parentNode = "/servers";

    private ZooKeeper zk = null;

    /**
     * 创建到zk的客户端连接
     */
    public void getConnect() throws Exception {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(event.getType() + "---" + event.getPath());
                try {
                    zk.getChildren("/", true);
                } catch (Exception e) {
                    System.err.println("failed to connect zookeeper cluster\n" + e.toString());
                }
            }
        });

    }

    /**
     * 向zk集群注册服务器信息
     */
    public void registerServer(String hostname) throws Exception {
        String create = zk.create(parentNode + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + "is online.." + create);
    }

    /**
     * 业务功能
     */
    public void handleBussiness(String hostname) throws InterruptedException {
        System.out.println(hostname + "start working.....");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {

        // 获取zk连接
        DistServer server = new DistServer();
        server.getConnect();

        // 利用zk连接注册服务器信息
        server.registerServer(args[0]);

        // 启动业务功能
        server.handleBussiness(args[0]);
    }
}
