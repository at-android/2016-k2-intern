package vn.asiantech.training.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.asiantech.training.MainActivity;
import vn.asiantech.training.R;
import vn.asiantech.training.adapter.RecyclerViewAdapter;
import vn.asiantech.training.fragment.AddTasksFragment;
import vn.asiantech.training.interfaces.OnLoadMoreListener;
import vn.asiantech.training.interfaces.RequesService;
import vn.asiantech.training.objects.Result;
import vn.asiantech.training.objects.Task;

import static vn.asiantech.training.MainActivity.API_URL;
import static vn.asiantech.training.MainActivity.KEY_SHARE;

public class HomeActivity extends AppCompatActivity implements RecyclerViewAdapter.OnShowDialogEdit, AddTasksFragment.OnAddNewTaskListener {
    private Button mBtnDialogAdd;
    private EditText mEdtDialogContent;
    private EditText mEdtDialogTitle;
    private RadioGroup mRadioGroupfavorite;
    private FloatingActionButton mBtnShowDialogAddTask;
    private List<Task> arrTasks = new ArrayList<>();
    private RecyclerView mRecylerView;
    private RecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SharedPreferences mSharePre;
    private String mToken;
    private OkHttpClient.Builder mHttpClient;
    private OkHttpClient mClient;
    private Retrofit mRetrofit;
    private RequesService mRequesService;
    private String mPosUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        onGetTask(0, 10);
        mRecylerView = (RecyclerView) findViewById(R.id.recyclerViewListTask);
        mBtnShowDialogAddTask = (FloatingActionButton) findViewById(R.id.floatingActionButtonShowDialogAddTask);
        mBtnShowDialogAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AddTasksFragment addTasksFragment = new AddTasksFragment();
                fragmentTransaction.add(R.id.activity_home2, addTasksFragment, "add");
                fragmentTransaction.commit();
            }
        });
    }

    public void init() {
        mSharePre = getSharedPreferences(KEY_SHARE, MODE_PRIVATE);
        mToken = mSharePre.getString("token", "");
        mHttpClient = new OkHttpClient.Builder();
        mHttpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", mToken)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        mClient = mHttpClient.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).client(mClient)
                .build();
        mRequesService = mRetrofit.create(RequesService.class);
    }

    public void onAddNewTask(String title, String content, int favorite) {
        Call<Result> user = mRequesService.addTask(title, content, favorite);
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    if ("true".equals(response.body().error.toString())) {
                        Toast.makeText(HomeActivity.this, "Email or Password is missing!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HomeActivity.this, response.body().taskID + "", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onUpdateTask(String id, String title, String content, int favorite) {
        Call<Result> user = mRequesService.editTask(id, title, content, favorite);
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    if ("true".equals(response.body().error.toString())) {
                        Toast.makeText(HomeActivity.this, "Email or Password is missing!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HomeActivity.this, response.body().message + "" + call.request(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onShowButtonAddTask(int id) {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.dialog_new_task);
        dialog.setTitle("New Task");
        mPosUpdate = arrTasks.get(id).getId().toString();
        mBtnDialogAdd = (Button) dialog.findViewById(R.id.btnDialogAddTask);
        mEdtDialogContent = (EditText) dialog.findViewById(R.id.edtDialogContent);
        mEdtDialogTitle = (EditText) dialog.findViewById(R.id.edtDialogTitle);
        mRadioGroupfavorite = (RadioGroup) dialog.findViewById(R.id.radioGroupFavorite);
        mEdtDialogTitle.setText(arrTasks.get(id).getTitle());
        mEdtDialogContent.setText(arrTasks.get(id).getContent());
        mBtnDialogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = 0;
                int isChecked = mRadioGroupfavorite.getCheckedRadioButtonId();
                switch (isChecked) {
                    case R.id.radioFavorite:
                        a = 1;
                        break;
                    case R.id.radioUnfavorite:
                        a = 0;
                        break;
                }
                // onAddNewTask(mEdtDialogTitle.getText().toString(), mEdtDialogContent.getText().toString(), a);
                onUpdateTask(mPosUpdate, mEdtDialogTitle.getText().toString(), mEdtDialogContent.getText().toString(), a);
                onGetTask(0, 10);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void onGetTask(int offset, int limmit) {
        Call<Result> user = mRequesService.getTask(offset, limmit);
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    arrTasks.clear();
                    for (int i = 0, lenght = response.body().listTasks.size(); i < lenght; i++) {
                        arrTasks.add(new Task(response.body().listTasks.get(i).getId(), response.body().listTasks.get(i).getTitle(), response.body().listTasks.get(i).getContent(), response.body().listTasks.get(i).getFavorite()));
                    }
                    mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecylerView.setHasFixedSize(true);
                    mRecylerView.setLayoutManager(mLinearLayoutManager);
                    mAdapter = new RecyclerViewAdapter(arrTasks, HomeActivity.this, mRecylerView);
                    mRecylerView.setAdapter(mAdapter);
                    mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void onLoadMore() {
                            arrTasks.add(null);
                            mAdapter.notifyItemInserted(arrTasks.size() - 1);
                            //Load more data for reyclerview
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Remove loading item
                                    arrTasks.remove(arrTasks.size() - 1);
                                    mAdapter.notifyItemRemoved(arrTasks.size());

                                    //Load data
                                    int index = arrTasks.size();
                                    int end = index + 10;
                                    onLoadTask(index, end);
                                    mAdapter.notifyDataSetChanged();
                                    mAdapter.setLoaded();
                                }
                            }, 5000);
                        }
                    });
                } else {
                    Toast.makeText(HomeActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Network error " + call.request() + mToken, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onLoadTask(int start, int stop) {
        Call<Result> user = mRequesService.getTask(start, stop);
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    for (int i = 0, lenght = response.body().listTasks.size(); i < lenght; i++) {
                        arrTasks.add(new Task(response.body().listTasks.get(i).getId(), response.body().listTasks.get(i).getTitle(), response.body().listTasks.get(i).getContent(), response.body().listTasks.get(i).getFavorite()));
                    }
                    mAdapter.notifyDataSetChanged();
                    Log.d("load", "add tasks");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    public void OnFavorite(int pos) {
        String id = arrTasks.get(pos).getId();
        int favorite = arrTasks.get(pos).getFavorite();
        if (favorite == 1) {
            favorite = 0;
        } else {
            favorite = 1;
        }
        Call<Result> user = mRequesService.editTask(id, arrTasks.get(pos).getTitle(), arrTasks.get(pos).getContent(), favorite);
        arrTasks.set(pos, new Task(arrTasks.get(pos).getId(), arrTasks.get(pos).getTitle(), arrTasks.get(pos).getContent(), favorite));
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //Toast.makeText(HomeActivity.this, response.body().error +"", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    if ("true".equals(response.body().error)) {
                        Toast.makeText(HomeActivity.this, "Email or Password is missing!!", Toast.LENGTH_SHORT).show();
                    } else {
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(HomeActivity.this, response.body().message + "" + call.request(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onShowDialog(int pos) {
        onShowButtonAddTask(pos);
    }

    @Override
    public void onChangeFavorite(int pos) {
        OnFavorite(pos);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void OnAddNewTask(Task task) {
        onAddNewTask(task.getTitle(), task.getContent(), 1);
        startActivity(new Intent(HomeActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(HomeActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                mSharePre.edit().clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}