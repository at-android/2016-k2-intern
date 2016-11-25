package vn.asiantech.training;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Bai1 extends AppCompatActivity {

    private TextView mTvNumber;
    private TextView mTvRandNumber;
    private Button mBtnStart;
    private Random mRand;
    private Handler mHandlerRandNumber;
    private int mCount = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        mTvNumber = (TextView) findViewById(R.id.tvNumber);
        mTvRandNumber = (TextView) findViewById(R.id.tvRandomNumber);
        mBtnStart = (Button) findViewById(R.id.btnstart);
        mRand = new Random();
        mTvNumber.setText(mRand.nextInt(4) + 1 + "");
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(mTvNumber.getText().toString()) > 0) {
                    mTvNumber.setText(Integer.parseInt(mTvNumber.getText().toString()) - 1 + "");
                }
                if (mHandlerRandNumber != null) {
                    mHandlerRandNumber.removeCallbacksAndMessages(null);
                }
                mHandlerRandNumber.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCount == 0) {
                            mTvNumber.setText(Integer.parseInt(mTvNumber.getText().toString()) + 1 + "");
                            mCount = 15;
                        }
                        doStart();
                        mHandlerRandNumber.postDelayed(this, 1000);
                    }
                }, 500);
            }
        });

        mHandlerRandNumber = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvRandNumber.setText(msg.arg1 + "");
                if (Integer.parseInt(mTvNumber.getText().toString()) == 5) {
                    mHandlerRandNumber.removeCallbacksAndMessages(null);
                }
            }
        };
    }

    public void doStart() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = mCount--;
                mHandlerRandNumber.sendMessage(message);
            }
        });
        thread.start();
    }
}
