package vn.asiantech.training.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.API.Api;
import vn.asiantech.training.API.ApiClient;
import vn.asiantech.training.Listener.EndlessRecyclerViewScrollListener;
import vn.asiantech.training.R;
import vn.asiantech.training.activity.MainActivity;
import vn.asiantech.training.adapter.TaskAdapter;
import vn.asiantech.training.model.Task;
import vn.asiantech.training.model.TaskResult;

import static android.content.Context.MODE_PRIVATE;

public class TaskFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_LIST_TASK = "Task";
    private List<Task> mArrTask = new ArrayList<Task>();
    private RecyclerView mRecyclerview;
    private TaskAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ImageView mImgViewPlus;
    private SendFromContact mCallback;
    private EndlessRecyclerViewScrollListener scrollListener;
    private String mAccessToken;
    private List<Task> mArrForRecycler = new ArrayList<Task>();
    private int ITEM_PARSE = 10;
    //scroll
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
    public TaskFragment() {
        // Required empty public constructor
    }

    public static TaskFragment newInstance(List<Task> arr) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST_TASK, (ArrayList<Task>) arr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArrForRecycler = getArguments().getParcelableArrayList(ARG_LIST_TASK);
        }
        getAccessToken();
        Log.i("taskToken", mAccessToken);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerViewContact);
        mImgViewPlus = (ImageView) view.findViewById(R.id.imgView);
        Log.i("taskSizeinVIew", mArrForRecycler.size() + "");
        mAdapter = new TaskAdapter(mArrForRecycler);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        mImgViewPlus.setOnClickListener(this);


        //scroll
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            Handler handler = new Handler();

                            final Runnable r = new Runnable() {
                                public void run() {
                                    GetDataFromService(ITEM_PARSE);
//                                    mAdapter = new TaskAdapter(mArrForRecycler);
//                                    mRecyclerview.setAdapter(mAdapter);

                                }
                            };

                            handler.post(r);
                        }
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgView:
                mCallback.SendFromContactFrag();
                break;
        }
    }

    public interface SendFromContact {
        public void SendFromContactFrag();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SendFromContact) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }



    public void GetDataFromService(int offset) {
        Log.d("tag", "connect");
        Api api = ApiClient.retrofit().create(Api.class);
        Call<TaskResult> result = api.listTasks(offset, 10, mAccessToken);
        result.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                Log.i("taskFragsize", response.body().getListTasks().size() + "");
                mArrTask = response.body().getListTasks();
                mArrForRecycler.addAll(mArrTask);
                mAdapter.notifyItemInserted(mArrForRecycler.size()  );
                ITEM_PARSE+=10;
                loading = true;
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {
            }
        });
    }

    public void getAccessToken() {
        SharedPreferences settings = getActivity().getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        mAccessToken = settings.getString(MainActivity.ACCESS_TOKEN, "");
        Log.i("Main access token:", mAccessToken);
    }

}


