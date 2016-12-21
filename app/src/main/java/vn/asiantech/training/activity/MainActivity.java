package vn.asiantech.training.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.API.Api;
import vn.asiantech.training.API.ApiClient;
import vn.asiantech.training.R;
import vn.asiantech.training.adapter.TaskAdapter;
import vn.asiantech.training.fragment.AddFragment;
import vn.asiantech.training.fragment.EditDialogFragment;
import vn.asiantech.training.fragment.EditFragment;
import vn.asiantech.training.fragment.TaskFragment;
import vn.asiantech.training.model.Task;
import vn.asiantech.training.model.TaskResult;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, TaskAdapter.DataFromAdapter, EditDialogFragment.DataFromEdDialogFrag, EditFragment.DataFromEdFrag, AddFragment.DataFromEdFrag, TaskFragment.SendFromContact, View.OnClickListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private static String TAG2 = "EditFragment";
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private List<Task> mArrTask = new ArrayList<Task>();
    private int mPosition;
    private String mAccessToken;
    public static final String ACCESS_TOKEN = "access_token";
    public static final String SHARED_PREFS = "my_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getAccessToken();
        GetDataFromService();
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        // display the first navigation drawer view on app launch
        //   displayView(0);

    }

    public void getAccessToken() {
        SharedPreferences settings = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mAccessToken = settings.getString(ACCESS_TOKEN, "");
        Log.i("Main access token:", mAccessToken);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
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
                TaskFragment frag0 = TaskFragment.newInstance(mArrTask);
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
        bundle.putParcelable("task", mArrTask.get(position));
        bundle.putInt("position", mPosition);
        EditDialogFragment frag = new EditDialogFragment();
        frag.setArguments(bundle);
        frag.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onArticleSelected(Task task, int position) {
        mPosition = position;
        EditFragment frag = EditFragment.newInstance(task, position, mAccessToken);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_body, frag).addToBackStack(TAG2).commit();
    }

    @Override
    public void SendData2(Task task, int position) {
        mArrTask.set(position, task);
    }

    //Callback from AddFragment
    @Override
    public void SendDataFromAddFrag() {
        GetDataFromService();
    }

    @Override
    public void SendFromContactFrag() {
        Bundle bundle = new Bundle();
        bundle.putString(ACCESS_TOKEN, mAccessToken);
        AddFragment frag = new AddFragment();
        frag.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_body, frag).addToBackStack(TAG2).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    public void GetDataFromService() {
        Log.d("tag", "connect");
        Api api = ApiClient.retrofit().create(Api.class);
        Call<TaskResult> result = api.listTasks(1, 10, mAccessToken);
        result.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                Log.i("size", response.body().getListTasks().size() + "");
                mArrTask = response.body().getListTasks();

            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {
            }
        });
    }
}
