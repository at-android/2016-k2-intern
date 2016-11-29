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
    ArrayList<Contact> mArr = new ArrayList<Contact>();

    /*Tên database*/
    private static final String DATABASE_NAME = "DB_CONTACT";

    /*Version database*/
    private static final int DATABASE_VERSION = 1;

    /*Tên tabel và các column trong database*/
    private static final String TABLE_CONTACT = "CONTACT";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE_NUMBER = "phonenumber";

    /*Các đối tượng khác*/
    static SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase arg0) {
        arg0.execSQL("CREATE TABLE " + TABLE_CONTACT + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_PHONE_NUMBER + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int i, int i1) {
        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        onCreate(arg0);
    }

    public DatabaseHelper open() throws SQLException {
        db = getWritableDatabase();
        return this;
    }

    public void close(){
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long createData(String name, String phoneNumber) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PHONE_NUMBER, phoneNumber);
        return db.insert(TABLE_CONTACT, null, cv);
    }

    public ArrayList<Contact> getData() {
        Cursor c = db.query(TABLE_CONTACT, null, null, null, null, null, null);
        //getColumnIndex(COLUMN_ID); là lấy chỉ số, vị trí của cột COLUMN_ID ...

        c.moveToFirst();
        //Vòng lặp lấy dữ liệu của con trỏ
        while(!c.isAfterLast()){
            Contact contact = new Contact();
            contact.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
            contact.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
            contact.setPhoneNumber(c.getString(c.getColumnIndex(COLUMN_PHONE_NUMBER)));
            mArr.add(contact);
            c.moveToNext();
        }
        c.close();
        return mArr;
    }

    public ArrayList<Contact> getList(){
        return this.mArr;
    }
}
