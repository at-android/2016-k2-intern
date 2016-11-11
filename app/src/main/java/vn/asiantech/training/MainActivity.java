package vn.asiantech.training;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btList = (ImageButton) findViewById(R.id.btListNumber);
        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInten = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01239543423"));
                startActivity(myInten);
                waitData();
            }
        });
    }

    public void waitData() {
        Intent intent = getIntent();
        if (intent.getData() != null && Intent.ACTION_DIAL.equals(intent.getAction())) {
            Log.d("intent received", intent.getData().toString());
            String phoneNumber = intent.getData().toString();
            phoneNumber = phoneNumber.substring(4);
            Log.d("intent received", "Received phone number : " + phoneNumber);
        } else {
            Log.d("intent received", "null intent received");
        }
    }
}
