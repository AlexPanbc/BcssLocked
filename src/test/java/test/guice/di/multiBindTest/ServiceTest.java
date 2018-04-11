package test.guice.di.multiBindTest;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.kenai.jaffl.annotations.In;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.inject.Named;


/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 下午11:11
 * \* Description:测试获取client类实例
 * \
 */
public class ServiceTest {

    private static Injector injector;

    @Inject
    @Named("mysql")
    private DbInterface dbInterface;


    public static void main(String[] args) {
        ServiceTest test=Guice.createInjector(new ConfigBindModel()).getInstance(ServiceTest.class);
        test.dbInterface.connect();
    }
}
