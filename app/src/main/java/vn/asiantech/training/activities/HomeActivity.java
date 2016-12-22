package vn.asiantech.training.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.R;
import vn.asiantech.training.adapters.TaskAdapter;
import vn.asiantech.training.dialogUtils.DetailTaskDialog;
import vn.asiantech.training.dialogUtils.DetailTaskDialog_;
import vn.asiantech.training.fragments.AddTaskFragment_;
import vn.asiantech.training.listeners.ItemClickListener;
import vn.asiantech.training.models.Task;
import vn.asiantech.training.models.TaskResult;
import vn.asiantech.training.networks.Api;
import vn.asiantech.training.networks.ApiClient;

/**
 * Created by phuong on 16/12/2016.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements ItemClickListener {
    public static final String EDIT_BUNDLE = "edit_bundle";
    @ViewById(R.id.recyclerViewTasks)
    RecyclerView mRecyclerViewTask;
    @ViewById(R.id.btnAdd)
    FloatingActionButton mBtnAdd;
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    private List<Task> tasks;
    private List<Task> mTasks;
    private TaskAdapter mTaskAdapter;
    private String access_token;

    @Override
    void inits() {
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerViewTask.setLayoutManager(layoutManager);
        setSupportActionBar(mToolbar);
        mTasks = new ArrayList<>();
        getListTasks(0);

        SharedPreferences settings = getSharedPreferences(LoginActivity_.NAME_SHAREPREFERENCE, 0);
        access_token = settings.getString(LoginActivity_.ACCESS_TOKEN, "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionLogout:
                Intent intent = new Intent(HomeActivity.this, MenuActivity_.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void getListTasks(final int index) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                Api api = ApiClient.retrofit().create(Api.class);
                Call<TaskResult> result = api.listTasks(index, index + 10, access_token);
                tasks = new ArrayList<Task>();
                result.enqueue(new Callback<TaskResult>() {
                    @Override
                    public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                        if (response.body().getListTasks().size() > 0) {
                            tasks = response.body().getListTasks();
                            mTasks.addAll(tasks);
                            mTaskAdapter = new TaskAdapter(mTasks, HomeActivity.this, HomeActivity.this,mRecyclerViewTask);
                            mRecyclerViewTask.setAdapter(mTaskAdapter);
                            mTaskAdapter.setLoadMoreListener(new TaskAdapter.OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    mTaskAdapter.setVisiBleProgressBar(true);
                                    mTaskAdapter.notifyItemInserted(mTasks.size() - 1);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                           // mTasks.remove(mTasks.size() - 1);
                                            //mTaskAdapter.notifyItemRemoved(mTasks.size());
                                            //mTaskAdapter.notifyDataSetChanged();
                                            //Load data
                                            mTaskAdapter.setVisiBleProgressBar(false);
                                            int index = mTasks.size();
                                            getListTasksLoadMore(index);
                                            mTaskAdapter.notifyDataSetChanged();
                                            mTaskAdapter.setLoaded();
                                        }
                                    }, 3000);
                                }
                            });
                        } else {
                            Toast.makeText(getApplication(), "No Data Availble", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TaskResult> call, Throwable t) {
                        Log.d("tag", "no connect");

                    }
                });
            }
        });
    }

    @Override
    public void itemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EDIT_BUNDLE, mTasks.get(position));
        DetailTaskDialog detailTaskDialog = DetailTaskDialog_.builder().build();
        detailTaskDialog.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        detailTaskDialog.show(fm, DetailTaskDialog.class.getName());
    }

    @Override
    public void favClick(int position) {
        SharedPreferences settings = getSharedPreferences(LoginActivity_.NAME_SHAREPREFERENCE, 0);
        String access_token = settings.getString(LoginActivity_.ACCESS_TOKEN, "");
        int fav = 1;
        if (mTasks.get(position).getFavorite() == 1) {
            fav = 0;
        }
        Api api = ApiClient.retrofit().create(Api.class);
        Call<TaskResult> result = api.editTask(mTasks.get(position).getId(), mTasks.get(position).getMTitle(), mTasks.get(position).getMContent(), fav, access_token);
        result.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                Log.d("tag", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {
                Log.d("tag", "fail");
            }
        });
    }

    @Click(R.id.btnAdd)
    void addAction() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, AddTaskFragment_.builder().build()).commit();
    }

    public void getListTasksLoadMore(int index){
        Api api = ApiClient.retrofit().create(Api.class);
        Call<TaskResult> result = api.listTasks(index, index + 10, access_token);
        tasks = new ArrayList<Task>();
        result.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                tasks = response.body().getListTasks();
                mTasks.addAll(tasks);
                mTaskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {

            }
        });
    }
}
