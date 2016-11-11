package vn.asiantech.training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_DIAL.equals(action)) {

        }*/
        waitData();
    }

    public void waitData() {
        Intent intent = getIntent();
        String action = intent.getAction();

    }

}
