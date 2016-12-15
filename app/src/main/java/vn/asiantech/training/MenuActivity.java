package vn.asiantech.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by phuong on 29/11/2016.
 */
@EActivity(R.layout.activity_menu)
public class MenuActivity extends AppCompatActivity {
    @ViewById(R.id.btnlogin)
    Button mBtnLogin;
    @ViewById(R.id.btnregister)
    Button mBtnRegister;

    @Click(R.id.btnlogin)
    void loginAction() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Click(R.id.btnRegister)
    void registerAction() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
