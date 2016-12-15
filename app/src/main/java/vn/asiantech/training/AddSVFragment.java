package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class AddSVFragment extends Fragment {
    @ViewById(R.id.ImgBtn)
     ImageButton mImgBtn;
    @ViewById(R.id.edSchoolName)
     EditText mEdSchoolName;
    @ViewById(R.id.edName)
     EditText mEdName;
    @ViewById(R.id.edAddress)
     EditText mEdAddress;
    @ViewById(R.id.edAge)
     EditText mEdAge;
    @ViewById(R.id.btnAdd)
     Button mBtnAdd;
    private OnHeadlineSelectedListener mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sv, null);
        return view;
    }

    @Click(R.id.btnAdd)
    public void clickBtnAdd(){
        SinhVien sv = new SinhVien();
        sv.setSchoolName(mEdSchoolName.getText().toString());
        sv.setName(mEdName.getText().toString());
        sv.setAddress(mEdAge.getText().toString());
        sv.setAge(mEdAddress.getText().toString());
        mCallback.onArticleSelected(sv);
    }

    @Click(R.id.ImgBtn)
    public void clickImgBtn(){
        getActivity().onBackPressed();
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(SinhVien sv);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
