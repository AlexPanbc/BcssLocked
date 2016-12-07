package test.HbaseTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.UUID;

/**
 * 功能：迁移mysql上电池历史数据到hbase
 * Created by liuhuichao on 2016/12/6.
 */
public class MySqlToHBase {

    /**
     * 获取表
     * @param tableName
     * @return
     * @throws IOException
     */
    private  HTable connectHBase(String tableName) throws IOException{
        HTable table=null;
        Configuration conf= HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","lhc-centos");
        table=new HTable(conf,tableName);
        return table;
    }

    /**
     * 获取mysql连接
     * @return
     * @throws Exception
     */
    private  java.sql.Connection connectDB() throws  Exception{
        String userName="root";
        String password="root";
        String url="jdbc:mysql://10.0.1.42:3306/energy?useUnicode=true&characterEncoding=UTF-8";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        java.sql.Connection conn=DriverManager .getConnection(url,userName,password);
        return  conn;
    }

    /**
     * 将电池历史数据表中输入导入HBase
     * @throws Exception
     */
    @Test
    public void exportFromMySqlToHBase() throws Exception{
        java.sql.Connection dbConn=null;
        HTable table=null;

        Statement stmt=null;
        String strQuery="SELECT * FROM `res_battery_data_history` limit 10000,100000";
        dbConn=connectDB();//连接mysql
        table=connectHBase("batteryDataHistory");//连接HBase
        try{

            stmt=dbConn.createStatement();
            ResultSet rs=stmt.executeQuery(strQuery);
            long beginTime=System.currentTimeMillis();//开始
            System.out.println( "beginTime:---"+beginTime);
            while (rs.next()) {
                UUID uuid = UUID.randomUUID();
                String rowKey = uuid.toString();//作为行健
                Put put = new Put(Bytes.toBytes(rowKey));
                /**
                 * family:baseData
                 */
                // Integer id=rs.getInt("id");
                String batteryNo = rs.getString("battery_no");
                Integer batteryType = rs.getInt("battery_type");
                Float voltageDeviation = rs.getFloat("voltage_deviation");
                Float totalVoltage = rs.getFloat("total_voltage");
                Float temprature1 = rs.getFloat("temprature1");
                Float temprature2 = rs.getFloat("temprature2");
                Float chargeNum = rs.getFloat("charge_num");
                Float longtitude = rs.getFloat("longtitude");
                Float latitude = rs.getFloat("latitude");
                Float totalCurrent = rs.getFloat("total_current");
                Float soc = rs.getFloat("soc");

                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("batteryNo"), Bytes.toBytes(batteryNo));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("batteryType"), Bytes.toBytes(batteryType));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("voltageDeviation"), Bytes.toBytes(voltageDeviation));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("totalVoltage"), Bytes.toBytes(totalVoltage));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("temprature1"), Bytes.toBytes(temprature1));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("temprature2"), Bytes.toBytes(temprature2));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("chargeNum"), Bytes.toBytes(chargeNum));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("longtitude"), Bytes.toBytes(longtitude));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("latitude"), Bytes.toBytes(latitude));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("totalCurrent"), Bytes.toBytes(totalCurrent));
                put.add(Bytes.toBytes("baseData"), Bytes.toBytes("soc"), Bytes.toBytes(soc));
                /**
                 * family:volumnData
                 */
                Float vol1 = rs.getFloat("vol1");
                Float vol2 = rs.getFloat("vol2");
                Float vol3 = rs.getFloat("vol3");
                Float vol4 = rs.getFloat("vol4");
                Float vol5 = rs.getFloat("vol5");
                Float vol6 = rs.getFloat("vol6");
                Float vol7 = rs.getFloat("vol7");
                Float vol8 = rs.getFloat("vol8");
                Float vol9 = rs.getFloat("vol9");
                Float vol10 = rs.getFloat("vol10");
                Float vol11 = rs.getFloat("vol11");
                Float vol12 = rs.getFloat("vol12");
                Float vol13 = rs.getFloat("vol13");
                Float vol14 = rs.getFloat("vol14");
                Float vol15 = rs.getFloat("vol15");
                Float vol16 = rs.getFloat("vol16");
                Float vol17 = rs.getFloat("vol17");
                Float vol18 = rs.getFloat("vol18");
                Float vol19 = rs.getFloat("vol19");
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v1"), Bytes.toBytes(vol1));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v2"), Bytes.toBytes(vol2));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v3"), Bytes.toBytes(vol3));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v4"), Bytes.toBytes(vol4));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v5"), Bytes.toBytes(vol5));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v6"), Bytes.toBytes(vol6));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v7"), Bytes.toBytes(vol7));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v8"), Bytes.toBytes(vol8));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v9"), Bytes.toBytes(vol9));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v10"), Bytes.toBytes(vol10));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v11"), Bytes.toBytes(vol11));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v12"), Bytes.toBytes(vol12));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v13"), Bytes.toBytes(vol13));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v14"), Bytes.toBytes(vol14));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v15"), Bytes.toBytes(vol15));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v16"), Bytes.toBytes(vol16));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v17"), Bytes.toBytes(vol17));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v18"), Bytes.toBytes(vol18));
                put.add(Bytes.toBytes("volumnData"), Bytes.toBytes("v19"), Bytes.toBytes(vol19));
                /**
                 * family:extraData
                 */
                String remarks = rs.getString("remarks");
                String testUserName = rs.getString("test_user_name");
                String createTime = rs.getString("create_time");
                Integer createUser = rs.getInt("create_user");
                Integer source = rs.getInt("source");
                put.add(Bytes.toBytes("extraData"), Bytes.toBytes("remarks"), Bytes.toBytes(remarks));
                put.add(Bytes.toBytes("extraData"), Bytes.toBytes("testUserName"), Bytes.toBytes(testUserName));
                put.add(Bytes.toBytes("extraData"), Bytes.toBytes("createTime"), Bytes.toBytes(createTime));
                put.add(Bytes.toBytes("extraData"), Bytes.toBytes("createUser"), Bytes.toBytes(createUser));
                put.add(Bytes.toBytes("extraData"), Bytes.toBytes("source"), Bytes.toBytes(source));
                table.put(put);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(stmt !=null){
                    stmt.close();
                }
                if(dbConn !=null){
                    dbConn.close();
                }
                if(table!=null){
                    table.close();
                }
                long endTime=System.currentTimeMillis();//开始
                System.out.println( "endTime:---"+endTime);
            }catch(Exception e){
                e.printStackTrace();
            }
        }








    }
}
