package vn.asiantech.training.activities;

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
import vn.asiantech.training.R;
import vn.asiantech.training.interfaces.RequesService;
import vn.asiantech.training.objects.Result;

import static vn.asiantech.training.MainActivity.API_URL;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEdtName;
    private EditText mEdtEmail;
    private EditText mEdtPassword;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterNewUser();
            }
        });
    }

    private void init() {
        mEdtName = (EditText) findViewById(R.id.edtRegName);
        mEdtEmail = (EditText) findViewById(R.id.edtRegEmail);
        mEdtPassword = (EditText) findViewById(R.id.edtRegPassword);
        mBtnRegister = (Button) findViewById(R.id.btnRegisterNewUser);
    }

    public void onRegisterNewUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        RequesService requesService = retrofit.create(RequesService.class);
        Call<Result> user = requesService.registerUser(mEdtName.getText().toString(), mEdtEmail.getText().toString(), mEdtPassword.getText().toString());
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, response.body().message + "", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Required field(s) name, email, password is missing or empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
