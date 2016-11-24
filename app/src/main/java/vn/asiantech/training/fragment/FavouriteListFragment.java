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

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.FavListAdapter;
import vn.asiantech.training.model.Contact;

/**
 * Created by phuong on 22/11/2016.
 */

public class FavouriteListFragment extends Fragment {
    private static final String ARG_LIST_CONTACT_FAV = "listcontactfav";
    private ArrayList<Contact> mContactFav;
    private RecyclerView mRecyclerView;
    private FavListAdapter mFavListAdapter;

    public static FavouriteListFragment newInstance(List<Contact> contactList) {
        FavouriteListFragment fragment = new FavouriteListFragment();
        Bundle arg = new Bundle();
        arg.putParcelableArrayList(ARG_LIST_CONTACT_FAV, (ArrayList<Contact>) contactList);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContactFav = getArguments().getParcelableArrayList(ARG_LIST_CONTACT_FAV);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFavourite);
        mFavListAdapter = new FavListAdapter(getActivity().getBaseContext(), mContactFav);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFavListAdapter);
        return view;
    }


}
