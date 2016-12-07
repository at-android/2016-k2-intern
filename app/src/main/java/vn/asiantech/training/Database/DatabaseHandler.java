package vn.asiantech.training.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MaiManhDuy on 12/5/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHandler(Context context) {
        super(context, "AlarmManager.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_alarm_manager_table = "CREATE TABLE AlarmManager (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, hour INTEGER, minute INTEGER, dayofweek TEXT)";
        sqLiteDatabase.execSQL(create_alarm_manager_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*String drop_alarm_manager_table = String.format("DROP TABLE IF EXISTS", "AlarmManager");
        sqLiteDatabase.execSQL(drop_alarm_manager_table);
        onCreate(sqLiteDatabase);*/
    }

    public void open() {
        sqLiteDatabase = getWritableDatabase();
    }

    public void close() {
        sqLiteDatabase.close();
    }

    public Cursor getAll(String sql) {
        open();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        close();
        return cursor;
    }

    public long insert(String table, ContentValues values) {
        open();
        long index = sqLiteDatabase.insert(table, null, values);
        close();
        return index;
    }

    public boolean update(String table, ContentValues values, String where) {
        open();
        long index = sqLiteDatabase.update(table, values, where, null);
        close();
        return index > 0;
    }

    public boolean delete(String table, String where) {
        open();
        long index = sqLiteDatabase.delete(table, where, null);
        close();
        return index > 0;
    }
}
