package test.ZooKeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 删除zk的持久结点
 * Created by liuhuichao on 2017/7/26.
 */
public class ZooKeeperDeleteNode implements Watcher {

    private  static CountDownLatch conntedSamphore=new CountDownLatch(1);
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            conntedSamphore.countDown();
        }

    }

    public static void main(String[] args) throws Exception{
        /**同步删除节点**/
        ZooKeeper zooKeeper=new ZooKeeper("lhc-centos0:2181",5000,new ZooKeeperDeleteNode());
        conntedSamphore.await();
        zooKeeper.delete("/zk-test-30000000014",0);
    }


}
