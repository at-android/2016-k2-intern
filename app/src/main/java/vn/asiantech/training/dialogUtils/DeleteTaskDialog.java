package vn.asiantech.training.dialogUtils;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.R;
import vn.asiantech.training.activities.HomeActivity;
import vn.asiantech.training.activities.HomeActivity_;
import vn.asiantech.training.activities.LoginActivity_;
import vn.asiantech.training.fragments.EditTaskFragment;
import vn.asiantech.training.fragments.EditTaskFragment_;
import vn.asiantech.training.models.Task;
import vn.asiantech.training.models.TaskResult;
import vn.asiantech.training.networks.Api;
import vn.asiantech.training.networks.ApiClient;

/**
 * Created by phuong on 16/12/2016.
 */
@EFragment(R.layout.dialog_fragment_delete_task)
public class DeleteTaskDialog extends DialogFragment {
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @ViewById(R.id.btnOk)
    Button mBtnOk;

    @FragmentArg
    public Task task;

    @AfterViews
    void inits() {
    }

    @Click(R.id.btnOk)
    void deleteAction() {
        Api api = ApiClient.retrofit().create(Api.class);
        SharedPreferences settings = getActivity().getSharedPreferences(LoginActivity_.NAME_SHAREPREFERENCE, 0);
        String access_token = settings.getString(LoginActivity_.ACCESS_TOKEN, "");
        Call<TaskResult> result = api.deleteTask(task.getId(),access_token);
        result.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity().getApplication(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), HomeActivity_.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {
                Log.d("tag","fail "+t.getMessage());
            }
        });
    }

    @Click(R.id.btnCancel)
    void cancelAction() {
        dismiss();
    }

}
