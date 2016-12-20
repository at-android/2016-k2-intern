package vn.asiantech.training.dialogUtils;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.R;
import vn.asiantech.training.activities.HomeActivity;
import vn.asiantech.training.fragments.EditTaskFragment;
import vn.asiantech.training.fragments.EditTaskFragment_;
import vn.asiantech.training.models.Task;

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

    @ViewById(R.id.btnDelete)
    Button mBtnDelete;

    private Task task;

    @AfterViews
    void inits() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            task = bundle.getParcelable(HomeActivity.EDIT_BUNDLE);
            mTvTitle.setText(task.getMTitle());
            mTvContent.setText(task.getMContent());
        }
    }

    @Click(R.id.btnEdit)
    void editAction() {
        EditTaskFragment editTaskFragment = EditTaskFragment_.builder().build();
        editTaskFragment.task = task;
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, editTaskFragment).commit();
        dismiss();
    }

    @Click(R.id.btnCancel)
    void cancelAction() {
        dismiss();
    }

    @Click(R.id.btnDelete)
    void deleteAction(){
        DeleteTaskDialog deleteTaskDialog = DeleteTaskDialog_.builder().build();
        deleteTaskDialog.task = task;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        deleteTaskDialog.show(fm,DetailTaskDialog.class.getName());
        dismiss();
    }
}
