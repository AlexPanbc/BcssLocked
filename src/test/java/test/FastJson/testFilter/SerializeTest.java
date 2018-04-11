package test.FastJson.testFilter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/30
 * \* Time: 下午4:24
 * \* Description:测试SerializeFilter
 * \
 */
public class SerializeTest {


    /**
     * 是否过滤字段
     *  PropertyFilter根据PropertyName情况，来返回是否需要序列号此字段
     */
    @Test
    public void propertyFilterTest(){
        PropertyFilter filter =new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object value) {
                if("name".equals(name)){
                    if(value.toString().equals("admin")){
                        return false;
                    }
                }
                return true;
            }
        };

        User admin=new User(1,"admin");
        User normalUser=new User(2,"LHC");
        System.out.println("admin:"+JSONObject.toJSONString(admin,filter));
        System.out.println("lhc:"+JSONObject.toJSONString(normalUser,filter));
    }

    /**
     * 序列化时修改Key
     */
    @Test
    public void nameFilterTest(){
        NameFilter  filter=new NameFilter() {
            @Override
            public String process(Object object, String name, Object value) {
                if("id".equals(name)){
                    return "userId";
                }
                if("name".equals(name)){
                    return "userName";
                }
                return name;
            }
        };
        User admin=new User(1,"admin");
        System.out.println("admin:"+JSONObject.toJSONString(admin,filter)); //  -->>>>> admin:{"userId":1,"userName":"admin"}

    }

    /**
     * 序列化时候修改value
     */
    @Test
    public void valueFilterTest(){
        ValueFilter filter=new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if("id".equals(name)){
                    return ((long)value)*10;
                }
                return value;
            }
        };
        User admin=new User(1,"admin");
        System.out.println("admin:"+JSONObject.toJSONString(admin,filter)); //  -->>>>> admin:{"id":10,"name":"admin"}

    }

    /**
     * 序列化前面加上kv
     */
    @Test
    public void beforeFilterTest(){
        BeforeFilter filter=new BeforeFilter() {
            @Override
            public void writeBefore(Object object) {
                writeKeyValue("location","beijing");
            }
        };
        User admin=new User(1,"admin");
        System.out.println("admin:"+JSONObject.toJSONString(admin,filter)); //  -->>>>> admin:{"location":"beijing","id":1,"name":"admin"}

    }

    /**
     * 序列号后面加上kv
     */
    @Test
    public void afterFilterTest(){
        AfterFilter filter=new AfterFilter() {
            @Override
            public void writeAfter(Object object) {
                writeKeyValue("location","beijing");
            }
        };
        User admin=new User(1,"admin");
        System.out.println("admin:"+JSONObject.toJSONString(admin,filter)); //  -->>>>> admin:{"id":1,"name":"admin","location":"beijing"}

    }


    @Test
    public void SimplePropertyPreFilterTest(){
        SimplePropertyPreFilter simplePropertyPreFilter=new SimplePropertyPreFilter(User.class,"id");
        User admin=new User(1,"admin");


        System.out.println(JSONObject.toJSONString(admin,simplePropertyPreFilter));



    }


}
