package vn.asiantech.training.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.training.R;
import vn.asiantech.training.dialog.DialogUtils;

public class DialogActivity extends AppCompatActivity {
    //Show dialog cancle ringtone
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        DialogUtils.dialogCancleRingtone(DialogActivity.this);
    }
}
