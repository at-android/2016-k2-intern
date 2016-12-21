package vn.asiantech.training;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.model.Task;


@EFragment(R.layout.fragment_edit)
public class EditFragment extends Fragment {

    @FragmentArg("task")
    Task mTask;
    @FragmentArg("pos")
    int mPos;
    @ViewById(R.id.edtTitle)
    EditText mEdtTitle;
    @ViewById(R.id.edtContent)
    EditText mEdtContent;

    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void init() {
        mEdtTitle.setText(mTask.getTitle());
        mEdtContent.setText(mTask.getContent());
    }

    @Click(R.id.btnUpdate)
    void update() {
        Task task = new Task(mTask.getId(), mEdtTitle.getText().toString(), mEdtContent.getText().toString(), mTask.getFavorite());
        mListener.onEditFragmentInteraction(task, mPos);
        getActivity().onBackPressed();
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
        void onEditFragmentInteraction(Task task, int pos);
    }
}
