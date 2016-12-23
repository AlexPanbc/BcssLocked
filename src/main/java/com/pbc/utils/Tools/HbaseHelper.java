package com.pbc.utils.Tools;

import com.pbc.utils.exceptions.HbaseModel;
import net.sf.json.JSONArray;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import com.pbc.utils.exceptions.HbaseModel.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

/**
 * Created by panbingcan on 2016/12/23.
 */
public class HbaseHelper {
    public static Configuration configuration;

    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hbase-centos");
    }
    //<editor-fold desc="HBase Helper">

    /**
     * 单条件查询,根据rowkey查询唯一一条记录
     *
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public static List<CellDate> GetRow(String tableName, String rowKey) throws Exception {
        List<CellDate> cellDateList = null;
        try {
            HTable table = new HTable(configuration, tableName);
            Result result = table.get(new Get(Bytes.toBytes(rowKey)));
            for (KeyValue value : result.raw()) {
                CellDate cellDate = null;
                cellDate.setFamily(Bytes.toString(value.getFamily()));//所属列族名称
                cellDate.setColumn(Bytes.toString(value.getQualifier()));//列名称
                cellDate.setValue(Bytes.toString(value.getValue()));//存储的值
                cellDate.setTimestamp(value.getTimestamp());//获取时间戳
                cellDateList.add(cellDate);
            }
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return cellDateList;
    }
    //</editor-fold>
}
