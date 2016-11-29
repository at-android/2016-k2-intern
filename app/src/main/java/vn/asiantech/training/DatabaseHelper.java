package vn.asiantech.training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 29/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    ArrayList<Contact> list = new ArrayList<Contact>();
    /*Tên database*/
    private static final String DATABASE_NAME = "DB_USER";

    /*Version database*/
    private static final int DATABASE_VERSION = 1;

    /*Tên tabel và các column trong database*/
    private static final String TABLE_ACCOUNT = "CONTACT";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NUMBER = "number";

    /*Các đối tượng khác*/
    static SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /*Tạo mới database*/
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        arg0.execSQL("CREATE TABLE " + TABLE_ACCOUNT + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_NUMBER + " TEXT NOT NULL);");
    }

    /*Kiểm tra phiên bản database nếu khác sẽ thay đổi*/
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        onCreate(arg0);
    }

    public DatabaseHelper open() throws SQLException {
        db = getWritableDatabase();
        return this;
    }


    /*Hàm đóng kết nối với database*/
    public void close() {
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Hàm createData dùng để chèn dữ mới dữ liệu vào database*/
    public long createData(String name, String number) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NUMBER, number);
        return db.insert(TABLE_ACCOUNT, null, cv);
    }

    public ArrayList<Contact> getList() {
        return this.list;
    }

    public ArrayList<Contact> getData(String sql) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            Contact contact = new Contact();
            contact.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            contact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
            list.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
