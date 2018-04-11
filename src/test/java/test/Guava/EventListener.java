package test.Guava;

import com.google.common.eventbus.Subscribe;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/3
 * \* Time: 下午5:04
 * \* Description: 订阅者
 * \
 */
public class EventListener {


    @Subscribe
    public void listen(OrderEvent event){
        System.out.println(" EventListener receive message: "+event.getMessage());
    }

    /*
        一个subscriber也可以同时订阅多个事件
        Guava会通过事件类型来和订阅方法的形参来决定到底调用subscriber的哪个订阅方法
    */
    @Subscribe
    public void listen(String message){
        System.out.println("EventListener receive message: "+message);
    }

}
