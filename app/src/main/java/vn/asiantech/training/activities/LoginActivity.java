package vn.asiantech.training.activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import vn.asiantech.training.models.LoginResult;
import vn.asiantech.training.networks.Api;
import vn.asiantech.training.networks.ApiClient;

/**
 * Created by phuong on 16/12/2016.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    public static final String NAME_SHAREPREFERENCE = "SharePreferences";
    public static final String ACCESS_TOKEN = "access_token";
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.btnLogin)
    Button mBtnLogin;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @Override
    void inits() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mEdtEmail.setText(bundle.getString("email"));
            mEdtPassword.setText(bundle.getString("password"));
        }
    }

    @Click(R.id.btnCancel)
    void cancelAction() {
        Intent intent = new Intent(this, MenuActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.btnLogin)
    void loginAction() {
        Api api = ApiClient.retrofit().create(Api.class);
        Call<LoginResult> result = api.login(mEdtEmail.getText().toString(), mEdtPassword.getText().toString());
        result.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    SharedPreferences settings = getSharedPreferences(NAME_SHAREPREFERENCE, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString(ACCESS_TOKEN, response.body().getAccount().getAccess_token());
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity_.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplication(), "Login Fail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d("tag", t.getMessage());
            }
        });
    }
}