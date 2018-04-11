package test.guice.di.testSimple;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 上午9:37
 * \* Description:
 * \
 */
public class LogServiceImpl implements LogService {

    @Override
    public void log(String msg) {
        System.out.println("------LOG:" + msg);
    }
}
