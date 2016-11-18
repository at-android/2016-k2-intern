package vn.asiantech.training;

import android.content.Context;
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
 * {@link DoQuizzFragment.waitCallBackPosition} interface
 * to handle interaction events.
 * Use the {@link DoQuizzFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoQuizzFragment extends Fragment {
    private int mPOSITION;
    private waitCallBackPosition mListener;
    private TextView mTvTitle;
    private TextView mTvQuestion;
    private RadioButton mRdBtnResultA;
    private RadioButton mRdBtnResultB;
    private RadioButton mRdBtnResultC;
    private RadioButton mRdBtnResultD;
    private String mResultCorrect;
    private RadioGroup mRdGroupResult;

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
        mTvTitle = (TextView) view.findViewById(R.id.tvTitleLayout);
        mTvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        mRdBtnResultA = (RadioButton) view.findViewById(R.id.rdBtnResultA);
        mRdBtnResultB = (RadioButton) view.findViewById(R.id.rdBtnResultB);
        mRdBtnResultC = (RadioButton) view.findViewById(R.id.rdBtnResultC);
        mRdBtnResultD = (RadioButton) view.findViewById(R.id.rdBtnResultD);
        mRdGroupResult = (RadioGroup) view.findViewById(R.id.radioGroupResult);
        int numberTitle = mPOSITION + 1;
        mListener.onGetPositionFormDoQuizztoMain(mPOSITION);
        mTvTitle.setText("Question " + numberTitle);
        loadDataFormList();
        mRdGroupResult.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                MainActivity main = (MainActivity) getActivity();
                switch (i) {
                    case R.id.rdBtnResultA:
                        if (mResultCorrect.equals(mRdBtnResultA.getText().toString())) {
                            main.ScoreArray.add(mPOSITION, "T");
                        } else {
                            main.ScoreArray.add(mPOSITION, "F");
                        }
                        break;
                    case R.id.rdBtnResultB:
                        if (mResultCorrect.equals(mRdBtnResultB.getText().toString())) {
                            main.ScoreArray.add(mPOSITION, "T");
                        } else {
                            main.ScoreArray.add(mPOSITION, "F");
                        }
                        break;
                    case R.id.rdBtnResultC:
                        if (mResultCorrect.equals(mRdBtnResultC.getText().toString())) {
                            main.ScoreArray.add(mPOSITION, "T");
                        } else {
                            main.ScoreArray.add(mPOSITION, "F");
                        }
                        break;
                    case R.id.rdBtnResultD:
                        if (mResultCorrect.equals(mRdBtnResultD.getText().toString())) {
                            main.ScoreArray.add(mPOSITION, "T");
                        } else {
                            main.ScoreArray.add(mPOSITION, "F");
                        }
                        break;
                }
            }
        });
        return view;
    }

    public void loadDataFormList() {
        MainActivity main = (MainActivity) getActivity();
        mTvQuestion.setText(main.sQuizzArray.get(mPOSITION).getmQuestion());
        mRdBtnResultA.setText(main.sQuizzArray.get(mPOSITION).getmResultA());
        mRdBtnResultB.setText(main.sQuizzArray.get(mPOSITION).getmResultB());
        mRdBtnResultC.setText(main.sQuizzArray.get(mPOSITION).getmResultC());
        mRdBtnResultD.setText(main.sQuizzArray.get(mPOSITION).getmResultD());
        mResultCorrect = main.sQuizzArray.get(mPOSITION).getmResultCorrect();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof waitCallBackPosition) {
            mListener = (waitCallBackPosition) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement waitCallBackPosition");
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
    public interface waitCallBackPosition {
        // TODO: Update argument type and name
        void onGetPositionFormDoQuizztoMain(int position);
    }
}
