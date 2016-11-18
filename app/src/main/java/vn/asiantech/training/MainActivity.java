package vn.asiantech.training;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.ArrayList;

import vn.asiantech.training.application.App;
import vn.asiantech.training.fragment.ListFoodFragment;
import vn.asiantech.training.model.Food;

public class MainActivity extends AppCompatActivity {
    ArrayList<Food> mFoods;
    private FrameLayout mFrContain;
    private ListFoodFragment mListFoodFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrContain = (FrameLayout) findViewById(R.id.frContain);
        mListFoodFragment = new ListFoodFragment();
        switchFragment(mListFoodFragment,false,R.id.frContain,"");
    }
    public ArrayList<Food> getDataForList(){
        mFoods = new ArrayList<>();
        ((App) getApplication()).initListFood();
        mFoods = ((App) getApplication()).getListFood();
        return mFoods;
    }

    public void switchFragment(Fragment fragment, boolean addToBackStack, int id, String nameFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment, nameFragment);
        if (addToBackStack) {
            ft.addToBackStack(nameFragment);
        }
        ft.commit();
    }

}
