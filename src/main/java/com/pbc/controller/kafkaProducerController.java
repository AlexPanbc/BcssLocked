package com.pbc.controller;

import com.pbc.utils.Tools.BaseController;
import com.pbc.utils.Tools.kafka.Producertest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by panbingcan on 2017/1/9.
 */
public class kafkaProducerController extends BaseController {

    private static Logger log = LogManager.getLogger(kafkaProducerController.class);

    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public void get(@PathVariable("id") int id) {
        log.debug("根据id查询订单信息，订单id为：" + toJSONString(id));
        Producertest.producer(id);
    }
}
