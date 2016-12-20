package vn.asiantech.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.API.Api;
import vn.asiantech.training.API.ApiClient;
import vn.asiantech.training.R;
import vn.asiantech.training.model.RegisterResult;


@EActivity
public class RegisterActivity extends AppCompatActivity {
    @ViewById(R.id.edName)
    EditText mEdName;
    @ViewById(R.id.edEmail)
    EditText mEdEmail;
    @ViewById(R.id.edPassword)
    EditText mEdPass;
    @ViewById(R.id.edPassConfirm)
    EditText mEdPassConfirm;
    @ViewById(R.id.btnSubmit)
    Button mBtnSubmit;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Click(R.id.btnCancel)
    void CancelAction(){
        Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    @Click(R.id.btnSubmit)
    void SubmitAction(){
        String name = mEdName.getText().toString();
        final String email = mEdEmail.getText().toString();
        final String password = mEdPass.getText().toString();

        Api api = ApiClient.retrofit().create(Api.class);
        Call<RegisterResult> resultRegister = api.createAccount(name, email, password);
        resultRegister.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                if (response.isSuccessful()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    bundle.putString("password", password);

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity_.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Fail" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }
}
