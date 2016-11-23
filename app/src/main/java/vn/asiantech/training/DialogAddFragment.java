package vn.asiantech.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAddFragment extends DialogFragment implements View.OnClickListener {
    private EditText mEdName;
    private EditText mEdNumber;
    private Button mBtnOK;
    private Button mBtnCancel;
    private SendData mCallback;
    public DialogAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_add, container, false);
        mEdName = (EditText) view.findViewById(R.id.edName);
        mEdNumber = (EditText) view.findViewById(R.id.edNumber);
        mBtnOK = (Button) view.findViewById(R.id.btnOK);
        mBtnCancel = (Button) view.findViewById(R.id.btnCancel);
        mBtnOK.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOK:
                Human man = new Human();
                man.setName(mEdName.getText().toString());
                man.setPhoneNumber(mEdNumber.getText().toString());
                man.setInterest(false);
                mCallback.onArticleSelected(man);
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    public interface SendData {
        public void onArticleSelected(Human man);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SendData) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SendData");
        }
    }
    public void setOnCallbackDataListener(SendData onCallbackDataListener) {
        mCallback = onCallbackDataListener;
    }
}
