package test.ZooKeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *
 * 同步/异步获取数据
 * Created by liuhuichao on 2017/7/27.
 */
public class GetData_API_Sync_Usage  implements Watcher{

    private static CountDownLatch conntedSamphore=new CountDownLatch(1);
    private static ZooKeeper zk=null;
    private static Stat stat=new Stat();
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            conntedSamphore.countDown();
        }else if(watchedEvent.getType()== Event.EventType.NodeCreated){
            System.out.println("node changed:"+watchedEvent.getPath());
        }

    }

    public static void main(String[] args) throws Exception{
        String path="/test-1";
       zk =new ZooKeeper("rc-zkp-datn-rse-nmg-ooz-woasis:2181",5000,new GetData_API_Sync_Usage());
        conntedSamphore.await();
        System.out.println("zk-19 连接成功！");
        //zk.create(path+"/lhc", "".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        List<String> children=zk.getChildren(path,new GetData_API_Sync_Usage());
        System.out.println("children node:"+children);
        //zk.setData(path+"/lhc","lllhc".getBytes(),-1);//同步设置数据
        zk.setData(path+"/lhc","lhc".getBytes(),-1,new IStatCallback(),null);
        zk.getData(path+"/lhc",true,new IDataCallback(),null);//异步获取数据
        Thread.sleep(Integer.MAX_VALUE);
    }
}
