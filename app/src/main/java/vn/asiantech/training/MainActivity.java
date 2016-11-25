package vn.asiantech.training;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    public static final int NUMBER = 5;
    public static final int SECOND = 15;
    private Button mBtn;
    private TextView mTvNumber;
    private TextView mTvSecond;
    private int mNumber = NUMBER;
    private int mSecond = SECOND;
    private Handler mHandler;
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidget();
        mTvNumber.setText(mNumber + "");
        mTvSecond.setText(mSecond + "");

        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvNumber.setText(msg.arg1 + "");
            }
        };

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDecrementNumber();
                doIncrement();
            }
        });
    }

    public void getFormWidget() {
        mBtn = (Button) findViewById(R.id.btnOK);
        mTvNumber = (TextView) findViewById(R.id.tvNumber);
        mTvSecond = (TextView) findViewById(R.id.tvSecond);
    }

    public void doDecrementNumber() {
        if (mNumber > 0) {
            mNumber = Integer.parseInt(mTvNumber.getText().toString()) - 1;
            mTvNumber.setText(mNumber + "");
        }
    }

    public void doIncrement() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        mHandler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                if (mSecond == 0) {
                    mNumber = Integer.parseInt(mTvNumber.getText().toString()) + 1;
                    mTvNumber.setText(mNumber + "");
                    mSecond = SECOND + 1;
                }
                mSecond--;
                mTvSecond.setText(mSecond + "");
                if (mNumber < NUMBER) {
                    mHandler.postDelayed(this, 500);
                }
            }
        };
        mHandler.postDelayed(r, 500);
    }


}
