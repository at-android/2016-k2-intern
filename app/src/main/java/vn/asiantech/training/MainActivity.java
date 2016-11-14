package vn.asiantech.training;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton btCall;
    TextView txtNumber;
    String numberphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        waitData();
        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + numberphone));
                startActivity(callIntent);

            }
        });
    }

    public void init() {
        btCall = (ImageButton) findViewById(R.id.btCallPhone);
        txtNumber = (TextView) findViewById(R.id.txtNumberPhone);
    }
    public void waitData() {
        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_DIAL.equals(action)) {
            numberphone = intent.getStringExtra(Intent.EXTRA_TEXT);
            //Toast.makeText(MainActivity.this, numberphone+"", Toast.LENGTH_SHORT).show();
            txtNumber.setText(numberphone);
        }
    }

}
