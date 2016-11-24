package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.ContactListAdapter;
import vn.asiantech.training.model.Contact;

/**
 * Created by phuong on 22/11/2016.
 */

public class ContactListFragment extends Fragment {
    private static final String ARG_LIST_CONTACT = "listcontact";
    private RecyclerView mRecyclerView;
    private ContactListAdapter mContactListAdapter;
    private ArrayList<Contact> mContacts;

    public static ContactListFragment newInstance(List<Contact> contactList) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle arg = new Bundle();
        arg.putParcelableArrayList(ARG_LIST_CONTACT, (ArrayList<Contact>) contactList);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContacts = getArguments().getParcelableArrayList(ARG_LIST_CONTACT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewContact);
        mContactListAdapter = new ContactListAdapter(getActivity().getBaseContext(), mContacts);
        mContactListAdapter.setListener(new ISendData() {
            @Override
            public void sendData(Contact contact) {
                Toast.makeText(getActivity(), "ahihi" + contact, Toast.LENGTH_LONG).show();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mContactListAdapter);
        return view;
    }


}
