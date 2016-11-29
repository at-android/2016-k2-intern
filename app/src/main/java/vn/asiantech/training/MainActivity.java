package vn.asiantech.training;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

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

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences settings = getSharedPreferences(NAME_SHAREPREPERENCE, 0);
                String login = settings.getString(LoginActivity.NAME_LOGIN,"");
                if("".equals(login)){
                    Intent mainIntent = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(mainIntent);
                }
                else{
                    Intent mainIntent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(mainIntent);
                }
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


}
