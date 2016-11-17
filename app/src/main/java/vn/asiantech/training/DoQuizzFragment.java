package vn.asiantech.training;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DoQuizzFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DoQuizzFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoQuizzFragment extends Fragment {
    private int mPOSITION;
    private ViewPager mVpQuizz;
    private OnFragmentInteractionListener mListener;
    private Button mBtnNext;
    public DoQuizzFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DoQuizzFragment newInstance(int position) {
        DoQuizzFragment fragment = new DoQuizzFragment();
        Bundle args = new Bundle();
        args.putInt("mPOSITION", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPOSITION = getArguments().getInt("mPOSITION");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_do_quizz, container, false);
        mVpQuizz = (ViewPager) view.findViewById(R.id.vpContentQuizz);
        mBtnNext = (Button) view.findViewById(R.id.btnNext);
        final ArrayList<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new DoQuizzFragment());
        listFragment.add(new DoQuizzFragment());
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                PagerAdapter adapter = new QuizzAdapter(manager, listFragment);
                mVpQuizz.setAdapter(adapter);
                mVpQuizz.setCurrentItem(1);
            }
        });
        Toast.makeText(getActivity(), mPOSITION + "", Toast.LENGTH_SHORT).show();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
