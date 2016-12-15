package vn.asiantech.training.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.R;
import vn.asiantech.training.models.Student;

/**
 * Created by phuong on 15/12/2016.
 */
@EFragment(R.layout.fragment_add_student)
public class AddStudentFragment extends Fragment {
    public static OnFragmentAddListener mListener;
    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtAge)
    EditText mEdtAge;
    @ViewById(R.id.edtSchool)
    EditText mEdtSchool;
    @ViewById(R.id.edtAddress)
    EditText mEdtAddress;
    @ViewById(R.id.btnAdd)
    Button mBtnAdd;
    @ViewById(R.id.imgBack)
    ImageView mImgBack;
    private ListStudentFragment mListStudentFragment;

    public static void onFragmentAddListener(OnFragmentAddListener listener) {
        mListener = listener;
    }

    @Click(R.id.btnAdd)
    void addStudent() {
        Student student = new Student(mEdtName.getText().toString(), mEdtAge.getText().toString(),
                mEdtAddress.getText().toString(), mEdtSchool.getText().toString());
        if (mListener != null) {
            Log.d("TAG1112", "TAG111");
            mListener.onFragmentAdd(student);
        }
        formatData();
    }

    @Click(R.id.imgBack)
    void callBackList() {
        getActivity().onBackPressed();
    }

    public void formatData() {
        mEdtName.setText("");
        mEdtAddress.setText("");
        mEdtAge.setText("");
        mEdtSchool.setText("");
    }

    public interface OnFragmentAddListener {
        void onFragmentAdd(Student student);
    }

}
