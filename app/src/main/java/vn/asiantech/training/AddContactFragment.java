package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import vn.asiantech.training.Adapter.RecyclerAdapter;
import vn.asiantech.training.Model.Contact;

public class AddContactFragment extends DialogFragment implements View.OnClickListener {
    private static final String ARG_LIST_CONTACT = "listcontact";
    private EditText mEdName;
    private EditText mEdNumber;
    private Button mBtnOK;
    private Button mBtnCancel;
    private DatabaseHelper data ;
    private ArrayList<Contact> mArr = new ArrayList<Contact>();
    private RecyclerAdapter adapter;
    public AddContactFragment() {
    }

    public static AddContactFragment newInstance(ArrayList<Contact> arr) {
        AddContactFragment fragment = new AddContactFragment();
        Bundle arg = new Bundle();
        arg.putParcelableArrayList(ARG_LIST_CONTACT,arr);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mArr = getArguments().getParcelableArrayList(ARG_LIST_CONTACT);
        }
        data = new DatabaseHelper(getContext());
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
                data.open();
                String name = mEdName.getText().toString();
                String number = mEdNumber.getText().toString();
                Log.i("create", name.toString());
                long x = data.createData(name, number);
                if (x > 0) {
                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();
                }
                data.close();
                adapter = new RecyclerAdapter(mArr);
                Contact c = new Contact(name,number);
                mArr.add(c);
                /*phuong thuc updateList phai tu viet trong adapter */
                adapter.updateList(mArr);
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
