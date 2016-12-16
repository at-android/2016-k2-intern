package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    public final String TITLE = "Login";
    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    private String mName;
    private String mPassword;

    @Click(R.id.btnLogin)
    void loginToHomeActivity() {
        if (mEdtUsername.getText().toString().equals(mName) && mEdtPassword.getText().toString().equals(mPassword)) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity_.class);
            startActivity(intent);
        }
    }

    @AfterViews
    void init() {
        getSupportActionBar().setTitle(TITLE);
        SharedPreferences sharedpreferences = this.getSharedPreferences(RegisterActivity.MyPREFERENCES, MODE_PRIVATE);
        mName = sharedpreferences.getString(RegisterActivity.KEY_NAME, null);
        mPassword = sharedpreferences.getString(RegisterActivity.KEY_PWD, null);
    }

}
