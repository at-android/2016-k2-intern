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
                String check = preferences.getString("check", "");
                if (check.equals("false") || check.equals("")) {
                    startActivity(new Intent(MainActivity.this, ManagerActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
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
