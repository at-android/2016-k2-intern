package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import vn.asiantech.training.Model.Contact;
import vn.asiantech.training.Model.RealmPeople;

public class AddContactFragment extends DialogFragment implements View.OnClickListener {
    private static final String ARG_LIST_CONTACT = "listcontact";
    private EditText mEdName;
    private EditText mEdNumber;
    private Button mBtnOK;
    private Button mBtnCancel;
    private RealmResults<RealmPeople> mRealmList;
    private Realm mRealm;
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
        if (getArguments() != null) {
        }
        //Realm
        Realm.init(getActivity());
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        mRealm = Realm.getInstance(config);
        mRealmList = mRealm.where(RealmPeople.class).findAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
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
                int id = mRealmList.size();
                Log.i("realmlist",mRealmList.size()+"");
                String name = mEdName.getText().toString();
                String number = mEdNumber.getText().toString();
                mRealm.beginTransaction();
                RealmPeople people = mRealm.createObject(RealmPeople.class,id);
                people.setId(id);
                people.setName(name);
                people.setPhoneNumber(number);
                mRealm.commitTransaction();
                mRealmList.addChangeListener(new RealmChangeListener<RealmResults<RealmPeople>>() {
                    @Override
                    public void onChange(RealmResults<RealmPeople> element) {
                        mRealmList.size();
                    }
                });
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
