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
    private Activity mContext = null;
    private ArrayList<StudentObject> mStudentArray = null;
    private int mLayoutId;
    private TextView mTvName;
    private TextView mTvSchool;
    private TextView mTvAddress;
    private TextView mTvOld;

    public ListStudentArrayAdapter(Activity mContext, int mLayoutId, ArrayList<StudentObject> mStudentArray) {
        super(mContext, mLayoutId, mStudentArray);
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mStudentArray = mStudentArray;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                mContext.getLayoutInflater();
        convertView = inflater.inflate(mLayoutId, null);
        if (mStudentArray.size() > 0 && position >= 0) {
            mTvName = (TextView)
                    convertView.findViewById(R.id.txtName);
            mTvSchool = (TextView)
                    convertView.findViewById(R.id.txtSchool);
            mTvAddress = (TextView)
                    convertView.findViewById(R.id.txtAddress);
            mTvOld = (TextView)
                    convertView.findViewById(R.id.txtOld);
            StudentObject studentObj = mStudentArray.get(position);
            mTvName.setText(studentObj.getName());
            mTvSchool.setText(studentObj.getSchool());
            mTvAddress.setText(studentObj.getAddress());
            mTvOld.setText(studentObj.getOld());
        }
        return convertView;
    }
}