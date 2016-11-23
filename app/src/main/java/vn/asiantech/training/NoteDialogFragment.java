package vn.asiantech.training;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

/**
 * Created by HoangDuy on 23/11/2016.
 */
public class NoteDialogFragment extends DialogFragment {
    public static final String NAME_POSITIVE_BTN = "Note";
    public static final String NAME_NEGATIVE_BTN = "Cancel";
    public NoteDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        alertDialog.setView(layoutInflater.inflate(R.layout.dialog_note, null))
                .setNegativeButton(NAME_NEGATIVE_BTN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onNoteDialogNegativeClick(NoteDialogFragment.this);
                    }
                })
                .setPositiveButton(NAME_POSITIVE_BTN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onNoteDialogPositiveClick(NoteDialogFragment.this);
                    }
                });
        return alertDialog.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoteDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface NoteDialogListener {
        void onNoteDialogPositiveClick(DialogFragment dialog);

        void onNoteDialogNegativeClick(DialogFragment dialog);
    }
}
