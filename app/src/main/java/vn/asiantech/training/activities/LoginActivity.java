package vn.asiantech.training.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.training.R;

import static vn.asiantech.training.activities.RegisterActivity.PRIMARY_KEY;

public class LoginActivity extends AppCompatActivity {
    private EditText mEdtName;
    private EditText mEdtPassword;
    private String mName;
    private String mPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEdtName = (EditText) findViewById(R.id.edtInputName);
        mEdtPassword = (EditText) findViewById(R.id.edtInputPassword);
        SharedPreferences preferences = getSharedPreferences(PRIMARY_KEY, MODE_PRIVATE);
        mName = preferences.getString("name", "");
        mPassword = preferences.getString("pass", "");
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mName.equals(mEdtName.getText().toString()) && mPassword.equals(mEdtPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
