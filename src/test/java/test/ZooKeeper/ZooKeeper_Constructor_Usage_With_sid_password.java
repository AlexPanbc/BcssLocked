package test.ZooKeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 复用sessionId和sessionPassword的会话
 * Created by liuhuichao on 2017/7/25.
 */
public class ZooKeeper_Constructor_Usage_With_sid_password implements Watcher {

    private static CountDownLatch connectedSemaphore=new CountDownLatch(1);
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event:"+watchedEvent);
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            connectedSemaphore.countDown();
        }


    }

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper=new ZooKeeper("lhc-centos0:2181",5000,new ZooKeeper_Constructor_Usage_With_sid_password());
        connectedSemaphore.await();
        long sessionId=zooKeeper.getSessionId();
        byte[] password=zooKeeper.getSessionPasswd();

        /**使用错误的sessionID跟sessionPwd连连接测试[192.168.99.215 lhc-centos0]**/
        ZooKeeper zkWrong=new ZooKeeper("lhc-centos0:2181",5000,new ZooKeeper_Constructor_Usage_With_sid_password(),1l,"lhc".getBytes());
        /**使用正确的来进行连接**/
        ZooKeeper zkTrue=new ZooKeeper("lhc-centos0:2181",5000,new ZooKeeper_Constructor_Usage_With_sid_password(),sessionId,password);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
