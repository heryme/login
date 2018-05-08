package p.com.gameproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by hirenk on 2/27/16.
 * Build create table database string
 */
abstract public class DatabaseService extends SQLiteOpenHelper {

    public DatabaseService(Context context, String databaseName, int version) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(getTable(getTableName()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /**
         * create new tables
         */
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        super.onOpen(db);

        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    private String getTable(String tableName) {

        StringBuilder createTableQuery = new StringBuilder();

        createTableQuery.append("CREATE TABLE ");

        createTableQuery.append(tableName);

        createTableQuery.append("(");

        for (DatabaseColumn column: getColumns()) {

            createTableQuery.append(column.getName());

            createTableQuery.append(" ");

            createTableQuery.append(column.getColumnDataType());

            createTableQuery.append(" ");

            if(column.isPrimaryKey()) {

                createTableQuery.append("PRIMARY KEY");

            } else if(column.getReferenceTable() != null && column.getReferenceTable().length() > 0 &&
                    column.getReferenceColumn() != null && column.getReferenceColumn().length() > 0) {

                createTableQuery.append("REFERENCES ");

                createTableQuery.append(column.getReferenceTable());

                createTableQuery.append("(");

                createTableQuery.append(column.getReferenceColumn());

                createTableQuery.append(") ON DELETE CASCADE");
            }
            createTableQuery.append(",");
        }

        //Remove last comma from query
        createTableQuery.deleteCharAt(createTableQuery.length()-1);

        createTableQuery.append(")");

        Log.i("=====>","Create Table:-"+createTableQuery.toString());
        return createTableQuery.toString();
    }

    abstract protected String getTableName();

    abstract protected List<DatabaseColumn> getColumns();
}
