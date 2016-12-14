package test.HbaseTest;

import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;

/**
 *
 * HBase条件查询封装实体类
 * Created by liuhuichao on 2016/12/13.
 */
public class QueryCondition {

    String family; //列族
    String qualifier; //列修饰符
    CompareFilter.CompareOp  compareOp; //操作符
    String value; //列值
    FilterList.Operator operator;  //连接操作符


    public QueryCondition(String family, String qualifier, CompareFilter.CompareOp compareOp, String value) {
        this.family = family;
        this.qualifier = qualifier;
        this.compareOp = compareOp;
        this.value = value;
    }

    public QueryCondition(String family, String qualifier, CompareFilter.CompareOp compareOp, String value, FilterList.Operator operator) {
        this.family = family;
        this.qualifier = qualifier;
        this.compareOp = compareOp;
        this.value = value;
        this.operator = operator;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public CompareFilter.CompareOp getCompareOp() {
        return compareOp;
    }

    public void setCompareOp(CompareFilter.CompareOp compareOp) {
        this.compareOp = compareOp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FilterList.Operator getOperator() {
        return operator;
    }

    public void setOperator(FilterList.Operator operator) {
        this.operator = operator;
    }
}
