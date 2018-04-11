package test.ZooKeeper;

import org.apache.zookeeper.AsyncCallback;

/**
 * Created by liuhuichao on 2017/7/26.
 */
public class IStringCallBack implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("result:"+rc+"; path="+path+" ctx="+ctx+" name = "+name);
        }
}
