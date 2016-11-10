package vn.asiantech.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.*;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button mBtn1,mBtn2,mBtn3,mBtn4,mBtn5,mBtn6,mBtn7,mBtn8;
    ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFromWidget();
    }
    public void getFromWidget(){
        mBtn1 = (Button)findViewById(R.id.btn_1);
        mBtn2 = (Button)findViewById(R.id.btn_2);
        mBtn3 = (Button)findViewById(R.id.btn_3);
        mBtn4 = (Button)findViewById(R.id.btn_4);
        mBtn5 = (Button)findViewById(R.id.btn_5);
        mBtn6 = (Button)findViewById(R.id.btn_6);
        mBtn7 = (Button)findViewById(R.id.btn_7);
        mBtn8 = (Button)findViewById(R.id.btn_8);
        mImg = (ImageView)findViewById(R.id.main_imgView);

    }
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
            case R.id.btn_6:
                break;
            case R.id.btn_7:
                break;
            case R.id.btn_8:
                break;

        }
    }
}
