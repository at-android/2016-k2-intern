package vn.asiantech.training;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ContentProContactFragment extends Fragment {
    private String s = "";
    private String name = "";
    private String phoneNumber = "";
    private ListView mLv;
    private ArrayAdapter mAdapter = null;
    private ArrayList<String> mArr = new ArrayList<String>();

    public ContentProContactFragment() {
        // Required empty public constructor
    }

    public static ContentProContactFragment newInstance() {
        ContentProContactFragment fragment = new ContentProContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_pro_contact, container, false);
        mLv = (ListView) view.findViewById(R.id.lvContact);
        getNumber(getActivity().getContentResolver());
        return view;
    }

    public void getNumber(ContentResolver cr) {
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            s += name + "---" + phoneNumber + "\n";
        }
        mArr.add(s);
        phones.close();
        mAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, mArr);
        mLv.setAdapter(mAdapter);
    }
}
