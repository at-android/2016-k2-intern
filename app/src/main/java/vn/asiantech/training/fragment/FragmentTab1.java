package vn.asiantech.training.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.PhoneRecycleViewAdapter;
import vn.asiantech.training.adapter.ViewPagerAdapter;
import vn.asiantech.training.database.MyDatabase;
import vn.asiantech.training.model.Phone;

/**
 * Created by phuong on 29/11/2016.
 */

public class FragmentTab1 extends Fragment {
    private RecyclerView mRecyclerView;
    private TextView mTvAdd;
    private PhoneRecycleViewAdapter mPhoneRecycleViewAdapter;
    private ArrayList<Phone> mPhones = new ArrayList<>();
    private MyDatabase database ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvContactTab);
        mTvAdd = (TextView) view.findViewById(R.id.btnAdd);

        database = new MyDatabase(getActivity());
        database.open();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mPhones = database.getData();
        Log.d("size",String.valueOf(mPhones.size()));
        mPhoneRecycleViewAdapter = new PhoneRecycleViewAdapter(mPhones,getContext());
        mRecyclerView.setAdapter(mPhoneRecycleViewAdapter);
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogNotification();
            }
        });
        return view;
    }

    public void showDialogNotification() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_phone);
        final EditText edName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edPhone = (EditText) dialog.findViewById(R.id.edtPhone);
        final Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
        final Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String phone = edPhone.getText().toString();

                database.createData(name,phone);
                Phone newPhone = new Phone();
                newPhone.setmName(name);
                newPhone.setmPhone(phone);
                mPhones.add(newPhone);
                mPhoneRecycleViewAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        database.close();
    }
}
