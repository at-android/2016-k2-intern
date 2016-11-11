package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static vn.asiantech.training.R.id.btLogOut;

public class Information extends AppCompatActivity {
    TextView txtName, txtMail, txtPass;
    Button btLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        init();
        Intent myIntet = getIntent();
        txtName.setText(myIntet.getStringExtra("name"));
        txtMail.setText(myIntet.getStringExtra("mail"));
        txtPass.setText(myIntet.getStringExtra("pass"));
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Information.this, MainActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });
    }

    public void init() {
        txtName = (TextView) findViewById(R.id.txtUser);
        txtMail = (TextView) findViewById(R.id.txtMail);
        txtPass = (TextView) findViewById(R.id.txtPassword);
        btLogout = (Button) findViewById(btLogOut);
    }
}

