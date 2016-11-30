package vn.asiantech.training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MaiManhDuy on 11/29/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_number_mangager_table = "CREATE TABLE NumberManager" +
                "(" +
                "id int NOT NULL PRIMARY KEY," +
                "name TEXT," +
                "number TEXT" +
                ");";
        sqLiteDatabase.execSQL(create_number_mangager_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVerson, int newVerson) {
        String drop_number_mangager_table = String.format("DROP TABLE IF EXISTS", "NumberManager");
        sqLiteDatabase.execSQL(drop_number_mangager_table);
        onCreate(sqLiteDatabase);
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
}
