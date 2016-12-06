package vn.asiantech.training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 05/12/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";
    public static final String COLUMN_DAY_OF_WEEK = "dayofweek";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FLAG = "flag";
    public static final String TABLE_NAME = "schedule";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table schedule " +
                        "( id integer, hour text,minute text,dayofweek text,flag integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS schedule");
        onCreate(db);
    }

    public void insert(int id, String hour, String minute, String dayofweek, int flag) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_DAY_OF_WEEK, dayofweek);
        contentValues.put(COLUMN_HOUR, hour);
        contentValues.put(COLUMN_MINUTE, minute);
        contentValues.put(COLUMN_FLAG, flag);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Time> getAlarm() {
        ArrayList<Time> timeArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String hour = res.getString(res.getColumnIndex(COLUMN_HOUR));
            String minute = res.getString(res.getColumnIndex(COLUMN_MINUTE));
            String dayofweek = res.getString(res.getColumnIndex(COLUMN_DAY_OF_WEEK));
            int flag = res.getInt(res.getColumnIndex(COLUMN_FLAG));
            Time alarm = new Time(Integer.parseInt(hour), Integer.parseInt(minute), dayofweek, flag);
            timeArrayList.add(alarm);
            res.moveToNext();
        }
        return timeArrayList;
    }

    public Time getTime(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_ID + "=" + id, null);
        res.moveToFirst();
        String hour = res.getString(res.getColumnIndex(COLUMN_HOUR));
        String minute = res.getString(res.getColumnIndex(COLUMN_MINUTE));
        String dayofweek = res.getString(res.getColumnIndex(COLUMN_DAY_OF_WEEK));
        int flag = res.getInt(res.getColumnIndex(COLUMN_FLAG));
        Time time = new Time(Integer.parseInt(hour), Integer.parseInt(minute), dayofweek, flag);
        return time;
    }

    public void delete() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);

    }

    public void update(Time time, int id, int isRunning) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DAY_OF_WEEK, time.getDayofweek());
        contentValues.put(COLUMN_HOUR, time.getHour() + "");
        contentValues.put(COLUMN_MINUTE, time.getMinute() + "");
        contentValues.put(COLUMN_FLAG, isRunning);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id=" + id, null);
    }

    public void updateFlag(int id, int flag) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FLAG, flag);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id=" + id, null);
    }
}