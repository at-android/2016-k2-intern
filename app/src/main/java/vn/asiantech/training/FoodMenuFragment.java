package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodMenuFragment.OnFoodMenuFragmentListener} interface
 * to handle interaction events.
 * Use the {@link FoodMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodMenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRvListFood;
    private ImageView mImgNewProduct;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFoodMenuFragmentListener mListener;

    public FoodMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodMenuFragment newInstance(String param1, String param2) {
        FoodMenuFragment fragment = new FoodMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_menu, container, false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRvListFood = (RecyclerView) view.findViewById(R.id.rvListFood);
        mImgNewProduct = (ImageView) view.findViewById(R.id.imgAddNewProduct);
        mRvListFood.setHasFixedSize(true);
        mRvListFood.setLayoutManager(llm);
        MainActivity main = (MainActivity) getActivity();
        FoodArrayAdapter rvAdapter = new FoodArrayAdapter(getActivity(), main.sFoodArray);
        mRvListFood.setAdapter(rvAdapter);
        mImgNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                NewProductFragment newProductFragment = new NewProductFragment();
                transaction.replace(R.id.activity_main, newProductFragment, "Add").commit();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFoodMenuFragmentListener) {
            mListener = (OnFoodMenuFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFoodMenuFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFoodMenuFragmentListener {
    }
}
