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
    private EditText mEdtSchoolName, mEdtName, mEdtAddress, mEdtAge;
    private Button mBtnAdd;
    private  OnHeadlineSelectedListener mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sv, null);
        mImgBtn = (ImageButton) view.findViewById(R.id.addSvImgBtn);
        mEdtSchoolName = (EditText) view.findViewById(R.id.addSvEdtSchoolName);
        mEdtName = (EditText) view.findViewById(R.id.addSvEdtName);
        mEdtAddress = (EditText) view.findViewById(R.id.addSvEdtAddress);
        mEdtAge = (EditText) view.findViewById(R.id.addSvEdtAge);
        mBtnAdd = (Button) view.findViewById(R.id.addSvBtnAdd);

        mBtnAdd.setOnClickListener(this);
        mImgBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addSvBtnAdd:
                SinhVien sv = new SinhVien();
                sv.setSchoolName(mEdtSchoolName.getText().toString());
                sv.setName(mEdtName.getText().toString());
                sv.setAddress(mEdtAge.getText().toString());
                sv.setAge(mEdtAddress.getText().toString());
                onButtonClicked(sv);
                break;
            case R.id.addSvImgBtn:
                getActivity().onBackPressed();
                break;
        }
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(SinhVien sv);
    }

    public void onButtonClicked(SinhVien sv) {
        mCallback.onArticleSelected(sv);
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
