package vn.asiantech.training.fragments;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.R;
import vn.asiantech.training.activities.HomeActivity_;

/**
 * Created by phuong on 16/12/2016.
 */
@EFragment(R.layout.dialog_fragment_detail_task)
public class EditTaskFragment extends BaseFragment {
    @ViewById(R.id.tvTitle)
    TextView mTvTitle;
    @ViewById(R.id.tvContent)
    TextView mTvContent;
    @ViewById(R.id.btnEdit)
    Button mBtnEdit;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @Override
    void inits() {
        Log.d("TAG112","TAG11 here");
        mTvTitle.setText("ạvndnvkdmv");
        mTvContent.setText("fbvfvkfvkmdkvfdvfklkvf");
    }

    @Click(R.id.btnCancel)
    void cancelAction(){
        Intent intent = new Intent(getActivity(), HomeActivity_.class);
        startActivity(intent);
        getActivity().finish();
    }
}
