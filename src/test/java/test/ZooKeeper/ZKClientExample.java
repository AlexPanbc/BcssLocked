package test.ZooKeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * zkclient example
 * Created by liuhuichao on 2017/7/28.
 */
public class ZKClientExample {

    public static void main(String[] args) {
        ZkClient zkClient=new ZkClient("lhc-centos0:2181",5000);
        System.out.println("-------zk connected-------");


    }
}
