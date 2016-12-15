package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_information)
public class InformationFragment extends Fragment {

    public OnHeadlineSelectedListener2 mCallback;
    @ViewById(R.id.tvSchool)
    TextView mTvSchool;
    @ViewById(R.id.tvAddress)
    TextView mTvAddress;
    @ViewById(R.id.tvStudentName)
    TextView mTvName;
    @ViewById(R.id.tvAge)
    TextView mTvAge;
    @FragmentArg("student")
    Student mStudent;
    @FragmentArg("position")
    int mPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("pos", mPosition + "");
    }

    @Click(R.id.imgBack)
    void backToListStudent() {
        mStudent.setName(mTvName.getText().toString());
        mStudent.setAddress(mTvAddress.getText().toString());
        mStudent.setAge(mTvAge.getText().toString());
        mStudent.setSchool(mTvSchool.getText().toString());
        if (mCallback != null) {
            mCallback.onFragmentUpdateInteraction(mStudent, mPosition);
        }
    }

    @Click(R.id.imgNext)
    void goToEditStudent() {
        EditFragment editFragment = new EditFragment_().builder().mStudent(mStudent).mPosition(mPosition).build();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContainer, editFragment).commit();
    }


    @AfterViews
    void setInfo() {
        mTvSchool.setText(mStudent.getSchool());
        mTvAddress.setText(mStudent.getAddress());
        mTvName.setText(mStudent.getName());
        mTvAge.setText(mStudent.getAge());
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
        void onFragmentUpdateInteraction(Student student, int position);
    }
}
