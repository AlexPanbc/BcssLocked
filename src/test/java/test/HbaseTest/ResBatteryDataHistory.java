package test.HbaseTest;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能：电池历史数据数据结构
 * Created by liuhuichao on 2016/12/5.
 */
public class ResBatteryDataHistory implements Serializable {

    private Integer id;

    private String batteryNo;

    private Integer batteryType;

    private Float voltageDeviation;

    private Float totalVoltage;

    private Date createTime;

    private Integer createUser;

    private Integer source;

    private Float vol1;

    private Float vol2;

    private Float vol3;

    private Float vol4;

    private Float vol5;

    private Float vol6;

    private Float vol7;

    private Float vol8;

    private Float vol9;

    private Float vol10;

    private Float vol11;

    private Float vol12;

    private Float vol13;

    private Float vol14;

    private Float vol15;

    private Float vol16;

    private Float vol17;

    private Float vol18;

    private Float vol19;

    private Float temprature1;

    private Float temprature2;

    private Float chargeNum;

    private Float longtitude;

    private Float latitude;

    private String remarks;

    private Float totalCurrent;

    private Float soc;

    private String testUserName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatteryNo() {
        return batteryNo;
    }

    public void setBatteryNo(String batteryNo) {
        this.batteryNo = batteryNo;
    }

    public Integer getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(Integer batteryType) {
        this.batteryType = batteryType;
    }

    public Float getVoltageDeviation() {
        return voltageDeviation;
    }

    public void setVoltageDeviation(Float voltageDeviation) {
        this.voltageDeviation = voltageDeviation;
    }

    public Float getTotalVoltage() {
        return totalVoltage;
    }

    public void setTotalVoltage(Float totalVoltage) {
        this.totalVoltage = totalVoltage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Float getVol1() {
        return vol1;
    }

    public void setVol1(Float vol1) {
        this.vol1 = vol1;
    }

    public Float getVol2() {
        return vol2;
    }

    public void setVol2(Float vol2) {
        this.vol2 = vol2;
    }

    public Float getVol3() {
        return vol3;
    }

    public void setVol3(Float vol3) {
        this.vol3 = vol3;
    }

    public Float getVol4() {
        return vol4;
    }

    public void setVol4(Float vol4) {
        this.vol4 = vol4;
    }

    public Float getVol5() {
        return vol5;
    }

    public void setVol5(Float vol5) {
        this.vol5 = vol5;
    }

    public Float getVol6() {
        return vol6;
    }

    public void setVol6(Float vol6) {
        this.vol6 = vol6;
    }

    public Float getVol7() {
        return vol7;
    }

    public void setVol7(Float vol7) {
        this.vol7 = vol7;
    }

    public Float getVol8() {
        return vol8;
    }

    public void setVol8(Float vol8) {
        this.vol8 = vol8;
    }

    public Float getVol9() {
        return vol9;
    }

    public void setVol9(Float vol9) {
        this.vol9 = vol9;
    }

    public Float getVol10() {
        return vol10;
    }

    public void setVol10(Float vol10) {
        this.vol10 = vol10;
    }

    public Float getVol11() {
        return vol11;
    }

    public void setVol11(Float vol11) {
        this.vol11 = vol11;
    }

    public Float getVol12() {
        return vol12;
    }

    public void setVol12(Float vol12) {
        this.vol12 = vol12;
    }

    public Float getVol13() {
        return vol13;
    }

    public void setVol13(Float vol13) {
        this.vol13 = vol13;
    }

    public Float getVol14() {
        return vol14;
    }

    public void setVol14(Float vol14) {
        this.vol14 = vol14;
    }

    public Float getVol15() {
        return vol15;
    }

    public void setVol15(Float vol15) {
        this.vol15 = vol15;
    }

    public Float getVol16() {
        return vol16;
    }

    public void setVol16(Float vol16) {
        this.vol16 = vol16;
    }

    public Float getVol17() {
        return vol17;
    }

    public void setVol17(Float vol17) {
        this.vol17 = vol17;
    }

    public Float getVol18() {
        return vol18;
    }

    public void setVol18(Float vol18) {
        this.vol18 = vol18;
    }

    public Float getVol19() {
        return vol19;
    }

    public void setVol19(Float vol19) {
        this.vol19 = vol19;
    }

    public Float getTemprature1() {
        return temprature1;
    }

    public void setTemprature1(Float temprature1) {
        this.temprature1 = temprature1;
    }

    public Float getTemprature2() {
        return temprature2;
    }

    public void setTemprature2(Float temprature2) {
        this.temprature2 = temprature2;
    }

    public Float getChargeNum() {
        return chargeNum;
    }

    public void setChargeNum(Float chargeNum) {
        this.chargeNum = chargeNum;
    }

    public Float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Float longtitude) {
        this.longtitude = longtitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Float getTotalCurrent() {
        return totalCurrent;
    }

    public void setTotalCurrent(Float totalCurrent) {
        this.totalCurrent = totalCurrent;
    }

    public Float getSoc() {
        return soc;
    }

    public void setSoc(Float soc) {
        this.soc = soc;
    }

    public String getTestUserName() {
        return testUserName;
    }

    public void setTestUserName(String testUserName) {
        this.testUserName = testUserName;
    }
}
