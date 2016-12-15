package vn.asiantech.training.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.MainActivity_;
import vn.asiantech.training.R;
import vn.asiantech.training.adapter.ListStudentAdapter;

@EFragment(R.layout.fragment_list_student)
public class ListStudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @ViewById(R.id.recyclerViewListStudent)
    RecyclerView recyclerViewListStudent;
    @FragmentArg
    int position;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private onListStudentFragmentInterface mListener;

    public ListStudentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListStudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListStudentFragment newInstance(String param1, String param2) {
        ListStudentFragment fragment = new ListStudentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @AfterViews
    void setAdapter() {
        recyclerViewListStudent.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewListStudent.setLayoutManager(linearLayoutManager);
        MainActivity_ mainActivity = (MainActivity_) getActivity();
        ListStudentAdapter adapter = new ListStudentAdapter(getActivity(), mainActivity.studentArrays);
        recyclerViewListStudent.setAdapter(adapter);
    }

    @Click(R.id.imgAddStudent)
    void addStudent() {
        mListener.onItentAddStudent();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onListStudentFragmentInterface) {
            mListener = (onListStudentFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onListStudentFragmentInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface onListStudentFragmentInterface {
        void onItentAddStudent();
    }
}
