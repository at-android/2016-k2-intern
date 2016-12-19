package vn.asiantech.training.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.R;
import vn.asiantech.training.activities.HomeActivity_;
import vn.asiantech.training.activities.LoginActivity_;
import vn.asiantech.training.models.TaskResult;
import vn.asiantech.training.networks.Api;
import vn.asiantech.training.networks.ApiClient;

/**
 * Created by phuong on 16/12/2016.
 */
@EFragment(R.layout.fragment_add_task)
public class AddTaskFragment extends BaseFragment {
    @ViewById(R.id.edtTitle)
    EditText mEdtTitle;
    @ViewById(R.id.edtContent)
    EditText mEdtContent;
    @ViewById(R.id.btnAdd)
    Button mBtnAdd;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    private String access_token;

    @Override
    void inits() {
        SharedPreferences settings = getActivity().getSharedPreferences(LoginActivity_.NAME_SHAREPREFERENCE, 0);
        access_token = settings.getString(LoginActivity_.ACCESS_TOKEN, "");

    }

    @Click(R.id.btnAdd)
    void addAction() {
        Api api = ApiClient.retrofit().create(Api.class);
        Call<TaskResult> result = api.createTask(mEdtTitle.getText().toString(), mEdtContent.getText().toString(), 0, access_token);
        result.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity().getApplication(), "Created Task Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), HomeActivity_.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else{
                    Toast.makeText(getActivity().getApplication(), "Create Fail", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {
                Toast.makeText(getActivity().getApplication(), "Error Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Click(R.id.btnCancel)
    void cancelAction() {
        Intent intent = new Intent(getActivity(), HomeActivity_.class);
        startActivity(intent);
        getActivity().finish();
    }
}
