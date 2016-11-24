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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import vn.asiantech.training.model.Note;

import static vn.asiantech.training.R.id.btnOK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogNoteAddFragment extends DialogFragment implements View.OnClickListener{
    SendDataFromNoteAddFragment mCallback;
    EditText mEdTitle;
    EditText mEdContent;
    Button mBtnOK;
    Button mBtnCancel;
    public DialogNoteAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_note_add, container, false);
        mEdTitle = (EditText)view.findViewById(R.id.edTitle);
        mEdContent = (EditText)view.findViewById(R.id.edContent);
        mBtnOK = (Button)view.findViewById(btnOK);
        mBtnCancel = (Button)view.findViewById(R.id.btnCancel);
        mBtnOK.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnOK:
                Note note = new Note();
                note.setTitle(mEdTitle.getText().toString());
                note.setContent(mEdContent.getText().toString());
                DateFormat dataFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                note.setTime(dataFormat.format(cal.getTime())+"");
                mCallback.onArticleSelected(note);
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
    public interface SendDataFromNoteAddFragment {
        public void onArticleSelected(Note note);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SendDataFromNoteAddFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SendData");
        }
    }
    public void setOnCallbackDataListener(SendDataFromNoteAddFragment onCallbackDataListener) {
        mCallback = onCallbackDataListener;
    }
}
