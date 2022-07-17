package com.drpicox.game.util;

import java.util.List;

public class DataTable {

    public static DataTable EMPTY = new DataTable(List.of());

    public DataTable(List<DataTableRow> rows) {
        this.rows = rows;
    }

    private List<DataTableRow> rows;

    public List<DataTableRow> getRows() {
        return rows;
    }

    public List<String> getColumn(String name) {
        return rows.stream().map(row -> row.get(name)).toList();
    }
}
