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

public class LoginActivity extends AppCompatActivity {

    public static final String MYPREFERENCE = "myPreference";
    public static final String IS_ACTIVE = "isactive";
    public final String KEY_NAME = "username";
    public final String KEY_PWD = "password";
    private SharedPreferences mSharedPreferences;
    private Button mBtnLogin;
    private EditText mEdtUsername;
    private EditText mEdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        final SharedPreferences.Editor editor = mSharedPreferences.edit();
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mEdtUsername = (EditText) findViewById(R.id.edtUsername);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        if (mSharedPreferences.getBoolean(IS_ACTIVE, false)) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdtUsername.getText().toString().equals(mSharedPreferences.getString(KEY_NAME, null)) &&
                        mEdtPassword.getText().toString().equals(mSharedPreferences.getString(KEY_PWD, null))) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    editor.putBoolean(IS_ACTIVE, true);
                    editor.commit();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
