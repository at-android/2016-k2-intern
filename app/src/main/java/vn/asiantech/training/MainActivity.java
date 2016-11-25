package vn.asiantech.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownAsyncTask countDownAsyncTask;
    private Button mBtnStart;
    private TextView mTvNumberRandom;
    private int mNumberRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mTvNumberRandom = (TextView) findViewById(R.id.txt_number_random);
        Random random = new Random();
        mNumberRandom = random.nextInt(5) + 1;
        mTvNumberRandom.setText(mNumberRandom + "");
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumberRandom = Integer.parseInt(mTvNumberRandom.getText().toString());
                mNumberRandom = mNumberRandom - 1;
                mTvNumberRandom.setText(mNumberRandom + "");
                countDownAsyncTask = new CountDownAsyncTask(MainActivity.this);
                countDownAsyncTask.execute();
                mBtnStart.setEnabled(false);
            }
        });

    }

}
