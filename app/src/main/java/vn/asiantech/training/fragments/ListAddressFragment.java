package vn.asiantech.training.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.RecyclerViewAdapter;
import vn.asiantech.training.database.RealmHander;
import vn.asiantech.training.object.Address;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListAddressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListAddressFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Address> addressesArray = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListAddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListAddressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListAddressFragment newInstance(String param1, String param2) {
        ListAddressFragment fragment = new ListAddressFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_address, container, false);
        addressesArray = RealmHander.getRealmHander(getContext()).getAddress();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewListAddress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(addressesArray, getContext());
        recyclerView.setAdapter(adapter);
        Button btnAddPhone = (Button) view.findViewById(R.id.btnAdd);
        btnAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                // khởi tạo dialog
                dialog.setContentView(R.layout.dialog_insert_data);
                // xét layout cho dialog
                dialog.setTitle("Thêm danh bạ");
                // xét tiêu đề cho dialog

                Button btnDialogAdd = (Button) dialog.findViewById(R.id.btnDialogAdd);
                final EditText edtName = (EditText) dialog.findViewById(R.id.edtDialogName);
                final EditText edtPhone = (EditText) dialog.findViewById(R.id.edtDialogPhone);
                // khai báo control trong dialog để bắt sự kiện
                btnDialogAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RealmHander.getRealmHander(getContext()).addAddress(new Address(UUID.randomUUID().toString(), edtName.getText().toString(), edtPhone.getText().toString()));
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                // bắt sự kiện cho nút đăng kí
                dialog.show();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
