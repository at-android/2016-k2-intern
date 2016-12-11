package vn.asiantech.training.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import vn.asiantech.training.R;

public class HomeActivity extends AppCompatActivity implements Toolbar.onToolbarInteraction, View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private Button mBtnDrawShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBtnDrawShape = (Button) findViewById(R.id.btnDrawShape);
        mBtnDrawShape.setOnClickListener(this);
    }

    @Override
    public void logOut() {
        mSharedPreferences = getSharedPreferences(LoginActivity.MYPREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(LoginActivity.IS_ACTIVE, false);
        Log.i("aaaaa", "aaa");
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDrawShape:
                Intent intent = new Intent(HomeActivity.this, ShapeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
