package vn.asiantech.training.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import vn.asiantech.training.API.Api;
import vn.asiantech.training.API.ApiClient;
import vn.asiantech.training.MainActivity;
import vn.asiantech.training.R;
import vn.asiantech.training.model.LoginResult;

@EActivity
public class LoginActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "my_data";
    public static final String ACCESS_TOKEN = "access_token";
    @ViewById(R.id.edEmail)
    EditText mEdEmail;
    @ViewById(R.id.edPassword)
    EditText mEdPass;
    @ViewById(R.id.btnLogin)
    Button mBtnLogin;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Click(R.id.btnCancel)
    void CancelAction(){
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    @Click(R.id.btnLogin)
    void LoginAction(){
        Api api = ApiClient.retrofit().create(Api.class);
        Call<LoginResult> result = api.login(mEdEmail.getText().toString(), mEdPass.getText().toString());
        result.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (!response.body().isError()) {
                    SharedPreferences pre=getSharedPreferences
                            (SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor=pre.edit();
                    String authorization = response.body().getAccount().getAccess_token();
                    editor.putString("accesstoken",authorization);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplication(), "Login Fail", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d("tag", t.getMessage());
            }
        });
    }
}
