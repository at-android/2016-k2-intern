package vn.asiantech.training;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.validator.EmailValidator;

/**
 * Created by phuong on 29/11/2016.
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity{
    public static String NAME_SHAREPREPERENCE = "contact";
    public static String SP_FIELDNAME = "name";
    public static String SP_FIELDEMAIL = "email";
    public static String SP_FIELDPASSWORD = "password";

    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.edtPasswordRewrite)
    EditText mEdtPasswordConfirm;
    @ViewById(R.id.btnRegister)
    Button mBtnRegister;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @Click(R.id.btnCancel)
    void cancelAction(){
        finish();
    }

    @Click(R.id.btnRegister)
    void registerAction(){
        String mName = mEdtName.getText().toString();
        String mEmail = mEdtEmail.getText().toString();
        String mPassword = mEdtPassword.getText().toString();
        String mPasswordConfirm = mEdtPasswordConfirm.getText().toString();
        //validate
        if (checkValidate(mName, mEmail, mPassword, mPasswordConfirm)) {
            SharedPreferences settings = getSharedPreferences(NAME_SHAREPREPERENCE, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(SP_FIELDNAME, mName);
            editor.putString(SP_FIELDEMAIL, mEmail);
            editor.putString(SP_FIELDPASSWORD, mPassword);
            editor.commit();

            showDialogNotification();
        }
    }

    public boolean checkValidate(String name, String email, String password, String passwordConfirm) {
        Log.d("1", password);
        Log.d("2", passwordConfirm);
        boolean check = true;
        if (!name.matches("^[a-zA-Z0-9]{1,100}$")) {
            mEdtName.setError("Enter the name. Please!");
            check = false;
        }
        if (!(new EmailValidator()).validate(email)) {
            mEdtEmail.setError("Your email isn't right. Please!");
            check = false;
        }
        if (!password.matches("^[a-zA-Z0-9]{1,15}$")) {
            mEdtPassword.setError("Enter password again. Please!");
            check = false;
        }
        if (!passwordConfirm.matches("^[a-zA-Z0-9]{1,15}$")) {
            mEdtPasswordConfirm.setError("Enter password again. Please!");
            check = false;
        }
        if (!password.equals(passwordConfirm)) {
            mEdtPasswordConfirm.setError("Enter confirm password again. Please!");
            check = false;
        }
        return check;
    }

    public void showDialogNotification() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notificaition);
        final TextView tvClose = (TextView) dialog.findViewById(R.id.tvClose);
        final TextView tvTitle = (TextView) dialog.findViewById(R.id.tvNotification);
        tvTitle.setText(R.string.dialog_notification_success);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dialog.show();
    }
}
