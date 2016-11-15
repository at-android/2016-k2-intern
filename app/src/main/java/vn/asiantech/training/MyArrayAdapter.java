package vn.asiantech.training;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 14/11/2016.
 */

public class MyArrayAdapter extends ArrayAdapter<SinhVien> {
    Activity context = null;
    ArrayList<SinhVien> myArray = null;
    int layoutId;

    /**
     * Constructor này dùng để khởi tạo các giá trị
     * từ MainActivity truyền vào
     *
     * @param context   : là Activity từ Main
     * @param layoutId: Là layout custom do ta tạo (my_item_layout.xml)
     * @param arr       : Danh sách Verb truyền từ Main
     */
    public MyArrayAdapter(Activity context,
                          int layoutId,
                          ArrayList<SinhVien> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //gán layout vào coding
        convertView = context.getLayoutInflater().inflate(layoutId, null);
        //lấy các control ra theo id
        TextView tv = (TextView) convertView.findViewById(R.id.customItemTv);

        SinhVien sv = myArray.get(position);
        tv.setText(sv.toString());

        return convertView;
    }
}
