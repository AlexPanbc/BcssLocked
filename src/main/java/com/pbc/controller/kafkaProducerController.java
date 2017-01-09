package com.pbc.controller;

import com.pbc.utils.Tools.BaseController;
import com.pbc.utils.Tools.HbaseHelper;
import com.pbc.utils.exceptions.HbaseModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panbingcan on 2017/1/9.
 */
public class kafkaProducerController extends BaseController {

    private static Logger log = LogManager.getLogger(kafkaProducerController.class);

    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String get(@PathVariable("id") int id) {
        log.debug("根据id查询订单信息，订单id为：" + toJSONString(id));
        HbaseModel.InsertRowData ird = new HbaseModel().new InsertRowData();
        ird.setRowKey(HbaseHelper.padLeft(id));
        List<HbaseModel.InsertCellData> listCd = new ArrayList<>();
        HbaseModel.InsertCellData icdsj = new HbaseModel().new InsertCellData();
        icdsj.setFamily("dc");//电池 列族
        icdsj.setColumn("sj");//时间
        icdsj.setValue(Long.toString(System.currentTimeMillis()));
        listCd.add(icdsj);
        HbaseModel.InsertCellData icddl = new HbaseModel().new InsertCellData();
        icddl.setFamily("dc");//电池 列族
        icddl.setColumn("dl");//时间
//        icddl.setValue(Integer.toString(Math.random()));
        listCd.add(icddl);
        return toJSONString("");
    }
}
