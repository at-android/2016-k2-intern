package vn.asiantech.training.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.training.R;
import vn.asiantech.training.custom_layout.DrawShape;

public class ShapeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        setContentView(new DrawShape(this));
    }
}
