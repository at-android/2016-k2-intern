package vn.asiantech.training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    Button mBtnDone;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] list;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        mBtnDone = (Button) view.findViewById(R.id.btnDone);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        layoutManager = new GridLayoutManager(getContext(), 10);
        recyclerView.setLayoutManager(layoutManager);

        Bundle bundle = getArguments();
        list = bundle.getStringArray("resultArray");
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });
        return view;
    }
}
