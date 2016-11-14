package vn.asiantech.training.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import vn.asiantech.training.R;
import vn.asiantech.training.model.Student;


/**
 * Created by Quang_TD on 10/26/2016.
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    private List<Student> mStudents;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
        mContext = context;
        mStudents = students;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (mStudents.isEmpty()) {
            return 0;
        }
        return mStudents.size();
    }

    @Override
    public Student getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Student student = mStudents.get(position);
        StudentAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_listview, null, false);
            viewHolder = new StudentAdapter.ViewHolder();
            viewHolder.mTvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.mTvAge = (TextView) convertView.findViewById(R.id.tvAge);
            viewHolder.mTvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            viewHolder.mTvSchool = (TextView) convertView.findViewById(R.id.tvSchool);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (StudentAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.mTvName.setText(student.getmName());
        viewHolder.mTvAge.setText(student.getmAge());
        viewHolder.mTvSchool.setText(student.getmSchool());
        viewHolder.mTvAddress.setText(student.getmAddress());

        return convertView;
    }

    private static class ViewHolder {
        TextView mTvName;
        TextView mTvAge;
        TextView mTvSchool;
        TextView mTvAddress;
    }

}
