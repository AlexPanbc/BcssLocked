package test.HbaseTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 功能：操作HBase的类
 * Created by liuhuichao on 2016/12/8.
 */
public class HBaseManager extends Thread {
    public Configuration config;
    public HTable table;
    public HBaseAdmin admin;


    /**
     * 构造：配置初始化
     */
    public HBaseManager() {
        config= HBaseConfiguration.create();
        config.set("hbase.master",""); //HMaster地址
        config.set("hbase.zookeeper.property.clientPort",""); //Zookeeper端口设置
        config.set("hbase.zookeeper.quorum","");//Zookeeper队列名称
        try{
            table=new HTable(config, Bytes.toBytes("demo_table_name")); //连接表
            admin=new HBaseAdmin(config);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
