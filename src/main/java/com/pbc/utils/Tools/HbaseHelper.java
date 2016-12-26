package com.pbc.utils.Tools;

import com.pbc.utils.exceptions.HbaseModel;
import net.sf.json.JSONArray;
import org.apache.cxf.common.i18n.Exception;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import com.pbc.utils.exceptions.HbaseModel.*;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.lang.reflect.Method;
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

    //<editor-fold desc="生成行建">



    //</editor-fold>

    //<editor-fold desc="插入 数据">

    /**
     * 写入单列数据
     *
     * @param tableName 表名
     * @param rowKey    行键
     * @param family    列族
     * @param column    列名
     * @param value     值
     * @return
     * @throws Exception
     */
    public static void inst(String tableName, String rowKey, String family, String column, String value) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Put put = new Put(Bytes.toBytes(rowKey));
            put.add(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
            table.put(put);
            table.close();
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
    }

    /**
     * 写入多列数据
     *
     * @param tableName
     * @param rowsDate
     * @throws Exception
     */
    public static void inst(String tableName, List<InsertRowData> rowsDate) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            List<Put> lstp = new ArrayList<>();
            for (InsertRowData ir : rowsDate) {
                Put put = new Put(Bytes.toBytes(ir.getRowKey()));
                for (InsertCellData ic : ir.getColumns()) {
                    put.add(Bytes.toBytes(ic.getFamily()), Bytes.toBytes(ic.getColumn()), Bytes.toBytes(ic.getValue()));
                }
                lstp.add(put);
            }
            table.put(lstp);
            table.close();
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
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
    public static List<CellDate> getRow(String tableName, String rowKey) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Result result = table.get(new Get(Bytes.toBytes(rowKey)));
            return getCellDate(result);
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    /**
     * 单条件查询,根据rowkey查询唯一一条记录 返回指定列族数据
     *
     * @param tableName
     * @param rowKey
     * @param family
     * @throws Exception
     */
    public static List<CellDate> getRow(String tableName, String rowKey, String family) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addFamily(Bytes.toBytes(family));
            Result result = table.get(get);
            return getCellDate(result);
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
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
    public static List<CellDate> getRow(String tableName, String rowKey, String family, String column) throws Exception {

        try {
            HTable table = new HTable(conf, tableName);
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
            Result result = table.get(get);
            return getCellDate(result);
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    /**
     * 获取table所有数据
     *
     * @param tableName
     */
    public static List<RowData> getTable(String tableName) {
        try {
            HTable table = new HTable(conf, tableName);
            return getRowData(table.getScanner(new Scan()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询rowkey在startRow和stopRow之间的所有数据
     *
     * @param tableName
     * @param startRow
     * @param stopRow
     * @throws Exception
     */
    public static List<RowData> getRows(String tableName, String startRow, String stopRow) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Scan scan = new Scan();
            scan.setStartRow(startRow.getBytes());
            scan.setStopRow(stopRow.getBytes());
            return getRowData(table.getScanner(scan));
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    /**
     * 查询rowkey在startRow开始往后count条数据
     *
     * @param tableName
     * @param startRow
     * @param count
     * @throws Exception
     */
    public static List<RowData> getRows(String tableName, String startRow, int count) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Scan scan = new Scan();
            scan.setStartRow(startRow.getBytes());
            scan.setFilter(new PageFilter(count));
            ResultScanner rs = table.getScanner(scan);
            return getRowData(rs);
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    /**
     * 查询rowkey在startRow开始往后count条指定列族和列数据
     *
     * @param tableName
     * @param startRow
     * @param count
     * @throws Exception
     */
    public static List<RowData> getRows(String tableName, String startRow, List<HColumn> columns, int count) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Scan scan = new Scan();
            scan.setStartRow(startRow.getBytes());
            scan.setFilter(new PageFilter(count));
            for (HColumn hc : columns) {
                scan.addColumn(hc.getFamily().getBytes(), hc.getColumn().getBytes());
            }
            scan.setStartRow(startRow.getBytes());
            return getRowData(table.getScanner(scan));
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    /**
     * 查询rowkey在startRow和stopRow之间的指定列族和列数据
     *
     * @param tableName
     * @param startRow
     * @param stopRow
     * @throws Exception
     */
    public static List<RowData> getRows(String tableName, String startRow, String stopRow, List<HColumn> columns) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Scan scan = new Scan();
            for (HColumn hc : columns) {
                scan.addColumn(hc.getFamily().getBytes(), hc.getColumn().getBytes());
            }
            scan.setStartRow(startRow.getBytes());
            scan.setStopRow(stopRow.getBytes());
            return getRowData(table.getScanner(scan));
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    /**
     * ResultScanner 数据转换
     *
     * @param rs
     * @return
     */
    private static List<RowData> getRowData(ResultScanner rs) {
        List<RowData> lstRd = new ArrayList<>();
        for (Result r : rs) {
            RowData rd = new HbaseModel().new RowData();
            rd.setRowKey(new String(r.getRow()));
            rd.setRowValue(getCellDate(r));
            lstRd.add(rd);
        }
        return lstRd;
    }

    /**
     * CellDate数据转换
     *
     * @param result
     * @return
     */
    private static List<CellDate> getCellDate(Result result) {
        List<HbaseModel.CellDate> cellDateList = new ArrayList<>();
        for (Cell value : result.listCells()) {
            HbaseModel.CellDate cellDate = new HbaseModel().new CellDate();
            cellDate.setFamily(Bytes.toString(value.getFamily()));//所属列族名称
            cellDate.setColumn(Bytes.toString(value.getQualifier()));//列名称
            cellDate.setValue(Bytes.toString(value.getValue()));//存储的值
            cellDate.setTimestamp(value.getTimestamp());//获取时间戳
            cellDateList.add(cellDate);
        }
        return cellDateList;
    }

    //</editor-fold>

    //</editor-fold>
}
