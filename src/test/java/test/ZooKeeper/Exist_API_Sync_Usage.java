package test.ZooKeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * 检测zk node
 * Created by liuhuichao on 2017/7/27.
 */
public class Exist_API_Sync_Usage implements Watcher{
    private static CountDownLatch connetedSamphore=new CountDownLatch(1);
    private static ZooKeeper zk=null;

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            connetedSamphore.countDown();
        }else if(Event.EventType.NodeCreated==watchedEvent.getType()){
            System.out.println("node created=="+watchedEvent.getPath());
        }else if(Event.EventType.NodeDataChanged==watchedEvent.getType()){
            System.out.println("node changed=="+watchedEvent.getPath());
        }else if(Event.EventType.NodeDeleted==watchedEvent.getType()){
            System.out.println("node deleted=="+watchedEvent.getPath());
        }
    }

    public static void main(String[] args)throws Exception {
        String path="/test-1";
        zk =new ZooKeeper("rc-zkp-datn-rse-nmg-ooz-woasis:2181",5000,new Exist_API_Sync_Usage());
        connetedSamphore.await();
        System.out.println("zk-19 连接成功！");
        Stat stat=zk.exists(path,new Exist_API_Sync_Usage());
        System.out.println("stat="+stat==null?"为空":"不为空");
        zk.setData(path,"".getBytes(),-1);
        Thread.sleep(Integer.MAX_VALUE);

    }
}
