package test.JmsTest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 功能：定义消息处理的Listener
 * Created by liuhuichao on 2016/11/24.
 */
public class ConsumerMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage=(TextMessage)message;
        System.out.println("接收到了一个纯文本的消息");

        try {
            System.out.println("消息的内容为："+ textMessage.getText());
        } catch (JMSException e) {
            System.out.println("消息接收出现异常！");
            e.printStackTrace();
        }

    }
}
