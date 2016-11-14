package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.asiantech.training.model.Account;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * Created by phuong on 08/11/2016.
 */

public class ShowDataRegister extends AppCompatActivity {
    private TextView mTvUsername;
    private TextView mTvPassword;
    private TextView mTvGender;
    private TextView mTvEmail;
    private Button mBtnLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_register);

        mTvEmail = (TextView) findViewById(R.id.tvDataEmail);
        mTvGender = (TextView) findViewById(R.id.tvDataGender);
        mTvPassword = (TextView) findViewById(R.id.tvDataPassword);
        mTvUsername = (TextView) findViewById(R.id.tvDataUsername);
        mBtnLogout = (Button) findViewById(R.id.btnLogout);

        Bundle bundle = getIntent().getExtras();
        Account account = bundle.getParcelable(RegisterActivity.REGISTER_SEND);
        mTvUsername.setText(account.getUsername());
        mTvPassword.setText(account.getPassword());
        mTvGender.setText(account.getGender());
        mTvEmail.setText(account.getEmail());

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowDataRegister.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}
