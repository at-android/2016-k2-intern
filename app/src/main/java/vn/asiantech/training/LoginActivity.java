package vn.asiantech.training;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by phuong on 29/11/2016.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static String NAME_LOGIN = "logined";
    private EditText mEdtName;
    private EditText mEdtPassword;
    private Button mBtnLogin;
    private Button mBtnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        mBtnCancel.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    public void initView() {
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnCancel = (Button) findViewById(R.id.btnCancel);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                String name = mEdtName.getText().toString();
                String password = mEdtPassword.getText().toString();

                //check
                SharedPreferences settings = getSharedPreferences(RegisterActivity.NAME_SHAREPREPERENCE, 0);
                String Sname = settings.getString(RegisterActivity.SP_FIELDNAME, "");
                String Spassword = settings.getString(RegisterActivity.SP_FIELDPASSWORD, "");

                if (name.equals(Sname) && password.equals(Spassword)) {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString(NAME_LOGIN, "logined");
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.startActivity(intent);
                } else {
                    showDialogNotification();
                }
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }

    public void showDialogNotification() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notificaition);
        final TextView tvClose = (TextView) dialog.findViewById(R.id.tvClose);
        final TextView tvTitle = (TextView) dialog.findViewById(R.id.tvNotification);
        tvTitle.setText(R.string.dialog_notification_fail);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dialog.show();
    }
}
