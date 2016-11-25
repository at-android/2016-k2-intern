package vn.asiantech.training;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 25/11/2016.
 */

public class myAsyncTask extends AsyncTask<String, Object, Void> {
    private Activity mContext;
    private ProgressBar progressBar;
    private TextView mTvString;
    private TextView mTvPercent;
    private String mShowString = "";
    private String s;
    private int mLength;
    private float mPercent = 0;

    public myAsyncTask(Activity context) {
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(mContext,"That's a beautiful name",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        progressBar = (ProgressBar) mContext.findViewById(R.id.progressBar);
        mTvString = (TextView) mContext.findViewById(R.id.tvString);
        mTvPercent = (TextView) mContext.findViewById(R.id.tvPercent);
        char s = (char) values[0];
        mShowString += s;
        mTvString.setText(mShowString);
        mPercent += 100/mLength;
        progressBar.setProgress((int)(mPercent));
        mTvPercent.setText(mPercent+"%");
    }

    @Override
    protected Void doInBackground(String... strings) {
        s = strings[0];
        mLength = s.length();
        for (int i = 0; i < s.length(); i++) {
            SystemClock.sleep(300);
            publishProgress(s.charAt(i));
        }
        return null;
    }
}
