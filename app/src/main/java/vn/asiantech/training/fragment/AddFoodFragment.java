package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.application.App;
import vn.asiantech.training.model.Food;

/**
 * Created by phuong on 18/11/2016.
 */

public class AddFoodFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText mEdName;
    private EditText mEdCost;
    private Spinner mSpType;
    private ImageView mImvBack;
    private Button mBtnAdd;
    private List<String> mTypes;
    private String mTypeSelected = "";
    private ListFoodFragment mListFoodFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_food, null);
        mEdName = (EditText) view.findViewById(R.id.edName);
        mEdCost = (EditText) view.findViewById(R.id.edCost);
        mSpType = (Spinner) view.findViewById(R.id.spType);
        mBtnAdd = (Button) view.findViewById(R.id.btnAdd);
        mImvBack = (ImageView) view.findViewById(R.id.imvBack);
        mTypes = initDataForSpinner();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpType.setAdapter(dataAdapter);
        mSpType.setOnItemSelectedListener(this);
        mBtnAdd.setOnClickListener(this);

        return view;
    }

    public ArrayList<String> initDataForSpinner() {
        return ((App) getActivity().getApplication()).initDataForSpinner();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                String name = mEdName.getText().toString();
                String cost = mEdCost.getText().toString();
                int idPicture = 0;
                if (mTypeSelected.equals("food"))
                    idPicture = 1;
                else if (mTypeSelected.equals("drink"))
                    idPicture = 2;
                else
                    idPicture = 3;
                Food food = new Food(name, mTypeSelected, cost, idPicture);
                ((App) getActivity().getApplication()).addFood(food);
                mListFoodFragment = new ListFoodFragment();
                getActivity().onBackPressed();
                break;
            case R.id.imvBack:
                getActivity().onBackPressed();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mTypeSelected = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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

}
