package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by phuong on 29/11/2016.
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnLogin;
    private Button mBtnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mBtnLogin = (Button) findViewById(R.id.btnlogin);
        mBtnRegister = (Button) findViewById(R.id.btnregister);

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnlogin:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnregister:
                intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
