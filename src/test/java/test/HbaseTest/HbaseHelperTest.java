package test.HbaseTest;

import com.pbc.utils.Tools.BRHbase.HbaseDemol;
import com.pbc.utils.Tools.DateTools;
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


    //<editor-fold desc="创建表">
    @Test
    public void createTable() {
        List<String> lstName = new ArrayList<>();
        for (int i = 1; i < 3; i++)
            lstName.add("n" + i);
        HbaseDemol.createTable("t1", lstName);
    }

    //</editor-fold>

    @Test
    public void getRow() throws Exception {
//
//        Object object = HbaseHelper.getRow("t1", "001");
//        Object object = HbaseHelper.getTable("t1");
//        Object object = HbaseHelper.getRow("t1", "001", "f1");
//        Object object = HbaseHelper.getRow("t1", "002", "f2", "address");
//        Object object = HbaseHelper.getRows("t1", "001",10);
//
//        HbaseModel.CellDate hfn = new HbaseModel().new CellDate();
//        hfn.setFamily("n1");
//        hfn.setColumn("pv");
//        hfn.setValue("1");
//        List<HbaseModel.CellDate> lst = new ArrayList<>();
//        lst.add(hfn);
//        Object object = HbaseHelper.getRowsWhere("t1", lst);

//        List<HbaseModel.HColumn> lstH = new ArrayList<>();
//        HbaseModel.HColumn hfn = new HbaseModel().new HColumn();
//        hfn.setFamily("n1");
//        hfn.setColumn("pv");
//        lstH.add(hfn);
//        HbaseModel.HColumn hf2n = new HbaseModel().new HColumn();
//        hf2n.setFamily("f2");
//        hf2n.setColumn("img");
//        lstH.add(hf2n);
//        Object object = HbaseHelper.getRows("t1", "001", "010", lstH);

        Object object = HbaseHelper.getRowsByRow("t1","09");

        if (object != null)
            System.out.println(JSONArray.fromObject(object));
        else
            System.out.println("数据不存在！");
    }

    /**
     * 生成rowkey
     */
    @Test
    public void createRowKey() {
        System.out.println(HbaseHelper.padLeft(2337));
    }

    @Test
    public void inst() throws Exception {
        // HbaseHelper.inst("t1", "002", "f2", "address", "lai yi duan wen zi ");//// TODO: 2016/12/25  插入单列数据
        List<HbaseModel.InsertRowData> list = new ArrayList<>();
        for (int i = 21; i < 100; i++) {
            HbaseModel.InsertRowData ird = new HbaseModel().new InsertRowData();
            ird.setRowKey("0" + String.valueOf(i));
            List<HbaseModel.InsertCellData> listCd = new ArrayList<>();
            HbaseModel.InsertCellData irn = new HbaseModel().new InsertCellData();
            irn.setFamily("n1");
            irn.setColumn("pv");
            irn.setValue(Integer.toString(i));
            listCd.add(irn);
            HbaseModel.InsertCellData iri = new HbaseModel().new InsertCellData();
            iri.setFamily("n1");
            iri.setColumn("uv");
            iri.setValue(Integer.toString(i));
            listCd.add(iri);
            HbaseModel.InsertCellData ira = new HbaseModel().new InsertCellData();
            ira.setFamily("n2");
            ira.setColumn("pv");
            ira.setValue(Integer.toString(i));
            listCd.add(ira);
            HbaseModel.InsertCellData ir2i = new HbaseModel().new InsertCellData();
            ir2i.setFamily("n2");
            ir2i.setColumn("uv");
            ir2i.setValue(Integer.toString(i));
            listCd.add(ir2i);
            ird.setColumns(listCd);
            list.add(ird);
        }
        HbaseHelper.inst("t1", list);///// TODO: 2016/12/25 批量插入数据
    }
}
