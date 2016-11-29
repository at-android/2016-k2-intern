package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidget();
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    public void getFormWidget(){
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                Intent i1 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i1);
                break;
            case R.id.btnRegister:
                Intent i2 = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i2);
                break;
        }
    }
}
