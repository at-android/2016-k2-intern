package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.training.activities.HomeActivity;
import vn.asiantech.training.activities.LoginActivity;
import vn.asiantech.training.activities.RegisterActivity;

import static vn.asiantech.training.activities.RegisterActivity.PRIMARY_KEY;

public class MainActivity extends AppCompatActivity {
    private Button mBtnRegister;
    private Button mBtnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnLogin = (Button) findViewById(R.id.btnToLogin);
        mBtnRegister = (Button) findViewById(R.id.btnToRegister);
        SharedPreferences preferences = getSharedPreferences(PRIMARY_KEY, MODE_PRIVATE);
        String isCheck = preferences.getString("status", "");
        if ("true".equals(isCheck.toString())) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}
