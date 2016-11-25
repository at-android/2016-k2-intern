package vn.asiantech.training;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private TextView mTvContent;
    private TextView mTvProgessBar;
    private EditText mEdtProgessBar;
    private String mContent;
    private Button mBtnStart;
    private Button mBtnStop;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mTvContent = (TextView) findViewById(R.id.tvContentString);
        mTvProgessBar = (TextView) findViewById(R.id.tvProgessBar);
        mEdtProgessBar = (EditText) findViewById(R.id.edString);
        mBtnStart = (Button) findViewById(R.id.btnStart);
        mBtnStop = (Button) findViewById(R.id.btnStop);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnStart.setEnabled(false);
                mBtnStop.setEnabled(true);
                mProgressBar.setProgress(0);
                isRunning = true;
                if(isRunning){
                    new performTask(getBaseContext()).execute(mContent);
                }

            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnStart.setEnabled(true);
                mBtnStop.setEnabled(false);
                mProgressBar.setProgress(0);
                isRunning = false;
            }
        });
        mTvContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialogChangeContent();
                return true;
            }
        });
        mContent = mTvContent.getText().toString();
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
                    mTvContent.setText(edName.getText().toString());
                    mContent = edName.getText().toString();
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

    public void showDialogNotificationComplete(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notification);
        dialog.show();
    }
    private class performTask extends AsyncTask<String, Integer, String> {
        Context context;

        public performTask(Context cont) {
            this.context = cont;
        }

        @Override
        protected String doInBackground(String... strings) {
            String mCharacter = "";
            String mResult = "";
            int count = 1;
            for (int i = 0; i < strings[0].length(); i++) {
                SystemClock.sleep(300);
                mCharacter = String.valueOf(strings[0].charAt(i));
                count += Math.floor(100 / (mContent.length()));
                publishProgress(count);
                mResult += mCharacter;
                final String finalMResult = mResult;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEdtProgessBar.setText(finalMResult);
                    }
                });
            }
            return mResult;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mTvProgessBar.setText(String.valueOf(values[0])+ "%");
            mProgressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            if(mTvProgessBar.getText().toString().equals("100%")){
                showDialogNotificationComplete();
            }
            super.onPostExecute(s);
        }
    }


}
