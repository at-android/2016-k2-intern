package vn.asiantech.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.training.model.Account;


public class MainActivity extends AppCompatActivity {
    private ImageView mImvAdd;
    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private ImageView mImvFacebook;
    private ImageView mImvGoogle;
    private ImageView mImvTwitter;
    private TextView mTvSignIn;
    private TextView mTvForgotPassword;
    private final int REQUEST_CODE = 100;
    public static String LOGIN_SEND = "account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImvAdd = (ImageView) findViewById(R.id.imAdd);
        mEdtUsername = (EditText) findViewById(R.id.edUsername);
        mEdtPassword = (EditText) findViewById(R.id.edPassword);
        mImvFacebook = (ImageView) findViewById(R.id.imFacebook);
        mImvGoogle = (ImageView) findViewById(R.id.imGoogle);
        mImvTwitter = (ImageView) findViewById(R.id.imTwitter);
        mTvSignIn = (TextView) findViewById(R.id.tvSignIn);
        mTvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);

        mImvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mImvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginFacebookActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("======", requestCode + "   " + resultCode);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getExtras();
                Account account = bundle.getParcelable(LOGIN_SEND);
                if (account == null) {
                    return;
                }
                mEdtUsername.setText(account.getUsername());
                mEdtPassword.setText(account.getPassword());
            }

        }
    }
}
