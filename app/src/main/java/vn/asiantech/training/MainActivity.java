package vn.asiantech.training;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 1;
    private static final int CAMERA_TAKE_PICTURE = 1;
    Button btCall;
    Button btSendSMS;
    Button btSendMail;
    Button btOpenWeb;
    Button btOpenGooglePlay;
    Button btOpenGoogleMap;
    Button btGetImages;
    Button btTakePictures;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
            }
        });
        btSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });
        btSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
        btOpenWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebsite();
            }
        });
        btOpenGoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleMap();
            }
        });
        btGetImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImagesFormGala();
            }
        });
        btTakePictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicute();
            }
        });
    }

    public void init() {
        btCall = (Button) findViewById(R.id.btCall);
        btSendSMS = (Button) findViewById(R.id.btSendSMS);
        btSendMail = (Button) findViewById(R.id.btSendMail);
        btOpenWeb = (Button) findViewById(R.id.btOpenWeb);
        btOpenGoogleMap = (Button) findViewById(R.id.btOpenGGmap);
        btOpenGooglePlay = (Button) findViewById(R.id.btOpenGGplay);
        btGetImages = (Button) findViewById(R.id.btGetImg);
        btTakePictures = (Button) findViewById(R.id.btTakePic);
        imageView = (ImageView) findViewById(R.id.imgShow);
    }

    public void callPhone() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0918273645"));
        startActivity(callIntent);
    }

    public void sendSMS() {
        String content = "わたしはDUYです。よろしくおねがいします。";
        String number = "0918273645";
        Intent sendSMSIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
        sendSMSIntent.putExtra("sms_body", content);
        startActivity(sendSMSIntent);
    }

    public void sendMail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tamnm@asiantech.vn"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void openWebsite() {
        String url = "http://vnexpress.net/";
        Intent intentOpenWebsite = new Intent(Intent.ACTION_VIEW);
        intentOpenWebsite.setData(Uri.parse(url));
        startActivity(intentOpenWebsite);
    }

    public void openGooglePlay() {
        //Emalator have not google play
    }

    public void openGoogleMap() {
        Intent intentMap = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/C%C3%B4ng+Ty+TNHH+Asian+Tech/@16.0756786,108.2314881,17z/data=!3m1!4b1!4m5!3m4!1s0x3142182647552867:0x8760801d587e8d6e!8m2!3d16.0756786!4d108.2336768"));
        startActivity(intentMap);
    }

    public void getImagesFormGala() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    public void takePicute() {
        File destination;
        String name = "abcd";
        destination = new File(Environment
                .getExternalStorageDirectory(), name + ".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(destination));
        startActivityForResult(intent, CAMERA_TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (requestCode == CAMERA_TAKE_PICTURE && resultCode == RESULT_OK && data.getData() != null) {
                Uri uri = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);
            }
        }

    }
}
