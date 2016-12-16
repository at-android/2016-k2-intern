package vn.asiantech.training.Fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import vn.asiantech.training.Adapter.RecyclerAdapter;
import vn.asiantech.training.AddContactFragment;
import vn.asiantech.training.DatabaseHelper;
import vn.asiantech.training.Model.Contact;
import vn.asiantech.training.Model.RealmPeople;
import vn.asiantech.training.R;


public class ListContactFragment extends Fragment {
    private EditText mEdSearch;
    private ImageView mImgView;
    private ArrayList<Contact> mArr = new ArrayList<Contact>();
    private DatabaseHelper data;
    private RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Realm mRealm;
    private  List<RealmPeople> mList;
    private RealmResults<RealmPeople> mRealmList;

    public ListContactFragment() {
    }

    public static ListContactFragment newInstance() {
        ListContactFragment fragment = new ListContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getActivity());
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        mRealm = Realm.getInstance(config);
        mList = new ArrayList<RealmPeople>();

        mRealmList = mRealm.where(RealmPeople.class).findAll();
        Log.i("realmlist size",mRealmList.size()+"");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_contact, container, false);
        mImgView = (ImageView) view.findViewById(R.id.img);
        mEdSearch = (EditText)view.findViewById(R.id.edSearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewList);
        layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new RecyclerAdapter(mRealmList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment frag = AddContactFragment.newInstance(mArr);
                frag.show(getFragmentManager(), "dialog");
            }
        });
        addTextListener();
        return view;
    }

    //Search in recyclerView
    public void addTextListener(){
        mEdSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();
                String s = query.toString();
                final RealmResults<RealmPeople> listContain = mRealm.where(RealmPeople.class).contains("name",s).findAll();
                layoutManager = new LinearLayoutManager(getContext());
                mAdapter = new RecyclerAdapter(listContain);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
