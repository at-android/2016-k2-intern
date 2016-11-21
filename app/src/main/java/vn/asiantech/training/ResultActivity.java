package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import vn.asiantech.training.adapter.RecyclerViewAdapter;

/**
 * Created by phuong on 18/11/2016.
 */

public class ResultActivity extends AppCompatActivity {
    private Button mBtnBack;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mBtnBack = (Button) findViewById(R.id.btnBack);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcvResult);

        //nhan du lieu
        Bundle bundle = getIntent().getExtras();
        String[] result = bundle.getStringArray(QuestionActivity.KEY_STRING_ARRAY);

        mLayoutManager = new GridLayoutManager(this, 10);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(result);
        mRecyclerView.setAdapter(mAdapter);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}
