package vn.asiantech.training;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTvHour,mTvMinute;
    private Button mBtnStart;
    public static final String ACTION="vn.asiantech.training.CUSTOM_INTENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidget();
    //    startService(new Intent(getBaseContext(),myService.class));
        broadcastIntent(getApplicationContext());
    }

    public void getFormWidget(){
        mTvHour = (TextView)findViewById(R.id.tvHour);
        mTvMinute = (TextView)findViewById(R.id.tvMinute);
        mBtnStart = (Button)findViewById(R.id.btnStart);
    }

    public void broadcastIntent(Context view){
        Intent intent = new Intent();
        Bundle b = new Bundle();
        b.putInt("hour",Integer.parseInt(mTvHour.getText().toString()));
        b.putInt("minute",Integer.parseInt(mTvMinute.getText().toString()));
        intent.putExtra("data",b);
        intent.setAction(ACTION);
        sendBroadcast(intent);
    }
}
