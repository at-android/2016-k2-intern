package vn.asiantech.training.fragment;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.PhoneRecycleViewAdapter;
import vn.asiantech.training.database.RealmHelper;
import vn.asiantech.training.model.Phone;

/**
 * Created by phuong on 29/11/2016.
 */
@EFragment(R.layout.fragment_tab1)
public class FragmentTab1 extends Fragment {
    @ViewById(R.id.rvContactTab)
    RecyclerView mRecyclerView;
    @ViewById(R.id.btnAdd)
    TextView mTvAdd;
    private PhoneRecycleViewAdapter mPhoneRecycleViewAdapter;
    private List<Phone> mPhones = new ArrayList<>();

    @AfterViews
    void showRecyclerViewData() {
        Log.d("TAG11","TAG11");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mPhones = RealmHelper.getInstance(getContext()).getPhones();
        Log.d("TAG11","TAG11 size "+mPhones.size());
        mPhoneRecycleViewAdapter = new PhoneRecycleViewAdapter(mPhones, getContext());
        mRecyclerView.setAdapter(mPhoneRecycleViewAdapter);
    }

    @Click(R.id.btnAdd)
    void addAction() {
        showDialogNotification();
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
                String id = UUID.randomUUID().toString();
                Phone newPhone = new Phone(id, name, phone);
                RealmHelper.getInstance(getContext()).addPhone(newPhone);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
