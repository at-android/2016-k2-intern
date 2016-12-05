package vn.asiantech.training;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/12/2016.
 */

public class MyReceiver extends BroadcastReceiver {
    private int mHour;
    private int mMinute;
    private ArrayList<Day> mArr = new ArrayList<Day>();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == MainActivity.ACTION) {
            Bundle b = intent.getBundleExtra("data");
            mHour = b.getInt("hour");
            mMinute = b.getInt("minute");
            mArr = b.getParcelableArrayList("array");
            Log.i("mHourInReceiver", mHour +"");
            Log.i("mMinuteInReceiver", mMinute +"");
            Bundle bundle = new Bundle();
            bundle.putInt("hour",mHour);
            bundle.putInt("minute",mMinute);
            bundle.putParcelableArrayList("array",mArr);
            Intent i = new Intent(context,myService.class);
            i.putExtra("data",bundle);
            context.startService(i);
        }
    }

}
