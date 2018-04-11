package test.ZooKeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 测试创建Zk会话
 * Created by liuhuichao on 2017/7/25.
 */
public class ZooKeeper_Constructor_Usage_Simple implements Watcher {
    private static CountDownLatch connectedSemaphore=new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zk=new ZooKeeper("192.168.99.215:2181",5000,new ZooKeeper_Constructor_Usage_Simple());
        System.out.println(zk.getState());
        connectedSemaphore.await();
        System.out.println("zk session established");
    }

    /**
     * 处理来自ZK服务端的watcher通知
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event:"+watchedEvent);
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            connectedSemaphore.countDown();//解除等待阻塞
        }

    }
}
