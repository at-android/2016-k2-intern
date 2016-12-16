package vn.asiantech.training.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.training.R;

public class RegisterActivity extends AppCompatActivity {
    public static final String PRIMARY_KEY = "DATA";
    private EditText mEdtName;
    private EditText mEdtEmail;
    private EditText mEdtPassword;
    private EditText mEdtConfirmPassword;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtEmail = (EditText) findViewById(R.id.edtEmail);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mEdtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        mBtnRegister = (Button) findViewById(R.id.btnRegister);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEdtEmail.getText().toString().equals("") || mEdtName.getText().toString().equals("") || mEdtPassword.getText().toString().equals("") || mEdtConfirmPassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please insert full feld", Toast.LENGTH_SHORT).show();
                } else {
                    if (mEdtPassword.getText().toString().equals(mEdtConfirmPassword.getText().toString())) {
                        SharedPreferences pre = getSharedPreferences(PRIMARY_KEY, MODE_PRIVATE);
                        SharedPreferences.Editor edit = pre.edit();
                        edit.putString("name", mEdtName.getText().toString());
                        edit.putString("pass", mEdtPassword.getText().toString());
                        edit.apply();
                        Toast.makeText(RegisterActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Confirm password wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
