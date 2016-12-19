package vn.asiantech.training.activities;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import vn.asiantech.training.R;
import vn.asiantech.training.adapter.RecyclerViewAdapter;
import vn.asiantech.training.interfaces.RequesService;
import vn.asiantech.training.objects.Result;
import vn.asiantech.training.objects.Task;

import static vn.asiantech.training.MainActivity.API_URL;
import static vn.asiantech.training.MainActivity.KEY_SHARE;

public class HomeActivity extends AppCompatActivity {
    private Button mBtnDialogAdd;
    private EditText mEdtDialogContent;
    private EditText mEdtDialogTitle;
    private RadioGroup mRadioGroupfavorite;
    private FloatingActionButton mBtnShowDialogAddTask;
    private List<Task> arrTasks = new ArrayList<>();
    private RecyclerView recylerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        onGetTask();
        recylerView = (RecyclerView) findViewById(R.id.recyclerViewListTask);
        mBtnShowDialogAddTask = (FloatingActionButton) findViewById(R.id.floatingActionButtonShowDialogAddTask);
        mBtnShowDialogAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onShowButtonAddTask();
                Toast.makeText(HomeActivity.this, "ád", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onAddNewTask(String title, String content, int favorite) {
        SharedPreferences pre = getSharedPreferences(KEY_SHARE, MODE_PRIVATE);
        final String token = pre.getString("token", "");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).client(client)
                .build();
        RequesService requesService = retrofit.create(RequesService.class);
        Call<Result> user = requesService.addTask(title, content, favorite);
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

    public void onShowButtonAddTask() {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.dialog_new_task);
        dialog.setTitle("New Task");
        mBtnDialogAdd = (Button) dialog.findViewById(R.id.btnDialogAddTask);
        mEdtDialogContent = (EditText) dialog.findViewById(R.id.edtDialogContent);
        mEdtDialogTitle = (EditText) dialog.findViewById(R.id.edtDialogTitle);
        mRadioGroupfavorite = (RadioGroup) dialog.findViewById(R.id.radioGroupFavorite);
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
                onAddNewTask(mEdtDialogTitle.getText().toString(), mEdtDialogContent.getText().toString(), a);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void onGetTask() {
        SharedPreferences pre = getSharedPreferences(KEY_SHARE, MODE_PRIVATE);
        final String token = pre.getString("token", "");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        RequesService requesService = retrofit.create(RequesService.class);
        Call<Result> user = requesService.getTask(0, 10);
        user.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(HomeActivity.this, response.body().listTasks.get(1).getContent(), Toast.LENGTH_SHORT).show();
                    for (int i = 0, lenght = response.body().listTasks.size(); i < lenght; i++) {
                        arrTasks.add(new Task(response.body().listTasks.get(i).getId(), response.body().listTasks.get(i).getTitle(), response.body().listTasks.get(i).getContent(), response.body().listTasks.get(i).getFavorite()));
                    }
                    Log.d("listsize", arrTasks.size() + "");
                    RecyclerView.LayoutManager lln = new LinearLayoutManager(getApplicationContext());
                    recylerView.setHasFixedSize(true);
                    recylerView.setLayoutManager(lln);
                    adapter = new RecyclerViewAdapter(arrTasks, HomeActivity.this);
                    recylerView.setAdapter(adapter);
                } else {
                    Toast.makeText(HomeActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Network error " + call.request() + token, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
