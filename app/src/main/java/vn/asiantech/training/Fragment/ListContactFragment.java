package vn.asiantech.training.Fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import vn.asiantech.training.Adapter.RecyclerAdapter;
import vn.asiantech.training.AddContactFragment_;
import vn.asiantech.training.Controller.RealmController;
import vn.asiantech.training.Model.RealmPeople;
import vn.asiantech.training.R;

@EFragment
public class ListContactFragment extends Fragment {
    @ViewById(R.id.edSearch)
    EditText mEdSearch;
    @ViewById(R.id.img)
    ImageView mImgView;
    @ViewById(R.id.recyclerViewList)
    RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Realm mRealm;
    RealmResults<RealmPeople> mRealmList;
    private List<RealmPeople> mList;

    public ListContactFragment() {
    }

    public static ListContactFragment newInstance() {
        ListContactFragment fragment = new ListContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_contact, container, false);

        return view;
    }

    @AfterViews
    public void init() {
        RealmController controller = new RealmController(getActivity());
        mList = controller.getContact();
        layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new RecyclerAdapter(mList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        addTextListener();
    }

    @Click(R.id.img)
    public void ClickImgView() {
        DialogFragment frag = new AddContactFragment_();
        frag.show(getFragmentManager(), "dialog");
    }

    //Search in recyclerView
    public void addTextListener() {
        mEdSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();
                String s = query.toString();
                Realm.init(getContext());
                mRealm = Realm.getDefaultInstance();
                final RealmResults<RealmPeople> listContain = mRealm.where(RealmPeople.class).contains("name", s).findAll();
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
