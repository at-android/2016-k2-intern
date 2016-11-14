package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import vn.asiantech.training.model.Account;


/**
 * Created by phuong on 08/11/2016.
 */

public class LoginFacebookActivity extends AppCompatActivity {
    private EditText mEdUsername;
    private EditText edPassword;
    private TextView btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_facebook);

        mEdUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (TextView) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account();
                account.setUsername(mEdUsername.getText().toString());
                account.setPassword(edPassword.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.LOGIN_SEND, account);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
