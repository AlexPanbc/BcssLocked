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
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 左补齐
     *
     * @param id
     * @return
     */
    public String padLeft(int id) {
        int strlen = Integer.toString(id).length();
        String str = Integer.toString(id);
        if (strlen < 8)
            for (int i = 0; i < 8 - strlen; i++)
                str = str + '0';
        String sl = Long.toString(Long.MAX_VALUE - new Date().getTime());
        if (sl.length() < 19)
            for (int i = 0; i < 19 - sl.length(); i++)
                sl = sl + '0';
        return str + sl;
    }

    //</editor-fold>

    //<editor-fold desc="插入 数据">

    /**
     * 写入单行单列数据
     *
     * @param tableName 表名
     * @param rowKey    行键
     * @param family    列族
     * @param column    列名
     * @param value     值
     * @return
     * @throws Exception
     */
    public void inst(String tableName, String rowKey, String family, String column, String value) throws Exception {
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
     * 写入多行多列数据
     *
     * @param tableName
     * @param rowsDate
     * @throws Exception
     */
    public void inst(String tableName, List<InsertRowData> rowsDate) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            List<Put> lstp = new ArrayList<>();
            for (InsertRowData ir : rowsDate) {
                Put put = new Put(Bytes.toBytes(ir.getRowKey()));
                for (InsertCellData ic : ir.getColumns())
                    put.add(Bytes.toBytes(ic.getFamily()), Bytes.toBytes(ic.getColumn()), Bytes.toBytes(ic.getValue()));
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
    public List<CellDate> getRow(String tableName, String rowKey) throws Exception {
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
    public List<CellDate> getRow(String tableName, String rowKey, String family) throws Exception {
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
    public List<CellDate> getRow(String tableName, String rowKey, String family, String column) throws Exception {

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
    public List<RowData> getTable(String tableName) {
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
    public List<RowData> getRows(String tableName, String startRow, String stopRow) throws Exception {
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
    public List<RowData> getRows(String tableName, String startRow, int count) throws Exception {
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
    public List<RowData> getRows(String tableName, String startRow, List<HColumn> columns, int count) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Scan scan = new Scan();
            scan.setStartRow(startRow.getBytes());
            scan.setFilter(new PageFilter(count));
            for (HColumn hc : columns)
                scan.addColumn(hc.getFamily().getBytes(), hc.getColumn().getBytes());
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
    public List<RowData> getRows(String tableName, String startRow, String stopRow, List<HColumn> columns) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Scan scan = new Scan();
            for (HColumn hc : columns)
                scan.addColumn(hc.getFamily().getBytes(), hc.getColumn().getBytes());
            scan.setStartRow(startRow.getBytes());
            scan.setStopRow(stopRow.getBytes());
            return getRowData(table.getScanner(scan));
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    /**
     * 根据value like数据
     *
     * @param tableName
     * @param where
     * @return
     */
    public List<RowData> getRowsByValue(String tableName, List<CellDate> where) {
        try {
            HTable table = new HTable(conf, tableName);
            // 参数的格式：columnFamily,columnName,columnValue
            List<Filter> filters = new ArrayList<Filter>();
            for (CellDate cd : where) {
                SingleColumnValueFilter filter = new SingleColumnValueFilter(
                        Bytes.toBytes(cd.getFamily()), Bytes.toBytes(cd.getColumn()),
                        CompareFilter.CompareOp.EQUAL,
                        Bytes.toBytes(cd.getValue()));
                filter.setFilterIfMissing(true);
                filters.add(filter);
            }
            FilterList filterList = new FilterList(filters);
            Scan scan = new Scan();
            scan.setFilter(filterList);
            return getRowData(table.getScanner(scan));
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    /**
     * 根据rowkey like 数据
     *
     * @param tableName
     * @param str
     * @return
     */
    public List<RowData> getRowsByRow(String tableName, String str) {
        try {
            Scan scan = new Scan();
            RegexStringComparator comp = new RegexStringComparator(str);
            RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, comp);
            scan.setFilter(filter);
            scan.setCaching(200);
            scan.setCacheBlocks(false);
            HTable hTable = new HTable(conf, tableName);
            ResultScanner scanner = hTable.getScanner(scan);
            return getRowData(scanner);
        } catch (IOException e) {
            System.out.println(JSONArray.fromObject(e));
        }
        return null;
    }

    //<editor-fold desc="内部通用方法">

    /**
     * ResultScanner 数据转换
     *
     * @param rs
     * @return
     */
    private List<RowData> getRowData(ResultScanner rs) {
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
    private List<CellDate> getCellDate(Result result) {
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

    //</editor-fold>
}
