package vn.asiantech.training;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class ListContactFragment extends Fragment {
    private ImageView mImgView;
    private ArrayList<Contact> mArr = new ArrayList<Contact>();
    private DatabaseHelper data;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public ListContactFragment() {
    }

    public static ListContactFragment newInstance() {
        ListContactFragment fragment = new ListContactFragment();
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
        mImgView = (ImageView)view.findViewById(R.id.img);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewList);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RecyclerAdapter(mArr);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment frag = AddContactFragment.newInstance(mArr);
                frag.show(getFragmentManager(),"dialog");
            }
        });
        return view;
    }

}
