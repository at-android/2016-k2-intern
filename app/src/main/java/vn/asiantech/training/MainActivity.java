package vn.asiantech.training;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnCall;
    Button mBtnMessage;
    Button mBtnMail;
    Button mBtnLaunch;
    Button mBtnStore;
    Button mBtnMap;
    Button mBtnGalerry;
    Button mBtnCamera;
    ImageView mImgView;
    public static final int RESULT_GALLERY = 0;
    public static final int CAMERA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFromWidget();
    }

    public void getFromWidget() {
        mBtnCall = (Button) findViewById(R.id.btnCall);
        mBtnMessage = (Button) findViewById(R.id.btnMessage);
        mBtnMail = (Button) findViewById(R.id.btnMail);
        mBtnLaunch = (Button) findViewById(R.id.btnLaunchWeb);
        mBtnStore = (Button) findViewById(R.id.btnOpenStore);
        mBtnMap = (Button) findViewById(R.id.btnGoogleMap);
        mBtnGalerry = (Button) findViewById(R.id.btnOpenGallery);
        mBtnCamera = (Button) findViewById(R.id.btnOpenCamera);
        mImgView = (ImageView) findViewById(R.id.mainImgView);

        mBtnCall.setOnClickListener(this);
        mBtnMessage.setOnClickListener(this);
        mBtnMail.setOnClickListener(this);
        mBtnLaunch.setOnClickListener(this);
        mBtnStore.setOnClickListener(this);
        mBtnMap.setOnClickListener(this);
        mBtnGalerry.setOnClickListener(this);
        mBtnCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCall:
                choseManyCall();
                break;
            case R.id.btnMessage:
                doSendMessage();
                break;
            case R.id.btnMail:
                doSendEmail();
                break;
            case R.id.btnLaunchWeb:
                launchWebsite();
                break;
            case R.id.btnOpenStore:
                openPlayStore();
                break;
            case R.id.btnGoogleMap:
                openGoogleMap();
                break;
            case R.id.btnOpenGallery:
                openGallery();
                break;
            case R.id.btnOpenCamera:
                openCamera();
                break;
        }
    }

    public void choseManyCall() {
        Intent mainIntent = new Intent(Intent.ACTION_DIAL, null);
        mainIntent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> pkgAppsList = getApplicationContext().getPackageManager().queryIntentActivities(mainIntent, 0);
        if (pkgAppsList.size() > 0) {
            Intent chooser = Intent.createChooser(mainIntent, "Choose");
            Log.i("info", chooser.toString());
            startActivity(chooser);
        }
    }

    public void doMakeCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0934888706"));
        startActivity(callIntent);
    }

    public void doSendMessage() {
        //    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("0934888706", null, "bla bla", null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void doSendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tamnm@asiantech.vn"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Intent filter");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello anh Tam");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, ""));
        }
    }

    public void launchWebsite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vnexpress.net"));
        if (browserIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(browserIntent);
        }
    }

    public void openPlayStore() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + this.getPackageName()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openGoogleMap() {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 16.054407, 108.202167);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void openGallery() {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_GALLERY);
    }

    public void openCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    //tra ve ket qua anh
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_GALLERY: {
                Uri photoUri = data.getData();
                if (photoUri != null) {
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(photoUri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    Log.v("Load Image", "Gallery File Path=====>>>" + filePath);

                    mImgView.setImageBitmap(BitmapFactory.decodeFile(filePath));
                }
            }
            break;

            case CAMERA_REQUEST: {
                if (resultCode == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    mImgView.setImageBitmap(photo);
                }
            }
        }

    }
}
