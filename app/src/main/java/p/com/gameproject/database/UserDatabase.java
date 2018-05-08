package p.com.gameproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.accounts.AccountManager.KEY_PASSWORD;
import static p.com.gameproject.database.DbInfo.TABLE_NAME;


/**
 * Created by Rid's Patel on 21-04-2018.
 */

public class UserDatabase extends DatabaseService {
    private static final int VERSION = 1;
    private static Context _ctx = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    public UserDatabase(Context context) {

        super(context, DbInfo.DATABASE_NAME, VERSION);
        this._ctx = context;

    }

    @Override
    protected String getTableName() {

        return TABLE_NAME;
    }

    @Override
    protected List<DatabaseColumn> getColumns() {

        List<DatabaseColumn> databaseColumns = new ArrayList<>();

        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[0], ColumnDataType.INTEGER, true));
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[1], ColumnDataType.TEXT));
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[2], ColumnDataType.TEXT, true, true));
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[3], ColumnDataType.TEXT));
        return databaseColumns;
    }


    /**
     * Insert  data
     *
     * @return
     */
    public long insertData(String name, String email, String pass) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbInfo.TABLE_COLUMN[1], name);
        values.put(DbInfo.TABLE_COLUMN[2], email);
        values.put(DbInfo.TABLE_COLUMN[3], pass);
        return db.insert(TABLE_NAME, null, values);
    }


    public void getAllACHCList() {

        // List<AchcGeneralInfoItem> achcGeneralInfoItemList = new ArrayList<AchcGeneralInfoItem>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Log.d("TAG", "Cursor Size--->" + c.getCount());

        /**
         * looping through all rows and adding to list
         */
        if (c.moveToFirst()) {

            do {

                String id = c.getString(0);
                String latLog = c.getString(2);
                Log.d("TAG", "ID--->" + id);
                Log.d("TAG", "latLog--->" + latLog);
            } while (c.moveToNext());
        }
        c.close();
    }

    /***
     * Check user exits or not
     * @param email
     * @return
     */
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DbInfo.TABLE_NAME,// Selecting Table
                new String[]{DbInfo.TABLE_COLUMN[0], DbInfo.TABLE_COLUMN[1], DbInfo.TABLE_COLUMN[2], DbInfo.TABLE_COLUMN[3]},//Selecting columns want to query
                DbInfo.TABLE_COLUMN[2] + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

    /**
     * Login
     * @return
     */
    public boolean auth(String email,String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DbInfo.TABLE_NAME + " WHERE " + DbInfo.TABLE_COLUMN[2] + "=? AND " + DbInfo.TABLE_COLUMN[3] + "=?", new String[]{email, pass});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return true;

            } else {
                return false;
            }

        }
        return false;

    }
}
