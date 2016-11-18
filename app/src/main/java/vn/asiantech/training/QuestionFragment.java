package vn.asiantech.training;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public OnFragmentInteractionListener mListener;
    private RadioButton mRdbChoiceA;
    private RadioButton mRdbChoiceB;
    private RadioButton mRdbChoiceC;
    private RadioButton mRdbChoiceD;
    private RadioGroup mRdbGroup;
    // TODO: Rename and change types of parameters
    private int mParam1;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(int position) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_question, container, false);
        TextView tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        mRdbChoiceA = (RadioButton) view.findViewById(R.id.rdbChoiceA);
        mRdbChoiceB = (RadioButton) view.findViewById(R.id.rdbChoiceB);
        mRdbChoiceC = (RadioButton) view.findViewById(R.id.rdbChoiceC);
        mRdbChoiceD = (RadioButton) view.findViewById(R.id.rdbChoiceD);
        mRdbGroup = (RadioGroup) view.findViewById(R.id.rdbGroup);
        final Resources res = getActivity().getResources();
        String[] questions = res.getStringArray(R.array.questions);
        String[] answersA = res.getStringArray(R.array.answersA);
        String[] answersB = res.getStringArray(R.array.answersB);
        String[] answersC = res.getStringArray(R.array.answersC);
        String[] answersD = res.getStringArray(R.array.answersD);
        tvQuestion.setText(questions[mParam1]);
        mRdbChoiceA.setText(answersA[mParam1]);
        mRdbChoiceB.setText(answersB[mParam1]);
        mRdbChoiceC.setText(answersC[mParam1]);
        mRdbChoiceD.setText(answersD[mParam1]);

        mRdbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdbChoiceA:
                        mListener.onFragmentInteraction("A", mParam1);
                        break;
                    case R.id.rdbChoiceB:
                        mListener.onFragmentInteraction("B", mParam1);
                        break;
                    case R.id.rdbChoiceC:
                        mListener.onFragmentInteraction("C", mParam1);
                        break;
                    case R.id.rdbChoiceD:
                        mListener.onFragmentInteraction("D", mParam1);
                        break;
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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

    /*@Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String answer, int position);
    }
}
