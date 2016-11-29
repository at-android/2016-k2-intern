package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener, LoginFragment.OnLoginCallBack {
    private Button mBtnRegister;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RegisterFragment registerFragment = new RegisterFragment();
                fragmentTransaction.add(R.id.fr_register_and_login, registerFragment, "Register").commit();
                mBtnRegister.setVisibility(View.GONE);
                mBtnLogin.setVisibility(View.GONE);
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction.add(R.id.fr_register_and_login, loginFragment, "Register").commit();
                mBtnRegister.setVisibility(View.GONE);
                mBtnLogin.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onFragmentInteraction() {
        mBtnLogin.setVisibility(View.VISIBLE);
        mBtnRegister.setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction().
                remove(getSupportFragmentManager().findFragmentById(R.id.fr_register_and_login)).commit();
    }

    @Override
    public void onCallFormLogin() {

    }
}
