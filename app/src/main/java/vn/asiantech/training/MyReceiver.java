package vn.asiantech.training;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Administrator on 4/12/2016.
 */

public class MyReceiver extends BroadcastReceiver {
    int hour;
    int minute;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()==MainActivity.ACTION){
            Bundle b =  intent.getBundleExtra("data");
            int hour = b.getInt("hour");
            int minute = b.getInt("minute");
            Log.i("hour",hour+"");
        }

    }
}
