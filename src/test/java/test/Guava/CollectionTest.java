package test.Guava;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/3
 * \* Time: 下午5:42
 * \* Description:guava 集合测试
 * \
 */
public class CollectionTest {


    /**
     * 不可变集合
     */
    @Test
    public void immutableTest(){
        ImmutableSet<String> set= ImmutableSet.of("a","b","c"); //of创建不可变集合方法
        ImmutableSet<String> set1=ImmutableSet.copyOf(set);//copyOf创建不可变集合方法
        ImmutableSet<String> set2=ImmutableSet.<String>builder().addAll(set).add("e").build();
        System.out.println(set);
        ImmutableList<String> list=set.asList();
    }

    /**
     * 统计一个词在文档中出现了多少次
     */
    @Test
    public void multisetTest(){
        Multiset<String> set= LinkedHashMultiset.create();
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("a");
        set.setCount("a",6);//添加或删除指定元素使其在集合中的数量是count
        System.out.println(set.count("a"));
        System.out.println(set);
        System.out.println(set.size()); //所有元素计数的总和,包括重复元素
        System.out.println(set.elementSet().size()); //所有元素计数的总和,不包括重复元素
        set.clear(); //清空集合
        System.out.println(set);
    }

    /**
     * Multimap是把键映射到任意多个值的一般方式
     */
    @Test
    public void multimapTest(){
        Multimap<String,Integer> map=HashMultimap.create();//Multimap是把键映射到任意多个值的一般方式
        map.put("a",1);
        map.put("a",2);
        map.put("a",3);
        System.out.println(map);
        System.out.println(map.get("a"));
        System.out.println(map.size());
        System.out.println(map.keySet().size());
        Map<String ,Collection<Integer>> mapView=map.asMap();
        System.out.println(mapView);

    }

    /**
     * BiMap
     *  在BiMap中,如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常
        如果对特定值,你想要强制替换它的键，请使用 BiMap.forcePut(key, value)
     */
    @Test
    public void biMapTest(){
        BiMap bitMap=HashBiMap.create();
        bitMap.put("key1","v1");
        bitMap.put("key2","v2");
        bitMap.put("key2","v3");
        //bitMap.put("key3","v1");//异常
        bitMap.forcePut("key3","v1");

        System.out.println(bitMap);
        System.out.println(bitMap.get("key3"));
        System.out.println(bitMap.inverse().get("v1"));
        System.out.println(bitMap.inverse()==bitMap);
        System.out.println(bitMap.inverse().inverse()==bitMap);
    }

    /**
     * Table它有两个支持所有类型的键：”行”和”列”
     */
    @Test
    public void TableTest(){
        Table<String,String,Integer> table=HashBasedTable.create();
        table.put("jack","java",100);
        table.put("jack","c",30);
        table.put("lhc","java",12);
        table.put("lhc","c",2);
        Set<Table.Cell<String,String,Integer> > cells=table.cellSet();
        for(Table.Cell<String,String,Integer> cell : cells){
            System.out.println(cell.getRowKey()+"; "+cell.getColumnKey()+";" +cell.getValue());
        }
        System.out.println(table);
        System.out.println(table.row("jack"));
        System.out.println(table.rowKeySet());
        System.out.println(table.columnKeySet());
        System.out.println(table.values());

    }

    /**
     * filter
     *      集合过滤器
     */
    @Test
    public void Collections2FilterTest(){
        List<String>  list=Lists.newArrayList("lhc","mom","daad");
        Collection<String> palindromeList= Collections2.filter(list,input->{
            return new StringBuilder(input).reverse().toString().equals(input);
        });
        System.out.println(palindromeList);
    }

    /**
     * transform
     *      类型转换
     */
    @Test
    public void Collections2Transform(){
        Set<Long> times= Sets.newHashSet();
        times.add(91299990701L);
        times.add(9320001010L);
        times.add(9920170621L);
        Collection<String> timeStrCol= Collections2.transform(times, new Function<Long, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Long input) {
                return new SimpleDateFormat("yyyy-MM-dd").format(input);
            }
        });
        System.out.println(timeStrCol);
    }

    /**
     * 多个Function组合
     */
    @Test
    public void Collections2Function(){
        List<String> list=Lists.newArrayList("lhc","jay","lalalalalalalalallal");

        Function<String,String> f1=new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.length()>5 ? input.substring(0,5) : input;
            }
        };

        Function<String,String> f2=new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.toUpperCase();
            }
        };

        Function<String,String> function= Functions.compose(f1,f2);
        Collection<String> results=Collections2.transform(list,function);
        System.out.println(results);

    }


    /**
     * 交集、差集、并集
     */
    @Test
    public void SetsTest(){
        Set<Integer> set1= Sets.newHashSet(1,2,3,4,5);
        Set<Integer> set2=Sets.newHashSet(3,4,5,6);

        Sets.SetView<Integer> inter=Sets.intersection(set1,set2); //交集  ----[3, 4, 5]
        System.out.println(inter);

        Sets.SetView<Integer> diff=Sets.difference(set1,set2); //差集,在A中不在B中----[1, 2]
        System.out.println(diff);

        Sets.SetView<Integer> union=Sets.union(set1,set2); //并集---[1, 2, 3, 4, 5, 6]
        System.out.println(union);

    }

}

