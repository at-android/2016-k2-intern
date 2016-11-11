package vn.asiantech.training;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.net.Uri.parse;

public class MainActivity extends AppCompatActivity {
    private TextView mTvCall;
    private TextView mTvMessage;
    private TextView mTvMail;
    private TextView mTvWeb;
    private TextView mTvStore;
    private TextView mTvMap;
    private TextView mTvCamera;
    private TextView mTvGalery;
    private ImageView mImvPicture;
    private int REQUEST_CAMERA = 100;
    private int REQUEST_GALERY = 101;

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
        mImvPicture = (ImageView) findViewById(R.id.imvPicture);

        mTvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(parse("tel:01645484112"));
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
                sendSMS("01645484112", "test Send Message");
            }
        });

        mTvMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

        mTvWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lauchWeb();
            }
        });

        mTvGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lauchGalery();
            }
        });

        mTvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lauchCamera();
            }
        });

        mTvStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lauchPlayStore();
            }
        });

        mTvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lauchMap();
            }
        });
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    private void sendMail() {
        String subject = "Demo subject";
        String message = "Demo message";
        String to = "tamnm@asiantech.com";

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    private void lauchPlayStore() {
        final String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    private void lauchWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW, parse("http://www.vnexpress.net"));
        startActivity(intent);
    }

    private void lauchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    }

    private void lauchMap() {
        Uri uri = parse("google.streetview:cbll=46.414382,10.013988");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void lauchGalery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImvPicture.setImageBitmap(imageBitmap);
        }
        if (requestCode == REQUEST_GALERY) {
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            mImvPicture.setImageBitmap(bitmap);
        }
    }
}
