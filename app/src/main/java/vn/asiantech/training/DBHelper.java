package vn.asiantech.training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 29/11/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_PHONE = "phone";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key autoincrement, name text,phone text)"
        );

    }

    public void insert(String name, String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, name);
        contentValues.put(CONTACTS_COLUMN_PHONE, phone);
        sqLiteDatabase.insert("contacts", null, contentValues);
    }

    public ArrayList<People> getPeoples() {
        ArrayList<People> peoples = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from contacts", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String name = res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME));
            String phoneNumber = res.getString(res.getColumnIndex(CONTACTS_COLUMN_PHONE));
            People people = new People(name, phoneNumber);
            peoples.add(people);
            res.moveToNext();
        }
        return peoples;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

}
