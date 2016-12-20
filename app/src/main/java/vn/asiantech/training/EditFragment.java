package vn.asiantech.training;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.API.Api;
import vn.asiantech.training.API.ApiClient;
import vn.asiantech.training.model.Task;
import vn.asiantech.training.model.TaskResult;


public class EditFragment extends Fragment implements View.OnClickListener{
    private EditText mEdTitle;
    private EditText mEdContent;
    private Button mBtnEdit;
    private Button mBtnCancel;
    private DataFromEdFrag mCallback;
    private Task mTask;
    private int mPosition;
    private String mAccessToken;
    public EditFragment() {
        // Required empty public constructor
    }


    public static EditFragment newInstance(Task task,int position,String mAccessToken) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putParcelable("task",task);
        args.putInt("position",position);
        args.putString("access_token",mAccessToken);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTask = getArguments().getParcelable("task");
            mPosition = getArguments().getInt("position");
            mAccessToken = getArguments().getString("access_token");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        mBtnEdit = (Button)view.findViewById(R.id.btnEdit);
        mBtnCancel = (Button)view.findViewById(R.id.btnCancel);
        mEdTitle = (EditText)view.findViewById(R.id.edTitle);
        mEdContent = (EditText)view.findViewById(R.id.edContent);

        mEdTitle.setText(mTask.getTitle());
        mEdContent.setText(mTask.getContent());
        mBtnEdit.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEdit:
                Log.i("EdFragToken",mAccessToken);

                mTask.setTitle(mEdTitle.getText().toString());
                mTask.setContent(mEdContent.getText().toString());
                Log.i("id",mTask.getId()+"");
                Api api = ApiClient.retrofit().create(Api.class);
                String title = mEdTitle.getText().toString();
                String content = mEdContent.getText().toString();

                Call<TaskResult> result = api.editTask(mTask.getId(), mTask.getTitle(), mTask.getContent(), mTask.getInterest(), mAccessToken);
                result.enqueue(new Callback<TaskResult>() {
                    @Override
                    public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                        if (response.isSuccessful()) {
                            mCallback.SendData2();
                            Toast.makeText(getActivity().getApplication(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<TaskResult> call, Throwable t) {
                        Log.d("tag", "fail" + t.getMessage());
                    }
                });
                break;

            case R.id.btnCancel:
                getActivity().onBackPressed();
                break;
        }
    }

    public interface DataFromEdFrag {
        public void SendData2();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (DataFromEdFrag) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
