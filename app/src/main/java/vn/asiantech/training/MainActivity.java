package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import vn.asiantech.training.activity.FragmentDrawer;
import vn.asiantech.training.model.Task;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener,ContactAdapter.DataFromAdapter,EditDialogFragment.DataFromEdDialogFrag,EditFragment.DataFromEdFrag,AddFragment.DataFromEdFrag,ContactFragment.SendFromContact,View.OnClickListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private static String TAG2 = "EditFragment";
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private ArrayList<Task> mArrTask = new ArrayList<Task>();
    private int mPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initArrayHuman();
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        // display the first navigation drawer view on app launch
        displayView(0);


    }

    public void initArrayHuman() {
        mArrTask.add(new Task("Task1", "Go Home", 0));
        mArrTask.add(new Task("Task2", "Go School", 0));
        mArrTask.add(new Task("Task3", "Go Church", 0));
        mArrTask.add(new Task("Task4", "Go Go", 0));
        mArrTask.add(new Task("Task4", "Go Go", 0));
        mArrTask.add(new Task("Task4", "Go Go", 0));
        mArrTask.add(new Task("Task4", "Go Go", 0));
        mArrTask.add(new Task("Task4", "Go Go", 0));
        mArrTask.add(new Task("Task4", "Go Go", 0));
        mArrTask.add(new Task("Task4", "Go Go", 0));

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
                ContactFragment frag0 = ContactFragment.newInstance(mArrTask);
                ft.replace(R.id.container_body, frag0).commit();
                getSupportActionBar().setTitle(title);
                break;
            default:
                break;
        }
    }

    @Override
    public void SendData(int position) {
        mPosition = position;
        Bundle bundle = new Bundle();
        bundle.putParcelable("task",mArrTask.get(position));
        bundle.putInt("position",mPosition);
        EditDialogFragment frag = new EditDialogFragment();
        frag.setArguments(bundle);
        frag.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void onArticleSelected(Task task, int position) {
        mPosition = position;
        EditFragment frag =  EditFragment.newInstance(task,position);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_body,frag).addToBackStack(TAG2).commit();
    }

    @Override
    public void SendData2(Task task, int position) {
        mArrTask.set(position,task);
    }

    @Override
    public void SendDataFromAddFrag(Task task) {
        mArrTask.add(task);
    }

    @Override
    public void SendFromContactFrag() {
        AddFragment frag = new AddFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_body,frag).addToBackStack(TAG2).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
