package vn.asiantech.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;


public class SetTimeFragment extends DialogFragment {
    private Button mBtn;
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
    private int mHour;
    private int mMinute;
    private ArrayList<Day> mArr = new ArrayList<Day>();
    public SetTimeFragment() {
        // Required empty public constructor
    }


    public static SetTimeFragment newInstance() {
        SetTimeFragment fragment = new SetTimeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_time, container, false);
        mBtn = (Button) view.findViewById(R.id.btnStart);
        mEdHour = (EditText) view.findViewById(R.id.edHour);
        mEdMinute = (EditText) view.findViewById(R.id.edMinute);
        mCkMonday = (CheckBox) view.findViewById(R.id.ckMonday);
        mCkTuesday = (CheckBox) view.findViewById(R.id.ckTuesday);
        mCkWednesday = (CheckBox) view.findViewById(R.id.ckWednesday);
        mCkThursday = (CheckBox) view.findViewById(R.id.ckThursday);
        mCkFriday = (CheckBox) view.findViewById(R.id.ckFriday);
        mCkSaturday = (CheckBox) view.findViewById(R.id.ckSaturday);
        mCkSunday = (CheckBox) view.findViewById(R.id.ckSunday);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHour = Integer.parseInt(mEdHour.getText().toString());
                mMinute = Integer.parseInt(mEdMinute.getText().toString());
                if(mCkMonday.isChecked()){
                    Day day = new Day("Monday");
                    mArr.add(day);
                }
                if(mCkTuesday.isChecked()){
                    Day day = new Day("Tuesday");
                    mArr.add(day);
                }
                if(mCkWednesday.isChecked()){
                    Day day = new Day("Wednesday");
                    mArr.add(day);
                }
                if(mCkThursday.isChecked()){
                    Day day = new Day("Thursday");
                    mArr.add(day);
                }
                if(mCkFriday.isChecked()){
                    Day day = new Day("Friday");
                    mArr.add(day);
                }
                if(mCkSaturday.isChecked()){
                    Day day = new Day("Saturday");
                    mArr.add(day);
                }
                if(mCkSunday.isChecked()){
                    Day day = new Day("Sunday");
                    mArr.add(day);
                }
                mCallback.onArticleSelected(mHour,mMinute,mArr);
                dismiss();
            }
        });
        return view;
    }

    public interface SendData {
        public void onArticleSelected(int hour,int minute,ArrayList<Day> arr);
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
