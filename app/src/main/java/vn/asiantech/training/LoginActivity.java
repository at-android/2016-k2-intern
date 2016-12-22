package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.api.Api;
import vn.asiantech.training.api.ApiClient;
import vn.asiantech.training.model.LoginResult;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    public static final String MYPREFERENCE = "mypref";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_BUNDLE = "bundle";
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;

    @AfterViews
    void init() {
        Bundle bundle = getIntent().getBundleExtra(KEY_BUNDLE);
        if (bundle != null) {
            mEdtEmail.setText(bundle.getString(KEY_EMAIL).toString());
            mEdtPassword.setText(bundle.getString(KEY_PASSWORD).toString());
        }
    }

    @Click(R.id.tvRegister)
    void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
        startActivity(intent);
        finish();
    }

    @Click(R.id.btnLogin)
    void login() {
        String email = String.valueOf(mEdtEmail.getText()).toString();
        Api api = ApiClient.getClient().create(Api.class);
        retrofit2.Call<LoginResult> loginResultCall = api.login(email, mEdtPassword.getText().toString());
        loginResultCall.enqueue(new Callback<LoginResult>() {
                                    @Override
                                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getError().toString().equals("false")) {
                                                SharedPreferences sharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString(ACCESS_TOKEN, response.body().getUser().getAccess_token());
                                                Log.i("accesstoken", response.body().getUser().getAccess_token());
                                                editor.commit();
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                            } else {
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<LoginResult> call, Throwable t) {

                                    }
                                }
        );
    }
}
