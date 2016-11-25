package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnEx1;
    private Button mBtnEx2;
    private Button mBtnEx3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnEx1 = (Button) findViewById(R.id.btnBai1);
        mBtnEx2 = (Button) findViewById(R.id.btnBai2);
        mBtnEx3 = (Button) findViewById(R.id.btnBai3);

        mBtnEx1.setOnClickListener(this);
        mBtnEx2.setOnClickListener(this);
        mBtnEx3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBai1:
                Intent intent = new Intent(MainActivity.this, Bai1.class);
                startActivity(intent);
                break;
            case R.id.btnBai2:
                Intent intent1 = new Intent(MainActivity.this, Bai2.class);
                startActivity(intent1);
                break;
            case R.id.btnBai3:
                Intent intent2 = new Intent(MainActivity.this, Bai3.class);
                startActivity(intent2);
                break;
        }
    }
}
