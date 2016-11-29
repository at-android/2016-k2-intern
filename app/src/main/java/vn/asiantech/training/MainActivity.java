package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashScreen();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                SharedPreferences preferences = getSharedPreferences("Info", MODE_PRIVATE);
                String check = preferences.getString("username", "");
                if (check.equals("")) {
                    startActivity(new Intent(MainActivity.this, ManagerActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }

            }
        };
    }

    void splashScreen() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        });
        thread.start();
    }
}
