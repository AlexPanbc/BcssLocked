package test.JmsTest;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 功能：消息的生产者实现类
 * Created by liuhuichao on 2016/11/24.
 */
public class ProducerServiceImpl implements ProducerService {

    @Resource
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Destination destination, final String message) {
        System.out.println("--------生产者发送了一个消息--------");
        System.out.println("生产者发送的消息内容为："+message);

        jmsTemplate.send(destination, new MessageCreator() {//通过jmsTemplate发送消息到目的消费者
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });

    }
}
