package test.ZooKeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * 异步获取node数据回调
 * Created by liuhuichao on 2017/7/27.
 */
public class IDataCallback implements AsyncCallback.DataCallback {
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println("rc="+rc+" ;path="+path+" ;ctx="+ctx+" ;data="+data+" ;stat="+stat);
        System.out.println("string data="+new String(data));
        System.out.println("max version="+stat.getVersion());
    }
}
