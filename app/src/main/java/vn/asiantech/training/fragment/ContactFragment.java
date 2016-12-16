package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import vn.asiantech.training.R;
import vn.asiantech.training.adapter.ContactAdapter;
import vn.asiantech.training.controller.RealmController;
import vn.asiantech.training.model.People;


@EFragment(R.layout.fragment_contact)
public class ContactFragment extends Fragment {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<People> mPeoples;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ContactFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmController realmController = new RealmController(getContext());
        mPeoples = realmController.getContact();
    }

    @AfterViews
    void init() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ContactAdapter(mPeoples);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Click(R.id.imgAdd)
    void goToAddFragment() {
        AddFragmentDialog addFragmentDialog = new AddFragmentDialog();
        addFragmentDialog.show(getFragmentManager(), "");
    }

    @TextChange(R.id.edtSearch)
    void search(TextView hello) {
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<People> peoples = realm.where(People.class).contains("name", hello.getText().toString()).findAll();
        mAdapter = new ContactAdapter(peoples);
        mRecyclerView.swapAdapter(mAdapter, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
