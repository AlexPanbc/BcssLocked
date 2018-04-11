package test.Guava;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/30
 * \* Time: 下午6:12
 * \* Description:简单guava使用
 * \
 */
public class simpleGuavaTest {


    /**
     * Optional 类使用
     *      可能为null的T类型引用
     */
    @Test
    public void optionalTest(){
        Integer value1=null;
        Integer value2=10;
        Optional<Integer> a=Optional.fromNullable(value1);
        Optional<Integer> b=Optional.of(value2);
        System.out.println("sum="+sum(a,b));
    }

    private Integer sum(Optional<Integer> a,Optional<Integer> b){
        System.out.println("first param is present:"+a.isPresent());
        System.out.println("second param is present:"+b.isPresent());
        Integer value1;
        Integer value2;
        if(a.isPresent()){
            value1=a.get();//返回所包含的实例,它必须存在,通常在调用该方法时会调用isPresent()判断是否为null
        }else {
            value1=a.or(0);//返回Optional所包含的引用,若引用缺失,返回指定的值
        }
        if(b.isPresent()){
            value2=b.get();
        }else{
            value2=b.or(0);
        }
        return value1+value2;
    }

    /**
     *      前置条件Preconditions提供静态方法来检查方法或构造函数，
     *          被调用是否给定适当的参数。
     *          它检查的先决条件。
     *          其方法失败抛出IllegalArgumentException
     */
    @Test
    public void preconditionsTest(){
        //System.out.println(getValue(10)); //检查数组索引越界

        /**
         * 检查null值
         */
       /* Object target=null;
        Preconditions.checkNotNull(target);*/

        /**
         * 条件检查
         */
        Preconditions.checkArgument(1>4);
    }

    private int getValue(int dataIndex){
        int[] data={0,1,2,3,4};
        int index= Preconditions.checkElementIndex(dataIndex,data.length);
        return data[index];
    }

    /**
     *  Joiner 提供了各种方法来处理字符串加入操作，对象等。
     *  Joiner的实例不可变的，因此是线程安全的。
     */
    @Test
    public void JoinerTest(){
        StringBuilder sb=new StringBuilder();
        Joiner.on(",").skipNulls().appendTo(sb,"hello","lhc");
        //System.out.println(sb);
        //System.out.println(Joiner.on(",").useForNull("none").join(1,null,3));//1,none,3
        //System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1,2,3,null)));//1,2,3
        Map<String,String> map=new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));//key1=value1,key2=value2,key3=value3
    }

    /**
     * Splitter 能够将一个字符串按照指定的分隔符拆分成可迭代遍历的字符串集合，Iterable
     *
     *
     on():指定分隔符来分割字符串
     limit():当分割的子字符串达到了limit个时则停止分割
     fixedLength():根据长度来拆分字符串
     trimResults():去掉子串中的空格
     omitEmptyStrings():去掉空的子串
     withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
     */
    @Test
    public void SplitterTest(){
        //System.out.println(Splitter.on(",").limit(2).trimResults().split("1,2,3,4"));//[1, 2,3,4]
        //System.out.println(Splitter.fixedLength(3).split("1 2 3"));//[1 2,  3]
        //System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1 2  3"));//[1, 2, 3]
        //System.out.println(Splitter.on(",").omitEmptyStrings().splitToList("1,,,,,,,2,23"));//[1, 2, 23]
        //System.out.println(Splitter.on(";").omitEmptyStrings().withKeyValueSeparator(":").split("key1:v1;key2:v2;key3:v3;"));//{key1=v1, key2=v2, key3=v3}
    }


}
