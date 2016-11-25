package vn.asiantech.training;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Bai2 extends Activity {

    private TextView mTvluckyNumber;
    private TextView mTvrandomNumber1;
    private TextView mTvrandomNumber2;
    private Button mBtnStart;
    private Handler mHandlerRandomN1;
    private Handler mHandlerRandomN2;
    private Handler mHandlerTurn;
    private TextView mTvSaveRandNumber;
    private int mCount = 0;
    private TextView mTvturn;
    private boolean isRight = false;
    private Random mRand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        mTvluckyNumber = (TextView) findViewById(R.id.tvLuckyNumber);
        mTvrandomNumber1 = (TextView) findViewById(R.id.tvRandomNumber1);
        mTvrandomNumber2 = (TextView) findViewById(R.id.tvRandomNumber2);
        mBtnStart = (Button) findViewById(R.id.btnstart);
        mTvturn = (TextView) findViewById(R.id.tvTurn);
        mTvSaveRandNumber = (TextView) findViewById(R.id.tvSaveRandNumber);
        mRand = new Random();

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvluckyNumber.setText(mRand.nextInt(89) + 10 + "");
                mHandlerTurn = new Handler();
                if (mHandlerTurn != null) {
                    mHandlerTurn.removeCallbacksAndMessages(null);
                    mCount = 0;
                    mTvSaveRandNumber.setText("");
                    mTvrandomNumber1.setText("");
                    mTvrandomNumber2.setText("");
                }

                mHandlerTurn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCount > 14 || isRight == true) {
                            mHandlerTurn.removeCallbacks(this);
                            Toast.makeText(getApplicationContext(), "See you next times!", Toast.LENGTH_LONG).show();
                        } else {
                            mCount++;
                            mTvturn.setText(mCount + "");
                            startNumber1();
                            startNumber2();
                            mHandlerTurn.postDelayed(this, 1500);
                            String str = mTvrandomNumber1.getText().toString() + mTvrandomNumber2.getText().toString();
                            Log.i("str", str + " " + mTvluckyNumber.getText().toString());
                            if (str.equals(mTvluckyNumber.getText().toString())) {
                                Toast.makeText(getApplicationContext(), "Congratulation!", Toast.LENGTH_LONG).show();
                                isRight = true;
                            }
                            mTvSaveRandNumber.setText(mTvSaveRandNumber.getText().toString() + " " + str);
                            mTvrandomNumber1.setText("");
                            mTvrandomNumber2.setText("");
                        }
                    }
                }, 1500);
            }
        });

        mHandlerRandomN1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvrandomNumber1.setText(msg.arg1 + "");
            }
        };

        mHandlerRandomN2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvrandomNumber2.setText(msg.arg2 + "");
            }
        };
    }

    public void startNumber1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = mRand.nextInt(9);
                mHandlerRandomN1.sendMessage(message);
            }
        });
        thread.start();
    }

    public void startNumber2() {
        mHandlerRandomN2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg2 = mRand.nextInt(9);
                mHandlerRandomN2.sendMessage(message);
            }
        }, 1000);
    }

}
