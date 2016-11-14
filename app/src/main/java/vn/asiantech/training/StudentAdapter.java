package vn.asiantech.training;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 14/11/2016.
 */
public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.student_row, null);
        }
        TextView schoolName = (TextView) view.findViewById(R.id.tvSchoolName);
        TextView address = (TextView) view.findViewById(R.id.tvAddress);
        TextView studentName = (TextView) view.findViewById(R.id.tvStudentName);
        TextView age = (TextView) view.findViewById(R.id.tvAge);

        Student student = getItem(position);
        if (student != null) {

            schoolName.setText(student.getSchool());
            studentName.setText(student.getName());
            address.setText(student.getAddress());
            age.setText(student.getAge());
        }
        return view;
    }
}
