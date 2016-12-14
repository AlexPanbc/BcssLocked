package test.HbaseTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：管理电池历史数据
 * Created by liuhuichao on 2016/12/8.
 */
public class HBaseBatteryDataManager extends Thread {

    private static class HBaseBatteryDataMasterHolder{
        private static final HBaseBatteryDataManager INSTANCE = new HBaseBatteryDataManager();
    }

    private  final String  colunm_family_baseData="baseData";//列族baseData
    private  final String  colunm_family_extraData="extraData";//列族extraData

    /**
     * 构造：配置初始化
     */
    private HBaseBatteryDataManager() {
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

    /**
     * 外部调用这个类的方法
     * @return
     */
    public static final HBaseBatteryDataManager getInstance() {
        return HBaseBatteryDataMasterHolder.INSTANCE;
    }

    private Configuration config;
    public HTable table;
    public HBaseAdmin admin;

    /**
     * 增加一条电池历史数据
     * @param resBatteryDataHistory
     */
    public void addBatteryData(ResBatteryDataHistory resBatteryDataHistory) throws IOException {
        if(resBatteryDataHistory==null){
            return;
        }
        Put put=new Put(Bytes.toBytes(resBatteryDataHistory.getBatteryNo()+System.currentTimeMillis()));
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("battery_no"),Bytes.toBytes(resBatteryDataHistory.getBatteryNo()));//电池编号
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("battery_type"),Bytes.toBytes(resBatteryDataHistory.getBatteryType()));//电池类型
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("voltage_deviation"),Bytes.toBytes(resBatteryDataHistory.getVoltageDeviation()));//压差
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("total_voltage"),Bytes.toBytes(resBatteryDataHistory.getTotalVoltage()));//总电压
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("total_current"),Bytes.toBytes(resBatteryDataHistory.getTotalCurrent()));//总电流
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol1"),Bytes.toBytes(resBatteryDataHistory.getVol1()));//电芯电压1
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol2"),Bytes.toBytes(resBatteryDataHistory.getVol2()));//电芯电压2
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol3"),Bytes.toBytes(resBatteryDataHistory.getVol3()));//电芯电压3
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol4"),Bytes.toBytes(resBatteryDataHistory.getVol4()));//电芯电压4
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol5"),Bytes.toBytes(resBatteryDataHistory.getVol5()));//电芯电压5
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol6"),Bytes.toBytes(resBatteryDataHistory.getVol6()));//电芯电压6
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol7"),Bytes.toBytes(resBatteryDataHistory.getVol7()));//电芯电压7
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol8"),Bytes.toBytes(resBatteryDataHistory.getVol8()));//电芯电压8
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol9"),Bytes.toBytes(resBatteryDataHistory.getVol9()));//电芯电压9
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol10"),Bytes.toBytes(resBatteryDataHistory.getVol10()));//电芯电压10
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol11"),Bytes.toBytes(resBatteryDataHistory.getVol11()));//电芯电压11
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol12"),Bytes.toBytes(resBatteryDataHistory.getVol12()));//电芯电压12
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol13"),Bytes.toBytes(resBatteryDataHistory.getVol13()));//电芯电压13
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol14"),Bytes.toBytes(resBatteryDataHistory.getVol14()));//电芯电压14
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol15"),Bytes.toBytes(resBatteryDataHistory.getVol15()));//电芯电压15
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol16"),Bytes.toBytes(resBatteryDataHistory.getVol16()));//电芯电压16
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol17"),Bytes.toBytes(resBatteryDataHistory.getVol17()));//电芯电压17
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol18"),Bytes.toBytes(resBatteryDataHistory.getVol18()));//电芯电压18
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vol19"),Bytes.toBytes(resBatteryDataHistory.getVol19()));//电芯电压19
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("temprature1"),Bytes.toBytes(resBatteryDataHistory.getTemprature1()));//温度1
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("temprature2"),Bytes.toBytes(resBatteryDataHistory.getTemprature2()));//温度2
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("charge_num"),Bytes.toBytes(resBatteryDataHistory.getChargeNum()));//充电次数
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("soc"),Bytes.toBytes(resBatteryDataHistory.getSoc()));//总电压
        put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("create_time"),Bytes.toBytes(resBatteryDataHistory.getCreateTime()));//创建时间
        if(resBatteryDataHistory.getCityId()!=null){
            put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("city_id"),Bytes.toBytes(resBatteryDataHistory.getCityId()));//城市ID
        }
        if(resBatteryDataHistory.getVehicleId()!=null){
            put.addColumn(Bytes.toBytes(colunm_family_baseData),Bytes.toBytes("vehicle_id"),Bytes.toBytes(resBatteryDataHistory.getVehicleId()));//车辆ID
        }
        put.addColumn(Bytes.toBytes(colunm_family_extraData),Bytes.toBytes("create_user"),Bytes.toBytes(resBatteryDataHistory.getBatteryNo()));//创建人
        put.addColumn(Bytes.toBytes(colunm_family_extraData),Bytes.toBytes("source"),Bytes.toBytes(resBatteryDataHistory.getSource()));//数据来源
        put.addColumn(Bytes.toBytes(colunm_family_extraData),Bytes.toBytes("longtitude"),Bytes.toBytes(resBatteryDataHistory.getLongtitude()));//精度
        put.addColumn(Bytes.toBytes(colunm_family_extraData),Bytes.toBytes("latitude"),Bytes.toBytes(resBatteryDataHistory.getLatitude()));//纬度
        put.addColumn(Bytes.toBytes(colunm_family_extraData),Bytes.toBytes("remarks"),Bytes.toBytes(resBatteryDataHistory.getRemarks()));//备注
        put.addColumn(Bytes.toBytes(colunm_family_extraData),Bytes.toBytes("test_user_name"),Bytes.toBytes(resBatteryDataHistory.getTestUserName()));//测试用户
        table.put(put);
        table.close();
    }


    /**
     * 根据rowKey获取电池历史数据
     * @param rowKey
     * @return
     */
    public ResBatteryDataHistory getBatteryDataHistoryByRowKey(String rowKey) throws IOException {
        Get get=new Get(Bytes.toBytes(rowKey));
        Result result=table.get(get);
        if(result==null){
            return null;
        }
        return convertToData(result);
    }

    /**
     * 获取电池数据某个单元格的数据
     * @param rowKey
     * @param colFamily
     * @param col
     * @return
     */
    public  String getCellData(String rowKey,String colFamily,String col) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
        //获取的result数据是结果集，还需要格式化输出想要的数据才行
        Result result = table.get(get);
        String cellValue=new String(result.getValue(colFamily.getBytes(),col==null?null:col.getBytes())).intern();
        table.close();
        return cellValue;
    }

    /**
     * 更新表中的一个单元格
     * @param rowKey
     * @param familyName
     * @param columnName
     * @param value
     */
   public void  updateCell(String rowKey,String familyName, String columnName, String value) throws IOException {
       Put put = new Put(Bytes.toBytes(rowKey));
       put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName),
               Bytes.toBytes(value));

       table.put(put);
       table.close();
   }

    /**
     * 删除一个单元格
     * @param rowKey
     * @param familyName
     * @param columnName
     */
    public void deleteCell( String rowKey,String familyName, String columnName) throws IOException {
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        delete.addColumn(Bytes.toBytes(familyName),
                Bytes.toBytes(columnName));
        table.delete(delete);
        table.close();
    }

    /**
     * 删除一行
     * @param rowKey
     */
    public void deleteAllColumns(String rowKey) throws IOException {
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();
    }

    /**
     * 获取所有数据
     * @param size
     * @return
     */
    public List<ResBatteryDataHistory> getResultScans(int size) throws IOException {
        Scan scan = new Scan();
        ResultScanner resultScanner = null;
        List<ResBatteryDataHistory> list=new ArrayList<>() ;
        long beginTime=System.currentTimeMillis();
        resultScanner=table.getScanner(scan);
        long endTime=System.currentTimeMillis();
        double spendTime=(endTime-beginTime)/1000.0;
        if(resultScanner==null){
            return null;
        }
        for (Result result : resultScanner){
            list.add(convertToData(result));
        }
        table.close();
        return list;
    }


    /**
     *  * 根据单个列查询数据 【and查询】
     *      例如，查询extraData:create_user==‘1’的所有数据
     * @param queryConditionList  条件拼接列表
     * @return
     * @throws IOException
     */
    public List<ResBatteryDataHistory> QueryDataByConditionsAnd(List<QueryCondition> queryConditionList) throws IOException {
        if(queryConditionList==null || queryConditionList.size()<1){
            return null;
        }
        ResultScanner rs = null;
        List<ResBatteryDataHistory> list=new ArrayList<>() ;
        List<Filter> filters = new ArrayList<Filter>();
        for(QueryCondition query : queryConditionList){
            SingleColumnValueFilter filter = new SingleColumnValueFilter(
                    Bytes.toBytes(query.getFamily()), Bytes.toBytes(query.getQualifier()),
                    query.getCompareOp(),Bytes.toBytes(query.getValue()));
            filter.setFilterIfMissing(true); //设置这些列不存在的数据不返回
            filters.add(filter);
        }
        FilterList filterList = new FilterList(filters);
        Scan scan = new Scan();
        scan.setFilter(filterList);
        rs = table.getScanner(scan);
        for (Result result : rs){
            list.add(convertToData(result));
        }
        table.close();
        return list;
    }


    /**
     * 查询数据 【Or查询】
     * @param queryConditionList
     * @return
     */
    public List<ResBatteryDataHistory> QueryDataByConditionsOr(List<QueryCondition> queryConditionList) throws IOException {
        if(queryConditionList==null || queryConditionList.size()<1){
            return null;
        }
        List<ResBatteryDataHistory> list=new ArrayList<>() ;
        ResultScanner rs = null;
        List<Filter> filters = new ArrayList<Filter>();
        Scan scan = new Scan();
        for(QueryCondition query : queryConditionList){
            SingleColumnValueFilter filter = new SingleColumnValueFilter(
                    Bytes.toBytes(query.getFamily()), Bytes.toBytes(query.getQualifier()),
                    query.getCompareOp(),Bytes.toBytes(query.getValue()));
            filter.setFilterIfMissing(true);
            filters.add(filter);
        }
        FilterList filterList = new FilterList(
                FilterList.Operator.MUST_PASS_ONE, filters);
        scan.setFilter(filterList);
        rs = table.getScanner(scan);
        for (Result result : rs){
            list.add(convertToData(result));
        }
        table.close();
        return list;
    }

    /**
     * 混合条件查询
     * @param queryConditionList
     * @return
     * @throws IOException
     */
    public List<ResBatteryDataHistory> QueryDataByConditions(List<QueryCondition> queryConditionList) throws IOException {
        if(queryConditionList==null || queryConditionList.size()<1){
            return null;
        }
        List<ResBatteryDataHistory> list=new ArrayList<>() ;
        Scan scan = new Scan();
        FilterList filterList = null;
        FilterList.Operator operator = null;
        List<Filter> filters = new ArrayList<Filter>();
        ResultScanner rs = null;
        for(QueryCondition query : queryConditionList){

            SingleColumnValueFilter filter = new SingleColumnValueFilter(
                    Bytes.toBytes(query.getFamily()), Bytes.toBytes(query.getQualifier()),
                    query.getCompareOp(),Bytes.toBytes(query.getValue()));
            filter.setFilterIfMissing(true);  //去掉没有这种列的数据

            if(query.getOperator()!=null){//有操作符的时候
                if (operator == null) {
                    operator = query.getOperator();
                    filterList = new FilterList(
                            query.getOperator());
                    filterList.addFilter(filter);
                    System.out.println("filterList==1" + filterList);
                } else if (operator.equals(query.getOperator())) {
                    filterList.addFilter(filter);
                } else {
                    filterList.addFilter(filter);
                    System.out.println("filterList==2" + filterList);
                    FilterList addFilterList = new FilterList(
                            query.getOperator());
                    addFilterList.addFilter(filterList);
                    System.out.println("addFilterList==1" + addFilterList);
                    filterList = addFilterList;
                    System.out.println("filterList==3" + filterList);
                }
            } else {
                if (filterList == null) {
                    filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);// 默认只有一个条件的时候
                }
                filterList.addFilter(filter);
            }
        }
        scan.setFilter(filterList);
        rs = table.getScanner(scan);
        for (Result result : rs){
            list.add(convertToData(result));
        }
        table.close();
        return list;
    }

    /**
     *混合条件分页查询
     * @param queryConditionList
     * @param pageSize
     * @param lastRow
     * @return
     */
    public  List<ResBatteryDataHistory>  QueryDataByConditionsAndPage(List<QueryCondition> queryConditionList, int pageSize,byte[] lastRow) throws IOException {
        List<ResBatteryDataHistory> list=new ArrayList<>() ;
        final byte[] POSTFIX = new byte[] { 0x00 };
        ResultScanner rs = null;
        Scan scan = new Scan();
        FilterList filterList = null;
        FilterList.Operator operator = null;

        //拼接查询条件
        for(QueryCondition query : queryConditionList){
            SingleColumnValueFilter filter = new SingleColumnValueFilter(
                    Bytes.toBytes(query.getFamily()), Bytes.toBytes(query.getQualifier()),
                    query.getCompareOp(),Bytes.toBytes(query.getValue()));
            filter.setFilterIfMissing(true);  //去掉没有这种列的数据

            if(query.getOperator()!=null){//有操作符的时候
                if (operator == null) {
                    operator = query.getOperator();
                    filterList = new FilterList(
                            query.getOperator());
                    filterList.addFilter(filter);
                    System.out.println("filterList==1" + filterList);
                } else if (operator.equals(query.getOperator())) {
                    filterList.addFilter(filter);
                } else {
                    filterList.addFilter(filter);
                    System.out.println("filterList==2" + filterList);
                    FilterList addFilterList = new FilterList(
                            query.getOperator());
                    addFilterList.addFilter(filterList);
                    System.out.println("addFilterList==1" + addFilterList);
                    filterList = addFilterList;
                    System.out.println("filterList==3" + filterList);
                }
            } else {
                if (filterList == null) {
                    filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);// 默认只有一个条件的时候
                }
                filterList.addFilter(filter);
            }
        }

        FilterList pageFilterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);// 默认只有一个条件的时候
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
        for (Result result : rs){
            list.add(convertToData(result));
        }
        table.close();
        return list;
    }

    /**
     * 分页查询——无条件
     * @param pageSize
     * @param lastRow
     * @return
     * @throws IOException
     */
    public  List<ResBatteryDataHistory>  QueryDataByPage( int pageSize,byte[] lastRow) throws IOException {
        List<ResBatteryDataHistory> list=new ArrayList<>() ;
        final byte[] POSTFIX = new byte[] { 0x00 };
        Scan scan = new Scan();
        ResultScanner resultScanner = null;
        Table table = null;
        Filter filter = new PageFilter(pageSize);
        scan.setFilter(filter);
        if (lastRow != null) {
            // 注意这里添加了POSTFIX操作，不然死循环了
            byte[] startRow = Bytes.add(lastRow, POSTFIX);
            scan.setStartRow(startRow);
        }
        resultScanner = table.getScanner(scan);
        for (Result result : resultScanner){
            list.add(convertToData(result));
        }
        table.close();
        return list;
    }


    /**
     * 查询总行数
     * @param pageSize
     * @return
     */
    public int QueryDataByPage(int pageSize) throws IOException {
        final byte[] POSTFIX = new byte[] { 0x00 };
        int totalRows = 0;
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
        return totalRows;
    }

    /**
     * 将Result转换成电池历史数据对象
     * @param result
     * @return
     */
    private ResBatteryDataHistory convertToData(Result result){
        ResBatteryDataHistory resBatteryDataHistory=new ResBatteryDataHistory();
        if(result==null || result.list().size()<1){
            return null;
        }
        for(KeyValue kv :result.list()){
            if(Bytes.toString(kv.getFamilyArray()).equals(colunm_family_baseData)){
                switch (Bytes.toString(kv.getQualifier())){
                    case "battery_no":
                        resBatteryDataHistory.setBatteryNo(Bytes.toString(kv.getValue()));
                        break;
                    case "battery_type":
                        resBatteryDataHistory.setBatteryType(Integer.parseInt(Bytes.toString(kv.getValue())));
                        break;
                    case "voltage_deviation":
                        resBatteryDataHistory.setVoltageDeviation(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "total_voltage":
                        resBatteryDataHistory.setVoltageDeviation(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "total_current":
                        resBatteryDataHistory.setTotalCurrent(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol1":
                        resBatteryDataHistory.setVol1(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol2":
                        resBatteryDataHistory.setVol2(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol3":
                        resBatteryDataHistory.setVol3(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol4":
                        resBatteryDataHistory.setVol4(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol5":
                        resBatteryDataHistory.setVol5(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol6":
                        resBatteryDataHistory.setVol6(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol7":
                        resBatteryDataHistory.setVol7(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol8":
                        resBatteryDataHistory.setVol8(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol9":
                        resBatteryDataHistory.setVol9(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol10":
                        resBatteryDataHistory.setVol10(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol11":
                        resBatteryDataHistory.setVol11(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol12":
                        resBatteryDataHistory.setVol12(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol13":
                        resBatteryDataHistory.setVol13(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol14":
                        resBatteryDataHistory.setVol14(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol15":
                        resBatteryDataHistory.setVol15(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol16":
                        resBatteryDataHistory.setVol16(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol17":
                        resBatteryDataHistory.setVol17(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol18":
                        resBatteryDataHistory.setVol18(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "vol19":
                        resBatteryDataHistory.setVol19(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "temprature1":
                        resBatteryDataHistory.setTemprature1(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "temprature2":
                        resBatteryDataHistory.setTemprature2(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "charge_num":
                        resBatteryDataHistory.setChargeNum(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "soc":
                        resBatteryDataHistory.setSoc(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "create_time":
                        resBatteryDataHistory.setCreateTime(Bytes.toString(kv.getValue()));
                        break;
                    case "city_id":
                        resBatteryDataHistory.setCityId(Integer.parseInt(Bytes.toString(kv.getValue())));
                        break;
                    case "vehicle_id":
                        resBatteryDataHistory.setVehicleId(Integer.parseInt(Bytes.toString(kv.getValue())));
                        break;
                }
            }
            if(Bytes.toString(kv.getFamilyArray()).equals(colunm_family_extraData)){
                switch (Bytes.toString(kv.getQualifier())){
                    case "create_user" :
                        resBatteryDataHistory.setCreateUser(Integer.parseInt(Bytes.toString(kv.getValue())));
                        break;
                    case "source" :
                        resBatteryDataHistory.setSource(Integer.parseInt(Bytes.toString(kv.getValue())));
                        break;
                    case "longtitude" :
                        resBatteryDataHistory.setLongtitude(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "latitude" :
                        resBatteryDataHistory.setLatitude(Float.parseFloat(Bytes.toString(kv.getValue())));
                        break;
                    case "remarks" :
                        resBatteryDataHistory.setRemarks(Bytes.toString(kv.getValue()));
                        break;
                    case "test_user_name" :
                        resBatteryDataHistory.setTestUserName(Bytes.toString(kv.getValue()));
                        break;
                }
            }
        }
        return  resBatteryDataHistory;
    }



   }
