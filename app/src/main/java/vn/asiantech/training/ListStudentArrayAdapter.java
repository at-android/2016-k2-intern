package vn.asiantech.training;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/14/2016.
 */

public class ListStudentArrayAdapter extends ArrayAdapter<StudentObject> {
    private Activity context = null;
    private ArrayList<StudentObject> studentArr = null;
    private int layoutID;
    private TextView tvName;
    private TextView tvSchool;
    private TextView tvAddress;
    private TextView tvOld;

    public ListStudentArrayAdapter(Activity context, int layoutID, ArrayList<StudentObject> studentArr) {
        super(context, layoutID, studentArr);
        this.context = context;
        this.layoutID = layoutID;
        this.studentArr = studentArr;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        if (studentArr.size() > 0 && position >= 0) {
            tvName = (TextView)
                    convertView.findViewById(R.id.txtName);
            tvSchool = (TextView)
                    convertView.findViewById(R.id.txtSchool);
            tvAddress = (TextView)
                    convertView.findViewById(R.id.txtAddress);
            tvOld = (TextView)
                    convertView.findViewById(R.id.txtOld);
            StudentObject studentObj = studentArr.get(position);
            tvName.setText(studentObj.getName());
            tvSchool.setText(studentObj.getSchool());
            tvAddress.setText(studentObj.getAddress());
            tvOld.setText(studentObj.getOld());
        }
        return convertView;
    }
}