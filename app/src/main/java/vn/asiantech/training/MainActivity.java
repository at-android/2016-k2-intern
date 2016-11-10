package vn.asiantech.training;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTvCall;
    private TextView mTvMessage;
    private TextView mTvMail;
    private TextView mTvWeb;
    private TextView mTvStore;
    private TextView mTvMap;
    private TextView mTvCamera;
    private TextView mTvGalery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvCall = (TextView) findViewById(R.id.tvCall);
        mTvMessage = (TextView) findViewById(R.id.tvMessage);
        mTvMail = (TextView) findViewById(R.id.tvMail);
        mTvWeb = (TextView) findViewById(R.id.tvWeb);
        mTvStore = (TextView) findViewById(R.id.tvStore);
        mTvMap = (TextView) findViewById(R.id.tvMap);
        mTvCamera = (TextView) findViewById(R.id.tvCamera);
        mTvGalery = (TextView) findViewById(R.id.tvGalery);

        mTvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goi dien
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:01645484112"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        mTvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goi tin nhan
            }
        });

        mTvMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goi mail
            }
        });

        mTvWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mo web
            }
        });

        mTvGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mo thu vien anh
            }
        });

        mTvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mo camera
            }
        });

        mTvStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mo web
            }
        });
    }
}
