package vn.asiantech.training;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by phuong on 25/11/2016.
 */

public class Bai1 extends AppCompatActivity {
    private TextView mTvNumber;
    private TextView mTvRandomResult;
    private Button mBtnStart;
    private Button mBtnReset;
    private Handler mHandler;
    private int mCount = 1;
    private Handler mHandlerMain;
    private boolean mFlag = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvNumber = (TextView) findViewById(R.id.tvNumber);
        mBtnStart = (Button) findViewById(R.id.btnStart);
        mTvRandomResult = (TextView) findViewById(R.id.tvResult);
        mBtnReset = (Button) findViewById(R.id.btnStop);

        mTvNumber.setText(String.valueOf(new Random().nextInt(4)+1));

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(mTvNumber.getText().toString())>=1){
                    mTvNumber.setText(String.valueOf(Integer.parseInt(mTvNumber.getText().toString())-1));
                }
                if(mHandlerMain!=null){
                    mHandlerMain.removeCallbacksAndMessages(null);
                }
                mHandlerMain = new Handler();
                mHandlerMain.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mCount==16){
                            mTvNumber.setText(String.valueOf(Integer.parseInt(mTvNumber.getText().toString()) + 1));
                            mCount = 1;
                        }
                        getNumber();
                        mHandlerMain.postDelayed(this, 1000);
                    }
                },1000);

            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvRandomResult.setText(String.valueOf(msg.arg1));
                if (Integer.parseInt(mTvNumber.getText().toString()) == 5) {
                    mHandler.removeCallbacksAndMessages(null);
                }
            }
        };

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFlag = true;
                mTvRandomResult.setText("");
            }
        });

    }


    public void getNumber() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = (mCount++);
                mHandler.sendMessage(message);
            }
        });
        thread.start();
    }
}
