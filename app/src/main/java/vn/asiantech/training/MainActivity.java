package vn.asiantech.training;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    Boolean check = true;
    private Button mBtnStart;
    private TextView mTvRandom;
    private TextView mTvRandomAfterSecond;
    private int FirsNumber = 0;
    private int ResultNumber;
    private TextView mTvTurn;
    private TextView mTvTurnNumber;
    private Timer mTimer;
    private int mCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnStart = (Button) findViewById(R.id.btnStart);
        mTvRandom = (TextView) findViewById(R.id.tvRandom);
        mTvRandomAfterSecond = (TextView) findViewById(R.id.tvRandomAfterSecond);
        mTvTurn = (TextView) findViewById(R.id.tvTurnRandom);
        mTvTurnNumber = (TextView) findViewById(R.id.tvTurnNumber);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rad = new Random();
                ResultNumber = rad.nextInt(99 - 10 + 1) + 10;
                mTvRandom.setText(ResultNumber + "");
                doStart();
                timecheck();
                mBtnStart.setEnabled(false);
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (check) {
                    mTvRandomAfterSecond.setText(msg.arg1 + "");
                    FirsNumber = msg.arg1;
                    check = false;
                } else {
                    mTvRandomAfterSecond.setText(msg.arg1 + "" + FirsNumber);
                }
                super.handleMessage(msg);
            }
        };
    }

    void doStart() {
        check = true;
        FirsNumber = 0;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 2; i++) {
                    SystemClock.sleep(1000);
                    Message msg = handler.obtainMessage();
                    Random rad = new Random();
                    int number = rad.nextInt(9 + 1);
                    msg.arg1 = number;
                    handler.sendMessage(msg);
                }
            }

        });
        th.start();
    }

    public void timecheck() {
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String result = mTvRandomAfterSecond.getText().toString();
                        int resulInt = Integer.parseInt(result);
                        if (resulInt == ResultNumber) {
                            mBtnStart.setEnabled(true);
                            Toast.makeText(MainActivity.this, "Congratulations", Toast.LENGTH_SHORT).show();
                            mTvTurn.setText(mCount + "");
                            mTimer.cancel();
                        } else {
                            if (mCount < 14) {
                                doStart();
                                ++mCount;
                                mTvRandomAfterSecond.setText("00");
                            } else {
                                ++mCount;
                                mBtnStart.setEnabled(true);
                                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                                mTimer.cancel();
                            }
                        }
                        mTvTurnNumber.setText(mTvTurnNumber.getText() + " " + result);
                        mTvTurn.setText(mCount + "");
                    }
                });
            }
        }, 3500, 3500);
    }
}
