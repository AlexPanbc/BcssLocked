package test.ZooKeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 使用同步API创建一个节点
 * Created by liuhuichao on 2017/7/25.
 */
public class ZooKeeper_Create_API_Sync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore=new CountDownLatch(1);
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper=new ZooKeeper("192.168.99.215:2181",5000,new ZooKeeper_Create_API_Sync_Usage());
        connectedSemaphore.await();
        String path1=zooKeeper.create("/zk-test1","lhc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);//临时结点
        System.out.println(path1+" 创建成功！");

        String path2=zooKeeper.create("/zk-test2","lllhhhhhhhhhhhhhhhhc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(path2+"  创建成功！");

    }
}
