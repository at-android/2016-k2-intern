package vn.asiantech.training;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class InformationFragment extends Fragment {

    Student student;
    Bundle bundle;
    int position;
    OnHeadlineSelectedListener2 mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if (bundle != null) {
            student = (Student) bundle.getSerializable(Exfragment.STUDENT_NAME);
            position = bundle.getInt(Exfragment.POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        final TextView tvSchool = (TextView) view.findViewById(R.id.textviewSchool);
        final TextView tvAddress = (TextView) view.findViewById(R.id.textviewAddress);
        final TextView tvName = (TextView) view.findViewById(R.id.textviewName);
        final TextView tvAge = (TextView) view.findViewById(R.id.textviewAge);

        ImageButton imgbNext = (ImageButton) view.findViewById(R.id.imgNext);
        ImageButton imgbBack = (ImageButton) view.findViewById(R.id.imgBack);
        tvSchool.setText(student.getSchool());
        tvAddress.setText(student.getAddress());
        tvName.setText(student.getName());
        tvAge.setText(student.getAge());

        imgbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditFragment editFragment = new EditFragment();
                editFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContainer, editFragment).commit();

            }
        });
        imgbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setName(tvName.getText().toString());
                student.setAddress(tvAddress.getText().toString());
                student.setAge(tvAge.getText().toString());
                student.setSchool(tvSchool.getText().toString());
                onButtonPressed(student, position);
            }
        });
        return view;
    }

    public void onButtonPressed(Student student, int position) {
        if (mCallback != null) {
            mCallback.onFragmentInteraction2(student, position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnHeadlineSelectedListener2) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener2");
        }
    }

    public interface OnHeadlineSelectedListener2 {
        public void onFragmentInteraction2(Student student, int position);
    }
}
