package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.api.Api;
import vn.asiantech.training.api.ApiClient;
import vn.asiantech.training.model.RegisterResult;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {
    @ViewById(R.id.edtUsername)
    EditText mEdtName;
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.edtRetypePwd)
    EditText mEdtRetypePwd;

    @Click(R.id.btnSignUp)
    void signUp() {
        String name = mEdtName.getText().toString();
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();
        String retypePwd = mEdtRetypePwd.getText().toString();
        if (name.equals("") || email.equals("") || password.equals("") || retypePwd.equals("")) {
            Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
        } else {
            Api api = ApiClient.getClient().create(Api.class);
            retrofit2.Call<RegisterResult> resultCall = api.createAccount(name, email, password);
            resultCall.enqueue(new Callback<RegisterResult>() {
                @Override
                public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                    if (response.isSuccessful()) {
                        Log.i("successful", "successful");
                        Bundle bundle = new Bundle();
                        bundle.putString("email", mEdtEmail.getText().toString());
                        bundle.putString("password", mEdtPassword.getText().toString());
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity_.class);
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                        finish();
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
}
