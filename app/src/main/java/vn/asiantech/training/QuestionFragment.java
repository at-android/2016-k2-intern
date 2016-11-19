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


public class QuestionFragment extends Fragment {
    private TextView mTvQuestion;
    private RadioGroup mGroup;
    private RadioButton mRadA;
    private RadioButton mRadB;
    private RadioButton mRadC;
    private RadioButton mRadD;
    private RadioButton mRadAnswer;
    private int mPosition;
    OnHeadlineSelectedListener mCallback;
    View view;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public void getFormWidget(View view) {
        mTvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        mGroup = (RadioGroup) view.findViewById(R.id.radGroup);
        mRadA = (RadioButton) view.findViewById(R.id.radA);
        mRadB = (RadioButton) view.findViewById(R.id.radB);
        mRadC = (RadioButton) view.findViewById(R.id.radC);
        mRadD = (RadioButton) view.findViewById(R.id.radD);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question, container, false);
        getFormWidget(view);
        Bundle bundle = getArguments();
        Question q = (Question) bundle.getSerializable("question");
        mPosition = bundle.getInt("position");
        mTvQuestion.setText(q.getQuestion());
        mRadA.setText(q.getKeyA());
        mRadB.setText(q.getKeyB());
        mRadC.setText(q.getKeyC());
        mRadD.setText(q.getKeyD());

        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                i = mGroup.getCheckedRadioButtonId();
                mRadAnswer = (RadioButton) view.findViewById(i);
                mCallback.onArticleSelected(mRadAnswer.getText().toString(), mPosition);
            }
        });

        return view;
    }

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(String chosenKey, int position);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
