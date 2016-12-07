package vn.asiantech.training;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.ArrayList;

public class EditFragment extends DialogFragment implements View.OnClickListener {
    private Button mBtnEdit;
    private Button mBtnSetTime;
    private Button mBtnClose;
    private TextView mTvHour;
    private TextView mTvMinute;
    private CheckBox mCkMonday;
    private CheckBox mCkTuesday;
    private CheckBox mCkWednesday;
    private CheckBox mCkThursday;
    private CheckBox mCkFriday;
    private CheckBox mCkSaturday;
    private CheckBox mCkSunday;
    private DatabaseHelper db;
    private int mSaveHour;
    private int mSaveMinute;
    private String s = "";
    private ArrayList<Time> mArrTime = new ArrayList<Time>();
    private int mPosition;
    private Time mTime;
    private SendDataFromEditFrag mCallback;
    public EditFragment() {
    }

    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mTime = bundle.getParcelable("time");
            mPosition = bundle.getInt("position");
        }
        db = new DatabaseHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        mBtnEdit = (Button) view.findViewById(R.id.btnEdit);
        mBtnSetTime = (Button) view.findViewById(R.id.btnSetTime);
        mBtnClose = (Button) view.findViewById(R.id.btnClose);
        mTvHour = (TextView) view.findViewById(R.id.tvHour);
        mTvMinute = (TextView) view.findViewById(R.id.tvMinute);
        mCkMonday = (CheckBox) view.findViewById(R.id.ckMonday);
        mCkTuesday = (CheckBox) view.findViewById(R.id.ckTuesday);
        mCkWednesday = (CheckBox) view.findViewById(R.id.ckWednesday);
        mCkThursday = (CheckBox) view.findViewById(R.id.ckThursday);
        mCkFriday = (CheckBox) view.findViewById(R.id.ckFriday);
        mCkSaturday = (CheckBox) view.findViewById(R.id.ckSaturday);
        mCkSunday = (CheckBox) view.findViewById(R.id.ckSunday);

        mTvHour.setText(mTime.getHour());
        mTvMinute.setText(mTime.getMinute());

        mBtnEdit.setOnClickListener(this);
        mBtnSetTime.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEdit:
                s = "";
                String nameOfDay = "";
                mTime.setHour(mSaveHour + "");
                mTime.setMinute(mSaveMinute + "");
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
                mTime.setDate(s);
                mTime.setNameOfDay(nameOfDay);
                Log.i("mTime.getDay",mTime.getDate());
                Log.i("mTime.getHour",mTime.getHour());
                Log.i("mTime.getMinute",mTime.getMinute());
                db.open();
                db.updateData(mTime.getDate(),mTime.getHour(),mTime.getMinute(),mTime.getId());
                db.close();
                mCallback.TimeEdited(mTime,mPosition);
                dismiss();
                break;
            case R.id.btnSetTime:
                showTimePickerDialog();
                break;
            case R.id.btnClose:
                dismiss();
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
        time.setTitle("Pick Time");
        time.show();
    }

    public interface SendDataFromEditFrag {
        public void TimeEdited(Time t,int position);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SendDataFromEditFrag) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
