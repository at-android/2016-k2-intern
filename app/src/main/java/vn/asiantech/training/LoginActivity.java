package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String TITLE = "Login";
    private Button mBtnLogin;
    private EditText mEdtUsername;
    private EditText mEdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(TITLE);
        SharedPreferences sharedpreferences = this.getSharedPreferences(RegisterActivity.MyPREFERENCES, MODE_PRIVATE);
        final String name = sharedpreferences.getString(RegisterActivity.KEY_NAME, null);
        final String password = sharedpreferences.getString(RegisterActivity.KEY_PWD, null);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mEdtUsername = (EditText) findViewById(R.id.edtUsername);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdtUsername.getText().toString().equals(name) && mEdtPassword.getText().toString().equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
