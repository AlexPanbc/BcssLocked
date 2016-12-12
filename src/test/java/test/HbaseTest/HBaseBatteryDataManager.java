package test.HbaseTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
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
        ResBatteryDataHistory resBatteryDataHistory=new ResBatteryDataHistory();
        for(KeyValue kv :result.list()){
            if(Bytes.toString(kv.getFamilyArray())==colunm_family_baseData){
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
            if(Bytes.toString(kv.getFamilyArray())==colunm_family_extraData){
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
        return resBatteryDataHistory;
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



   }
