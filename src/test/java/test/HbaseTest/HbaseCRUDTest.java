package test.HbaseTest;


import net.sf.json.JSONArray;
import com.alibaba.druid.sql.visitor.functions.Char;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 功能：测试Hbase基本的增删改查操作
 * Created by liuhuichao on 2016/12/5.
 */
public class HbaseCRUDTest {
    public static Configuration configuration;


    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hbase-centos");
    }

    /**
     * 测试创建student表:测试已通过
     *
     * @throws IOException
     */
    @Test
    public void createTable() throws IOException {
        HBaseAdmin admin = new HBaseAdmin(configuration); //HBaseAdmin负责管理HBase集群，添加和丢弃表
        if (admin.tableExists("studentInfo")) {
            System.out.println("student表已经存在");
            return;
        }
        HTableDescriptor descriptor = new HTableDescriptor("studentInfo");
        descriptor.addFamily(new HColumnDescriptor("Name"));//创建列族，名字是Name
        descriptor.addFamily(new HColumnDescriptor("Address"));//创建列族，名字是Address
        admin.createTable(descriptor); //创建表
        System.out.println("student表创建成功！！！");
    }


    /**
     * 功能：想hbase中插入一行记录 --测试已通过
     *
     * @throws IOException
     */
    @Test
    public void insertHbaseStudentTable() throws IOException {
        HTable table = new HTable(configuration, Bytes.toBytes("studentInfo"));
        Put put = new Put(Bytes.toBytes("1"));
        put.addColumn(Bytes.toBytes("Name"), Bytes.toBytes("firstName"), Bytes.toBytes("liu"));
        put.addColumn(Bytes.toBytes("Name"), Bytes.toBytes("secondName"), Bytes.toBytes("huichao"));
        put.addColumn(Bytes.toBytes("Address"), Bytes.toBytes("province"), Bytes.toBytes("hebei"));
        put.addColumn(Bytes.toBytes("Address"), Bytes.toBytes("city"), Bytes.toBytes("baoding"));
        put.addColumn(Bytes.toBytes("Address"), Bytes.toBytes("area"), Bytes.toBytes("qingyuan"));
        table.put(put);
    }

    /**
     * 功能：根据行健获取数据
     *
     * @throws IOException
     */
    @Test
    public void getDataByRowKey() throws IOException {
        HTable table = new HTable(configuration, Bytes.toBytes("studentInfo"));
        Get get = new Get(Bytes.toBytes("1"));
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.println("family:" + Bytes.toString(kv.getFamilyArray()));//所属列族名称
            System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));//列名称
            System.out.println("value:" + Bytes.toString(kv.getValue()));//存储的值
            System.out.println("Timestamp:" + kv.getTimestamp());//获取时间戳
        }
    }


    /**
     * 功能：测试全表扫描
     *
     * @throws IOException
     */
    @Test
    public void selectHBaseScan() throws IOException {
        HTable table = new HTable(configuration, Bytes.toBytes("studentInfo"));
        /*遍历查询*/
        Scan scan = new Scan();
        ResultScanner rs = null;
        try {
            rs = table.getScanner(scan);
            for (Result result : rs) {
                for (KeyValue kv : result.list()) {
                    System.out.println("family:" + Bytes.toString(kv.getFamilyArray()));//所属列族名称
                    System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));//列名称
                    System.out.println("value:" + Bytes.toString(kv.getValue()));//存储的值
                    System.out.println("Timestamp:" + kv.getTimestamp());//获取时间戳
                }
            }
        } finally {
            rs.close();
        }
    }

    /**
     * 更新
     *
     * @throws Exception
     */
    @Test
    public void updateHBase() throws Exception {
        HTable table = new HTable(configuration, Bytes.toBytes("studentInfo"));
        Put put = new Put(Bytes.toBytes("1")); //设置行健
        put.add(Bytes.toBytes("Address"), Bytes.toBytes("city"), Bytes.toBytes("beijing"));///更新的时候找对族名和列名，再给定新的value值就可以了
        table.put(put);
    }

    /**
     * 功能：查询nickname的多个(本示例为2个)版本值.
     *
     * @throws Exception
     */
    @Test
    public void selectSomeVersion() throws Exception {
        HTable table = new HTable(configuration, Bytes.toBytes("studentInfo"));
        Get get = new Get(Bytes.toBytes("1"));
        get.addColumn(Bytes.toBytes("Address"), Bytes.toBytes("city"));
        // get.setMaxVersions(3);
        List<KeyValue> results = table.get(get).list();
        int total = results.size();
        System.out.println("Address列族中city列的各个版本值");
        for (int i = 0; i < total; i++) {
            System.out.println(Bytes.toString(results.get(i).getValue()));
        }
    }

    /**
     * 功能：删除指定的某一行
     *
     * @throws Exception
     */
    @Test
    public void deleteColumn() throws Exception {
        HTable table = new HTable(configuration, Bytes.toBytes("studentInfo"));//HTabel负责跟记录相关的操作如增删改查等
        Delete deleteAll = new Delete(Bytes.toBytes("1"));
        table.delete(deleteAll);
    }

    /**
     * 功能：删除表
     *
     * @throws Exception
     */
    @Test
    public void deleteTable() throws Exception {
        HBaseAdmin admin = new HBaseAdmin(configuration); //HBaseAdmin负责管理HBase集群，添加和丢弃表
        admin.disableTable("student");
        admin.deleteTable("student");
    }


}
