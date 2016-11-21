package vn.asiantech.training.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import vn.asiantech.training.R;

public class QuestionFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int mParam1;

    private OnFragmentInteractionListener mListener;
    private RadioGroup mRdgQuestion;
    private RadioButton mRdChoiceA;
    private RadioButton mRdChoiceB;
    private RadioButton mRdChoiceC;
    private RadioButton mRdChoiceD;
    private TextView mTvQuestion;

    public QuestionFragment() {
    }

    public static QuestionFragment newInstance(int position) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        Log.d("position",String.valueOf(position));
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
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        mTvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        mRdgQuestion = (RadioGroup) view.findViewById(R.id.rdbGroup);
        mRdChoiceA = (RadioButton) view.findViewById(R.id.rdbChoiceA);
        mRdChoiceB = (RadioButton) view.findViewById(R.id.rdbChoiceB);
        mRdChoiceC = (RadioButton) view.findViewById(R.id.rdbChoiceC);
        mRdChoiceD = (RadioButton) view.findViewById(R.id.rdbChoiceD);
        final Resources resources = getActivity().getResources();
        String[] questions = resources.getStringArray(R.array.questions);
        String[] answersA = resources.getStringArray(R.array.answersA);
        String[] answersB = resources.getStringArray(R.array.answersB);
        String[] answersC = resources.getStringArray(R.array.answersC);
        String[] answersD = resources.getStringArray(R.array.answersD);
        mTvQuestion.setText(questions[mParam1]);
        mRdChoiceA.setText(answersA[mParam1]);
        mRdChoiceB.setText(answersB[mParam1]);
        mRdChoiceC.setText(answersC[mParam1]);
        mRdChoiceD.setText(answersD[mParam1]);

        mRdgQuestion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String answer, int position);
    }
}
