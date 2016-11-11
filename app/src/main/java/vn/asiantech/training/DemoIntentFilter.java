package vn.asiantech.training;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class DemoIntentFilter extends AppCompatActivity implements View.OnClickListener {

    public static int REQUEST_IMAGE_CAPTURE = 1;
    public static int REQUEST_IMAGE_PICK = 2;
    private Button mBtncall;
    private Button mBtnlaunchWeb;
    private Button mBtnmessage;
    private Button mBtnopenGallery;
    private Button mBtnopenGGmap;
    private Button mBtnopenGGstore;
    private Button mBtnsendEmail;
    private Button mBtncamera;
    private ImageView mImgdisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_intent_filter);
        init();
    }

    public void init() {
        mBtncall = (Button) findViewById(R.id.Btncall);
        mBtnlaunchWeb = (Button) findViewById(R.id.BtnlaunchWeb);
        mBtnmessage = (Button) findViewById(R.id.Btnmessage);
        mBtnopenGallery = (Button) findViewById(R.id.BtnopenGallery);
        mBtnopenGGmap = (Button) findViewById(R.id.BtnopenGGmap);
        mBtnopenGGstore = (Button) findViewById(R.id.BtnopenGGstore);
        mBtnsendEmail = (Button) findViewById(R.id.BtnsendEmail);
        mBtncamera = (Button) findViewById(R.id.Btncamera);
        mImgdisplay = (ImageView) findViewById(R.id.Imgdisplay);
        mBtncall.setOnClickListener(this);
        mBtnlaunchWeb.setOnClickListener(this);
        mBtnmessage.setOnClickListener(this);
        mBtnopenGGmap.setOnClickListener(this);
        mBtnopenGGstore.setOnClickListener(this);
        mBtnopenGallery.setOnClickListener(this);
        mBtnsendEmail.setOnClickListener(this);
        mBtncamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Btncall:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0377778888"));
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(callIntent);
                }
                break;
            case R.id.Btncamera:
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera, REQUEST_IMAGE_CAPTURE);
                break;
            case R.id.BtnlaunchWeb:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("http://www.vnexpress.net"));
                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                }
                break;
            case R.id.Btnmessage:
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                startActivity(sendIntent);
                break;
            case R.id.BtnopenGallery:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REQUEST_IMAGE_PICK);
                break;
            case R.id.BtnopenGGmap:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                String data = String.format("geo:%s,%s", 100, 200);
                intent.setData(Uri.parse(data));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.BtnopenGGstore:
                Intent intentGGstore = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
                if (intentGGstore.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentGGstore);
                }
                break;
            case R.id.BtnsendEmail:
                Intent intentSend = new Intent(Intent.ACTION_SEND);
                intentSend.setType("plain/text");
                if (intentSend.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(intentSend, ""));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mImgdisplay.setImageBitmap(imageBitmap);
            }
        } else if (requestCode == REQUEST_IMAGE_PICK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                mImgdisplay.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
