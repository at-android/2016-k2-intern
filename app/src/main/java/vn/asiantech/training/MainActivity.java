package vn.asiantech.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnStart = (Button) findViewById(R.id.btnStart);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsynctask asynctask = new MyAsynctask(MainActivity.this);
                asynctask.execute();
                mBtnStart.setEnabled(false);
            }
        });

    }
}
