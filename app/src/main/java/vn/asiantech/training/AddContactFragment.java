package vn.asiantech.training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class AddContactFragment extends Fragment {
    private ListView mLv;
    private ImageView mImgView;
    private ArrayAdapter mAdapter = null;
    private ArrayList<Contact> mArr = new ArrayList<Contact>();
    private DatabaseHelper data = new DatabaseHelper(getContext());
    public AddContactFragment() {
        // Required empty public constructor
    }

    public static AddContactFragment newInstance() {
        AddContactFragment fragment = new AddContactFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        mLv = (ListView)view.findViewById(R.id.lv);
        mImgView = (ImageView)view.findViewById(R.id.img);
        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.open();

            }
        });
        return view;
    }

}
