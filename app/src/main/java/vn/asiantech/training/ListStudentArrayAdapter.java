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
    Activity context = null;
    ArrayList<StudentObject> studentArr = null;
    int layoutID;
    private TextView txtName;
    private TextView txtSchool;
    private TextView txtAddress;
    private TextView txtOld;

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
            txtName = (TextView)
                    convertView.findViewById(R.id.txtName);
            txtSchool = (TextView)
                    convertView.findViewById(R.id.txtSchool);
            txtAddress = (TextView)
                    convertView.findViewById(R.id.txtAddress);
            txtOld = (TextView)
                    convertView.findViewById(R.id.txtOld);
            StudentObject studentObj = studentArr.get(position);
            txtName.setText(studentObj.getName());
            txtSchool.setText(studentObj.getSchool());
            txtAddress.setText(studentObj.getAddress());
            txtOld.setText(studentObj.getOld());
        }
        return convertView;
    }
}