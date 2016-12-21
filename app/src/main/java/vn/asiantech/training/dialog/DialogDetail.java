package vn.asiantech.training.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import vn.asiantech.training.MainActivity;
import vn.asiantech.training.R;
import vn.asiantech.training.model.Task;

/**
 * Created by HoangDuy on 19/12/2016.
 */

@EFragment
public class DialogDetail extends DialogFragment {
    public static final String NAME_POSITIVE_BTN = "Edit";
    public static final String NAME_NEGATIVE_BTN = "Cancel";
    public DialogListener mListener;
    @FragmentArg("task")
    Task mTask;
    @FragmentArg("pos")
    int mPos;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_detail, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvContent = (TextView) view.findViewById(R.id.tvContent);
        tvTitle.setText(mTask.getTitle());
        tvContent.setText(mTask.getContent());
        alertDialog.setView(view)
                .setNegativeButton(NAME_NEGATIVE_BTN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onAddDialogNegativeClick(DialogDetail.this);
                    }
                })
                .setPositiveButton(NAME_POSITIVE_BTN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onAddDialogPositiveClick(DialogDetail.this, mTask, mPos);
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
            mListener = (MainActivity) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogListener {
        void onAddDialogPositiveClick(DialogFragment dialog, Task task, int pos);

        void onAddDialogNegativeClick(DialogFragment dialog);
    }
}
