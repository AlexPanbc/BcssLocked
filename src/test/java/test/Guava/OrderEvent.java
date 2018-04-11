package test.Guava;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/3
 * \* Time: 下午4:59
 * \* Description: 发布-订阅模式中传递的事件,是一个普通的POJO类
 * \
 */
public class OrderEvent {

    private String message;

    public OrderEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
