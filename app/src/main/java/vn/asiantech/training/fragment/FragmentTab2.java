package vn.asiantech.training.fragment;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.PhoneRecycleViewAdapter;
import vn.asiantech.training.model.Phone;

/**
 * Created by phuong on 29/11/2016.
 */
@EFragment(R.layout.fragment_tab2)
public class FragmentTab2 extends Fragment {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    @ViewById(R.id.rvContactTab)
    RecyclerView mRecyclerView;
    private List<Phone> mPhones;
    private PhoneRecycleViewAdapter mPhoneRecycleViewAdapter;

    @AfterViews
    void initRecyclerViewData() {
        getDataContactFromDevice();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mPhoneRecycleViewAdapter = new PhoneRecycleViewAdapter(mPhones, getContext());
        mRecyclerView.setAdapter(mPhoneRecycleViewAdapter);
    }


    public List<Phone> getDataContactFromDevice() {
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
