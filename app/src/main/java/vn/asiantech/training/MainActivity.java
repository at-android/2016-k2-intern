package vn.asiantech.training;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SetTimeFragment.SendData,View.OnClickListener{
    private Button mBtnStart;
    private ImageView imgView;
    private int mHour;
    private int mMinute;
    private int mFlag = -1;
    public static final String ACTION="vn.asiantech.training.CUSTOM_INTENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidget();

        mBtnStart.setOnClickListener(this);
        imgView.setOnClickListener(this);
    }

    public void getFormWidget(){
        mBtnStart = (Button)findViewById(R.id.btnStart);
        imgView = (ImageView)findViewById(R.id.imgView);

    }

    public void broadcastIntent(Context view){
        Intent intent = new Intent();
        Bundle b = new Bundle();
        b.putInt("hour",mHour);
        b.putInt("minute",mMinute);
        intent.putExtra("data",b);
        intent.setAction(ACTION);
        sendBroadcast(intent);
    }

    @Override
    public void onArticleSelected(Time t) {
        mHour = Integer.parseInt(t.getHour());
        mMinute = Integer.parseInt(t.getMinute());
        Log.i("hourFromFrag",mHour+"");
        Log.i("minuteFromFrag",mMinute+"");
        startService(new Intent(this,myService.class));
        Intent i = new Intent(this,myService.AlarmReceiver.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Flag",mFlag);
        i.putExtra("data",bundle);
        sendBroadcast(i);
        mFlag++;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
            //    startService(new Intent(getBaseContext(),myService.class));
                break;
            case R.id.imgView:
                DialogFragment frag = new SetTimeFragment();
                frag.show(getSupportFragmentManager(),"SetTimeFragment");
                break;
        }
    }
}
