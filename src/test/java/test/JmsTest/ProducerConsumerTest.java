package test.JmsTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * 测试消息队列
 * Created by liuhuichao on 2016/11/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext-activeMQ.xml"})
public class ProducerConsumerTest {

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Test
    public void sendMsg(){
        for (int i=0;i<2;i++){
            producerService.sendMessage(destination,"["+i+"]");
        }
    }
}
