package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<FoodList> mList = new ArrayList<FoodList>();
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MainActivity main = (MainActivity)getActivity();
        adapter = new RecyclerAdapter(mList,getContext(),main);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void updateList(ArrayList<FoodList> list){
        mList = list;
    }
}
