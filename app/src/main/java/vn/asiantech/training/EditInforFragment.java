package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import static vn.asiantech.training.R.id.imgBtnLeft;

@EFragment
public class EditInforFragment extends Fragment {
    @ViewById(R.id.edSchoolName)
    EditText mEdSchool;
    @ViewById(R.id.edName)
    EditText mEdName;
    @ViewById(R.id.edAge)
    EditText mEdAge;
    @ViewById(R.id.edAddress)
    EditText mEdAddress;
    @ViewById(imgBtnLeft)
    ImageButton mImgBtnLeft;
    @FragmentArg
    SinhVien mSinhVien;

    OnHeadlineSelectedListener4 mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_infor, null);
        return view;
    }

    @AfterViews
    public void CreateData() {
        SinhVien sv = mSinhVien;
        mEdSchool.setText(sv.getSchoolName().toString());
        mEdName.setText(sv.getName().toString());
        mEdAge.setText(sv.getAge().toString());
        mEdAddress.setText(sv.getAddress().toString());
    }

    @Click(R.id.imgBtnLeft)
    public void ClickImgBtnLeft() {
        SinhVien sv = new SinhVien();
        sv.setSchoolName(mEdSchool.getText().toString());
        sv.setName(mEdName.getText().toString());
        sv.setAge(mEdAge.getText().toString());
        sv.setAddress(mEdAddress.getText().toString());
        mCallback.onArticleSelected4(sv);
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener4 {
        public void onArticleSelected4(SinhVien sv);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnHeadlineSelectedListener4) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
