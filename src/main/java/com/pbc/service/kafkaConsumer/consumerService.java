package com.pbc.service.kafkaConsumer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.pbc.service.kafkaConsumerService;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.log4j.LogManager;
import org.eclipse.jetty.util.ajax.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by panbingcan on 2017/1/12.
 */
public class consumerService extends Thread {

    private static Logger log = LogManager.getLogger(consumerService.class);
    private final ConsumerConnector consumer;
    private final String topic;
    @Autowired
    private kafkaConsumerService userService;

    public static void main(String[] args) {

        consumerService consumerThread = new consumerService("dianchi");
        // consumerThread.setDaemon(true);
        consumerThread.start();
    }

    public consumerService(String topic) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setValidating(false);
        context.load("classpath:config/spring/applicationContext*.xml");
        context.refresh();
        userService = context.getBean(kafkaConsumerService.class);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        // 设置zookeeper的链接地址
        props.put("zookeeper.connect", "kafka-centos:2181");
        // 设置group id
        props.put("group.id", "1");
        // kafka的group 消费记录是保存在zookeeper上的, 但这个信息在zookeeper上不是实时更新的, 需要有个间隔时间更新
        props.put("auto.commit.interval.ms", "1000");
        props.put("zookeeper.session.timeout.ms", "10000");
        return new ConsumerConfig(props);
    }

    public void run() {
        //设置Topic=>Thread Num映射关系, 构建具体的流
        Map<String, Integer> topickMap = new HashMap<String, Integer>();
        topickMap.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> streamMap = consumer.createMessageStreams(topickMap);

        KafkaStream<byte[], byte[]> stream = streamMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        System.out.println("~!@#$%^&*()_++_)(*&^%$#@!~~!@#$%^&*()_+");
        while (it.hasNext()) {
            try {
                int id = Integer.parseInt(new String(it.next().message()));
                System.out.println("输出:" + id);
                if (id > 0) userService.instBattery(id);
            } catch (NumberFormatException e) {
                System.out.println(JSON.toString(e));
            }
        }
    }
}
