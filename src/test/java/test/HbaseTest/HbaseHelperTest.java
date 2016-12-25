package test.HbaseTest;

import com.pbc.utils.Tools.HbaseHelper;
import com.pbc.utils.exceptions.HbaseModel;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by panbingcan on 2016/12/23.
 */
public class HbaseHelperTest {

    @Test
    public void getRow() throws Exception {
//
//        Object object = HbaseHelper.getRow("t1", "001");
//        Object object = HbaseHelper.getTable("t1");
//        Object object = HbaseHelper.getRow("t1", "001", "f1");
//        Object object = HbaseHelper.getRow("t1", "002", "f2", "address");
        Object object = HbaseHelper.getRows("t1", "008", 3);

//        List<HbaseModel.HColumn> lstH = new ArrayList<>();
//        HbaseModel.HColumn hfn = new HbaseModel().new HColumn();
//        hfn.setFamily("f1");
//        hfn.setColumn("name");
//        lstH.add(hfn);
//        HbaseModel.HColumn hf2n = new HbaseModel().new HColumn();
//        hf2n.setFamily("f2");
//        hf2n.setColumn("img");
//        lstH.add(hf2n);
//        Object object = HbaseHelper.getRows("t1", "001", "003", lstH);
        if (object != null)
            System.out.println(JSONArray.fromObject(object));
        else
            System.out.println("数据不存在！");
    }

    @Test
    public void inst() throws Exception {
        // HbaseHelper.inst("t1", "002", "f2", "address", "lai yi duan wen zi ");//// TODO: 2016/12/25  插入单列数据
        List<HbaseModel.InsertRowData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HbaseModel.InsertRowData ird = new HbaseModel().new InsertRowData();
            ird.setRowKey("00" + String.valueOf(i + 1));
            List<HbaseModel.InsertCellData> listCd = new ArrayList<>();
            HbaseModel.InsertCellData irn = new HbaseModel().new InsertCellData();
            irn.setFamily("f1");
            irn.setColumn("name");
            irn.setValue("哈哈" + i);
            listCd.add(irn);
            HbaseModel.InsertCellData iri = new HbaseModel().new InsertCellData();
            iri.setFamily("f1");
            iri.setColumn("img");
            iri.setValue("头像" + i);
            listCd.add(iri);
            HbaseModel.InsertCellData ira = new HbaseModel().new InsertCellData();
            ira.setFamily("f1");
            ira.setColumn("address");
            ira.setValue("地址" + i);
            listCd.add(ira);
            HbaseModel.InsertCellData ir2i = new HbaseModel().new InsertCellData();
            ir2i.setFamily("f2");
            ir2i.setColumn("img");
            ir2i.setValue("f2头像" + i);
            listCd.add(ir2i);
            ird.setColumns(listCd);
            list.add(ird);
        }
        HbaseHelper.inst("t1", list);///// TODO: 2016/12/25 批量插入数据
    }
}
