package vn.asiantech.training;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DemoRecyclerViewActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener, HomeAdapter.OnFragmentInteractionListener,
        AddFoodFragment.OnFragmentInteractionListener,
        EditFragment.OnFragmentInteractionListener {

    public static final String KEY_BUNDLE = "bundle";
    public static final String KEY_POS = "pos";
    private FragmentManager mFragmentManager;
    private List<Foody> mFoodies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_recycler_view);
        mFoodies = new ArrayList<>();
        mFoodies.add(new Foody(R.drawable.type_1, "Roast potatoes", "Food", "40.000đ"));
        mFoodies.add(new Foody(R.drawable.type_2, "Coffee", "Drink", "15.000đ"));
        mFoodies.add(new Foody(R.drawable.type_3, "Tobacco", "Other", "15.000đ"));
        mFoodies.add(new Foody(R.drawable.type_1, "Cheesecake", "Food", "70.000đ"));
        mFoodies.add(new Foody(R.drawable.type_2, "Ginger tea", "Drink", "10.000đ"));

        HomeFragment homeFragment = HomeFragment.newInstance("xx", "xx");
        homeFragment.initObj(mFoodies);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.Rlcontainer, homeFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Foody foody, int position) {
        setImage(foody);
        mFoodies.set(position, foody);
        HomeFragment homeFragment = HomeFragment.newInstance(HomeFragment.ARG_PARAM1, HomeFragment.ARG_PARAM2);
        homeFragment.initObj(mFoodies);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.Rlcontainer, homeFragment).commit();
    }

    @Override
    public void onFragmentInteraction(int position) {
        EditFragment editFragment = EditFragment.newInstance(HomeFragment.ARG_PARAM1, HomeFragment.ARG_PARAM2);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundle", mFoodies.get(position));
        bundle.putInt(KEY_POS, position);
        editFragment.setArguments(bundle);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.Rlcontainer, editFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void setImage(Foody foody) {
        if (foody.getCategory().equals("Food")) {
            foody.setImage(R.drawable.type_1);
        } else if (foody.getCategory().equals("Drink")) {
            foody.setImage(R.drawable.type_2);
        } else {
            foody.setImage(R.drawable.type_3);
        }
    }

    @Override
    public void onFragmentInteraction(Foody foody) {
        setImage(foody);
        mFoodies.add(foody);
        HomeFragment homeFragment = HomeFragment.newInstance(HomeFragment.ARG_PARAM1, HomeFragment.ARG_PARAM2);
        homeFragment.initObj(mFoodies);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.Rlcontainer, homeFragment).commit();
    }
}
