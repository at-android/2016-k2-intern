package vn.asiantech.training;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.adapter.ContactListAdapter;
import vn.asiantech.training.adapter.FavListAdapter;
import vn.asiantech.training.adapter.NavDrawerAdapter;
import vn.asiantech.training.adapter.NoteListAdapter;
import vn.asiantech.training.fragment.ContactListFragment;
import vn.asiantech.training.fragment.FavouriteListFragment;
import vn.asiantech.training.fragment.NoteListFragment;
import vn.asiantech.training.listener.RecyclerViewItemListener;
import vn.asiantech.training.model.Contact;
import vn.asiantech.training.model.NavDrawerItem;
import vn.asiantech.training.model.Note;

public class MainActivity extends AppCompatActivity implements ContactListAdapter.callBackContactLister, FavListAdapter.callBackFavouriteLister, NoteListAdapter.callBackNoteLister {
    private Toolbar mToolbar;
    private List<Object> mItems;
    private RecyclerView mRecyclerViewMenuDrawer;
    private NavDrawerAdapter mNavDrawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ArrayList<Contact> mContacts = new ArrayList<Contact>();
    private ContactListFragment mContactListFragment;
    private FavouriteListFragment mFavouriteListFragment;
    private NoteListFragment mNoteListFragment;
    private ContactListAdapter.callBackContactLister mCallBackContactLister;
    private ArrayList<Contact> mContactFavourites = new ArrayList<>();
    private ArrayList<Note> mNotes = new ArrayList<>();
    private MenuItem mItemShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerViewMenuDrawer = (RecyclerView) findViewById(R.id.recyclerViewMenuDrawer);
        mNavDrawerAdapter = new NavDrawerAdapter(this, getListItems());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewMenuDrawer.setLayoutManager(linearLayoutManager);
        mRecyclerViewMenuDrawer.setAdapter(mNavDrawerAdapter);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mContacts = initDataContact();
        displayView(1);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerViewMenuDrawer.addOnItemTouchListener(new RecyclerViewItemListener(getBaseContext(), mRecyclerViewMenuDrawer, new RecyclerViewItemListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                displayView(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private List<Object> getListItems() {
        mItems = new ArrayList<>();
        mItems.add("aaa");
        mItems.add(new NavDrawerItem(false, " Contact"));
        mItems.add(new NavDrawerItem(false, " Favourite"));
        mItems.add(new NavDrawerItem(false, " Note"));
        return mItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_contact, menu);
        mItemShare = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(mItemShare);
        mShareActionProvider.setShareIntent(getDefaultIntent());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                showDialogAddContact();
                break;
            case R.id.action_write:
                showDialogAddNote();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 1:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mContactListFragment = ContactListFragment.newInstance(mContacts);
                title = getString(R.string.nav_item_contact);
                switchFragment(title, mContactListFragment);
                break;
            case 2:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mFavouriteListFragment = FavouriteListFragment.newInstance(mContactFavourites);
                title = getString(R.string.nav_item_favourite);
                switchFragment(title, mFavouriteListFragment);
                break;
            case 3:
                mDrawerLayout.closeDrawer(GravityCompat.START);

                mNoteListFragment = NoteListFragment.newInstance(mNotes);
                title = getString(R.string.nav_item_note);
                switchFragment(title, mNoteListFragment);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frContainer, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

    public void showDialogAddContact() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_contact);
        final EditText edName = (EditText) dialog.findViewById(R.id.tvName);
        final EditText edPhone = (EditText) dialog.findViewById(R.id.tvPhone);
        TextView btnAdd = (TextView) dialog.findViewById(R.id.btnAdd);
        TextView btnCancel = (TextView) dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact mContact = new Contact(edName.getText().toString(), edPhone.getText().toString());
                mContacts.add(mContact);
                switchFragment(String.valueOf(R.string.nav_item_contact), mContactListFragment);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showDialogAddNote() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_note);
        final EditText edTitle = (EditText) dialog.findViewById(R.id.tvName);
        final EditText edNote = (EditText) dialog.findViewById(R.id.tvNote);
        TextView btnAdd = (TextView) dialog.findViewById(R.id.btnAdd);
        TextView btnCancel = (TextView) dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timestamp mTimeCreated = new Timestamp(System.currentTimeMillis());
                Note mNote = new Note(edTitle.getText().toString(), edNote.getText().toString(), mTimeCreated);
                mNotes.add(mNote);
                displayView(3);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showDialogEditNote(Note note) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_detail_note);
        TextView edTitle = (TextView) dialog.findViewById(R.id.tvName);
        TextView edNote = (TextView) dialog.findViewById(R.id.tvNote);
        TextView edTime = (TextView) dialog.findViewById(R.id.tvTime);
        TextView btnCancel = (TextView) dialog.findViewById(R.id.btnCancel);
        edTitle.setText(note.getmTitle());
        edNote.setText(note.getmContent());
        edTime.setText(note.getmTimeCreated().toString());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public ArrayList<Contact> initDataContact() {
        ArrayList contacts = new ArrayList<>();
        contacts.add(new Contact("Phuong", "01668583242"));
        contacts.add(new Contact("Phu", "01668583242"));
        contacts.add(new Contact("Huong", "01668583242"));
        contacts.add(new Contact("TaPu", "01660583242"));
        contacts.add(new Contact("Hai", "01688183242"));
        return contacts;
    }

    public void switchFragment(String title, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frContainer, fragment);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void listenerAddFavourite(Contact contact) {
        mContactFavourites.add(contact);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(mItemShare);
        mShareActionProvider.setShareIntent(getDefaultIntent());
    }

    @Override
    public void listenerRemoveFavourite(Contact contact, int position) {
        mContactFavourites.remove(position);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(mItemShare);
        mShareActionProvider.setShareIntent(getDefaultIntent());
        displayView(2);
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, mContactFavourites.toString());
        intent.setType("text/plain");
        return intent;
    }

    @Override
    public void listenerEditNote(Note note) {
        showDialogEditNote(note);
    }
}
