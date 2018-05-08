package p.com.gameproject.database;

/**
 * Created by hirenk
 * Set filed name and data type
 */
public class DatabaseColumn {

    private String name;

    private ColumnDataType columnDataType;

    private boolean isPrimaryKey;

    private String referenceTable;

    private String referenceColumn;

    private boolean isUnique;
    private boolean isNull;

    public boolean isUnique() {
        return isUnique;
    }

    public boolean isNull() {
        return isNull;
    }

    public DatabaseColumn(String name, ColumnDataType columnDataType) {
        this.name = name;
        this.columnDataType = columnDataType;
    }

    public DatabaseColumn(String name, ColumnDataType columnDataType, boolean isPrimaryKey) {
        this.name = name;
        this.columnDataType = columnDataType;
        this.isPrimaryKey = isPrimaryKey;
    }

    public DatabaseColumn(String name, ColumnDataType columnDataType, boolean isUnique, boolean isNull) {
        this.name = name;
        this.columnDataType = columnDataType;
        this.isUnique = isUnique;
        this.isNull = isNull;
    }

    public DatabaseColumn(String name, ColumnDataType columnDataType, boolean isPrimaryKey, String referenceTable, String referenceColumn) {
        this.name = name;
        this.columnDataType = columnDataType;
        this.isPrimaryKey = isPrimaryKey;
        this.referenceTable = referenceTable;
        this.referenceColumn = referenceColumn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnDataType getColumnDataType() {
        return columnDataType;
    }

    public void setColumnDataType(ColumnDataType columnDataType) {
        this.columnDataType = columnDataType;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(String referenceTable) {
        this.referenceTable = referenceTable;
    }

    public String getReferenceColumn() {
        return referenceColumn;
    }

    public void setReferenceColumn(String referenceColumn) {
        this.referenceColumn = referenceColumn;
    }
}
