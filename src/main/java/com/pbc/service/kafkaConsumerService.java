package com.pbc.service;

/**
 * Created by panbingcan on 2017/1/10.
 */
public interface kafkaConsumerService {

    /**
     * 插入hbase
     *
     * @param id
     * @return
     */
     void instBattery(int id);

    void inst(int id);
}
