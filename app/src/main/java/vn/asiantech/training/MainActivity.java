package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<AlarmObj> a = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a.add(new AlarmObj("haha", 4, 6, 2));
        a.add(new AlarmObj("mana", 3, 0, 5));
        a.add(new AlarmObj("mana", 21, 55, 5));
        a.add(new AlarmObj("mana", 19, 33, 5));
        a.add(new AlarmObj("mana", 17, 34, 5));
        final Intent newService = new Intent(this, AlarmClockService.class);
        newService.putExtra("a", a);
        startService(newService);
        final Button button = (Button) findViewById(R.id.btnStop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(newService);
            }
        });
    }
}
