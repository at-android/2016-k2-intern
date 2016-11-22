package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private NavigationAdapter mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mListItem;
    private ArrayList<Human> mArrHuman = new ArrayList<Human>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mArrHuman = initArray();
        mTitle = mDrawerTitle = getTitle();
        mListItem = getResources().getStringArray(R.array.array_for_recyclerView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView)findViewById(R.id.recyclerViewDrawer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mDrawerList.setLayoutManager(layoutManager);
        mAdapter = new NavigationAdapter(mListItem,mArrHuman,getApplicationContext(),MainActivity.this);
        mDrawerList.setAdapter(mAdapter);
    }

    public ArrayList<Human> initArray(){
        ArrayList<Human> arr = new ArrayList<Human>();
        arr.add(new Human("Tran Van A","0123456789",false));
        arr.add(new Human("Tran Van B","0223456789",false));
        arr.add(new Human("Tran Van C","0323456789",false));
        arr.add(new Human("Tran Van D","0423456789",false));
        return arr;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
