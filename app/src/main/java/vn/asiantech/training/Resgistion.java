package vn.asiantech.training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class Resgistion extends Activity {
    EditText mail, name, pass;
    Switch swonPW;
    Button btGetstarted;
    RadioGroup namnu;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistion);
        Anhxa();
        btGetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInten = new Intent(Resgistion.this, Information.class);
                myInten.putExtra("name", name.getText().toString());
                myInten.putExtra("mail", mail.getText().toString());
                myInten.putExtra("pass", pass.getText().toString());
                startActivity(myInten);
            }
        });
        swonPW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == false) {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ;
                }
                if (b == true) {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                // Toast.makeText(RegistrationAcitvity.this, b+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Anhxa() {
        mail = (EditText) findViewById(R.id.edMailRes);
        name = (EditText) findViewById(R.id.edUserRes);
        pass = (EditText) findViewById(R.id.edPassRes);
        swonPW = (Switch) findViewById(R.id.switchOn);
        btGetstarted = (Button) findViewById(R.id.btGetStarRes);
        namnu = (RadioGroup) findViewById(R.id.idRadioGroup1);
    }
}