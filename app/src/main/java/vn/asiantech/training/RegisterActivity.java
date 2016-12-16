package vn.asiantech.training;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class RegisterActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "mypreferences";
    public static final String KEY_NAME = "username";
    public static final String KEY_PWD = "password";
    public static final String KEY_EMAIL = "email";
    private static final String TITLE = "Register";
    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;

    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;

    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;

    @ViewById(R.id.edtConfirmPwd)
    EditText mEdtConfirmPwd;

    @ViewById(R.id.imgAlertUsername)
    ImageView mImgAlertUser;

    @ViewById(R.id.imgAlertEmail)
    ImageView mImgAlertEmail;

    @ViewById(R.id.imgAlertPwd)
    ImageView mImgAlertPwd;

    @ViewById(R.id.imgAlertConfirmPwd)
    ImageView mImgAlertConfirmPwd;

    @AfterViews
    void init() {
        getSupportActionBar().setTitle(TITLE);
    }

    @Click(R.id.btnRegister)
    void register() {
        mImgAlertUser.setVisibility(View.INVISIBLE);
        mImgAlertEmail.setVisibility(View.INVISIBLE);
        mImgAlertPwd.setVisibility(View.INVISIBLE);
        mImgAlertConfirmPwd.setVisibility(View.INVISIBLE);
        if (isValidate() == 0) {
            Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(KEY_NAME, mEdtUsername.getText().toString());
            editor.putString(KEY_PWD, mEdtPassword.getText().toString());
            editor.putString(KEY_EMAIL, mEdtEmail.getText().toString());
            editor.commit();
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Register Successfull!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            builder.show();
        } else {
            switch (isValidate()) {
                case 6:
                    mImgAlertPwd.setVisibility(View.VISIBLE);
                    makeMessage(6);
                    break;
                case 4:
                    mImgAlertConfirmPwd.setVisibility(View.VISIBLE);
                    makeMessage(4);
                    break;
                case 3:
                    mImgAlertPwd.setVisibility(View.VISIBLE);
                    makeMessage(3);
                    break;
                case 2:
                    mImgAlertEmail.setVisibility(View.VISIBLE);
                    makeMessage(2);
                    break;
                case 1:
                    mImgAlertUser.setVisibility(View.VISIBLE);
                    makeMessage(1);
                    break;
            }
        }
    }

    public void makeMessage(int cate) {
        String temp = "";
        switch (cate) {
            case 1:
                temp += "Username is empty! ";
                break;
            case 2:
                temp += "Email is not Esxiting ";
                break;
            case 3:
                temp += "Password is empty ";
                break;
            case 4:
                temp += "Confirm password not match ";
                break;
            case 6:
                temp += "Password is empty ";
                break;
        }
        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
    }

    public int isValidate() {
        if (mEdtUsername.getText().toString().equals("")) {
            return 1;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mEdtEmail.getText().toString()).matches()) {
            return 2;
        }
        if (mEdtPassword.getText().toString().length() > 16) {
            return 3;
        }
        if (!mEdtConfirmPwd.getText().toString().equals(mEdtPassword.getText().toString())) {
            return 4;
        }
        if (mEdtConfirmPwd.getText().toString().equals("")) {
            return 6;
        }
        return 0;
    }

}
