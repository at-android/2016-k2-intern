package vn.asiantech.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DemoIntentFilter extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_intent_filter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Btncall:
                break;
            case R.id.Btncamera:
                break;
            case R.id.BtnlaunchWeb:
                break;
            case R.id.Btnmessage:
                break;
            case R.id.BtnopenGallery:
                break;
            case R.id.BtnopenGGplay:
                break;
            case R.id.BtnopenGGstore:
                break;
            case R.id.BtnsendEmail:
                break;
        }
    }
}
