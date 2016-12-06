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
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;


public class SetTimeFragment extends DialogFragment implements View.OnClickListener {
    private Button mBtnStart;
    private Button mBtnDelete;
    private Button mBtnSetTime;
    private EditText mEdHour;
    private EditText mEdMinute;
    private CheckBox mCkMonday;
    private CheckBox mCkTuesday;
    private CheckBox mCkWednesday;
    private CheckBox mCkThursday;
    private CheckBox mCkFriday;
    private CheckBox mCkSaturday;
    private CheckBox mCkSunday;
    private SendData mCallback;
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
        mEdHour = (EditText) view.findViewById(R.id.edHour);
        mEdMinute = (EditText) view.findViewById(R.id.edMinute);
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
                Time t = new Time();
                t.setHour(mEdHour.getText().toString());
                t.setMinute(mEdMinute.getText().toString());
                if (mCkMonday.isChecked()) {
                    s += "2 ";
                }
                if (mCkTuesday.isChecked()) {
                    s += "3 ";
                }
                if (mCkWednesday.isChecked()) {
                    s += "4 ";
                }
                if (mCkThursday.isChecked()) {
                    s += "5 ";
                }
                if (mCkFriday.isChecked()) {
                    s += "6 ";
                }
                if (mCkSaturday.isChecked()) {
                    s += "7 ";
                }
                if (mCkSunday.isChecked()) {
                    s += "1 ";
                }
                t.setDate(s);
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
                mEdHour.setText(hourOfDay + "");
                mEdMinute.setText(minute + "");
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
