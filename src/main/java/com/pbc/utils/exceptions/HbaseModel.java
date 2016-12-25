package com.pbc.utils.exceptions;

import java.util.List;

/**
 * Created by panbingcan on 2016/12/23.
 */
public class HbaseModel {
    public class CellDate extends HColumn {
        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }

        /**
         * 值
         */
        private String Value;

    }

    public class RowData {
        private String RowKey;

        private List<CellDate> RowValue;

        public String getRowKey() {
            return RowKey;
        }

        public void setRowKey(String rowKey) {
            RowKey = rowKey;
        }

        public List<CellDate> getRowValue() {
            return RowValue;
        }

        public void setRowValue(List<CellDate> rowValue) {
            RowValue = rowValue;
        }

    }

    public class InsertCellData extends HColumn {
        public Object Value;
    }

    public class InsertRowData {
        private String RowKey;

        private List<InsertCellData> Columns;

        public String getRowKey() {
            return RowKey;
        }

        public void setRowKey(String rowKey) {
            RowKey = rowKey;
        }

        public List<InsertCellData> getColumns() {
            return Columns;
        }

        public void setColumns(List<InsertCellData> columns) {
            Columns = columns;
        }

    }

    /**
     * 列模型
     */
    public class HColumn {
        /**
         * 列族
         */
        private String Family;
        /**
         * 列名
         */
        private String Column;

        /**
         * 时间戳
         */
        private Long Timestamp;

        public String getFamily() {
            return Family;
        }

        public void setFamily(String family) {
            Family = family;
        }

        public String getColumn() {
            return Column;
        }

        public void setColumn(String column) {
            Column = column;
        }

        public Long getTimestamp() {
            return Timestamp;
        }

        public void setTimestamp(Long timestamp) {
            Timestamp = timestamp;
        }

    }
}
