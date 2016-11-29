package vn.asiantech.training;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MaiManhDuy on 11/29/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "numberphonemanager";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_number_mangager_table = String.format("CREATE TABLE ( INTEGER PRIMARY KEY,  TEXT,  TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_NUMBER);
        sqLiteDatabase.execSQL(create_number_mangager_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVerson, int newVerson) {
        String drop_number_mangager_table = String.format("DROP TABLE IF EXISTS", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_number_mangager_table);
        onCreate(sqLiteDatabase);
    }
}
