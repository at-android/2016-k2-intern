package vn.asiantech.training;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import static android.net.Uri.parse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvCall;
    private TextView mTvMessage;
    private TextView mTvMail;
    private TextView mTvWeb;
    private TextView mTvStore;
    private TextView mTvMap;
    private TextView mTvCamera;
    private TextView mTvGalery;
    private ImageView mImvPicture;
    private static final int REQUEST_CAMERA = 100;
    private static final int REQUEST_GALERY = 101;

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

        mTvCall.setOnClickListener(this);
        mTvMessage.setOnClickListener(this);
        mTvMail.setOnClickListener(this);
        mTvWeb.setOnClickListener(this);
        mTvGalery.setOnClickListener(this);
        mTvCamera.setOnClickListener(this);
        mTvStore.setOnClickListener(this);
        mTvMap.setOnClickListener(this);
    }

    private void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(parse("tel:01645484112"));
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
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
        String longitude = "20.344,34.34";
        String latitude = "20.5666,45.345";
        String uri = String.format(Locale.ENGLISH, "geo:%s,%s", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCall:
                launchCallApp();
                break;
            case R.id.tvMessage:
                sendSMS("01645484112", "test Send Message");
                break;
            case R.id.tvMail:
                sendMail();
                break;
            case R.id.tvWeb:
                lauchWeb();
                break;
            case R.id.tvStore:
                lauchPlayStore();
                break;
            case R.id.tvMap:
                lauchMap();
                break;
            case R.id.tvCamera:
                lauchCamera();
                break;
            case R.id.tvGalery:
                lauchGalery();
                break;
        }

    }

    private void launchCallApp() {
        Intent intent = new Intent(Intent.ACTION_CALL,null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List list = getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);
        if (list.size() > 0) {
            Intent chooser = Intent.createChooser(intent, "Choose");
            startActivity(chooser);
        }
    }

}
