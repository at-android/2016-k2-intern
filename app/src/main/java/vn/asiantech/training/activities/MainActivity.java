package vn.asiantech.training.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.training.R;
import vn.asiantech.training.custom_layout.HeaderLayout;

public class MainActivity extends AppCompatActivity {
    private EditText mUser;
    private EditText mPass;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HeaderLayout hlTitle = (HeaderLayout) findViewById(R.id.headLayoutTitle);
        hlTitle.setVisibleBack(false);
        hlTitle.setVisibleLogout(false);
        mUser = (EditText) findViewById(R.id.tvUsername);
        mPass = (EditText) findViewById(R.id.tvPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogIn);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("123321".equals(mPass.getText().toString()) && "admin".equals(mUser.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
