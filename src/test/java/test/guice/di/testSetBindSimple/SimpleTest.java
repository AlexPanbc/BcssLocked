package test.guice.di.testSetBindSimple;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/30
 * \* Time: 上午12:31
 * \* Description:
 * \
 */
public class SimpleTest {

    public static void main(String[] args) {

        //获取简单set
        SimpleTest test= Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Set.class).to(HashSet.class);
            }
        }).getInstance(SimpleTest.class);


    }
}
