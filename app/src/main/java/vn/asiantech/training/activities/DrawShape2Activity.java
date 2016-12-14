package vn.asiantech.training.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.asiantech.training.R;

/**
 * Created by phuong on 13/12/2016.
 */

public class DrawShape2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_shape2);
    }

    public void onClicked(View view) {
        if (view.getId() == R.id.header_login_shape2) {
            Intent intent = new Intent(DrawShape2Activity.this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
