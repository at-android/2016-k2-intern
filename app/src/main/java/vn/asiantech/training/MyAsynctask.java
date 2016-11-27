package vn.asiantech.training;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by MaiManhDuy on 11/27/2016.
 */

public class MyAsynctask extends AsyncTask<Void, Integer, Void> {
    private Activity mContextParent;
    private EditText mEdInput;
    private TextView mTvOutput;
    private TextView mTvShowProsent;
    private ProgressBar mPbLoading;
    private String strTmp[];
    private Button mBtnStart;
    private int strTmpLenght;
    private double mProsent;

    public MyAsynctask(Activity activity) {
        mContextParent = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i < strTmp.length; i++) {
            SystemClock.sleep(300);
            double a = Double.parseDouble(strTmpLenght + "");
            mProsent = (i / a) * 100;
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mPbLoading = (ProgressBar) mContextParent.findViewById(R.id.pbCountDown);
        mEdInput = (EditText) mContextParent.findViewById(R.id.ed_input_text);
        mTvOutput = (TextView) mContextParent.findViewById(R.id.tvShow_text);
        mTvShowProsent = (TextView) mContextParent.findViewById(R.id.tvShowProsent);
        mBtnStart = (Button) mContextParent.findViewById(R.id.btnStart);
        strTmp = mEdInput.getText().toString().split("");
        strTmpLenght = strTmp.length - 1;
        mPbLoading.setMax(strTmpLenght);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mBtnStart.setEnabled(true);
        Toast.makeText(mContextParent, "Success!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mPbLoading.setProgress(values[0]);
        mTvShowProsent.setText(mProsent + " %");
        mTvOutput.setText(mTvOutput.getText() + " " + strTmp[values[0]]);
        super.onProgressUpdate(values);
    }
}
