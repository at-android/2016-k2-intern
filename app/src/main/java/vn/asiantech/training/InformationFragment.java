package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class InformationFragment extends Fragment {

    public OnHeadlineSelectedListener2 mCallback;
    private Student mStudent;
    private Bundle mBundle;
    private int mPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        if (mBundle != null) {
            mStudent = (Student) mBundle.getSerializable(DemoFragmentActivity.KEY_STUDENT);
            mPosition = mBundle.getInt(DemoFragmentActivity.KEY_POSITION);
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
        tvSchool.setText(mStudent.getSchool());
        tvAddress.setText(mStudent.getAddress());
        tvName.setText(mStudent.getName());
        tvAge.setText(mStudent.getAge());

        imgbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditFragment editFragment = new EditFragment();
                editFragment.setArguments(mBundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContainer, editFragment).commit();

            }
        });
        imgbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStudent.setName(tvName.getText().toString());
                mStudent.setAddress(tvAddress.getText().toString());
                mStudent.setAge(tvAge.getText().toString());
                mStudent.setSchool(tvSchool.getText().toString());
                if (mCallback != null) {
                    mCallback.onFragmentInteraction2(mStudent, mPosition);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnHeadlineSelectedListener2) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener2");
        }
    }

    interface OnHeadlineSelectedListener2 {
        void onFragmentInteraction2(Student student, int position);
    }
}
