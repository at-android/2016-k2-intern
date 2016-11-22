package vn.asiantech.training;

import android.app.Dialog;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecycleViewAdapter.OnSetPosition, ListContactsFragment.OnFragmentInteractionListener {
    public ArrayList<ContactsObj> sContactsArray = new ArrayList<>();
    EditText name;
    EditText phone;
    private Dialog dialog;

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
        FragmentManager frm = getSupportFragmentManager();
        FragmentTransaction frtran = frm.beginTransaction();
        ListContactsFragment list = new ListContactsFragment();
        frtran.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        frtran.replace(R.id.fr_main, list, "ListContact");
        frtran.commitNowAllowingStateLoss();
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
            return true;
        }
        if (id == R.id.action_add) {
            dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.add_phone_number_dialog);
            dialog.setTitle("Add new number phone");
            Button btnAdd = (Button) dialog.findViewById(R.id.btnAddPhone);
            Button btnCancle = (Button) dialog.findViewById(R.id.btnCanclePhone);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name = (EditText) dialog.findViewById(R.id.etAddName);
                    phone = (EditText) dialog.findViewById(R.id.etAddPhone);
                    sContactsArray.add(new ContactsObj(true, name.getText().toString(), phone.getText().toString()));
                    FragmentManager frm = getSupportFragmentManager();
                    FragmentTransaction frtran = frm.beginTransaction();
                    ListContactsFragment list = new ListContactsFragment();
                    frtran.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    frtran.replace(R.id.fr_main, list, "ListContact");
                    frtran.commit();
                    dialog.dismiss();
                }
            });
            btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_contacts) {
        } else if (id == R.id.nav_favorite_contacts) {

        } else if (id == R.id.nav_notes) {

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
    }

    @Override
    public void pullPosition(int position) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
