package vn.asiantech.training.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.R;
import vn.asiantech.training.models.RegisterResult;
import vn.asiantech.training.networks.Api;
import vn.asiantech.training.networks.ApiClient;

/**
 * Created by phuong on 16/12/2016.
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.edtPasswordRewrite)
    EditText mEdtConfirmPassword;
    @ViewById(R.id.btnRegister)
    Button mBtnRegister;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @Override
    void inits() {

    }

    @Click(R.id.btnCancel)
    void cancelAction() {
        Intent intent = new Intent(this, MenuActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.btnRegister)
    void registerAction() {
        String name = mEdtName.getText().toString();
        final String email = mEdtEmail.getText().toString();
        final String password = mEdtPassword.getText().toString();

        Api api = ApiClient.retrofit().create(Api.class);
        Call<RegisterResult> resultRegister = api.createAccount(name, email, password);
        resultRegister.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                if (!response.body().isError()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    bundle.putString("password", password);

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity_.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Fail" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

}
