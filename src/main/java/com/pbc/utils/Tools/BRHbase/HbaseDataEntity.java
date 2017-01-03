package com.pbc.utils.Tools.BRHbase;

import java.util.Map;

/**
 * Created by panbingcan on 2016/12/29.
 */
public class  HbaseDataEntity {
    private String tableName;
    private String nameSpace;
    private String mobileKey;
    private Map<String, Map<String, String>> columns;//map<columnfamily,map<column,value>>

    public HbaseDataEntity() {
        super();
    }

    public HbaseDataEntity(String tableName, String nameSpace,
                           String mobileKey, Map<String, Map<String, String>> columns) {
        super();
        this.tableName = tableName;
        this.nameSpace = nameSpace;
        this.mobileKey = mobileKey;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getMobileKey() {
        return mobileKey;
    }

    public void setMobileKey(String mobileKey) {
        this.mobileKey = mobileKey;
    }

    public Map<String, Map<String, String>> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Map<String, String>> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "HbaseDataEntity [tableName=" + tableName + ", nameSpace="
                + nameSpace + ", mobileKey=" + mobileKey + ", columns="
                + columns + "]";
    }
}
