package vn.asiantech.training;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationDrawer.OnFragmentInteractionListener
        , RecyclerViewPeopleAdapter.clickListener, AddDialogFragment.DialogListener, NoteDialogFragment.NoteDialogListener
        , NoticeFragment.OnFragmentInteractionListener, RecyclerViewNoteAdapter.OnCallBackListener {

    public static final String KEY_PEOPLE = "listpeople";
    public static final String KEY_NOTICE = "Notice";
    public static final String KEY_ADD = "Add";
    private Toolbar mToolbar;
    private NavigationDrawer mDrawerFragment;
    private ArrayList<People> mPeoples;
    private ArrayList<People> mFavorites;
    private ArrayList<Note> mNotes;
    private FragmentManager mFragmentManager;
    private MenuItem mItemShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPeoples = new ArrayList<>();
        mNotes = new ArrayList<>();
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

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, mPeoples.toString());
        intent.setType("text/plain");
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        mItemShare = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(mItemShare);
        mShareActionProvider.setShareIntent(getDefaultIntent());
        return true;
    }

    public void showAddDialog() {
        DialogFragment dialog = new AddDialogFragment();
        dialog.show(getFragmentManager(), KEY_ADD);
    }

    public void showNoticeDialog() {
        DialogFragment dialogFragment = new NoteDialogFragment();
        dialogFragment.show(getFragmentManager(), KEY_NOTICE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            showAddDialog();

        } else if (id == R.id.action_note) {
            showNoticeDialog();
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

            case 2:
                fragment = NoticeFragment.newInstance(mNotes);
                title = getString(R.string.note);
                getSupportActionBar().setTitle(title);
                mFragmentManager = getSupportFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();
                break;
            default:
                break;

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
        if (flag == 1) {
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
    public void onAddDialogPositiveClick(DialogFragment dialog) {
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
    public void onAddDialogNegativeClick(DialogFragment dialog) {

       /* Fragment fragment = new ContactFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_PEOPLE, mPeoples);
        String title = getString(R.string.contact);
        initFragment(fragment, title, bundle);*/
    }

    @Override
    public void onNoteDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogview = dialog.getDialog();
        EditText edtTitle = (EditText) dialogview.findViewById(R.id.edtTitle);
        EditText edtContent = (EditText) dialogview.findViewById(R.id.edtContent);
        if (edtTitle.getText().toString().equals("")) {
            Toast.makeText(this, "Title is empty!Please provide a title", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show();
            mNotes.add(new Note(edtTitle.getText().toString(), edtContent.getText().toString(), new Date().toString()));
        }

    }

    @Override
    public void onNoteDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v, int positon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mNotes.get(positon).getTitle().toUpperCase() + "\n" + mNotes.get(positon).getContent() + "\n" + mNotes.get(positon).getDateTime())
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}
