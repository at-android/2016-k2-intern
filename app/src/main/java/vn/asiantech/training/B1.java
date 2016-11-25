package vn.asiantech.training;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class B1 extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
    private TextView mTvNumber;
    private TextView mTvRandomNumber;
    private Button mBtnStart;
    private Button mBtnStop;
    private boolean isRunning = false;
    private int mCount = 0;
    private int mFlag = 0;
    private TextView mTvRandomResult;
    private TextView mTvRandomResultDv;
    private  String mResultRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvNumber = (TextView) findViewById(R.id.tvNumber);
        mTvRandomNumber = (TextView) findViewById(R.id.tvRandomNumber);
        mBtnStart = (Button) findViewById(R.id.btnStart);
        mBtnStop = (Button) findViewById(R.id.btnStop);
        mTvRandomResult = (TextView) findViewById(R.id.tvResult);
        mTvRandomResultDv = (TextView) findViewById(R.id.tvResultDv);

        mBtnStart.setEnabled(true);
        mBtnStop.setEnabled(false);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mTvNumber.setOnLongClickListener(this);

        handlerCount=new Handler();
        handlerCount.postDelayed(myRunnable,0);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                isRunning = true;
                Thread myThread = new Thread(myRunnable);
                myThread.start();
                mBtnStart.setEnabled(false);
                mBtnStop.setEnabled(true);
                //mTvRandomNumber.setText("");

                mCount = 0;
                break;
            case R.id.btnStop:
                mBtnStart.setEnabled(true);
                mBtnStop.setEnabled(false);
                mCount = 0;
                isRunning = false;
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(view.getId() == R.id.tvNumber){
            showDialogChangeContent();
        }
        return false;
    }

    public void showDialogChangeContent() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_content);
        final EditText edName = (EditText) dialog.findViewById(R.id.edString);
        final TextView tvChange = (TextView) dialog.findViewById(R.id.tvChange);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);

        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(edName.getText().toString())) {
                    mTvNumber.setText(edName.getText().toString());
                }
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showDialogNotification() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notification);
        final TextView tvNotification = (TextView) dialog.findViewById(R.id.tvNotification);
        Log.d("abc count",String.valueOf(mCount));
        if(mCount==15){
            tvNotification.setText("Finish");
        }
        else{
            tvNotification.setText("Congratulation");
        }
        dialog.show();
    }
    Handler handlerCount;

    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
                Log.d("abc","lap");
                mCount += 1;
                final int chuc = randomInteger();
                final int dv = randomInteger();
                final String result = ""+chuc+""+dv;
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       abc(dv,chuc,result);
                   }
               });
            handlerCount.postDelayed(this,1500);
        }
    };

    private void abc(int dv,int chuc,String numberRandon){
        mTvRandomNumber.setText(String.valueOf(chuc));
        mTvRandomResultDv.setText(String.valueOf(dv));
        mTvRandomNumber.setText(mResultRandom);
        if(mTvNumber.getText().toString().equals(numberRandon)){
            //dialog
            showDialogNotification();
            Log.d("Chuc mung","Chuc mung");
            mFlag = 1;

        }
    }
    public int randomInteger(){
        Random random = new Random();
        return random.nextInt(9);
    }
}
