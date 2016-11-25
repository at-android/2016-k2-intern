package vn.asiantech.training;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    Boolean check = true;
    AtomicBoolean isrunning = new AtomicBoolean(false);
    private Button mBtnStart;
    private TextView mTvRandom;
    private TextView mTvRandomAfterSecond;
    private int FirsNumber = 0;
    private int SecondNumber = 0;
    private int ResultNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnStart = (Button) findViewById(R.id.btnStart);
        mTvRandom = (TextView) findViewById(R.id.tvRandom);
        mTvRandomAfterSecond = (TextView) findViewById(R.id.tvRandomAfterSecond);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rad = new Random();
                int number = rad.nextInt(99 - 10 + 1) + 10;
                ResultNumber = number;
                mTvRandom.setText(number + "");
                doStart();
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (check) {
                    mTvRandomAfterSecond.setText(msg.arg1 + "");
                    FirsNumber = msg.arg1;
                } else {
                    mTvRandomAfterSecond.setText(msg.arg1 + "" + FirsNumber);
                    SecondNumber = msg.arg1;
                    checkData();
                }
                super.handleMessage(msg);
            }
        };
    }

    void doStart() {
        isrunning.set(false);
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 2 && isrunning.get(); i++) {
                    SystemClock.sleep(1000);
                    Random rad = new Random();
                    int number = rad.nextInt(9 + 1);
                    if (i == 2) {
                        check = false;
                    }
                    Message msg = handler.obtainMessage();
                    msg.arg1 = number;
                    handler.sendMessage(msg);
                }

            }

        });
        isrunning.set(true);
        th.start();
    }

    void checkData() {
        Thread ths = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                if (SecondNumber == FirsNumber) {
                    Log.d("a", "asd");
                } else {
                    Message msg = handler.obtainMessage();
                    msg.arg1 = 10;
                    handler.sendMessage(msg);
                }
            }
        });
        ths.start();
    }
}
