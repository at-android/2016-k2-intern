package vn.asiantech.training.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static vn.asiantech.training.activities.MainActivity.DATABASE_NAME;
import static vn.asiantech.training.activities.MainActivity.DAY_OF_WEEK;
import static vn.asiantech.training.activities.MainActivity.HOUR;
import static vn.asiantech.training.activities.MainActivity.MINUTE;
import static vn.asiantech.training.activities.MainActivity.TITLE;

/**
 * Created by MaiManhDuy on 12/5/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase mSQLiteDatabase;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_alarm_manager_table = String.format("CREATE TABLE " + DATABASE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + TITLE + " TEXT, " + HOUR + " INTEGER, " + MINUTE + " INTEGER, " + DAY_OF_WEEK + " TEXT)");
        sqLiteDatabase.execSQL(create_alarm_manager_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*String drop_alarm_manager_table = String.format("DROP TABLE IF EXISTS", "AlarmManager");
        sqLiteDatabase.execSQL(drop_alarm_manager_table);
        onCreate(sqLiteDatabase);*/
    }

    public void open() {
        mSQLiteDatabase = getWritableDatabase();
    }

    public void close() {
        mSQLiteDatabase.close();
    }

    public Cursor getAll(String sql) {
        open();
        Cursor cursor = mSQLiteDatabase.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        close();
        return cursor;
    }

    public long insert(String table, ContentValues values) {
        long index = mSQLiteDatabase.insert(table, null, values);
        close();
        return index;
    }

    public boolean update(String table, ContentValues values, String where) {
        long index = mSQLiteDatabase.update(table, values, where, null);
        close();
        return index > 0;
    }

    public boolean delete(String table, String where) {
        open();
        long index = mSQLiteDatabase.delete(table, where, null);
        close();
        return index > 0;
    }
}
