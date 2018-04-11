package test.ZooKeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * 对于ZK的授权访问
 * Created by liuhuichao on 2017/7/27.
 */
public class AutoSample {

    private static  String path="/test-1";
    public static void main(String[] args)throws Exception {
      //  ZooKeeper zk=new ZooKeeper("rc-zkp-datn-rse-nmg-ooz-woasis:2181",5000,null);
       // zk.addAuthInfo("digest","lhc:true".getBytes());
       // zk.create(path,"lhc".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        //zk.create(path+"/node1","lhc".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);

        /**无授权信息访问**/
       // ZooKeeper zk2=new ZooKeeper("rc-zkp-datn-rse-nmg-ooz-woasis:2181",5000,null);
       // byte[] result=zk2.getData(path,false,null);

        /**使用错误信息访问结点**/
        //ZooKeeper zk3=new ZooKeeper("rc-zkp-datn-rse-nmg-ooz-woasis:2181",5000,null);
        //zk3.addAuthInfo("digest","lhc:false".getBytes());
       // zk3.getData(path,false,null);// KeeperErrorCode = NoAuth for /test-1

        /**测试删除节点**/
        ZooKeeper zk=new ZooKeeper("rc-zkp-datn-rse-nmg-ooz-woasis:2181",5000,null);
        zk.addAuthInfo("digest","lhc:true".getBytes());
        zk.delete(path+"/node1",-1);

        ZooKeeper zk1=new  ZooKeeper("rc-zkp-datn-rse-nmg-ooz-woasis:2181",5000,null);
        zk1.delete(path,-1);











    }

}
