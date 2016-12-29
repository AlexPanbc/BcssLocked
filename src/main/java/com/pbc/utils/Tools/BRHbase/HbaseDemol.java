

package com.pbc.utils.Tools.BRHbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;


/**
 * Created by panbingcan on 2016/12/29.
 */
public class HbaseDemol {


    private static Configuration configuration;
    private static Connection connection;

    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hbase-centos");
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//        configuration.set("hbase.zookeeper.quorum", "hostIp");
        //configuration.set("hbase.master", "<span style="font-family: Arial, Helvetica, sans-serif;">hostIp</span><span style="font-family: Arial, Helvetica, sans-serif;">:8020");</span>

        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     *
     * @param tableName
     * @param familyColumnName
     */
    public static void createTable(String name, List<String> familyColumnName) {

        try {

            TableName tableName = TableName.valueOf(name);
            Admin hAdmin = connection.getAdmin();

            HTableDescriptor descripter = new HTableDescriptor(tableName);
            for (String familyName : familyColumnName) {
                descripter.addFamily(new HColumnDescriptor(familyName));
            }

            if (hAdmin.tableExists(tableName)) {
                hAdmin.disableTable(tableName);
                hAdmin.deleteTable(tableName);
                System.out.println(tableName + "is exists...");
            }

            hAdmin.createTable(descripter);

            hAdmin.close();

        } catch (MasterNotRunningException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 往表里面添加数据
     *
     * @param tableName
     * @param rowkey
     * @param columnValues
     * @return
     */
    public static int addDataForTable(String name, String rowkey, Map<String, Map<String, String>> columnValues) {

        try {
            Put put = new Put(Bytes.toBytes(rowkey));
            TableName tableName = TableName.valueOf(name);

            Table htable = connection.getTable(tableName);

            HColumnDescriptor[] columnFamilies = htable.getTableDescriptor().getColumnFamilies();// 获取所有的列名

            for (HColumnDescriptor hColumnDescriptor : columnFamilies) {
                String familyName = hColumnDescriptor.getNameAsString();
                Map<String, String> columnNameValueMap = columnValues
                        .get(familyName);

                if (columnNameValueMap != null) {
                    for (String columnName : columnNameValueMap.keySet()) {
                        put.addColumn(Bytes.toBytes(familyName), Bytes
                                .toBytes(columnName), Bytes
                                .toBytes(columnNameValueMap.get(columnName)));
                    }
                }
            }

            htable.put(put);
            htable.close();

            return put.size();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 批量添加数据
     *
     * @param list
     */
    public static void insertDataList(List<HbaseDataEntity> list) {
        List<Put> puts = new ArrayList<Put>();
        Table table = null;
        Put put;
        try {
            for (HbaseDataEntity entity : list) {
                TableName tableName = TableName.valueOf(entity.getTableName());
                table = connection.getTable(tableName);

                put = new Put(entity.getMobileKey().getBytes());// 一个PUT代表一行数据，再NEW一个PUT表示第二行数据,每行一个唯一的ROWKEY
                for (String columnfamily : entity.getColumns().keySet()) {
                    for (String column : entity.getColumns().get(columnfamily)
                            .keySet()) {
                        put.addColumn(
                                columnfamily.getBytes(),
                                column.getBytes(),
                                entity.getColumns().get(columnfamily)
                                        .get(column).getBytes());
                    }
                }
                puts.add(put);
            }
            table.put(puts);
            table.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 更新表中的一列
     *
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param columnName
     * @param value
     */
    public static void updateTable(String name, String rowKey,
                                   String familyName, String columnName, String value) {
        try {
            TableName tableName = TableName.valueOf(name);
            Table table = connection.getTable(tableName);

            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName), Bytes.toBytes(value));

            table.put(put);
            table.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 批量删除数据
     *
     * @param list
     */
    public static void deleteDataList(List<HbaseDataEntity> list) {
        Table table = null;
        List<Delete> deletes = new ArrayList<Delete>();
        try {
            for (HbaseDataEntity entity : list) {

                TableName tableName = TableName.valueOf(entity.getTableName());
                table = connection.getTable(tableName);

                Delete delete = new Delete(Bytes.toBytes(entity.getMobileKey()));
                for (String columnfamily : entity.getColumns().keySet()) {
                    for (String column : entity.getColumns().get(columnfamily)
                            .keySet()) {
                        delete.addColumn(columnfamily.getBytes(),
                                column.getBytes());
                    }
                }

                deletes.add(delete);
            }

            table.delete(deletes);
            table.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 删除指定的列
     *
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param columnName
     */
    public static void deleteColumn(String name, String rowKey, String familyName, String columnName) {

        try {

            TableName tableName = TableName.valueOf(name);
            Table table = connection.getTable(tableName);

            Delete delete = new Delete(Bytes.toBytes(rowKey));
            delete.addColumn(Bytes.toBytes(familyName),
                    Bytes.toBytes(columnName));

            table.delete(delete);
            System.out.println(familyName + ":" + columnName + "is delete");

            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有列
     *
     * @param tableName
     * @param rowKey
     */
    public static void deleteAllColumns(String name, String rowKey) {

        try {

            TableName tableName = TableName.valueOf(name);
            Table table = connection.getTable(tableName);

            Delete delete = new Delete(Bytes.toBytes(rowKey));

            table.delete(delete);

            System.out.println("all columns are deleted!");

            table.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的数据
     *
     * @param name
     * @param size
     * @return
     */
    public static List<HbaseDataEntity> getResultScans(String name, int size) {

        Scan scan = new Scan();
        ResultScanner resultScanner = null;
        List<HbaseDataEntity> list = new ArrayList<HbaseDataEntity>();

        try {

            TableName tableName = TableName.valueOf(name);
            Table table = connection.getTable(tableName);

            long beiginTime = System.currentTimeMillis();
            resultScanner = table.getScanner(scan);
            long endTime = System.currentTimeMillis();
            double spentTime = (endTime - beiginTime) / 1000.0;
            System.out.println("cost:===" + spentTime + "s");

            for (Result result : resultScanner) {
                // System.out.println("获得到rowkey:" + new
                // String(result.getRow()));
                HbaseDataEntity entity = new HbaseDataEntity();

                entity.setTableName(name);
                entity.setMobileKey(new String(result.getRow()));

                Map<String, Map<String, String>> familyMap = new HashMap<String, Map<String, String>>();
                for (Cell cell : result.rawCells()) {

                    if (familyMap.get(new String(cell.getFamilyArray())) == null) {
                        Map<String, String> columnsMap = new HashMap<String, String>();

                        columnsMap.put(
                                new String(cell.getQualifierArray(), cell
                                        .getQualifierOffset(), cell
                                        .getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                        familyMap.put(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength()), columnsMap);
                    } else {
                        familyMap.get(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength())).put(
                                new String(cell.getQualifierArray(),
                                        cell.getQualifierOffset(),
                                        cell.getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                    }

                    // System.out.println("列：" + new
                    // String(cell.getFamilyArray(), cell.getFamilyOffset(),
                    // cell.getFamilyLength())
                    // + "====值:" + new
                    // String(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
                }

                entity.setColumns(familyMap);
                list.add(entity);

                if (size == list.size()) {
                    break;
                }
            }

            table.close();
            return list;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            resultScanner.close();

        }

        return null;
    }

    /**
     * 组合条件查询 and
     *
     * @param nameSpace  命名空间
     * @param tableName  表名
     * @param parameters 格式是：columnFamily,columnName,columnValue
     */
    public static List<HbaseDataEntity> QueryDataByConditionsAnd(
            String nameSpace, String name, List<String> parameters) {

        ResultScanner rs = null;
        Table table = null;
        List<HbaseDataEntity> list = new ArrayList<HbaseDataEntity>();

        try {
            TableName tableName = TableName.valueOf(name);
            table = connection.getTable(tableName);

            // 参数的格式：columnFamily,columnName,columnValue
            List<Filter> filters = new ArrayList<Filter>();
            for (String parameter : parameters) {
                String[] columns = parameter.split(",");
                SingleColumnValueFilter filter = new SingleColumnValueFilter(
                        Bytes.toBytes(columns[0]), Bytes.toBytes(columns[1]),
                        CompareOp.valueOf(columns[2]),
                        Bytes.toBytes(columns[3]));
                filter.setFilterIfMissing(true);
                filters.add(filter);
            }

            FilterList filterList = new FilterList(filters);

            Scan scan = new Scan();
            scan.setFilter(filterList);
            rs = table.getScanner(scan);
            for (Result r : rs) {
                System.out.println("获得到rowkey:" + new String(r.getRow()));
                HbaseDataEntity entity = new HbaseDataEntity();
                entity.setNameSpace(nameSpace);
                entity.setTableName(name);
                entity.setMobileKey(new String(r.getRow()));
                Map<String, Map<String, String>> familyMap = new HashMap<String, Map<String, String>>();
                for (Cell cell : r.rawCells()) {
                    if (familyMap.get(new String(cell.getFamilyArray(), cell
                            .getFamilyOffset(), cell.getFamilyLength())) == null) {
                        Map<String, String> columnsMap = new HashMap<String, String>();
                        columnsMap.put(
                                new String(cell.getQualifierArray(), cell
                                        .getQualifierOffset(), cell
                                        .getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                        familyMap.put(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength()), columnsMap);
                    } else {
                        familyMap.get(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength())).put(
                                new String(cell.getQualifierArray(),
                                        cell.getQualifierOffset(),
                                        cell.getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                    }
                }

                entity.setColumns(familyMap);
                list.add(entity);
            }

            rs.close();
            table.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 组合条件查询 or
     *
     * @param nameSpace  命名空间
     * @param tableName  表名
     * @param parameters 格式是：columnFamily,columnName,columnValue
     * @return
     */
    public static List<HbaseDataEntity> QueryDataByConditionsOr(
            String nameSpace, String name, List<String> parameters) {

        ResultScanner rs = null;
        List<HbaseDataEntity> list = new ArrayList<HbaseDataEntity>();

        try {

            TableName tableName = TableName.valueOf(name);
            Table table = connection.getTable(tableName);

            // 参数的额格式：columnFamily,columnName,columnValue
            List<Filter> filters = new ArrayList<Filter>();
            Scan scan = new Scan();

            byte[] columnFamily = null;
            byte[] columnName = null;

            for (String parameter : parameters) {
                String[] columns = parameter.split(",");
                columnFamily = Bytes.toBytes(columns[0]);
                columnName = Bytes.toBytes(columns[1]);
                SingleColumnValueFilter filter = new SingleColumnValueFilter(
                        Bytes.toBytes(columns[0]), Bytes.toBytes(columns[1]),
                        CompareOp.valueOf(columns[2]),
                        Bytes.toBytes(columns[3]));
                filter.setFilterIfMissing(true);
                filters.add(filter);
            }

            FilterList filterList = new FilterList(
                    FilterList.Operator.MUST_PASS_ONE, filters);

            scan.setFilter(filterList);

            rs = table.getScanner(scan);
            for (Result r : rs) {
                if (r.containsColumn(columnFamily, columnName)) {
                    System.out.println("获得到rowkey:" + new String(r.getRow()));
                    HbaseDataEntity entity = new HbaseDataEntity();
                    entity.setNameSpace(nameSpace);
                    entity.setTableName(name);
                    entity.setMobileKey(new String(r.getRow()));
                    Map<String, Map<String, String>> familyMap = new HashMap<String, Map<String, String>>();
                    for (Cell cell : r.rawCells()) {
                        if (familyMap
                                .get(new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength())) == null) {
                            Map<String, String> columnsMap = new HashMap<String, String>();
                            columnsMap.put(
                                    new String(cell.getQualifierArray(), cell
                                            .getQualifierOffset(), cell
                                            .getQualifierLength()),
                                    new String(cell.getValueArray(), cell
                                            .getValueOffset(), cell
                                            .getValueLength()));
                            familyMap.put(
                                    new String(cell.getFamilyArray(), cell
                                            .getFamilyOffset(), cell
                                            .getFamilyLength()), columnsMap);
                        } else {
                            familyMap.get(
                                    new String(cell.getFamilyArray(), cell
                                            .getFamilyOffset(), cell
                                            .getFamilyLength())).put(
                                    new String(cell.getQualifierArray(),
                                            cell.getQualifierOffset(),
                                            cell.getQualifierLength()),
                                    new String(cell.getValueArray(), cell
                                            .getValueOffset(), cell
                                            .getValueLength()));
                        }
                    }

                    entity.setColumns(familyMap);
                    list.add(entity);
                }
            }

            table.close();
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 组合条件查询 or
     *
     * @param nameSpace  命名空间
     * @param tableName  表名
     * @param parameters 格式是：columnFamily,columnName,columnValue
     * @return
     */
    public static List<HbaseDataEntity> QueryDataByConditions(String nameSpace,
                                                              String name, List<HbaseConditionEntity> hbaseConditions) {

        ResultScanner rs = null;
        List<HbaseDataEntity> list = new ArrayList<HbaseDataEntity>();

        try {

            TableName tableName = TableName.valueOf(name);
            Table table = connection.getTable(tableName);

            // 参数的额格式：columnFamily,columnName,columnValue
            // List<Filter> filters = new ArrayList<Filter>();
            Scan scan = new Scan();

            FilterList filterList = null;
            Operator operator = null;
            for (HbaseConditionEntity hbaseCondition : hbaseConditions) {

                SingleColumnValueFilter filter = new SingleColumnValueFilter(
                        hbaseCondition.getFamilyColumn(),
                        hbaseCondition.getColumn(),
                        hbaseCondition.getCompareOp(),
                        hbaseCondition.getValue());
                filter.setFilterIfMissing(true);

                if (hbaseCondition.getOperator() != null) {

                    if (operator == null) {
                        operator = hbaseCondition.getOperator();
                        filterList = new FilterList(
                                hbaseCondition.getOperator());
                        filterList.addFilter(filter);
                        System.out.println("filterList==1" + filterList);
                    } else if (operator.equals(hbaseCondition.getOperator())) {
                        filterList.addFilter(filter);
                    } else {
                        filterList.addFilter(filter);
                        System.out.println("filterList==2" + filterList);
                        FilterList addFilterList = new FilterList(
                                hbaseCondition.getOperator());
                        addFilterList.addFilter(filterList);
                        System.out.println("addFilterList==1" + addFilterList);
                        filterList = addFilterList;
                        System.out.println("filterList==3" + filterList);
                    }

                } else {
                    if (filterList == null) {
                        filterList = new FilterList(Operator.MUST_PASS_ALL);// 默认只有一个条件的时候
                    }
                    filterList.addFilter(filter);
                }

            }

            System.out.println(filterList + ":filterList");

            scan.setFilter(filterList);

            rs = table.getScanner(scan);
            for (Result r : rs) {
                System.out.println("获得到rowkey:" + new String(r.getRow()));
                HbaseDataEntity entity = new HbaseDataEntity();
                entity.setNameSpace(nameSpace);
                entity.setTableName(name);
                entity.setMobileKey(new String(r.getRow()));
                Map<String, Map<String, String>> familyMap = new HashMap<String, Map<String, String>>();
                for (Cell cell : r.rawCells()) {
                    if (familyMap.get(new String(cell.getFamilyArray(), cell
                            .getFamilyOffset(), cell.getFamilyLength())) == null) {
                        Map<String, String> columnsMap = new HashMap<String, String>();
                        columnsMap.put(
                                new String(cell.getQualifierArray(), cell
                                        .getQualifierOffset(), cell
                                        .getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                        familyMap.put(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength()), columnsMap);
                    } else {
                        familyMap.get(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength())).put(
                                new String(cell.getQualifierArray(),
                                        cell.getQualifierOffset(),
                                        cell.getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                    }
                }

                entity.setColumns(familyMap);
                list.add(entity);
            }

            table.close();
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 分页的复合条件查询
     *
     * @param nameSpace       命名空间
     * @param name            表名
     * @param hbaseConditions 复合条件
     * @param pageSize        每页显示的数量
     * @param lastRow         当前页的最后一行
     * @return
     */
    public static List<HbaseDataEntity> QueryDataByConditionsAndPage(
            String nameSpace, String name,
            List<HbaseConditionEntity> hbaseConditions, int pageSize,
            byte[] lastRow) {
        final byte[] POSTFIX = new byte[]{0x00};

        ResultScanner rs = null;
        List<HbaseDataEntity> list = new ArrayList<HbaseDataEntity>();

        try {

            TableName tableName = TableName.valueOf(name);
            Table table = connection.getTable(tableName);

            Scan scan = new Scan();

            FilterList filterList = null;
            Operator operator = null;
            for (HbaseConditionEntity hbaseCondition : hbaseConditions) {

                SingleColumnValueFilter filter = new SingleColumnValueFilter(
                        hbaseCondition.getFamilyColumn(),
                        hbaseCondition.getColumn(),
                        hbaseCondition.getCompareOp(),
                        hbaseCondition.getValue());
                filter.setFilterIfMissing(true);

                if (hbaseCondition.getOperator() != null) {

                    if (operator == null) {
                        operator = hbaseCondition.getOperator();
                        filterList = new FilterList(
                                hbaseCondition.getOperator());
                        filterList.addFilter(filter);
                        System.out.println("filterList==1" + filterList);
                    } else if (operator.equals(hbaseCondition.getOperator())) {
                        filterList.addFilter(filter);
                    } else {
                        filterList.addFilter(filter);
                        System.out.println("filterList==2" + filterList);
                        FilterList addFilterList = new FilterList(
                                hbaseCondition.getOperator());
                        addFilterList.addFilter(filterList);
                        System.out.println("addFilterList==1" + addFilterList);
                        filterList = addFilterList;
                        System.out.println("filterList==3" + filterList);
                    }

                } else {
                    if (filterList == null) {
                        filterList = new FilterList(Operator.MUST_PASS_ALL);// 默认只有一个条件的时候
                    }
                    filterList.addFilter(filter);
                }

            }

            System.out.println(filterList + ":filterList");

            FilterList pageFilterList = new FilterList(Operator.MUST_PASS_ALL);// 默认只有一个条件的时候
            Filter pageFilter = new PageFilter(pageSize);
            pageFilterList.addFilter(pageFilter);
            pageFilterList.addFilter(filterList);
            if (lastRow != null) {
                // 注意这里添加了POSTFIX操作，不然死循环了
                byte[] startRow = Bytes.add(lastRow, POSTFIX);
                scan.setStartRow(startRow);
            }

            System.out.println(pageFilterList + ":pageFilterList");
            scan.setFilter(pageFilterList);

            rs = table.getScanner(scan);
            for (Result r : rs) {
                System.out.println("获得到rowkey:" + new String(r.getRow()));
                HbaseDataEntity entity = new HbaseDataEntity();
                entity.setNameSpace(nameSpace);
                entity.setTableName(name);
                entity.setMobileKey(new String(r.getRow()));
                Map<String, Map<String, String>> familyMap = new HashMap<String, Map<String, String>>();
                for (Cell cell : r.rawCells()) {
                    if (familyMap.get(new String(cell.getFamilyArray(), cell
                            .getFamilyOffset(), cell.getFamilyLength())) == null) {
                        Map<String, String> columnsMap = new HashMap<String, String>();
                        columnsMap.put(
                                new String(cell.getQualifierArray(), cell
                                        .getQualifierOffset(), cell
                                        .getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                        familyMap.put(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength()), columnsMap);
                    } else {
                        familyMap.get(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength())).put(
                                new String(cell.getQualifierArray(),
                                        cell.getQualifierOffset(),
                                        cell.getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                    }
                }

                entity.setColumns(familyMap);
                list.add(entity);
            }

            table.close();
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 复合条件分页查询
     *
     * @param name
     * @param pageSize
     * @param lastRow
     * @return
     */
    public static List<HbaseDataEntity> getHbaseDatasByPage(String name,
                                                            int pageSize, byte[] lastRow) {
        final byte[] POSTFIX = new byte[]{0x00};

        Scan scan = new Scan();
        ResultScanner resultScanner = null;
        Table table = null;
        List<HbaseDataEntity> list = new ArrayList<HbaseDataEntity>();

        try {

            TableName tableName = TableName.valueOf(name);
            table = connection.getTable(tableName);

            Filter filter = new PageFilter(pageSize);
            scan.setFilter(filter);
            if (lastRow != null) {
                // 注意这里添加了POSTFIX操作，不然死循环了
                byte[] startRow = Bytes.add(lastRow, POSTFIX);
                scan.setStartRow(startRow);
            }
            resultScanner = table.getScanner(scan);

            for (Result result : resultScanner) {
                HbaseDataEntity entity = new HbaseDataEntity();
                entity.setTableName(name);
                entity.setMobileKey(new String(result.getRow()));
                Map<String, Map<String, String>> familyMap = new HashMap<String, Map<String, String>>();
                for (Cell cell : result.rawCells()) {
                    if (familyMap.get(new String(cell.getFamilyArray(), cell
                            .getFamilyOffset(), cell.getFamilyLength())) == null) {
                        Map<String, String> columnsMap = new HashMap<String, String>();
                        columnsMap.put(
                                new String(cell.getQualifierArray(), cell
                                        .getQualifierOffset(), cell
                                        .getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                        familyMap.put(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength()), columnsMap);
                    } else {
                        familyMap.get(
                                new String(cell.getFamilyArray(), cell
                                        .getFamilyOffset(), cell
                                        .getFamilyLength())).put(
                                new String(cell.getQualifierArray(),
                                        cell.getQualifierOffset(),
                                        cell.getQualifierLength()),
                                new String(cell.getValueArray(), cell
                                        .getValueOffset(), cell
                                        .getValueLength()));
                    }
                }
                entity.setColumns(familyMap);
                list.add(entity);
            }

            table.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            resultScanner.close();
        }

        return null;
    }

    public static int getDataByPage(String name, int pageSize) {

        final byte[] POSTFIX = new byte[]{0x00};
        TableName tableName = TableName.valueOf(name);
        Table table;
        int totalRows = 0;
        try {
            table = connection.getTable(tableName);
            Filter filter = new PageFilter(pageSize);
            byte[] lastRow = null;

            while (true) {
                Scan scan = new Scan();
                scan.setFilter(filter);
                if (lastRow != null) {
                    // 注意这里添加了POSTFIX操作，不然死循环了
                    byte[] startRow = Bytes.add(lastRow, POSTFIX);
                    scan.setStartRow(startRow);
                }

                ResultScanner scanner = table.getScanner(scan);
                int localRows = 0;
                Result result;
                while ((result = scanner.next()) != null) {
                    System.out.println(localRows++ + ":" + result);
                    totalRows++;
                    lastRow = result.getRow();
                }

                scanner.close();
                if (localRows == 0)
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("total rows:" + totalRows);
        return totalRows;
    }

    public static void main(String[] args) {

        // 1、Create table

        // /String tableName = "caoShuaiTest09";

		/*
         * List<String> columnFamilyName = new ArrayList<String>();
		 * columnFamilyName.add("info"); columnFamilyName.add("address");
		 * columnFamilyName.add("score");
		 *
		 * createTable(tableName, columnFamilyName);
		 */

        // 2、Insert data into table

		/*
         * String roeKey01 = "LiMing"; Map<String, Map<String, String>>
		 * familyColumnMap01 = new HashMap<String, Map<String, String>>();
		 * Map<String, String> columnMap01 = new HashMap<String, String>();
		 * columnMap01.put("age", "23"); columnMap01.put("phone",
		 * "13854285991"); familyColumnMap01.put("info", columnMap01);
		 *
		 * Map<String, String> columnMap02 = new HashMap<String, String>();
		 * columnMap02.put("province", "shandong"); columnMap02.put("city",
		 * "beijing"); familyColumnMap01.put("address", columnMap02);
		 *
		 * Map<String, String> columnMap03 = new HashMap<String, String>();
		 * columnMap03.put("english", "80"); columnMap03.put("chinese", "100");
		 * familyColumnMap01.put("score", columnMap03); int result01 =
		 * addDataForTable(tableName, roeKey01, familyColumnMap01);
		 * System.out.println("==result01==:" + result01);
		 */

        // 3、获取结果 getResult(tableName, roeKey01);

		/*
         * String roeKey02 = "WangNing"; Map<String, Map<String, String>>
		 * familyColumnMap01 = new HashMap<String, Map<String,String>>();
		 * Map<String, String> columnMap01 = new HashMap<String,String>();
		 * columnMap01.put("age", "50"); columnMap01.put("phone",
		 * "13854285991"); familyColumnMap01.put("info", columnMap01);
		 *
		 * Map<String, String> columnMap02 = new HashMap<String,String>();
		 * columnMap02.put("province", "shandong");
		 * columnMap02.put("city","beijing"); familyColumnMap01.put("address",
		 * columnMap02);
		 *
		 * Map<String, String> columnMap03 = new HashMap<String,String>();
		 * columnMap03.put("english", "40"); columnMap03.put("chinese","70");
		 * familyColumnMap01.put("score", columnMap03); int result01 =
		 * addDataForTable(tableName, roeKey02, familyColumnMap01);
		 * System.out.println("==result01==:" + result01);
		 */
        // 4
        // getResultScan(tableName);

		/*
         * List<String> parameters = new ArrayList<String>();
		 * parameters.add("info,age,EQUAL,23");
		 * parameters.add("score,english,GREATER_OR_EQUAL,40");
		 * QueryDataByConditionsAnd(null, tableName, parameters);
		 */

        // 5

		/*
         * String newTableName = "caoShuaiTest04";
		 *
		 * List<HbaseDataEntity> hbaseDatas = getResultScans(newTableName);
		 *
		 * System.out.println("hbaseDatas===" + hbaseDatas); for
		 * (HbaseDataEntity hbaseData : hbaseDatas) {
		 *
		 * String rowKey = hbaseData.getMobileKey();
		 *
		 * Map<String, Map<String, String>> maps = hbaseData.getColumns();
		 *
		 * for (String key : maps.keySet()) {
		 *
		 * System.out.println("key===" + key); Map<String, String> columnsMap =
		 * maps.get(key);
		 *
		 * for (String columnsKey : columnsMap.keySet()) {
		 * System.out.println("columnsKey===" + columnsKey);
		 *
		 * //updateTable("caoShuaiTest01", rowKey, key, columnsKey,columnsKey);
		 * //deleteColumn("caoShuaiTest04", rowKey, key, columnsKey); }
		 *
		 * } }
		 */

		/*
         * long beginTime = System.currentTimeMillis();
		 * System.out.println("begin:" + beginTime);
		 *
		 * for (int i = 0; i < 100000000; i++) {
		 *
		 * long startTime = System.currentTimeMillis();
		 *
		 * String tableName = "caoShuaiTest06";
		 *
		 * String roeKey01 = "LiMing" + i; Map<String, Map<String, String>>
		 * familyColumnMap01 = new HashMap<String, Map<String, String>>();
		 * Map<String, String>
		 *
		 * columnMap01 = new HashMap<String, String>(); int age = i % 100 + 1;
		 * columnMap01.put("age", String.valueOf(age)); columnMap01.put("phone",
		 * "13854285991"); columnMap01.put("province", "shandong");
		 * columnMap01.put("city", "beijing"); columnMap01.put("chinese",
		 * "100"); familyColumnMap01.put("info", columnMap01);
		 *
		 * int result01 = addDataForTable(tableName, roeKey01,
		 * familyColumnMap01); long finishedTime = System.currentTimeMillis();
		 *
		 * double smallTime = (finishedTime - startTime)/1000.0;
		 * System.out.println("第" + i + "个花费时间" + smallTime + "s" ); }
		 *
		 * long endTime = System.currentTimeMillis();
		 *
		 * System.out.println("end:" + endTime);
		 *
		 * double time = (endTime - beginTime)/1000.0;
		 *
		 * System.out.println("all spent time:" + time + "s");
		 */

		/*
		 * String tableName = "caoShuaiTest10"; List<HbaseDataEntity> hbaseDatas
		 * = new ArrayList<HbaseDataEntity>(); long startTime =
		 * System.currentTimeMillis(); int k = 0; for (int i = 1; i <=
		 * 100000000; i++) { HbaseDataEntity hbaseData = new HbaseDataEntity();
		 * hbaseData.setTableName(tableName);
		 * hbaseData.setMobileKey(String.valueOf(i)); Map<String, Map<String,
		 * String>> familyMaps = new HashMap<String, Map<String,String>>();
		 * Map<String, String> columnMaps = new HashMap<>(); int age = i % 100 +
		 * 1; columnMaps.put("age", String.valueOf(age));
		 * columnMaps.put("phone", "13854285991"); columnMaps.put("province",
		 * "shandong"); columnMaps.put("city", "beijing");
		 * columnMaps.put("chinese", "100"); familyMaps.put("info", columnMaps);
		 * hbaseData.setColumns(familyMaps);
		 *
		 * hbaseDatas.add(hbaseData);
		 *
		 * if(i%10000 == 0) { k ++; long time1 = System.currentTimeMillis();
		 * insertDataList(hbaseDatas); hbaseDatas.clear(); long time2 =
		 * System.currentTimeMillis(); double time = (time2 - time1)/1000.0;
		 * System.out.println(k + "万条数据存入hbase花费时间" + time + "s" );
		 *
		 * } } long finishedTime = System.currentTimeMillis(); double smallTime
		 * = (finishedTime - startTime)/1000.0; System.out.println("组装数据花费时间" +
		 * smallTime + "s" );
		 */

		/*
		 * long beiginTime = System.currentTimeMillis();
		 * insertDataList(hbaseDatas); long endTime =
		 * System.currentTimeMillis(); double spentTime = (endTime -
		 * beiginTime)/1000.0; System.out.println("数据花费时间" + spentTime + "s" );
		 */

		/*
		 * long beiginTime = System.currentTimeMillis(); String tableName =
		 * "caoShuaiTest04"; String hbaseTableName =
		 * "customer_portrait_library"; List<HbaseDataEntity> datas =
		 * getResultScans(tableName, 10000); //System.out.println(datas); long
		 * endTime = System.currentTimeMillis(); double spentTime = (endTime -
		 * beiginTime)/1000.0; System.out.println("数据花费时间" + spentTime + "s" );
		 *
		 * //System.out.println("hbaseDatas===" + datas);
		 *
		 * List<HbaseDataEntity> newDatas = new ArrayList<HbaseDataEntity>();
		 *
		 * long time1 = System.currentTimeMillis(); for (HbaseDataEntity
		 * hbaseData : datas) {
		 *
		 * String rowKey = hbaseData.getMobileKey();
		 *
		 *
		 * HbaseDataEntity newData = new HbaseDataEntity();
		 * newData.setMobileKey(rowKey); newData.setTableName(hbaseTableName);
		 *
		 * Map<String, Map<String, String>> maps = hbaseData.getColumns();
		 * Map<String, Map<String, String>> newMaps = new HashMap<String,
		 * Map<String,String>>();
		 *
		 * for (String key : maps.keySet()) {
		 *
		 * Map<String, String> columnsMap = maps.get(key); Map<String, String>
		 * newColumnsMap = new HashMap<String, String>(); for (String columnsKey
		 * : columnsMap.keySet()) {
		 *
		 * newColumnsMap.put(columnsKey, columnsKey);
		 * updateTable(hbaseTableName, rowKey, key, columnsKey, columnsKey);
		 * deleteColumn(tableName, rowKey, key, columnsKey); } newMaps.put(key,
		 * newColumnsMap);
		 *
		 * } newData.setColumns(newMaps); newDatas.add(newData);
		 *
		 * }
		 *
		 * if(newDatas != null && newDatas.size() > 0) { long insertTime1 =
		 * System.currentTimeMillis(); insertDataList(newDatas); long
		 * insertTime2 = System.currentTimeMillis(); double insertTime =
		 * (insertTime2 - insertTime1)/1000.0; System.out.println("修改数据时间" +
		 * insertTime + "s" );
		 *
		 * long deleteTime1 = System.currentTimeMillis(); deleteDataList(datas);
		 * long deleteTime2 = System.currentTimeMillis(); double deleteTime =
		 * (deleteTime2 - deleteTime1)/1000.0; System.out.println("删除数据时间" +
		 * deleteTime + "s" ); } long time2 = System.currentTimeMillis(); double
		 * time = (time2 - time1)/1000.0; System.out.println("组装时间" + time + "s"
		 * );
		 */

		/*
		 * List<String> list = new ArrayList<String>(); long insertTime1 =
		 * System.currentTimeMillis(); for(int i=0; i<100000000; i++) {
		 * list.add("abcdef" + i); } long insertTime2 =
		 * System.currentTimeMillis(); double insertTime = (insertTime2 -
		 * insertTime1)/1000.0; System.out.println("修改数据时间" + insertTime + "s"
		 * );
		 */

        // 7、复合条件查询

        String tableName = "caoShuaiTest01";
        List<HbaseConditionEntity> hbaseConditions = new ArrayList<HbaseConditionEntity>();
        hbaseConditions.add(new HbaseConditionEntity(Bytes.toBytes("info"),
                Bytes.toBytes("age"), Bytes.toBytes("23"),
                Operator.valueOf("MUST_PASS_ALL"), CompareOp.valueOf("EQUAL")));

        hbaseConditions.add(new HbaseConditionEntity(Bytes.toBytes("score"),
                Bytes.toBytes("english"), Bytes.toBytes("80"),
                Operator.valueOf("MUST_PASS_ALL"), CompareOp.valueOf("EQUAL")));

        hbaseConditions.add(new HbaseConditionEntity(Bytes.toBytes("score"),
                Bytes.toBytes("english"), Bytes.toBytes("80"),
                Operator.valueOf("MUST_PASS_ONE"), CompareOp.valueOf("EQUAL")));

        hbaseConditions.add(new HbaseConditionEntity(
                Bytes.toBytes("address"), Bytes.toBytes("city"),
                Bytes.toBytes("beijing"), null, CompareOp.valueOf("EQUAL")));

        hbaseConditions.add(new HbaseConditionEntity(Bytes.toBytes("score"),
                Bytes.toBytes("english"), Bytes.toBytes("70"), null,
                CompareOp.valueOf("EQUAL")));

        List<HbaseDataEntity> datas = QueryDataByConditionsAndPage(null, tableName, hbaseConditions, 2, null);
        //List<HbaseDataEntity> datas = QueryDataByConditions(null, tableName, hbaseConditions); //联合条件查询 String tableName = "caoShuaiTest01";
		/*  List<String> parameters = new ArrayList<String>();
		  parameters.add("info,age,EQUAL,23");
		  parameters.add("score,english,EQUAL,80");
		  parameters.add("address,city,EQUAL,beijing"); String pm =
		  "score,english,EQUAL,78";
		  List<HbaseDataEntity> datas = QueryDataByConditions(null, tableName, parameters, pm);*/
        System.out.println(datas);


        // 分页
		/*int pageSize = 1;
		String key = null;
		int dataCount = pageSize;
		String tableName = "caoShuaiTest01";

		while (dataCount == pageSize) {
			byte[] mobileKey = null;

			if (key != null) {
				mobileKey = key.getBytes();
			}

			List<HbaseDataEntity> hbaseDatas = getHbaseDatasByPage(tableName,
					pageSize, mobileKey);
			if (hbaseDatas != null && hbaseDatas.size() > 0) {
				System.out.println(hbaseDatas);
				dataCount = hbaseDatas.size();
				key = hbaseDatas.get(dataCount - 1).getMobileKey();
				System.out.println("Key:" + key);
			} else {
				break;
			}
		}*/
    }
}