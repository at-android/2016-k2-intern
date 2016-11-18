package vn.asiantech.training;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FoodMenuFragment.OnFoodMenuFragmentListener, FoodArrayAdapter.OnSetPosition, EditInfoFragment.OnFragmentInteractionListener {
    public ArrayList<FoodObject> sFoodArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertData();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FoodMenuFragment foodMenuFragment = new FoodMenuFragment();
        fragmentTransaction.add(R.id.activity_main, foodMenuFragment, "FoodMenu").commit();
    }

    public void insertData() {
        sFoodArray.add(new FoodObject("Grilled fish", 1, 45000));
        sFoodArray.add(new FoodObject("Ginger tea", 2, 10000));
        sFoodArray.add(new FoodObject("Tobacco", 3, 20000));
        sFoodArray.add(new FoodObject("Chewing gum", 3, 5000));
        sFoodArray.add(new FoodObject("Pudding", 1, 25000));
    }

    @Override
    public void pullPosition(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EditInfoFragment editInfoFragment = new EditInfoFragment();
        fragmentTransaction.replace(R.id.activity_main, editInfoFragment, "edtFoodMenu").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
