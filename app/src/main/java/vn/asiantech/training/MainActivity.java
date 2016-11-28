package vn.asiantech.training;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final int CHECK_COUNT = 15;
    private Button mBtnStart;
    private TextView mTvLuckyNumber;
    private TextView mTvFirstChar;
    private TextView mTvSecondChar;
    private TextView mTvContain;
    private Handler mHandler;
    private Handler mHandler2;
    private Handler mHandler3;
    private int luckyNumber;
    private int count = 0;
    private Random mRan = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidget();
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnStart.setEnabled(false);
                luckyNumber = 10 + mRan.nextInt(90);
                mTvLuckyNumber.setText(luckyNumber + "");
                findNumber();
            }
        });

        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvFirstChar.setText(msg.arg1 + "");
            }
        };

        mHandler2 = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvSecondChar.setText(msg.arg2 + "");
            }
        };

    }

    public void getFormWidget() {
        mBtnStart = (Button) findViewById(R.id.btn);
        mTvLuckyNumber = (TextView) findViewById(R.id.tvLuckyNumber);
        mTvFirstChar = (TextView) findViewById(R.id.tvFirstChar);
        mTvSecondChar = (TextView) findViewById(R.id.tvSecondChar);
        mTvContain = (TextView) findViewById(R.id.tvContain);
    }

    public void doRandomFirstChar() {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                Message msg = mHandler.obtainMessage();
                msg.arg1 = 1 + random.nextInt(9);
                mHandler.sendMessage(msg);
            }
        };
        mHandler.postDelayed(r, 1000);
    }

    public void doRandomSecondChar() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                Message msg = mHandler2.obtainMessage();
                msg.arg2 = random.nextInt(10);
                mHandler2.sendMessage(msg);
            }
        });
        t.start();
    }

    public void findNumber() {
        mHandler3 = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                doRandomSecondChar();
                doRandomFirstChar();
                mHandler3.postDelayed(this, 2000);
                String saveNumber = mTvFirstChar.getText().toString() + mTvSecondChar.getText().toString();
                String check = luckyNumber + "";
                mTvContain.setText(mTvContain.getText() + " " + saveNumber);
                count++;
                if (saveNumber == check) {
                    Toast.makeText(getApplicationContext(), "Congratulation", Toast.LENGTH_LONG).show();
                    mHandler3.removeCallbacksAndMessages(null);
                } else if (count > CHECK_COUNT) {
                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
                    mHandler3.removeCallbacksAndMessages(null);
                }
            }
        };
        mHandler3.postDelayed(r, 2000);
    }
}
