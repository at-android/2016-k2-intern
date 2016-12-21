package vn.asiantech.training;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.adapter.TaskAdapter;
import vn.asiantech.training.api.Api;
import vn.asiantech.training.api.ApiClient;
import vn.asiantech.training.dialog.DialogDetail;
import vn.asiantech.training.dialog.DialogDetail_;
import vn.asiantech.training.model.GetTaskResult;
import vn.asiantech.training.model.Task;
import vn.asiantech.training.model.TaskResult;

public class MainActivity extends AppCompatActivity implements DialogDetail.DialogListener,
        EditFragment.OnFragmentInteractionListener, AddFragment.OnFragmentInteractionListener, TaskAdapter.onAdapterInteraction {


    int mPastVisiblesItems;
    int mVisibleItemCount;
    int mTotalItemCount;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;
    private List<Integer> mColors;
    private Random mRandom;
    private Toolbar mToolbar;
    private ImageView mImgAdd;
    private String mAccess_token;
    private SharedPreferences mSharepreference;
    private List<Task> mTasks;
    private boolean mLoading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTasks = new ArrayList<>();
        mImgAdd = (ImageView) findViewById(R.id.imgAdd);
        mSharepreference = getSharedPreferences(LoginActivity.MYPREFERENCE, MODE_PRIVATE);
        mAccess_token = mSharepreference.getString(LoginActivity.ACCESS_TOKEN, "");
        mImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment addFragment = new AddFragment_().builder().mToken(mAccess_token).build();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.activity_main, addFragment).commit();
            }
        });
        getList();
    }

    public void getList() {
        Api api = ApiClient.getClient().create(Api.class);
        Call<GetTaskResult> getTaskResultCall = api.listTasks(1, 20, mAccess_token);
        getTaskResultCall.enqueue(new Callback<GetTaskResult>() {
            @Override
            public void onResponse(Call<GetTaskResult> call, Response<GetTaskResult> response) {
                mTasks = response.body().getTasks();
                init();
                Log.i("response", response.body().getTasks().size() + "");
            }

            @Override
            public void onFailure(Call<GetTaskResult> call, Throwable t) {
                Log.i("response", t.getMessage());
            }
        });
    }

    public void init() {
        mColors = new ArrayList<>();
        mRandom = new Random();
        Log.i("mt", mTasks.size() + "");
        for (int i = 0; i < mTasks.size(); i++) {
            mColors.add(Color.rgb(mRandom.nextInt(200), mRandom.nextInt(200), mRandom.nextInt(200)));
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new TaskAdapter(mTasks, mColors);
        mRecyclerView.setAdapter(mAdapter);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DialogDetail dialogDetail = new DialogDetail_().builder()
                        .mTask(mTasks.get(position)).mPos(position)
                        .build();
                dialogDetail.show(getFragmentManager(), "");
                dialogDetail.setCancelable(true);
            }
        }));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onAddDialogPositiveClick(DialogFragment dialog, Task task, int pos) {
        Dialog dialogView = dialog.getDialog();
        EditFragment editFragment = new EditFragment_().builder().mTask(task).mPos(pos).build();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.activity_main, editFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
        }
    }

    @Override
    public void onAddDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onEditFragmentInteraction(final Task task, final int pos) {
        Log.i("task", task.getTitle() + " " + pos);
        Api api = ApiClient.getClient().create(Api.class);
        Call<TaskResult> taskResultCall = api.editTask(task.getId(), task.getTitle(), task.getContent(), task.getFavorite(), mAccess_token);
        taskResultCall.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                if (response.body().getError().equals("false")) {
                    Toast.makeText(getApplicationContext(), "Edited!", Toast.LENGTH_LONG).show();
                    mTasks.set(pos, task);
                    mAdapter = new TaskAdapter(mTasks, mColors);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAddFragmentInteraction(Task task) {
        mTasks.add(task);
        startActivity(getIntent());
    }

    @Override
    public void onFavoriteListener(Task task) {
        Api api = ApiClient.getClient().create(Api.class);
        Call<TaskResult> taskResultCall = api.editTask(task.getId(), task.getTitle(), task.getContent(), task.getFavorite(), mAccess_token);
        taskResultCall.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                if (response.body().getError().equals("false")) {
                    Toast.makeText(getApplicationContext(), "Edited!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {

            }
        });
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
