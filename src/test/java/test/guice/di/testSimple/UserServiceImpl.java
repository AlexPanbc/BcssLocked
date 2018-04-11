package test.guice.di.testSimple;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 上午9:35
 * \* Description:
 * \
 */
public class UserServiceImpl implements UserService {

    @Override
    public void process() {
        System.out.println("我需要的一些操作");
    }
}
