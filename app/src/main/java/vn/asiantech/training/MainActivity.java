package vn.asiantech.training;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationDrawer.OnFragmentInteractionListener
        , RecyclerViewPeopleAdapter.clickListener, AddDialogFragment.DialogListener {

    public static final String KEY_PEOPLE = "listpeople";
    private Toolbar mToolbar;
    private NavigationDrawer mDrawerFragment;
    private ArrayList<People> mPeoples;
    private ArrayList<People> mFavorites;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPeoples = new ArrayList<>();
        mPeoples.add(new People("Hoang Duy", "0934888706", R.drawable.ic_favorite_border_green_500_24dp));
        mPeoples.add(new People("Hoang Duy", "0934888706", R.drawable.ic_favorite_border_green_500_24dp));
        mPeoples.add(new People("Hoang Duy", "0934888706", R.drawable.ic_favorite_border_green_500_24dp));
        mPeoples.add(new People("Hoang Duy", "0934888706", R.drawable.ic_favorite_border_green_500_24dp));
        mPeoples.add(new People("Hoang Duy", "0934888706", R.drawable.ic_favorite_border_green_500_24dp));
        mPeoples.add(new People("Hoang Duy", "0934888706", R.drawable.ic_favorite_border_green_500_24dp));
        mPeoples.add(new People("Hoang Duy", "0934888706", R.drawable.ic_favorite_border_green_500_24dp));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mDrawerFragment = (NavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.setDrawerListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void showNoticeDialog() {
        DialogFragment dialog = new AddDialogFragment();
        dialog.show(getFragmentManager(), "AddDialogFragment");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            showNoticeDialog();

        } else if (id == R.id.action_share) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new ContactFragment();
                Bundle bundle = new Bundle();
                title = getString(R.string.contact);
                bundle.putParcelableArrayList(KEY_PEOPLE, mPeoples);
                initFragment(fragment, title, bundle);
                break;

            case 1:
                fragment = new ContactFragment();
                title = getString(R.string.favorite);
                mFavorites = new ArrayList<>();
                UpdateFavorite();
                Bundle bundle1 = new Bundle();
                bundle1.putParcelableArrayList(KEY_PEOPLE, mFavorites);
                initFragment(fragment, title, bundle1);
                break;

           /* case 2:
                fragment = new MessagesFragment();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
                */
        }
    }

    public void UpdateFavorite() {
        for (int i = 0; i < mPeoples.size(); i++) {
            if (mPeoples.get(i).getIconFavorite() == R.drawable.ic_favorite_green_500_24dp) {
                mFavorites.add(new People(mPeoples.get(i).getName(), mPeoples.get(i).getPhoneNumber(), 0));
            }
        }
    }

    public void initFragment(Fragment fragment, String title, Bundle bundle) {
        fragment.setArguments(bundle);
        getSupportActionBar().setTitle(title);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();
    }

    @Override
    public void onClick(int position, int flag) {
        if (flag % 2 == 0) {
            mPeoples.get(position).setIconFavorite(R.drawable.ic_favorite_border_green_500_24dp);
        } else {
            mPeoples.get(position).setIconFavorite(R.drawable.ic_favorite_green_500_24dp);
        }
        Fragment fragment = new ContactFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_PEOPLE, mPeoples);
        String title = getString(R.string.contact);
        initFragment(fragment, title, bundle);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogview = dialog.getDialog();
        EditText edtName = (EditText) dialogview.findViewById(R.id.edtUsername);
        EditText edtPhoneNumber = (EditText) dialogview.findViewById(R.id.edtPhoneNumber);
        mPeoples.add(new People(edtName.getText().toString(), edtPhoneNumber.getText().toString(), R.drawable.ic_favorite_border_green_500_24dp));
        Fragment fragment = new ContactFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_PEOPLE, mPeoples);
        String title = getString(R.string.contact);
        initFragment(fragment, title, bundle);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
