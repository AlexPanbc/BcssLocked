package test.Guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.nio.channels.MulticastChannel;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/3
 * \* Time: 下午4:50
 * \* Description:  EventBus test
 * \
 */
public class EventBusTest {

    /**
     * 发布订阅测试
     */
    @Test
    public void pubSubTest(){
        EventBus eventBus=new EventBus();
        eventBus.register(new EventListener());
        eventBus.register(new MultiEventListener());
        eventBus.post(new OrderEvent(" i love icecream"));
        eventBus.post(new OrderEvent("me me da"));
        eventBus.post("apples");
    }



}
