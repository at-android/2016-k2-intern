package vn.asiantech.training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.asiantech.training.adapter.FavoriteAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    private static final String ARG_LIST_HUMAN = "Human";
    private ArrayList<Human> mArrHuman = new ArrayList<Human>();
    private ArrayList<Human> mArrFavoriteHuman = new ArrayList<Human>();
    private RecyclerView mRecyclerview;
    private FavoriteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance(ArrayList<Human> arr) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST_HUMAN, (ArrayList<Human>) arr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArrHuman = getArguments().getParcelableArrayList(ARG_LIST_HUMAN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerViewFavorite);
        initFavoriteHuman();
        mAdapter = new FavoriteAdapter(mArrFavoriteHuman);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        return view;
    }

    public void initFavoriteHuman(){
        for (int i=0;i<mArrHuman.size();i++){
            Human man = mArrHuman.get(i);
            if(man.isInterest()){
                mArrFavoriteHuman.add(man);
            }
        }
    }

}
