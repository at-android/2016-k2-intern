package vn.asiantech.training.fragment;


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
import vn.asiantech.training.activity.MainActivity;
import vn.asiantech.training.R;
import vn.asiantech.training.model.TaskResult;

public class AddFragment extends Fragment implements View.OnClickListener {
    private Button mBtnAdd;
    private Button mBtnCancel;
    private EditText mEdTitle;
    private EditText mEdContent;
    private DataFromEdFrag mCallback;
    private String mAccessToken;

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mAccessToken = bundle.getString(MainActivity.ACCESS_TOKEN);
            Log.i("add AToken", mAccessToken);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        mBtnAdd = (Button) view.findViewById(R.id.btnAdd);
        mBtnCancel = (Button) view.findViewById(R.id.btnCancel);
        mEdTitle = (EditText) view.findViewById(R.id.edTitle);
        mEdContent = (EditText) view.findViewById(R.id.edContent);
        mBtnAdd.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                Api api = ApiClient.retrofit().create(Api.class);
                Call<TaskResult> result = api.createTask(mEdTitle.getText().toString(), mEdContent.getText().toString(), 0, mAccessToken);
                result.enqueue(new Callback<TaskResult>() {
                    @Override
                    public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity().getApplication(), "Created Task Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getActivity().getApplication(), "Create Fail", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<TaskResult> call, Throwable t) {
                        Toast.makeText(getActivity().getApplication(), "Error Connection", Toast.LENGTH_SHORT).show();
                    }
                });
                mCallback.SendDataFromAddFrag();
                break;
            case R.id.btnCancel:
                getActivity().onBackPressed();
                break;
        }
    }

    public interface DataFromEdFrag {
        public void SendDataFromAddFrag();
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
