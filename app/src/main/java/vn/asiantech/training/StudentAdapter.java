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
        final ViewHolder viewHolder;
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.student_row, null);
            viewHolder = new ViewHolder();
            viewHolder.schoolName = (TextView) view.findViewById(R.id.tvSchoolName);
            viewHolder.address = (TextView) view.findViewById(R.id.tvAddress);
            viewHolder.studentName = (TextView) view.findViewById(R.id.tvStudentName);
            viewHolder.age = (TextView) view.findViewById(R.id.tvAge);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Student student = getItem(position);
        if (student != null) {

            viewHolder.schoolName.setText(student.getSchool());
            viewHolder.studentName.setText(student.getName());
            viewHolder.address.setText(student.getAddress());
            viewHolder.age.setText(student.getAge());
        }
        return view;
    }

    static class ViewHolder {
        private TextView schoolName;
        private TextView address;
        private TextView studentName;
        private TextView age;
    }
}
