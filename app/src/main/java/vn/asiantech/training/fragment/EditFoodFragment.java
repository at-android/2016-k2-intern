package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.application.App;
import vn.asiantech.training.model.Food;

/**
 * Created by phuong on 18/11/2016.
 */

public class EditFoodFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private EditText edName;
    private EditText edCost;
    private Spinner mSpType;
    private ImageView mImvBack;
    private List<String> mTypes;
    private Button mBtnEdit;
    private String mTypeSelected = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_food,null);
        edName = (EditText) view.findViewById(R.id.edName);
        edCost = (EditText) view.findViewById(R.id.edCost);
        mSpType = (Spinner) view.findViewById(R.id.spType) ;
        mBtnEdit = (Button) view.findViewById(R.id.btnEdit);
        mImvBack = (ImageView) view.findViewById(R.id.imvBack);

        mTypes = initDataForSpinner();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpType.setAdapter(dataAdapter);

        Bundle bundle = getArguments();
        Food food = bundle.getParcelable(ListFoodFragment.FOOD);
        final int position = bundle.getInt(ListFoodFragment.POSITION);

        edCost.setText(food.getmCost());
        edName.setText(food.getmName());
        mSpType.setSelection(mTypes.indexOf(food.getmType()));

        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String cost = edCost.getText().toString();
                int picture =0;
                if(mTypeSelected.equals(""))
                    mTypeSelected = mSpType.getSelectedItem().toString();
                if(mTypeSelected.equals("food"))
                    picture = 1;
                else
                    if(mTypeSelected.equals("drink"))
                        picture = 2;
                else
                        picture = 3;
                Food food = new Food(name,mTypeSelected,cost,picture);
                ((App) getActivity().getApplication()).editFood(food,position);
                getActivity().onBackPressed();
            }
        });

        mImvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    public List<String> initDataForSpinner(){
        return ((App) getActivity().getApplication()).initDataForSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mTypeSelected = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
