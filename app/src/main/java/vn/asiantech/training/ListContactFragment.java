package vn.asiantech.training;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class ListContactFragment extends Fragment {
    private ListView mLv;
    private ImageView mImgView;
    private ArrayAdapter mAdapter = null;
    private ArrayList<Contact> mArr = new ArrayList<Contact>();
    private DatabaseHelper data;
    public ListContactFragment() {
        // Required empty public constructor
    }

    public static ListContactFragment newInstance() {
        ListContactFragment fragment = new ListContactFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new DatabaseHelper(getContext());
        data.open();
        mArr = data.getData("select * from CONTACT");
        data.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_contact, container, false);
        mLv = (ListView)view.findViewById(R.id.lv);
        mImgView = (ImageView)view.findViewById(R.id.img);
        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment frag = AddContactFragment.newInstance();
                frag.show(getFragmentManager(),"dialog");
            }
        });
        mAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,mArr);
        mLv.setAdapter(mAdapter);
        return view;
    }

}
