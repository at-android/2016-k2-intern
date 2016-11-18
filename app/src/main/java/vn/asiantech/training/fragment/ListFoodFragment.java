package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.MainActivity;
import vn.asiantech.training.R;
import vn.asiantech.training.adapter.FoodRecyclerAdapter;
import vn.asiantech.training.application.App;
import vn.asiantech.training.listener.RecyclerTouchListener;
import vn.asiantech.training.model.Food;

/**
 * Created by phuong on 18/11/2016.
 */

public class ListFoodFragment extends Fragment {
    public static String FRAGMENT_NAME = "ListFoodFragment";
    public static String POSITION = "position";
    public static String FOOD = "food";
    private ArrayList<Food> mFoods = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FoodRecyclerAdapter mFoodRecyclerAdapter;
    private ImageView mImvAdd;
    private AddFoodFragment mAddFoodFragment;
    private EditFoodFragment mEditFoodFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_food, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvFood);
        mImvAdd = (ImageView) view.findViewById(R.id.imvAdd);
        ((MainActivity) getActivity()).getDataForList();
        mFoods = ((App) getActivity().getApplication()).getListFood();
        Log.d("abc", mFoods.toString());
        mFoodRecyclerAdapter = new FoodRecyclerAdapter(mFoods, getActivity().getBaseContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFoodRecyclerAdapter);
        mImvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddFoodFragment = new AddFoodFragment();
                switchFragment(mAddFoodFragment, true, R.id.frContain, "");
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getBaseContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(POSITION, position);
                bundle.putParcelable(FOOD, mFoods.get(position));
                mEditFoodFragment = new EditFoodFragment();
                mEditFoodFragment.setArguments(bundle);
                switchFragment(mEditFoodFragment, true, R.id.frContain, FRAGMENT_NAME);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void switchFragment(Fragment fragment, boolean addToBackStack, int id, String nameFragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment, nameFragment);
        if (addToBackStack) {
            ft.addToBackStack(nameFragment);
        }
        ft.commit();
    }

    public List<String> initDataForSpinner() {
        return ((App) getActivity().getApplication()).initDataForSpinner();
    }

    @Override
    public void onResume() {
        mFoodRecyclerAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
