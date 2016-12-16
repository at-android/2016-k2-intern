package vn.asiantech.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Click(R.id.btnRegister)
    void goToRegisterActivity() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.btnLogin)
    void gotoLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity_.class);
        startActivity(intent);
    }
}
