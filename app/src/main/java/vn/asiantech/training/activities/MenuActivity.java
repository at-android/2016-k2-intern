package vn.asiantech.training.activities;

import android.content.Intent;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.R;

/**
 * Created by phuong on 16/12/2016.
 */
@EActivity(R.layout.activity_menu)
public class MenuActivity extends BaseActivity {
    @ViewById(R.id.btnLogin)
    Button mBtnLogin;
    @ViewById(R.id.btnRegister)
    Button mBtnRegister;

    @Override
    void inits() {
    }

    @Click(R.id.btnLogin)
    void loginForward() {
        Intent intent = new Intent(MenuActivity.this, LoginActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.btnRegister)
    void registerForward() {
        Intent intent = new Intent(MenuActivity.this, RegisterActivity_.class);
        startActivity(intent);
    }
}
