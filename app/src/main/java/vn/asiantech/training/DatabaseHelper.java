package vn.asiantech.training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private ArrayList<Time> mArr = new ArrayList<Time>();
    /*Tên database*/
    private static final String DATABASE_NAME = "DB_TIMER";

    /*Version database*/
    private static final int DATABASE_VERSION = 1;

    /*Tên tabel và các column trong database*/
    private static final String TABLE_TIME = "TIME";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";

    /*Các đối tượng khác*/
    static SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*Tạo mới database*/
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        arg0.execSQL("CREATE TABLE " + TABLE_TIME + " ("
                + COLUMN_ID + " TEXT NOT NULL, "
                + COLUMN_DATE + " TEXT NOT NULL, "
                + COLUMN_HOUR + " TEXT NOT NULL, "
                + COLUMN_MINUTE + " TEXT NOT NULL);");
    }

    /*Kiểm tra phiên bản database nếu khác sẽ thay đổi*/
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_TIME);
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

    public long createData(String date, String hour, String minute,String id) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_HOUR, hour);
        cv.put(COLUMN_MINUTE, minute);
        cv.put(COLUMN_ID, id);
        return db.insert(TABLE_TIME, null, cv);
    }

    public ArrayList<Time> getData() {
        Cursor c = db.query(TABLE_TIME, null, null, null, null, null, null);
        mArr = new ArrayList<Time>();
        c.moveToFirst();
        //Vòng lặp lấy dữ liệu của con trỏ
        while (!c.isAfterLast()) {
            Time t = new Time();
            t.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
            t.setHour(c.getString(c.getColumnIndex(COLUMN_HOUR)));
            t.setMinute(c.getString(c.getColumnIndex(COLUMN_MINUTE)));
            mArr.add(t);
            c.moveToNext();
        }
        c.close();
        return mArr;
    }

    public void deleteData() {
        db.delete(TABLE_TIME, null, null);
    }

    public void updateData(String date,String hour,String minute,String id){
        ContentValues cv = new ContentValues();
        cv.put("date",date);
        db.update(TABLE_TIME,cv,"id=?",new String[]{id});

        ContentValues cv2 = new ContentValues();
        cv2.put("hour",hour);
        db.update(TABLE_TIME,cv2,"id=?",new String[]{id});

        ContentValues cv3 = new ContentValues();
        cv3.put("minute",minute);
        db.update(TABLE_TIME,cv3,"id=?",new String[]{id});
    }
    /* delete table */
//    public void deleteTable(){
//        open();
//        String sql = "DROP TABLE " + TABLE_TIME;
//        db.execSQL(sql);
//        close();
//    }

}
