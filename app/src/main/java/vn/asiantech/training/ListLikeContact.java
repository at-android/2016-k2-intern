package vn.asiantech.training;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListLikeContact.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListLikeContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListLikeContact extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRcListcontact;
    private ArrayList<ContactsObj> mContactsArray = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListLikeContact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListLikeContact.
     */
    // TODO: Rename and change types and number of parameters
    public static ListLikeContact newInstance(String param1, String param2) {
        ListLikeContact fragment = new ListLikeContact();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_like_contact, container, false);
        mRcListcontact = (RecyclerView) view.findViewById(R.id.rc_like_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRcListcontact.setHasFixedSize(true);
        mRcListcontact.setLayoutManager(llm);
        MainActivity mainActivity = (MainActivity) getActivity();
        for (int i = 0; i < mainActivity.sContactsArray.size(); i++) {
            if (mainActivity.sContactsArray.get(i).isLike() == true) {
                mContactsArray.add(mainActivity.sContactsArray.get(i));
            }
        }
        RecycleViewAdapterLike viewAdapter = new RecycleViewAdapterLike(getActivity(), mContactsArray);
        mRcListcontact.setAdapter(viewAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
