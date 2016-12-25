package com.pbc.utils.Tools;

import com.pbc.utils.exceptions.HbaseModel;
import net.sf.json.JSONArray;
import org.apache.cxf.common.i18n.Exception;
import org.apache.directory.api.util.ByteBuffer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import com.pbc.utils.exceptions.HbaseModel.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
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

    //<editor-fold desc="插入 数据">

    /// <summary>
    /// 写入数据
    /// </summary>
    /// <param name="tablename">表名</param>
    /// <param name="rowkey">行键</param>
    /// <param name="family"></param>
    /// <param name="columnname">列名</param>
    /// <param name="value">值</param>

    /**
     * 写入数据
     * @param tableName 表名
     * @param rowKey 行键
     * @param family 列族
     * @param column 列名
     * @param value 值
     * @return
     * @throws Exception
     */
    public static boolean Inst(String tableName, String rowKey, String family, String column, Object value) throws Exception {

        try {
            if(value instanceof String){
                HTable table = new HTable(conf, tableName);
                Put put = new Put(Bytes.toBytes(rowKey));
              /*  ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(value);
                oos.flush();*/
                //类型判断：目前只支持9中类型
                if(value instanceof String){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((String)value));//Bytes.toBytes(JSONArray.fromObject(value)
                }else if(value instanceof java.nio.ByteBuffer){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((java.nio.ByteBuffer)value));//Bytes.toBytes(JSONArray.fromObject(value).toString())
                }else if(value instanceof Boolean){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((Boolean) value));//Bytes.toBytes(JSONArray.fromObject(value)
                }else if(value instanceof Long){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((Long) value));//Bytes.toBytes(JSONArray.fromObject(value)
                }else if(value instanceof Float){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((Long) value));//Bytes.toBytes(JSONArray.fromObject(value)
                }else if(value instanceof Double){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((Double) value));//Bytes.toBytes(JSONArray.fromObject(value)
                }else if(value instanceof  Integer){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((Integer) value));//Bytes.toBytes(JSONArray.fromObject(value)
                }else if(value instanceof  Short){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((Short) value));//Bytes.toBytes(JSONArray.fromObject(value)
                }else if(value instanceof BigDecimal){
                    put.add(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes((BigDecimal) value));//Bytes.toBytes(JSONArray.fromObject(value)
                }
                table.put(put);
                table.close();
               /* oos.close();
                bos.close();*/
            }
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
            return false;
        }
        return true;
//        _client.Value.put(Encoding.UTF8.GetBytes(tablename), new TPut
//        {
//            Row = Encoding.UTF8.GetBytes(rowkey),
//                    ColumnValues = new List<TColumnValue>
//            {
//                new TColumnValue
//                {
//                    Family = Encoding.UTF8.GetBytes(family),
//                            Qualifier = Encoding.UTF8.GetBytes(columnname),
//                            Value = Serializer(value)
//                }
//            }
//        });
    }

    //</editor-fold>

    //<editor-fold desc="根据rowkey 获取表 列族 列">

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

    //</editor-fold>
}
