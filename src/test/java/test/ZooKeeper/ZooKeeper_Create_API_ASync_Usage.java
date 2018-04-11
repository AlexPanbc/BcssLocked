package test.ZooKeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 使用异步API创建一个节点
 * Created by liuhuichao on 2017/7/25.
 */
public class ZooKeeper_Create_API_ASync_Usage implements Watcher{
    private static CountDownLatch connectedSamphore=new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getState()== Event.KeeperState.SyncConnected){
            connectedSamphore.countDown();
        }
    }

    public static void main(String[] args) throws Exception{
        ZooKeeper zk1=new ZooKeeper("lhc-centos0:2181",5000,new ZooKeeper_Create_API_ASync_Usage());
        connectedSamphore.await();
        zk1.create("/zk-test-1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT,new IStringCallBack(),"i am a context");
        zk1.create("/zk-test-2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT,new IStringCallBack(),"i am a context");
        zk1.create("/zk-test-3","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL,new IStringCallBack(),"i am a context");
        Thread.sleep(Integer.MAX_VALUE);
    }



}


