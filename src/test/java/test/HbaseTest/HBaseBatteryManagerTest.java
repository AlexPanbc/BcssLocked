package test.HbaseTest;

import org.apache.activemq.openwire.tool.MultiSourceGenerator;
import org.junit.Test;

import java.io.IOException;

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

    /**
     * 测试根据RowKey获取数据
     * @throws IOException
     */
    @Test
    public void testGetByRowKey() throws IOException {
        System.out.println(manager.getBatteryDataHistoryByRowKey("40151060021481685540182"));
    }

}
