package vn.asiantech.training;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by phuong on 25/11/2016.
 */

public class MainActivity extends AppCompatActivity {
    private TextView mTvNumber;
    private TextView mTvRandomNumber;
    private Button mBtnStart;
    private boolean isRunning = true;
    private int mCount = 0;
    private int mFlag = 0;
    private TextView mTvRandomResult;
    private TextView mTvRandomResultDv;
    private String mResultRandom;
    private Handler mHandler1;
    private Handler mHandler2;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvNumber = (TextView) findViewById(R.id.tvNumber);
        mTvRandomNumber = (TextView) findViewById(R.id.tvRandomNumber);
        mBtnStart = (Button) findViewById(R.id.btnStart);
        mTvRandomResult = (TextView) findViewById(R.id.tvResult);
        mTvRandomResultDv = (TextView) findViewById(R.id.tvResultDv);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvNumber.setText((new Random()).nextInt(99) + "");
                mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("lap", ""+mCount);

                        if (mCount < 16 && isRunning) {
                            randomNumber1();
                            randomNumber2();
                            String result = mTvRandomResult.getText().toString() + "" + mTvRandomResultDv.getText().toString();

                            if (result.equals(mTvNumber.getText().toString())) {
                                showDialogNotification();
                                isRunning = false;
                            }
                            mTvRandomNumber.setText(mTvRandomNumber.getText().toString() + " " + result);
                            Log.d("kq", result);
                            Log.d("abc", mTvRandomNumber.getText().toString());
                            mTvRandomResult.setText("");
                            mTvRandomResultDv.setText("");
                            mHandler.postDelayed(this, 1500);
                            mCount++;
                        }
                        if(mCount==16){
                            mTvRandomResult.setText("");
                            mTvRandomResultDv.setText("");
                            showDialogNotification();
                        }
                    }
                }, 1500);
            }
        });

        mHandler1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvRandomResultDv.setText(String.valueOf(msg.arg1));
            }
        };

        mHandler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvRandomResult.setText(String.valueOf(msg.arg2));
            }
        };
    }

    public void randomNumber1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = (new Random()).nextInt(9);
                mHandler1.sendMessage(message);
            }
        });
        thread.start();
    }

    public void randomNumber2() {
        mHandler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg2 = (new Random()).nextInt(9);
                mHandler2.sendMessage(message);
            }
        }, 1000);
    }

    public void showDialogNotification() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notification);
        final TextView tvNotification = (TextView) dialog.findViewById(R.id.tvNotification);
        Log.d("abc count",String.valueOf(mCount));
        if(mCount==16){
            tvNotification.setText("Finish");
        }
        else{
            tvNotification.setText("Congratulation");
        }
        dialog.show();
    }

}
