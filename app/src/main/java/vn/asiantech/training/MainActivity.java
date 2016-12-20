package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.asiantech.training.activities.HomeActivity;
import vn.asiantech.training.activities.RegisterActivity;
import vn.asiantech.training.interfaces.RequesService;
import vn.asiantech.training.objects.Result;

public class MainActivity extends AppCompatActivity {
    public static final String API_URL = "http://atintership.esy.es/intership/v1/";
    public static final String KEY_SHARE = "Data";
    private Button mBtnLogin;
    private Button mBtnRegister;
    private EditText mEdtEmail;
    private EditText mEdtPassword;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void init() {
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnRegister = (Button) findViewById(R.id.btnRegister);
        mEdtEmail = (EditText) findViewById(R.id.edtEmail);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
    }

    private void loginUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        RequesService requesService = retrofit.create(RequesService.class);
        Call<Result> user = requesService.loginUser(mEdtEmail.getText().toString(), mEdtPassword.getText().toString());
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    if ("true".equals(response.body().error)) {
                        Toast.makeText(MainActivity.this, "Email or Password is missing!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences pre = getSharedPreferences(KEY_SHARE, MODE_PRIVATE);
                        SharedPreferences.Editor edit = pre.edit();
                        edit.putString("token", response.body().user.accessToken);
                        edit.apply();
                        startActivity(new Intent(MainActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
