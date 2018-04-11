package test.guice.di.testSimple;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 上午9:55
 * \* Description:
 * \
 */
public class MyAppTest {

    private static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new MyAppModule());//通过配置类初始化guice
    }

    @Test
    public void testMyApp() {
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }



}
