package vn.asiantech.training;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEdName;
    private EditText mEdEmail;
    private EditText mEdPass;
    private EditText mEdConfirmPass;
    private Button mBtnSubmit;
    public static final String prefname = "my_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getFormWidget();
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate()) {
                    failRegister();
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(RegisterActivity.this);
                    b.setTitle("Confirm");
                    b.setMessage("Continue to submit");
                    b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });

                    b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    b.show();
                }
            }
        });
    }

    public void getFormWidget() {
        mEdName = (EditText) findViewById(R.id.edName);
        mEdEmail = (EditText) findViewById(R.id.edEmail);
        mEdPass = (EditText) findViewById(R.id.edPassword);
        mEdConfirmPass = (EditText) findViewById(R.id.edConfirm);
        mBtnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    public void failRegister() {
        Toast.makeText(getApplicationContext(), "Please check form again", Toast.LENGTH_LONG).show();
    }

    public boolean validate() {
        boolean valid = true;
        String name = mEdName.getText().toString();
        String email = mEdEmail.getText().toString();
        String password = mEdPass.getText().toString();
        String password_confirm = mEdConfirmPass.getText().toString();

        if (name.isEmpty()) {
            mEdName.setError("Name can not empty");
            valid = false;
        } else if (name.length() < 4) {
            mEdName.setError("Name is too short");
            valid = false;
        } else {
            mEdName.setError(null);
        }

        if (email.isEmpty()) {
            mEdEmail.setError("Enter a valid email address");
            valid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEdEmail.setError("Wrong Format");
            valid = false;
        } else {
            mEdEmail.setError(null);
        }

        if (password.isEmpty()) {
            mEdPass.setError("Password can not empty");
            valid = false;
        } else if (password.length() > 16) {
            mEdPass.setError("Password is too long");
            valid = false;
        } else if (password.length() < 4) {
            mEdPass.setError("Password is too short");
            valid = false;
        } else {
            mEdPass.setError(null);
        }

        if (!password.equals(password_confirm)) {
            mEdConfirmPass.setError("Comfirm password did not match");
            Toast.makeText(getBaseContext(), (password + password_confirm), Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            mEdConfirmPass.setError(null);
        }
        return valid;
    }

    @Override
    protected void onPause() {
        super.onPause();
        savingPreferences();
    }

    public void savingPreferences() {
        SharedPreferences pre = getSharedPreferences
                (prefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        String name = mEdName.getText().toString();
        String pwd = mEdPass.getText().toString();
        String email = mEdEmail.getText().toString();
        //lưu vào editor
        editor.putString("name", name);
        editor.putString("pwd", pwd);
        editor.putString("email", email);
        Log.i("XXX", name);
        editor.commit();
    }
}
