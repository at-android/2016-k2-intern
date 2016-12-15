package vn.asiantech.training;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements AddFragmentDialog.DialogListener {


    private static final String TITLE = "Home";
    private BOModel mBoModel;
    private ArrayList<People> mPeoples;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(TITLE);
        getSupportActionBar().setIcon(R.drawable.ic_backspace_green_500_36dp);
        mBoModel = new BOModel(getApplicationContext());
        mPeoples = mBoModel.getPeoples();
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), mPeoples));
        mPagerTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mPagerTabStrip.setViewPager(mViewPager);
    }

    @Override
    public void onAddDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogview = dialog.getDialog();
        EditText edtName = (EditText) dialogview.findViewById(R.id.edtUsername);
        EditText edtPhoneNumber = (EditText) dialogview.findViewById(R.id.edtPhoneNumber);
        mBoModel.insert(edtName.getText().toString(), edtPhoneNumber.getText().toString());
        mPeoples = mBoModel.getPeoples();
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
