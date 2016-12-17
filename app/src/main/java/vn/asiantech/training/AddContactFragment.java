package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.training.Controller.RealmController;
import vn.asiantech.training.Model.Contact;
import vn.asiantech.training.Model.RealmPeople;

@EFragment
public class AddContactFragment extends DialogFragment {
    private static final String ARG_LIST_CONTACT = "listcontact";
    @ViewById(R.id.edName)
    EditText mEdName;
    @ViewById(R.id.edNumber)
    EditText mEdNumber;
    @ViewById(R.id.btnOK)
    Button mBtnOK;
    @ViewById(R.id.btnCancel)
    Button mBtnCancel;
    private SendData mCallback;
    public AddContactFragment() {
    }

    public static AddContactFragment newInstance(ArrayList<Contact> arr) {
        AddContactFragment fragment = new AddContactFragment();
        Bundle arg = new Bundle();
        arg.putParcelableArrayList(ARG_LIST_CONTACT, arr);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        return view;
    }

    @Click(R.id.btnOK)
    public void ClickBtnOK() {
        String name = mEdName.getText().toString();
        String number = mEdNumber.getText().toString();
        RealmController realm = new RealmController(getActivity());
        realm.addPeople(new RealmPeople(name,number));
        mCallback.onArticleSelected();
        dismiss();
    }

    @Click(R.id.btnCancel)
    public void ClickBtnCancle() {
        dismiss();
    }

    public interface SendData {
        public void onArticleSelected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (SendData) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
