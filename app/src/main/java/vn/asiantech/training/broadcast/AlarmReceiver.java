package vn.asiantech.training.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import vn.asiantech.training.activities.CaculatorActivity;


/**
 * Created by phuong on 07/12/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i=new Intent(context.getApplicationContext(),CaculatorActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }
}
