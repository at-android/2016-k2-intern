package vn.asiantech.training.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import vn.asiantech.training.model.Phone;

/**
 * Created by phuong on 29/11/2016.
 */

public class MyDatabase {
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    private static final String DATABASE_NAME = "DB_PHONE";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACT = "PHONE";
    static SQLiteDatabase db;
    private static Context context;
    ArrayList<Phone> arrInfo = null;
    Phone phone = null;
    private OpenHelper openHelper;

    public MyDatabase(Context c) {
        MyDatabase.context = c;
    }

    public MyDatabase open() throws SQLException {
        openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        openHelper.close();
    }

    public void createData(String name, String phone) {
        String query = "Insert into " + TABLE_CONTACT + " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ") values('" + name + "','" + phone + "')";
        db.execSQL(query);
    }

    public ArrayList<Phone> getData() {
        String[] columns = new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PHONE};
        Cursor c = db.query(TABLE_CONTACT, null, null, null, null, null, null);
        int iRow = c.getColumnIndex(COLUMN_ID);
        int iLogo = c.getColumnIndex(COLUMN_NAME);
        int iPhone = c.getColumnIndex(COLUMN_PHONE);

        arrInfo = new ArrayList<Phone>();
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            phone = new Phone();
            phone.setmId(c.getInt(iRow));
            phone.setmName(c.getString(iLogo));
            phone.setmPhone(c.getString(iPhone));
            arrInfo.add(phone);
        }
        c.close();
        return arrInfo;
    }


    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            String query = "CREATE TABLE " + TABLE_CONTACT + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_PHONE + " TEXT NOT NULL);";
            Log.d("query", query);
            arg0.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
            onCreate(arg0);
        }
    }
}
