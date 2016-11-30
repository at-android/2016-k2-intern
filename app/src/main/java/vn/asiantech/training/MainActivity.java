package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import static vn.asiantech.training.RegisterActivity.NAME_SHAREPREPERENCE;

/**
 * Created by phuong on 25/11/2016.
 */

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences settings = getSharedPreferences(NAME_SHAREPREPERENCE, 0);
                String login = settings.getString(LoginActivity.NAME_LOGIN, "");
                if ("".equals(login)) {
                    Intent mainIntent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(mainIntent);
                } else {
                    Intent mainIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(mainIntent);
                }
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


}
