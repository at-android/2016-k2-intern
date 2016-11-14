package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import vn.asiantech.training.model.Account;


/**
 * Created by phuong on 08/11/2016.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText mEdUsername;
    private EditText mEdPassword;
    private EditText mEdEmail;
    private Switch mSwPassword;
    private ImageView imClose;
    private TextView mBtnStarted;
    private RadioButton mRdbtnMale;
    private RadioButton mRdbtnFemale;
    private RadioGroup mRdgrGender;
    private String gender = "Female";
    public static String REGISTER_SEND = "account";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resgiter);

        mEdEmail = (EditText) findViewById(R.id.edEmail);
        mEdPassword = (EditText) findViewById(R.id.edPassword);
        mEdUsername = (EditText) findViewById(R.id.edUsername);
        mSwPassword = (Switch) findViewById(R.id.swPassword);
        imClose = (ImageView) findViewById(R.id.imClose);
        mBtnStarted = (TextView) findViewById(R.id.tvStarted);
        mRdbtnFemale = (RadioButton) findViewById(R.id.rdbtnFemale);
        mRdbtnMale = (RadioButton) findViewById(R.id.rdbtnMale);
        mRdgrGender = (RadioGroup) findViewById(R.id.rdgroupGender);

        mRdgrGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (mRdgrGender.getCheckedRadioButtonId()) {
                    case R.id.rdbtnMale:
                        gender = "Male";
                        break;
                    case R.id.rdbtnFemale:
                        gender = "Female";
                        break;
                }
            }
        });

        mBtnStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account();
                account.setEmail(mEdEmail.getText().toString());
                account.setUsername(mEdUsername.getText().toString());
                account.setPassword(mEdPassword.getText().toString());
                account.setGender(gender);

                Bundle bundle = new Bundle();
                bundle.putParcelable(REGISTER_SEND, account);
                Intent intent = new Intent(RegisterActivity.this, ShowDataRegister.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        imClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSwPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mEdPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mEdPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }
}
