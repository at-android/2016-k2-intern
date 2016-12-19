package vn.asiantech.training.activities;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import vn.asiantech.training.fragments.AddTaskFragment_;
import vn.asiantech.training.fragments.EditTaskFragment_;
import vn.asiantech.training.listeners.ItemClickListener;
import vn.asiantech.training.models.LoginResult;
import vn.asiantech.training.models.Task;
import vn.asiantech.training.networks.Api;
import vn.asiantech.training.networks.ApiClient;

/**
 * Created by phuong on 16/12/2016.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements ItemClickListener {
    @ViewById(R.id.recyclerViewTasks)
    RecyclerView mRecyclerViewTask;
    @ViewById(R.id.btnAdd)
    FloatingActionButton mBtnAdd;

    private List<Task> mTasks;
    private TaskAdapter mTaskAdapter;

    @Override
    void inits() {
        initData();
        //abc();
        mTaskAdapter = new TaskAdapter(mTasks, this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerViewTask.setLayoutManager(layoutManager);
        mRecyclerViewTask.setAdapter(mTaskAdapter);
    }

    public void initData() {
        mTasks = new ArrayList<>();
        Task task = new Task();
        task.setMTitle("avc");
        task.setMContent("avc");
        task.setMIsFavorite(true);
        mTasks.add(task);
    }

    public void abc(){
        SharedPreferences settings = getSharedPreferences(LoginActivity_.NAME_SHAREPREFERENCE, 0);
        String access_token = settings.getString(LoginActivity_.ACCESS_TOKEN, "");
        Api api = ApiClient.retrofit().create(Api.class);
        Call<List<Task>> result = api.listTasks(1,2,access_token);
        result.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                Log.d("tag",response.body().size()+"");
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.d("tag","no connect");

            }
        });
    }
    @Override
    public void itemClick() {
        FragmentManager fm = getSupportFragmentManager();
        (new DetailTaskDialog()).show(fm, DetailTaskDialog.class.getName());
    }

    @Click(R.id.btnAdd)
    void addAction() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, AddTaskFragment_.builder().build()).commit();
    }
}
