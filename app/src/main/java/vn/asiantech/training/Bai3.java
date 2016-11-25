package vn.asiantech.training;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Bai3 extends AppCompatActivity {

    private TextView mTvDisplay;
    private ProgressBar mProgresBar;
    private Button mBtnstart;
    private String mString;
    private double mProgress = 0;
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        mTvDisplay = (TextView) findViewById(R.id.tvDisplay);
        mProgresBar = (ProgressBar) findViewById(R.id.progressBar);
        mBtnstart = (Button) findViewById(R.id.btnStart);
        mString = "Le Hoang Duy";
        mBtnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlowTask slowTask = new SlowTask();
                slowTask.execute(mString);
                mIndex = 0;
                mProgress = 0;
                mTvDisplay.setText("");
                mBtnstart.setEnabled(false);
            }
        });
    }

    private class SlowTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... params) {
            double d = ((double) 1 / params[0].length()) * 100;
            for (int i = 0; i < params[0].length(); i++) {
                SystemClock.sleep(300);
                mProgress = mProgress + d;
                publishProgress((int) (mProgress));
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mTvDisplay.setText(mTvDisplay.getText() + "" + mString.charAt(mIndex++));
            mProgresBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mBtnstart.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_LONG).show();
            super.onPostExecute(aVoid);
        }
    }
}
