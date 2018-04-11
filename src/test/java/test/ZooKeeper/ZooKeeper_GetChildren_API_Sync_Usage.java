package test.ZooKeeper;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *获取结点-同步
 * Created by liuhuichao on 2017/7/26.
 */
public class ZooKeeper_GetChildren_API_Sync_Usage implements Watcher {
    private  static CountDownLatch conntedSamphore=new CountDownLatch(1);
    private static ZooKeeper zooKeeper=null;

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            conntedSamphore.countDown();
        }else if(watchedEvent.getType()== Event.EventType.NodeChildrenChanged){
            try {
                System.out.println("--------------------------------------reget children:"+zooKeeper.getChildren(watchedEvent.getPath(),true));
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) throws Exception{
        String path="/zk-test-1";
        zooKeeper=new ZooKeeper("lhc-centos0:2181",5000,new ZooKeeper_GetChildren_API_Sync_Usage());
        conntedSamphore.await();
        zooKeeper.create(path+"/test1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        List<String> childrenList=zooKeeper.getChildren(path,true);
        System.out.println(childrenList);
        zooKeeper.create(path+"/test2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }

}

