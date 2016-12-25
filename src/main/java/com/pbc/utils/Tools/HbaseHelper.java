package com.pbc.utils.Tools;

import com.mysql.jdbc.RowData;
import com.pbc.utils.exceptions.HbaseModel;
import net.sf.json.JSONArray;
import org.apache.cxf.common.i18n.Exception;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import com.pbc.utils.exceptions.HbaseModel.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panbingcan on 2016/12/23.
 */
public class HbaseHelper {
    public static Configuration conf;

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hbase-centos");
    }
    //<editor-fold desc="HBase Helper">

    /**
     * 单条件查询,根据rowkey查询唯一一条记录 返回rowkey对应的所有列族下的所有列数据
     *
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public static List<CellDate> GetRow(String tableName, String rowKey) throws Exception {
        List<HbaseModel.CellDate> cellDateList = new ArrayList<>();
        try {
            HTable table = new HTable(conf, tableName);
            Result result = table.get(new Get(Bytes.toBytes(rowKey)));
            for (KeyValue value : result.raw()) {
                HbaseModel.CellDate cellDate = new HbaseModel().new CellDate();
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

    /**
     * 单条件查询,根据rowkey查询唯一一条记录 返回指定列族数据
     *
     * @param tableName
     * @param rowKey
     * @param family
     * @throws Exception
     */
    public static List<CellDate> GetRow(String tableName, String rowKey, String family) throws Exception {
        List<HbaseModel.CellDate> cellDateList = new ArrayList<>();
        try {
            HTable table = new HTable(conf, tableName);
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addFamily(Bytes.toBytes(family));
            Result result = table.get(get);
            for (KeyValue value : result.raw()) {
                HbaseModel.CellDate cellDate = new HbaseModel().new CellDate();
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

    /**
     * 单条件查询,根据rowkey查询唯一一条记录 返回指定列族的列数据
     *
     * @param tableName
     * @param rowKey
     * @param family
     * @param column
     * @throws Exception
     */
    public static List<CellDate> GetRow(String tableName, String rowKey, String family, String column) throws Exception {
        List<HbaseModel.CellDate> cellDateList = new ArrayList<>();
        try {
            HTable table = new HTable(conf, tableName);
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
            Result result = table.get(get);
            for (KeyValue value : result.raw()) {
                HbaseModel.CellDate cellDate = new HbaseModel().new CellDate();
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
