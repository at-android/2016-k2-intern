package vn.asiantech.training;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmClockService extends Service {
    ArrayList<AlarmObj> a = new ArrayList<>();
    ArrayList<Integer> mangInt = new ArrayList<>();
    private Calendar c;
    private Runnable mRun;

    public AlarmClockService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        a = (ArrayList<AlarmObj>) intent.getSerializableExtra("a");
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                showForegroundNotification();
                Log.d("nowCount", "Bao thuc");
                super.handleMessage(msg);
            }
        };
        mRun = new Runnable() {
            @Override
            public void run() {
                mangInt.clear();
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_WEEK);
                int second = c.get(Calendar.SECOND);
                int minute = c.get(Calendar.MINUTE);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int nowtime = hour * 60 + minute;
                for (int i = 0; i < a.size(); i++) {
                    int check = (a.get(i).getHour() * 60) + a.get(i).getMinute();
                    if (check - nowtime > 0) {
                        mangInt.add(check - nowtime);
                    }
                }
                int mn = mangInt.get(0);
                for (int i = 0; i < mangInt.size(); i++) {

                    if (mangInt.get(i) < mn) {
                        mn = mangInt.get(i);
                    }
                }
                Log.d("nowtime", hour + ":" + minute + ":" + second + " " + day + "Time delay " + mn);
                mn = mn * 60000 - (second * 1000);
                Message a = new Message();
                a.arg1 = 1;
                mHandler.sendMessageDelayed(a, mn);
                // mHandler.removeCallbacks(mRun);
            }
        };
        mRun.run();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }

    private void showForegroundNotification() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        // Create intent that will bring our app to the front, as if it was tapped in the app
        // launcher
        Intent showTaskIntent = new Intent(getApplicationContext(), NotifiService.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                1000,
                showTaskIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ringtone);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.app_name))
                .setContentText("BaoThuc")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setSound(sound)
                .setAutoCancel(true)
                .setOngoing(false)
                .setContentIntent(contentIntent)
                .build();
        startForeground(1, notification);
        //  stopForeground(true);
    }
}
