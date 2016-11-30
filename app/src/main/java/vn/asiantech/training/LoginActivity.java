package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText mEdName;
    private EditText mEdPass;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getFormWidget();
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    if (getPreferences()) {
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Username or Password is wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void getFormWidget() {
        mEdName = (EditText) findViewById(R.id.edName);
        mEdPass = (EditText) findViewById(R.id.edPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
    }

    public boolean isValidate() {
        boolean valid = true;
        String name = mEdName.getText().toString();
        String password = mEdPass.getText().toString();
        if (name.isEmpty()) {
            mEdName.setError("Name can not empty");
            valid = false;
        } else {
            mEdName.setError(null);
        }
        if (password.isEmpty()) {
            mEdPass.setError("Password can not empty");
            valid = false;
        } else {
            mEdPass.setError(null);
        }
        return valid;
    }

    public boolean getPreferences() {
        SharedPreferences pre = getSharedPreferences
                (RegisterActivity.prefname, MODE_PRIVATE);
        String checkedName = pre.getString("name", "");
        String checkedPwd = pre.getString("pwd", "");
        Log.i("XYZ", checkedName);
        String name = mEdName.getText().toString();
        String pass = mEdPass.getText().toString();
        if (name.equals(checkedName) && pass.equals(checkedPwd)) {
            return true;
        } else {
            return false;
        }
    }
}
