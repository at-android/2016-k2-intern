package vn.asiantech.training.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.R;
import vn.asiantech.training.activities.HomeActivity_;
import vn.asiantech.training.activities.LoginActivity_;
import vn.asiantech.training.models.Task;
import vn.asiantech.training.models.TaskResult;
import vn.asiantech.training.networks.Api;
import vn.asiantech.training.networks.ApiClient;

/**
 * Created by phuong on 16/12/2016.
 */
@EFragment(R.layout.dialog_fragment_edit_task)
public class EditTaskFragment extends BaseFragment {
    @FragmentArg
    public Task task;
    @ViewById(R.id.edtTitle)
    EditText mEdtTitle;
    @ViewById(R.id.edtContent)
    EditText mEdtContent;
    @ViewById(R.id.btnEdit)
    Button mBtnEdit;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @Override
    void inits() {
        if (task != null) {
            mEdtTitle.setText(task.getMTitle());
            mEdtContent.setText(task.getMContent());
        }
    }

    @Click(R.id.btnCancel)
    void cancelAction() {
        Intent intent = new Intent(getActivity(), HomeActivity_.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Click(R.id.btnEdit)
    void editAction() {
        Api api = ApiClient.retrofit().create(Api.class);
        String title = mEdtTitle.getText().toString();
        String content = mEdtContent.getText().toString();
        SharedPreferences settings = getActivity().getSharedPreferences(LoginActivity_.NAME_SHAREPREFERENCE, 0);
        String access_token = settings.getString(LoginActivity_.ACCESS_TOKEN, "");
        Call<TaskResult> result = api.editTask(task.getId(), title, content, task.getFavorite(), access_token);
        result.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity().getApplication(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), HomeActivity_.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {
                Log.d("tag", "fail" + t.getMessage());
            }
        });
    }
}
