package test.HbaseTest;

import org.apache.activemq.openwire.tool.MultiSourceGenerator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：测试HBase的工具类
 * Created by liuhuichao on 2016/12/14.
 */
public class HBaseBatteryManagerTest {

    HBaseBatteryDataManager manager= HBaseBatteryDataManager.getInstance();

    /**
     * 测试Hbase的单条Put操作
     */
    @Test
    public void testPut() throws IOException {
        ResBatteryDataHistory battery=new ResBatteryDataHistory();
        battery.setBatteryNo("4015106002");
        battery.setBatteryType(0);
        battery.setVoltageDeviation(1F);
        battery.setTotalVoltage(1F);
        battery.setCreateTime("2016-12-12 11:11:11");
        battery.setCreateUser(250);
        battery.setSource(3);
        battery.setVol1(1F);
        battery.setVol2(1F);
        battery.setVol3(1F);
        battery.setVol4(1F);
        battery.setVol5(1F);
        battery.setVol6(1F);
        battery.setVol7(1F);
        battery.setVol8(1F);
        battery.setVol9(1F);
        battery.setVol10(1F);
        battery.setVol11(1F);
        battery.setVol12(1F);
        battery.setVol13(1F);
        battery.setVol14(1F);
        battery.setVol15(1F);
        battery.setVol16(1F);
        battery.setVol17(1F);
        battery.setVol18(1F);
        battery.setVol19(1F);
        battery.setTemprature1(1F);
        battery.setTemprature2(1F);
        battery.setChargeNum(11F);
        battery.setLongtitude(12F);
        battery.setLatitude(12F);
        battery.setRemarks("lhc-代码测试");
        battery.setTotalCurrent(22F);
        battery.setSoc(22F);
        battery.setTestUserName("lhc");
        battery.setCityId(11011);
        battery.setVehicleId(11011);
        manager.addBatteryData(battery);
    }

    @Test
    public void addBatteryDataListTest() throws IOException {
        List<ResBatteryDataHistory> batteryList=new ArrayList<>();
        for(int i=20000;i<200000;i++){
            ResBatteryDataHistory battery=new ResBatteryDataHistory();
            battery.setBatteryNo(String.valueOf(i));
            battery.setBatteryType(0);
            battery.setVoltageDeviation(1F);
            battery.setTotalVoltage(1F);
            battery.setCreateTime("2016-12-12 11:11:11");
            battery.setCreateUser(250);
            battery.setSource(3);
            battery.setVol1(1F);
            battery.setVol2(1F);
            battery.setVol3(1F);
            battery.setVol4(1F);
            battery.setVol5(1F);
            battery.setVol6(1F);
            battery.setVol7(1F);
            battery.setVol8(1F);
            battery.setVol9(1F);
            battery.setVol10(1F);
            battery.setVol11(1F);
            battery.setVol12(1F);
            battery.setVol13(1F);
            battery.setVol14(1F);
            battery.setVol15(1F);
            battery.setVol16(1F);
            battery.setVol17(1F);
            battery.setVol18(1F);
            battery.setVol19(1F);
            battery.setTemprature1(1F);
            battery.setTemprature2(1F);
            battery.setChargeNum(11F);
            battery.setLongtitude(12F);
            battery.setLatitude(12F);
            battery.setRemarks("lhc-代码测试");
            battery.setTotalCurrent(22F);
            battery.setSoc(22F);
            battery.setTestUserName("lhc");
            battery.setCityId(11011);
            battery.setVehicleId(11011);
            batteryList.add(battery);
        }
        long begin=System.currentTimeMillis();
        System.out.println("开始存入："+begin);
        manager.addBatteryDataList(batteryList);
        long end=System.currentTimeMillis();
        System.out.println("结束存入："+end);
        System.out.println((end-begin)/1000.0);
    }

    /**
     * 测试根据RowKey获取数据
     * @throws IOException
     */
    @Test
    public void testGetByRowKey() throws IOException {
        System.out.println(manager.getBatteryDataHistoryByRowKey("40151060021481685540182"));
    }

    /**
     * 测试获取单元格数据
     */
    @Test
    public void getCellDataTest() throws IOException {
        System.out.println(Bytes.toFloat(manager.getCellData("40151060021481685540182",manager.colunm_family_baseData,"vol1")));
    }


    /**
     * 更新用户名
     * @throws IOException
     */
    @Test
    public void updateCellTest() throws IOException {
        System.out.println(manager.getBatteryDataHistoryByRowKey("40151060021481685540182"));
        manager.updateCell("40151060021481685540182",manager.colunm_family_extraData,"test_user_name",Bytes.toBytes("liuhuichao128"));
        System.out.println(manager.getBatteryDataHistoryByRowKey("40151060021481685540182"));
    }

    /**
     * 测试表扫描
     * @throws IOException
     */
    @Test
    public void scanTest() throws IOException {
        System.out.println( manager.getResultScans().size());
    }


    /**
     * 测试and查询
     */
    @Test
    public void QueryDataByConditionsAnd() throws IOException {
        List<QueryCondition> queryConditionList=new ArrayList<>();
        queryConditionList.add(new QueryCondition(manager.colunm_family_baseData,"vol1", CompareFilter.CompareOp.EQUAL,Bytes.toBytes(1F)));
        long begin=System.currentTimeMillis();
        System.out.println(manager.QueryDataByConditionsAnd(queryConditionList).size());
        long end=System.currentTimeMillis();
        System.out.println((end-begin)/1000.00);
    }

    /**
     * 测试or查询
     * @throws IOException
     */
    @Test
    public void QueryDataByConditionsOrTest() throws IOException {
        List<QueryCondition> queryConditionList=new ArrayList<>();
        queryConditionList.add(new QueryCondition(manager.colunm_family_baseData,"vol1", CompareFilter.CompareOp.EQUAL,Bytes.toBytes(1F)));
        System.out.println(manager.QueryDataByConditionsOr(queryConditionList));
    }



}
