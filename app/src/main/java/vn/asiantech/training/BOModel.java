package vn.asiantech.training;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 30/11/2016.
 */
public class BOModel {
    private Context mContext;

    public BOModel(Context context) {
        this.mContext = context;
    }

    public ArrayList<People> getPeoples() {
        DBHelper dbHelper = new DBHelper(mContext);
        ArrayList<People> peoples = dbHelper.getPeoples();
        return peoples;
    }

    public void insert(String name, String phone) {
        DBHelper dbHelper = new DBHelper(mContext);
        dbHelper.insert(name, phone);
    }
}
