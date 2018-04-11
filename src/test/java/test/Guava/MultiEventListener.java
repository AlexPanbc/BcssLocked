package test.Guava;

import com.google.common.eventbus.Subscribe;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/3
 * \* Time: 下午5:13
 * \* Description:多个订阅者
 * \
 */
public class MultiEventListener {

    @Subscribe
    public void listen(OrderEvent event){
        System.out.println("MultiEventListener receive msg: "+event.getMessage());
    }

    @Subscribe
    public void listen(String message){
        System.out.println("MultiEventListener receive msg: "+message);
    }

}
