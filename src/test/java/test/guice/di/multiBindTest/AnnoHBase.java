package test.guice.di.multiBindTest;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 下午11:31
 * \* Description:需要绑定hbase客户端的注解标记
 * \
 */
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface AnnoHBase {

}
