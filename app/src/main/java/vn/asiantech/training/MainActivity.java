package vn.asiantech.training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.getData() != null) {
            Log.d("intent received", intent.getData().toString());
            String phoneNumber = intent.getData().toString();
            phoneNumber = phoneNumber.substring(4);
            Log.d("intent received", "Received phone number : " + phoneNumber);
        } else {
            Log.d("intent received", "null intent received");
        }
    }
}
