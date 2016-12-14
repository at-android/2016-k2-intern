package vn.asiantech.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ShapeActivity extends AppCompatActivity implements ToolbarCustomView.OnCallBack{
    ToolbarCustomView mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        mCallback = (ToolbarCustomView)findViewById(R.id.toolbar);
        mCallback.setCallBack(this);
    }

    @Override
    public void LogOut() {
        finish();
    }
}
