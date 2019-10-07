package org.zlasu.util.crud;

import java.util.List;

public class Result {
    private Integer affectedRowsCount;
    private List<String[]> rows;

    public Integer getAffectedRowsCount() {
        return affectedRowsCount;
    }

    public List<String[]> getRows() {
        return rows;
    }

    public void setRows(List<String[]> rows) {
        this.rows = rows;
    }
}