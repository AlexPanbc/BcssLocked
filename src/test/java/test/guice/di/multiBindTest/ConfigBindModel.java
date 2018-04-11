package test.guice.di.multiBindTest;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 下午11:32
 * \* Description:
 * \
 */
public class ConfigBindModel extends AbstractModule {
    @Override
    protected void configure() {
        bind(DbInterface.class).annotatedWith(Names.named("mysql")).to(MySqlDBClient.class).asEagerSingleton();
        bind(DbInterface.class).annotatedWith(Names.named("hbase")).to(HBaseDBClient.class).asEagerSingleton();
    }
}
