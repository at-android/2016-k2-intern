package vn.asiantech.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TextActivity extends AppCompatActivity implements ToolbarCustomView.OnCallBack{
    ToolbarCustomView mCallBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        mCallBack = (ToolbarCustomView)findViewById(R.id.toolbar);
        mCallBack.setCallBack(this);
    }

    @Override
    public void LogOut() {
        finish();
    }
}
