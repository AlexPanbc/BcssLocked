package test.ZooKeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * 异步获取结点回调接口
 * Created by liuhuichao on 2017/7/26.
 */
public class ICChild2Callback implements AsyncCallback.Children2Callback{

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println("get children zonde result:[reponse code:"+rc+" path="+path+" ctx="+ctx+"  childrenlist="+children+" stat="+stat);
    }
}