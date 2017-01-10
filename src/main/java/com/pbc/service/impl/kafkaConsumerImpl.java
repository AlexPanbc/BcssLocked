package com.pbc.service.impl;

import com.pbc.service.kafkaConsumerService;
import com.pbc.utils.Tools.HbaseHelper;
import com.pbc.utils.exceptions.HbaseModel;
import net.sf.json.JSONArray;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panbingcan on 2017/1/10.
 */
@Service
public class kafkaConsumerImpl implements kafkaConsumerService {

    private static Logger log = LogManager.getLogger(kafkaConsumerImpl.class);

    @Override
    public void instBattery(int id) {
        try {
            log.debug("人序号：" + id);
            List<HbaseModel.InsertRowData> list = new ArrayList<>();
            HbaseModel.InsertRowData ird = new HbaseModel().new InsertRowData();
            String sid = HbaseHelper.padLeft(id);
            ird.setRowKey(sid);
            List<HbaseModel.InsertCellData> listCd = new ArrayList<>();
            HbaseModel.InsertCellData icdsj = new HbaseModel().new InsertCellData();
            icdsj.setFamily("dc");//电池 列族
            icdsj.setColumn("sj");//时间
            icdsj.setValue(Long.toString(System.currentTimeMillis()));
            listCd.add(icdsj);
            HbaseModel.InsertCellData icddl = new HbaseModel().new InsertCellData();
            icddl.setFamily("dc");//电池 列族
            icddl.setColumn("dl");//电量
            icddl.setValue(Integer.toString(new java.util.Random().nextInt(100) + 1));
            listCd.add(icddl);
            HbaseModel.InsertCellData icddy = new HbaseModel().new InsertCellData();
            icddy.setFamily("dc");//电池 列族
            icddy.setColumn("dy");//电压
            icddy.setValue(Integer.toString(new java.util.Random().nextInt(72) + 1));
            listCd.add(icddy);
            HbaseModel.InsertCellData icdwd = new HbaseModel().new InsertCellData();
            icdwd.setFamily("dc");//电池 列族
            icdwd.setColumn("wd");//温度
            icdwd.setValue(Integer.toString(new java.util.Random().nextInt(42) + 1));
            listCd.add(icdwd);
            ird.setColumns(listCd);
            list.add(ird);
            HbaseHelper.inst("t1", list);
        } catch (Exception e) {
            System.out.println(JSONArray.fromObject(e));
        }
    }
}
