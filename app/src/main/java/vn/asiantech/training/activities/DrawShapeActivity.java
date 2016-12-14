package vn.asiantech.training.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.training.R;

/**
 * Created by phuong on 11/12/2016.
 */

public class DrawShapeActivity extends AppCompatActivity {
    private Button mBtnContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_shape);
        mBtnContinue = (Button) findViewById(R.id.btnContinue);

        mBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawShapeActivity.this, DrawShape2Activity.class);
                startActivity(intent);
            }
        });
    }

    public void onClicked(View view) {
        if (view.getId() == R.id.header_login) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
