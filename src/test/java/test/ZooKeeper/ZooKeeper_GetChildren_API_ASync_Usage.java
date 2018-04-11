package test.ZooKeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 异步获取结点
 * Created by liuhuichao on 2017/7/26.
 */
public class ZooKeeper_GetChildren_API_ASync_Usage implements Watcher {

    private static CountDownLatch connectedSemphore=new CountDownLatch(1);
    private static ZooKeeper zk=null;

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            connectedSemphore.countDown();
        }else if(watchedEvent.getType()== Event.EventType.NodeChildrenChanged){
            try {
                System.out.println("node changed===="+zk.getChildren(watchedEvent.getPath(),true));
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) throws Exception{
        String path="/zk-test-1";
        zk=new ZooKeeper("lhc-centos0:2181",5000,new ZooKeeper_GetChildren_API_ASync_Usage());
        connectedSemphore.await();
        zk.create(path+"/test3","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.getChildren(path,true,new ICChild2Callback(),null);
        Thread.sleep(Integer.MAX_VALUE);
    }


}
