package vn.asiantech.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditInfoFragment extends Fragment implements View.OnClickListener {
    private OnHeadlineSelectedListener mCallback;
    private ImageView mImg;
    private TextView mTvTitle;
    private EditText mEdName;
    private EditText mEdPrice;
    private TextView mTvType;
    private Spinner mSpn;
    private Button mBtn;
    private String arr[] = {"Food", "Beverage", "Other"};
    private ArrayAdapter<String> adapter = null;
    private int mPosition;
    public EditInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_info, container, false);
        mImg = (ImageView) view.findViewById(R.id.imgView);
        mTvTitle = (TextView) view.findViewById(R.id.tvEdit);
        mEdName = (EditText) view.findViewById(R.id.edName);
        mEdPrice = (EditText) view.findViewById(R.id.edPrice);
        mTvType = (TextView) view.findViewById(R.id.edType);
        mSpn = (Spinner) view.findViewById(R.id.spnType);
        mBtn = (Button) view.findViewById(R.id.btnSubmit);
        //init for spinner
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpn.setAdapter(adapter);
        //getdata
        Bundle bundle = getArguments();
        mPosition = bundle.getInt("position");
        FoodList f = (FoodList) bundle.getSerializable("Food");
        mEdName.setText(f.getName());
        mTvType.setText(f.getType());
        mEdPrice.setText(f.getPrice());
        mSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mTvType.setText(arr[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mImg.setOnClickListener(this);
        mBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgView:
                getActivity().onBackPressed();
                break;
            case R.id.btnSubmit:
                FoodList f = new FoodList();
                f.setName(mEdName.getText().toString());
                f.setType(mTvType.getText().toString());
                f.setPrice(mEdPrice.getText().toString());
                if(mTvType.getText().toString().equals("Food")){
                    f.setImage(R.drawable.type_1);
                }
                else if(mTvType.getText().toString().equals("Beverage")){
                    f.setImage(R.drawable.type_2);
                }
                else if(mTvType.getText().toString().equals("Other")){
                    f.setImage(R.drawable.type_3);
                }
                mCallback.onArticleSelected(f,mPosition);
                break;
        }
    }

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(FoodList food,int position);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
