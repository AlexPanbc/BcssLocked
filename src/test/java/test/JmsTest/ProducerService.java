package test.JmsTest;

import javax.jms.Destination;

/**
 *  * 功能：消息的生产者接口，定义发送消息的规范
 * Created by liuhuichao on 2016/11/24.
 */
public interface ProducerService {
     void sendMessage(Destination destination, final String message);
}
