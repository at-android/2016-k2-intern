package vn.asiantech.training;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class SetTimeFragment extends DialogFragment implements View.OnClickListener {
    private Button mBtnStart;
    private Button mBtnDelete;
    private Button mBtnSetTime;
    private TextView mTvHour;
    private TextView mTvMinute;
    private CheckBox mCkMonday;
    private CheckBox mCkTuesday;
    private CheckBox mCkWednesday;
    private CheckBox mCkThursday;
    private CheckBox mCkFriday;
    private CheckBox mCkSaturday;
    private CheckBox mCkSunday;
    private SendData mCallback;
    private int mSaveHour;
    private int mSaveMinute;
    private String s = "";
    private DatabaseHelper db;
    private Calendar cal;
    private Date hourFinish;

    public SetTimeFragment() {
        // Required empty public constructor
    }

    public static SetTimeFragment newInstance() {
        SetTimeFragment fragment = new SetTimeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_time, container, false);
        mBtnStart = (Button) view.findViewById(R.id.btnStart);
        mBtnDelete = (Button) view.findViewById(R.id.btnDelete);
        mBtnSetTime = (Button) view.findViewById(R.id.btnSetTime);
        mTvHour = (TextView) view.findViewById(R.id.tvHour);
        mTvMinute = (TextView) view.findViewById(R.id.tvMinute);
        mCkMonday = (CheckBox) view.findViewById(R.id.ckMonday);
        mCkTuesday = (CheckBox) view.findViewById(R.id.ckTuesday);
        mCkWednesday = (CheckBox) view.findViewById(R.id.ckWednesday);
        mCkThursday = (CheckBox) view.findViewById(R.id.ckThursday);
        mCkFriday = (CheckBox) view.findViewById(R.id.ckFriday);
        mCkSaturday = (CheckBox) view.findViewById(R.id.ckSaturday);
        mCkSunday = (CheckBox) view.findViewById(R.id.ckSunday);

        mBtnStart.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnSetTime.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                s = "";
                String nameOfDay = "";
                Time t = new Time();
                t.setHour(mSaveHour + "");
                t.setMinute(mSaveMinute + "");
                if (mCkMonday.isChecked()) {
                    s += "2 ";
                    nameOfDay += "Monday ";
                }
                if (mCkTuesday.isChecked()) {
                    s += "3 ";
                    nameOfDay += "Tuesday ";
                }
                if (mCkWednesday.isChecked()) {
                    s += "4 ";
                    nameOfDay += "Wednesday ";
                }
                if (mCkThursday.isChecked()) {
                    s += "5 ";
                    nameOfDay += "Thursday ";
                }
                if (mCkFriday.isChecked()) {
                    s += "6 ";
                    nameOfDay += "Friday ";
                }
                if (mCkSaturday.isChecked()) {
                    s += "7 ";
                    nameOfDay += "Saturday ";
                }
                if (mCkSunday.isChecked()) {
                    s += "1 ";
                    nameOfDay += "Sunday ";
                }
                t.setDate(s);
                t.setNameOfDay(nameOfDay);
                mCallback.onArticleSelected(t);
                db.open();
                long x = db.createData(t.getDate(), t.getHour(), t.getMinute());
                db.close();
                dismiss();
                break;
            case R.id.btnSetTime:
                showTimePickerDialog();
                break;
            case R.id.btnDelete:
                db.open();
                db.deleteData();
                db.close();
                break;
        }
    }

    public void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                mSaveHour = hourOfDay;
                mSaveMinute = minute;
                mTvHour.setText("HOUR: " + hourOfDay + "");
                mTvMinute.setText("MINUTE: " + minute + "");
            }
        };

        TimePickerDialog time = new TimePickerDialog(
                getActivity(),
                callback, 7, 7, true);
        time.setTitle("Chọn giờ hoàn thành");
        time.show();
    }

    public interface SendData {
        public void onArticleSelected(Time t);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SendData) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
