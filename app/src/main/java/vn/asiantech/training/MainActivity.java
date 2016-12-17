package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
@EActivity
public class MainActivity extends AppCompatActivity  {
    @ViewById(R.id.btnLogin)
    Button btnLogin;
    @ViewById(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Click(R.id.btnLogin)
    public void ClickBtnLogin(){
        Intent i1 = new Intent(MainActivity.this, LoginActivity_.class);
        startActivity(i1);
    }

    @Click(R.id.btnRegister)
    public void ClickBtnRegister(){
        Intent i2 = new Intent(MainActivity.this, RegisterActivity_.class);
        startActivity(i2);
    }
}
