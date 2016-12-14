package vn.asiantech.training.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import vn.asiantech.training.R;

/**
 * Created by phuong on 07/12/2016.
 */

public class CaculatorActivity extends AppCompatActivity {
    private MediaPlayer mp;
    private TextView mTvNumber1;
    private TextView mTvNumber2;
    private TextView mTvNumber3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp= MediaPlayer.create(this, R.raw.quehuongtoi);
        mp.start();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_caculator_alarm);

        mTvNumber1 = (TextView) findViewById(R.id.tvNumber1);
        mTvNumber2 = (TextView) findViewById(R.id.tvNumber2);
        mTvNumber3 = (TextView) findViewById(R.id.tvNumber3);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        Button btnOk = (Button) findViewById(R.id.btnOk);
        final EditText edtResult  = (EditText) findViewById(R.id.result);

        Random rd = new Random();
        int number1 = rd.nextInt(100);
        int number2 = rd.nextInt(100);
        int number3 = rd.nextInt(100);

        mTvNumber1.setText(String.valueOf(number1));
        mTvNumber2.setText(String.valueOf(number2));
        mTvNumber3.setText(String.valueOf(number3));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = Integer.parseInt(mTvNumber1.getText().toString()) - Integer.parseInt(mTvNumber2.getText().toString()) + Integer.parseInt(mTvNumber3.getText().toString());
                if(String.valueOf(result).equals(edtResult.getText().toString())){
                    mp.release();
                    onBackPressed();
                }
            }
        });
    }
}
