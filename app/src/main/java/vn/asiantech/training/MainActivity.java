package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import vn.asiantech.training.activity.FragmentDrawer;
import vn.asiantech.training.activity.NoteFragment;
import vn.asiantech.training.model.Note;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, DialogAddFragment.SendData {
    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private ArrayList<Human> mArrHuman = new ArrayList<Human>();
    private ArrayList<Note> mArrNote = new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initArrayHuman();
        initArrayNote();
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        // display the first navigation drawer view on app launch
        displayView(0);
    }

    public void initArrayHuman() {
        mArrHuman.add(new Human("Ted", "0123456777", false));
        mArrHuman.add(new Human("Barney", "0123456777", false));
        mArrHuman.add(new Human("Lily", "0123456777", false));
        mArrHuman.add(new Human("Marshall", "0123456777", false));
    }

    public void initArrayNote() {
        Note note = new Note();
        DateFormat dataFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        mArrNote.add(new Note("Attention", "blablabla", dataFormat.format(cal.getTime()) + ""));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            FragmentManager fm = getSupportFragmentManager();
            DialogAddFragment frag = new DialogAddFragment();
            frag.show(fm, "Open");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        String title = getString(R.string.app_name);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
            case 0:
                title = getString(R.string.title_Contact);


                ContactFragment frag0 = ContactFragment.newInstance(mArrHuman);
                ft.replace(R.id.container_body, frag0).commit();
                getSupportActionBar().setTitle(title);
                break;
            case 1:
                title = getString(R.string.title_Favorite);
                FavoriteFragment frag1 = FavoriteFragment.newInstance(mArrHuman);
                ft.replace(R.id.container_body, frag1).commit();
                getSupportActionBar().setTitle(title);
                break;
            case 2:
                title = getString(R.string.title_Note);
                NoteFragment frag2 = NoteFragment.newInstance(mArrNote);
                ft.replace(R.id.container_body,frag2).commit();
                getSupportActionBar().setTitle(title);
                break;
            default:
                break;
        }
    }

    @Override
    public void onArticleSelected(Human man) {
        mArrHuman.add(man);
        Log.i("length", mArrHuman.size() + "");
        FragmentManager fm = getSupportFragmentManager();
        ContactFragment frag = ContactFragment.newInstance(mArrHuman);
        fm.beginTransaction().replace(R.id.container_body, frag).commit();
    }
}
