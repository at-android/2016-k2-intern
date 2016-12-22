package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.asiantech.training.api.Api;
import vn.asiantech.training.api.ApiClient;
import vn.asiantech.training.model.Task;
import vn.asiantech.training.model.TaskResult;


@EFragment(R.layout.fragment_add)
public class AddFragment extends Fragment {

    @ViewById(R.id.edtTitle)
    EditText mEdtTitle;
    @ViewById(R.id.edtContent)
    EditText mEdtContent;
    @FragmentArg("token")
    String mToken;

    private OnFragmentInteractionListener mListener;

    public AddFragment() {
        // Required empty public constructor
    }

    @Click(R.id.btnAdd)
    void add() {
        Api api = ApiClient.getClient().create(Api.class);
        retrofit2.Call<TaskResult> taskResultCall = api.createTask(mEdtTitle.getText().toString()
                , mEdtContent.getText().toString(), 0, mToken);
        taskResultCall.enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                if (response.body().getError().equals("false")) {
                    Toast.makeText(getContext(), "Add successful", Toast.LENGTH_LONG).show();
                    Task task = new Task(0, mEdtTitle.getText().toString(), mEdtContent.getText().toString(), 0);
                    mListener.onAddFragmentInteraction(task);
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(), response.message().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAddFragmentInteraction(Task task);
    }
}
