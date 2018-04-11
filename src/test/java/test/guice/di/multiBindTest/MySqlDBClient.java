package test.guice.di.multiBindTest;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 下午11:08
 * \* Description:mysql数据库连接client
 * \
 */
public class MySqlDBClient implements DbInterface {
    @Override
    public void connect() {
        System.out.println("mysql connected");
    }
}
