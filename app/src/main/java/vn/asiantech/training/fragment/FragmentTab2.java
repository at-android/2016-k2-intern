package vn.asiantech.training.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.PhoneRecycleViewAdapter;
import vn.asiantech.training.model.Phone;

/**
 * Created by phuong on 29/11/2016.
 */

public class FragmentTab2 extends Fragment {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ArrayList<Phone> mPhones;
    private PhoneRecycleViewAdapter mPhoneRecycleViewAdapter;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvContactTab);
        getDataContactFromDevice();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mPhoneRecycleViewAdapter = new PhoneRecycleViewAdapter(mPhones, getContext());
        mRecyclerView.setAdapter(mPhoneRecycleViewAdapter);
        return view;
    }

    public ArrayList<Phone> getDataContactFromDevice() {
        mPhones = new ArrayList<>();
        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mPhones.add(new Phone(name, phoneNumber));
        }
        phones.close();
        return mPhones;
    }

}
