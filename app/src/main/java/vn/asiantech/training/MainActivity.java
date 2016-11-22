package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnExit = (Button) findViewById(R.id.btnExit);

        btnStart.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnExit:
                onBackPressed();
                break;
            case R.id.btnStart:
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(intent);

        }
    }
}
