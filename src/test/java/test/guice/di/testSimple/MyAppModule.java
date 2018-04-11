package test.guice.di.testSimple;

import com.google.inject.AbstractModule;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 上午9:51
 * \* Description:
 * \
 */
public class MyAppModule extends AbstractModule {

    /**
     * 调用AbstractModule类提供的一些方法来配置依赖关系
     */
    @Override
    protected void configure() {
        bind(LogService.class).to(LogServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(Application.class).to(MyApp.class);
    }
}
