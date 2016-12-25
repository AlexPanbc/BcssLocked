package test.HbaseTest;

import com.pbc.utils.Tools.HbaseHelper;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by panbingcan on 2016/12/23.
 */
public class HbaseHelperTest {

    @Test
    public void getRow() throws Exception {

//        Object object = HbaseHelper.GetRow("t1", "001");
//        Object object = HbaseHelper.GetRow("t1", "001", "f1");
        HbaseHelper.Inst("t1", "002", "f2", "address", "lai yi duan wen zi ");
        Object object = HbaseHelper.GetRow("t1", "002", "f2", "address");
        if (object != null)
            System.out.println(JSONArray.fromObject(object));
        else
            System.out.println("数据不存在！");
    }
}
