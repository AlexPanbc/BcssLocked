package com.pbc.utils.Tools.BRHbase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by panbingcan on 2016/12/29.
 */
public class HbaseConditionEntity {

    private byte[] familyColumn;
    private byte[] column;
    private byte[] value;
    private Operator operator;
    private CompareOp compareOp;

    public byte[] getFamilyColumn() {
        return familyColumn;
    }

    public void setFamilyColumn(byte[] familyColumn) {
        this.familyColumn = familyColumn;
    }

    public byte[] getColumn() {
        return column;
    }

    public void setColumn(byte[] column) {
        this.column = column;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public CompareOp getCompareOp() {
        return compareOp;
    }

    public void setCompareOp(CompareOp compareOp) {
        this.compareOp = compareOp;
    }

    public HbaseConditionEntity(byte[] familyColumn, byte[] column,
                                byte[] value, Operator operator, CompareOp compareOp) {
        super();
        this.familyColumn = familyColumn;
        this.column = column;
        this.value = value;
        this.operator = operator;
        this.compareOp = compareOp;
    }

    public HbaseConditionEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static List<HbaseConditionEntity> toHbaseConditions(String labels) {

        List<HbaseConditionEntity> hbaseConditions = new ArrayList<HbaseConditionEntity>();

        String[] labelArray = labels.split(";");

        for (String labelWithCompares : labelArray) {

            String[] labelWithComparesArray = labelWithCompares.split(" ");
            String label = labelWithComparesArray[0];
            Operator compare = null;

            if (labelWithComparesArray.length > 1) {
                if ("and".equals(labelWithComparesArray[1])) {
                    compare = Operator.MUST_PASS_ALL;
                } else {
                    compare = Operator.MUST_PASS_ONE;
                }

            }

            byte[] familyColumn = Bytes.toBytes("label");
            byte[] column = Bytes.toBytes(label);
            byte[] value = Bytes.toBytes(label);

            HbaseConditionEntity hbaseCondition = new HbaseConditionEntity(familyColumn, column, value, compare, CompareOp.EQUAL);

            hbaseConditions.add(hbaseCondition);
        }
        return hbaseConditions;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String labels = "label1 and;label2 or;label3";
        List<HbaseConditionEntity> conditions = toHbaseConditions(labels);
        System.out.println("==========begin==========");

        for (HbaseConditionEntity hbaseConditionEntity : conditions) {
            System.out.println("[familyColumn: " + new String(hbaseConditionEntity.getFamilyColumn(), "UTF-8")
                    + "] [column: " + new String(hbaseConditionEntity.getColumn(), "UTF-8")
                    + "] [value: " + new String(hbaseConditionEntity.getValue(), "UTF-8")
                    + "] [operator: " + hbaseConditionEntity.getOperator()
                    + "] [compare: " + hbaseConditionEntity.getCompareOp() + "]");
        }

        System.out.println("==========end==========");
    }

}
