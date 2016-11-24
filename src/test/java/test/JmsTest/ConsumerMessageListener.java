package test.JmsTest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 功能：定义消息处理的Listener
 *   要定义处理消息的MessageListener我们只需要实现JMS规范中的MessageListener接口就可以了。
 *   MessageListener接口中只有一个方法onMessage方法，当接收到消息的时候会自动调用该方法。
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
