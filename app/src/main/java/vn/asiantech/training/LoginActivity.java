package vn.asiantech.training;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by phuong on 29/11/2016.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    public static String NAME_LOGIN = "logined";

    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.btnLogin)
    Button mBtnLogin;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;

    @Click(R.id.btnLogin)
    void loginAction() {
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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        } else {
            showDialogNotification();
        }
    }

    @Click(R.id.btnCancel)
    void cancelAction() {
        finish();
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
