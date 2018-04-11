package test.ZooKeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * 异步设置数据回调接口
 * Created by liuhuichao on 2017/7/27.
 */
public class IStatCallback implements AsyncCallback.StatCallback{
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println("rc="+rc+" ;path="+path+" ;ctx="+ctx+" ;stat="+stat);
        if(rc==0){
            System.out.println("数据设置成功！");
        }
    }
}
