package vn.asiantech.training;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecycleViewAdapter.OnSetPosition
        , ListContactsFragment.OnFragmentInteractionListener,
        RecycleViewAdapterLike.OnSetPosition,
        ListLikeContact.OnFragmentInteractionListener,
        NoteRecyckeAdapter.OnSetPosition, ListNotesFragment.OnFragmentInteractionListener {
    public ArrayList<ContactsObj> sContactsArray = new ArrayList<ContactsObj>();
    public ArrayList<NotesObj> sNotesArray = new ArrayList<>();
    private EditText mEtName;
    private EditText mEtPhone;
    private RadioGroup mRdLike;
    private Dialog mDialog;
    private Menu menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        insertData();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListContactsFragment list = new ListContactsFragment();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fr_main, list, "ListContact");
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menuItem = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent myIntent = new Intent(Intent.ACTION_VIEW);
            startActivity(Intent.createChooser(myIntent, "Choose via ...."));
            return true;
        }
        if (id == R.id.action_add) {
            mDialog = new Dialog(MainActivity.this);
            mDialog.setContentView(R.layout.add_phone_number_dialog);
            mDialog.setTitle("Add new number phone");
            Button btnAdd = (Button) mDialog.findViewById(R.id.btnAddPhone);
            Button btnCancle = (Button) mDialog.findViewById(R.id.btnCanclePhone);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEtName = (EditText) mDialog.findViewById(R.id.etAddName);
                    mEtPhone = (EditText) mDialog.findViewById(R.id.etAddPhone);
                    mRdLike = (RadioGroup) mDialog.findViewById(R.id.phone_like_group);
                    int selectedId = mRdLike.getCheckedRadioButtonId();
                    RadioButton radioLikeButton = (RadioButton) mDialog.findViewById(selectedId);
                    boolean like;
                    if (radioLikeButton.getText().toString().equals("Like")) {
                        like = true;
                    } else {
                        like = false;
                    }
                    sContactsArray.add(new ContactsObj(like, mEtName.getText().toString(), mEtPhone.getText().toString()));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ListContactsFragment list = new ListContactsFragment();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.fr_main, list, "ListContact");
                    fragmentTransaction.commit();
                    mDialog.dismiss();
                }
            });
            btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                }
            });
            mDialog.show();
            return true;
        }
        if (id == R.id.action_add_notes) {
            mDialog = new Dialog(MainActivity.this);
            mDialog.setContentView(R.layout.add_notes_dialog);
            mDialog.setTitle("Add new note");
            final EditText edTitle = (EditText) mDialog.findViewById(R.id.ed_add_note_title);
            final EditText edContent = (EditText) mDialog.findViewById(R.id.ed_add_note_content);
            final TimePicker tpTime = (TimePicker) mDialog.findViewById(R.id.tpTimeZone);
            Button btnAddNote = (Button) mDialog.findViewById(R.id.btnAddNote);
            Button btnCancleNote = (Button) mDialog.findViewById(R.id.btnCancleNote);
            btnAddNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sNotesArray.add(new NotesObj(edTitle.getText().toString(), edContent.getText().toString(), tpTime.getCurrentHour() + ":" + tpTime.getCurrentMinute()));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ListNotesFragment list = new ListNotesFragment();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.fr_main, list, "ListNote");
                    fragmentTransaction.commit();
                    mDialog.dismiss();

                }
            });
            btnCancleNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                }
            });
            mDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item1) {
        // Handle navigation view item clicks here.
        int id = item1.getItemId();

        if (id == R.id.nav_contacts) {
            MenuItem action_add_note = menuItem.findItem(R.id.action_add_notes);
            action_add_note.setVisible(false);
            MenuItem action_share = menuItem.findItem(R.id.action_share);
            action_share.setVisible(false);
            MenuItem action_add = menuItem.findItem(R.id.action_add);
            action_add.setVisible(true);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListContactsFragment list = new ListContactsFragment();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.fr_main, list, "ListContact");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_favorite_contacts) {
            MenuItem action_add_note = menuItem.findItem(R.id.action_add_notes);
            action_add_note.setVisible(false);
            MenuItem action_share = menuItem.findItem(R.id.action_share);
            action_share.setVisible(true);
            MenuItem action_add = menuItem.findItem(R.id.action_add);
            action_add.setVisible(false);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListLikeContact list = new ListLikeContact();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.fr_main, list, "ListLike");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_notes) {
            MenuItem action_add_note = menuItem.findItem(R.id.action_add_notes);
            action_add_note.setVisible(true);
            MenuItem action_add = menuItem.findItem(R.id.action_add);
            action_add.setVisible(false);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListNotesFragment list = new ListNotesFragment();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.fr_main, list, "ListNote");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_share) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void insertData() {
        sContactsArray.add(new ContactsObj(true, "Phan Hung", "01634238797"));
        sContactsArray.add(new ContactsObj(true, "Tran Cuong", "09076565432"));
        sContactsArray.add(new ContactsObj(false, "Phan Ngoc", "01987385038"));
        sContactsArray.add(new ContactsObj(false, "Huu Tanh", "01634238797"));
        sContactsArray.add(new ContactsObj(true, "Trinh Thi", "01634238797"));
        sNotesArray.add(new NotesObj("Di hoc", "Ngay mai co tiet hoc quan trong", "17:15"));
        sNotesArray.add(new NotesObj("Di lam", "Mai di lam ca sang", "7:30"));
        sNotesArray.add(new NotesObj("Ve que", "Mai ve que", "23:00"));
    }

    @Override
    public void pullPosition(int position) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void pullPositionOnListNote(int position) {
        showDataNote(position);
    }

    public void showDataNote(int position) {
        mDialog = new Dialog(MainActivity.this);
        mDialog.setContentView(R.layout.show_infor_note_dialog);
        TextView tvContent = (TextView) mDialog.findViewById(R.id.tvContent_dialog_note);
        TextView tvTime = (TextView) mDialog.findViewById(R.id.tvTime_dialog_note);
        mDialog.setTitle(sNotesArray.get(position).getTitleNote());
        tvContent.setText(sNotesArray.get(position).getContentNote());
        tvTime.setText(sNotesArray.get(position).getTimesNote());
        mDialog.show();
    }
}
