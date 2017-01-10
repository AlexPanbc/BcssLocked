package com.pbc.utils.Tools.kafka;

/**
 * Created by panbingcan on 2016/12/31.
 */

import java.util.Date;
import java.util.Properties;
import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSONObject;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;


public class Producertest {
    public Producertest() {
        Properties pt = new Properties();
        pt.put("zk.connect", "kafka-centos:2181");
        pt.put("serializer.class", StringEncoder.class.getName());
        pt.put("metadata.broker.list", "kafka-centos:9092");
        pt.put("request.required.acks", "1");
        pt.put("num.partitions", "6");
        props = pt;
    }

    public static Properties getProps() {
        return props;
    }

    public static void setProps(Properties props) {
        Producertest.props = props;
    }

    private static Properties props;

    /***
     * 生产着
     * @param id
     */
    public static void producer(int id) {
        new Producer<String, String>(new ProducerConfig(props)).send(new KeyedMessage("test", id));
    }

    public static void aaa(String[] args) {
        Properties props = new Properties();
        props.put("zk.connect", "kafka-centos:2181");
        // serializer.class为消息的序列化类
//        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("serializer.class", StringEncoder.class.getName());
        // 配置metadata.broker.list, 为了高可用, 最好配两个broker实例
        props.put("metadata.broker.list", "kafka-centos:9092");
        // 设置Partition类, 对队列进行合理的划分
        //props.put("partitioner.class", "idoall.testkafka.Partitionertest");
        // ACK机制, 消息发送需要kafka服务端确认
        props.put("request.required.acks", "1");

        props.put("num.partitions", "1");
//        ProducerConfig config = new ProducerConfig(props);
//        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));
//        for (int i = 0; i < 3; i++) {
            // KeyedMessage<K, V>
            // 　　K对应Partition Key的类型

//　　 topic: "test", key: "key", message: "message"           // 　　V对应消息本身的类型
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SSS");
//            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//            String str = formatter.format(curDate);

//            String msg = "第  " + i + " = " + str;
//            String key = i + "";
            new Producer<String, String>(new ProducerConfig(props)).send(new KeyedMessage("dianchi", 2337));
            //   new Producer<String, String>(new ProducerConfig(props)).send(new KeyedMessage("test", key, msg));
//            producer.send(new KeyedMessage<String, String>("test", key, msg));
//        }
    }
}
