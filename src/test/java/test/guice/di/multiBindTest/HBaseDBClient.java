package test.guice.di.multiBindTest;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 下午11:08
 * \* Description:hbase 数据库连接客户端
 * \
 */
public class HBaseDBClient implements DbInterface {
    @Override
    public void connect() {
        System.out.println("hbase connected");
    }
}
