package vn.asiantech.training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
    EditText name, pass;
    ImageView imgFacebook, imgGetStar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        imgGetStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, Resgistion.class);
                startActivity(myIntent);
            }
        });
    }

    public void Anhxa() {
        name = (EditText) findViewById(R.id.edtUsernameLG);
        pass = (EditText) findViewById(R.id.edtPass1);
        imgFacebook = (ImageView) findViewById(R.id.imgFacebookLG);
        imgGetStar = (ImageView) findViewById(R.id.imgGetStart);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String nameSt = data.getStringExtra("user");
            String passSt = data.getStringExtra("pass");
            name.setText(nameSt);
            pass.setText(passSt);
        }
    }
}
