package vn.asiantech.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by phuong on 02/12/2016.
 */

public class ShowClockActivity extends AppCompatActivity {
    TextView txtdate;
    Calendar calendar;
    Date dates;
    Time time;
    String currentdate;
    AnalogClock ac;
    DigitalClock dc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clock);

        ac = (AnalogClock) findViewById(R.id.analogclock1);
        dc = (DigitalClock) findViewById(R.id.digitalClock1);
        txtdate = (TextView) findViewById(R.id.txtdate);
        time = new Time();
        time.setToNow();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");  // 04-05-2015
        calendar = Calendar.getInstance();
        currentdate = sf.format(calendar.getTime());  // getting current datetime of android.
        txtdate.setText(currentdate);

    }
}
