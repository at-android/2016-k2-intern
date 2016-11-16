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

public class AddSVFragment extends Fragment implements View.OnClickListener {
    private ImageButton mImgBtn;
    private EditText mEdSchoolName;
    private EditText mEdName;
    private EditText mEdAddress;
    private EditText mEdAge;
    private Button mBtnAdd;
    private OnHeadlineSelectedListener mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sv, null);
        mImgBtn = (ImageButton) view.findViewById(R.id.ImgBtn);
        mEdSchoolName = (EditText) view.findViewById(R.id.edSchoolName);
        mEdName = (EditText) view.findViewById(R.id.edName);
        mEdAddress = (EditText) view.findViewById(R.id.edAge);
        mEdAge = (EditText) view.findViewById(R.id.edAddress);
        mBtnAdd = (Button) view.findViewById(R.id.btnAdd);

        mBtnAdd.setOnClickListener(this);
        mImgBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                SinhVien sv = new SinhVien();
                sv.setSchoolName(mEdSchoolName.getText().toString());
                sv.setName(mEdName.getText().toString());
                sv.setAddress(mEdAge.getText().toString());
                sv.setAge(mEdAddress.getText().toString());
                mCallback.onArticleSelected(sv);
                break;
            case R.id.ImgBtn:
                getActivity().onBackPressed();
                break;
        }
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(SinhVien sv);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
