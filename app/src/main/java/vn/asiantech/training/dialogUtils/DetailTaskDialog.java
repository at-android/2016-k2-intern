package vn.asiantech.training.dialogUtils;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.R;
import vn.asiantech.training.fragments.EditTaskFragment;
import vn.asiantech.training.fragments.EditTaskFragment_;

/**
 * Created by phuong on 16/12/2016.
 */
@EFragment(R.layout.dialog_fragment_detail_task)
public class DetailTaskDialog extends DialogFragment {

    @ViewById(R.id.tvTitle)
    TextView mTvTitle;

    @ViewById(R.id.tvContent)
    TextView mTvContent;

    @ViewById(R.id.btnEdit)
    Button mBtnEdit;

    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @AfterViews
    void inits(){
        mTvTitle.setText("ffjvnfvkfmvlf,vlf,vlfvfdbvjnvkfmvkfmvkfvlfv");
        mTvContent.setText("vdbvjfnvkmdkvdjfkefmemkvvjfdnvdfnvnfvnkvfkv");
    }

    @Click(R.id.btnEdit)
    void editAction(){
        Log.d("TAG111","Click here");
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, EditTaskFragment_.builder().build()).commit();
        dismiss();
    }

    @Click(R.id.btnCancel)
    void cancelAction(){
        dismiss();
    }
}
