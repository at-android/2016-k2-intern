package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditInfoFragment.OnHeadlineSelectedListener{
    ArrayList<FoodList> mArr = new ArrayList<FoodList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArray();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment frag = new HomeFragment();
        Log.i("XyZ",mArr.size()+"");
        frag.updateList(mArr);
        fragmentTransaction.replace(R.id.activity_main,frag).commit();

    }
    public void initArray(){
        mArr.add(new FoodList(R.drawable.type_1,"Roast potatoes","Food","40000"));
        mArr.add(new FoodList(R.drawable.type_1,"Young rice cake","Food","50000"));
        mArr.add(new FoodList(R.drawable.type_2,"Coffee","Beverage","15000"));
        mArr.add(new FoodList(R.drawable.type_1,"Grilled fish","Food","45000"));
        mArr.add(new FoodList(R.drawable.type_2,"Ginger tea","Beverage","20000"));
        mArr.add(new FoodList(R.drawable.type_3,"Tobacco","Other","20000"));
        mArr.add(new FoodList(R.drawable.type_2,"Soda","Beverage","25000"));
    }

    @Override
    public void onArticleSelected(FoodList food,int position) {
        mArr.set(position,food);
        FragmentManager fm = getSupportFragmentManager();
        HomeFragment frag = new HomeFragment();
        frag.updateList(mArr);
        fm.beginTransaction().addToBackStack(null).replace(R.id.activity_main,frag).commit();
    }
}
