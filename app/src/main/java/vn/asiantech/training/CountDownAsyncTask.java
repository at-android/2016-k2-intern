package vn.asiantech.training;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by MaiManhDuy on 11/25/2016.
 */

public class CountDownAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity mContextParent;
    private TextView mTvNumberRandom;
    private TextView mTvNumberCountDown;
    private int mNumberRandom;
    private int mNumberCountDown = 15;
    private Button mBtnStart;

    public CountDownAsyncTask(Activity activity) {
        mContextParent = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTvNumberRandom = (TextView) mContextParent.findViewById(R.id.txt_number_random);
        mTvNumberCountDown = (TextView) mContextParent.findViewById(R.id.txt_number_countdown);
        mBtnStart = (Button) mContextParent.findViewById(R.id.btn_start);
        mNumberRandom = Integer.parseInt(mTvNumberRandom.getText().toString());
        mTvNumberCountDown.setText(mNumberCountDown + "");
        mTvNumberRandom.setText(mNumberRandom + "");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i < 15; i++) {
            SystemClock.sleep(100);
            mNumberCountDown = mNumberCountDown - 1;
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mTvNumberCountDown.setText(mNumberCountDown + "");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mNumberRandom = mNumberRandom + 1;
        mTvNumberRandom.setText("" + mNumberRandom);
        if (mNumberRandom < 5) {
            CountDownAsyncTask task = new CountDownAsyncTask(mContextParent);
            task.execute();
        } else {
            mBtnStart.setEnabled(true);
            Toast.makeText(mContextParent, "Done", Toast.LENGTH_SHORT).show();
        }

    }
}
