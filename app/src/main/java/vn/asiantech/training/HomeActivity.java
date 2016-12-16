package vn.asiantech.training;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.astuetz.PagerSlidingTabStrip;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.training.adapter.PageAdapter;
import vn.asiantech.training.controller.RealmController;
import vn.asiantech.training.fragment.AddFragmentDialog;
import vn.asiantech.training.model.People;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity implements AddFragmentDialog.DialogListener {

    private static final String TITLE = "Home";
    @ViewById(R.id.viewpager)
    ViewPager mViewPager;
    @ViewById(R.id.tabs)
    PagerSlidingTabStrip mPagerTabStrip;
    private ArrayList<People> mPeoples;

    @AfterViews
    void init() {
        getSupportActionBar().setTitle(TITLE);
        mPeoples = new ArrayList<>();
        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), mPeoples));
        mPagerTabStrip.setViewPager(mViewPager);
    }

    @Override
    public void onAddDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogview = dialog.getDialog();
        EditText edtName = (EditText) dialogview.findViewById(R.id.edtUsername);
        EditText edtPhoneNumber = (EditText) dialogview.findViewById(R.id.edtPhoneNumber);
        RealmController realmController = new RealmController(getApplicationContext());
        realmController.addPeople(new People(edtName.getText().toString(), edtPhoneNumber.getText().toString()));
        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), mPeoples));
        mPagerTabStrip.setViewPager(mViewPager);
    }

    @Override
    public void onAddDialogNegativeClick(DialogFragment dialog) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.iconLogout) {
            finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
