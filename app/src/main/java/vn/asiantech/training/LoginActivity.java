package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText mEdUser;
    private EditText mEdPass;
    private Button mBtnLogin;
    private String mPrefname = "my_data";
    private String mGetUsername;
    private String mGetPassword;
    private String mGetValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getFormWidget();

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Username or Password is Wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getFormWidget() {
        mEdUser = (EditText) findViewById(R.id.edUsername);
        mEdPass = (EditText) findViewById(R.id.edPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
    }

    public boolean validate() {
        if (mEdUser.getText().toString().equals(mGetUsername) && mEdPass.getText().toString().equals(mGetPassword)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        savingPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoringPreferences();
        if(mGetValid.equals("true")){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void savingPreferences() {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre = getSharedPreferences
                (mPrefname, MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = pre.edit();
        String user = "tan";
        String pwd = "123";
        String valid = "true";

        editor.putString("user", user);
        editor.putString("pwd", pwd);
        editor.putString("valid", valid);
        editor.commit();
    }

    public void restoringPreferences() {
        SharedPreferences pre = getSharedPreferences
                (mPrefname, MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
        mGetUsername = pre.getString("user", "");
        mGetPassword = pre.getString("pwd", "");
        mGetValid = pre.getString("valid","");

    }


}
