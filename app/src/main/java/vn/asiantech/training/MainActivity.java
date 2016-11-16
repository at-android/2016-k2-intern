package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddSVFragment.OnHeadlineSelectedListener,StudentFragment.OnHeadlineSelectedListener2,StudentInforFragment.OnHeadlineSelectedListener3,EditInforFragment.OnHeadlineSelectedListener4{
    private ArrayList<SinhVien> mArr = new ArrayList<SinhVien>();

    public ArrayList<SinhVien> getmArr() {
        return mArr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SinhVien sv1 = new SinhVien("DUT", "Tran Van A", "19", "Da Nang");
        SinhVien sv2 = new SinhVien("DUT", "Tran Van B", "20", "Da Nang");
        SinhVien sv3 = new SinhVien("DUT", "Tran Van C", "21", "Da Nang");
        mArr.add(sv1);
        mArr.add(sv2);
        mArr.add(sv3);

        StudentFragment fr = new StudentFragment();
        replaceAFragment(fr, true, R.id.activity_main);
    }

    public void replaceAFragment(Fragment fragment, boolean addToBackStack, int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
            ft.commit();
        } else {
            ft.commit();
        }
    }

    //Receive data of AddFragment and send to StudentFragment
    @Override
    public void onArticleSelected(SinhVien sv) {
        StudentFragment studentFragment = new StudentFragment();
        mArr.add(sv);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_main, studentFragment).commit();
    }

    //Receive data of StudentFragment and send to StudentInforFragment
    @Override
    public void onArticleSelected2(SinhVien sv,int position) {
        Log.i("xxx", sv.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("StudentInfor",sv);
        bundle.putInt("position",position);
        StudentInforFragment StuInfoFrag = new StudentInforFragment();
        StuInfoFrag.setArguments(bundle);
        replaceAFragment(StuInfoFrag,true,R.id.activity_main);
    }

    //Receive data of StudentInforFragment and send to EditInforFragment
    @Override
    public void onArticleSelected3(SinhVien sv,int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("StudentInfor",sv);
        bundle.putInt("position",position);
        EditInforFragment EditStuInfoFrag = new EditInforFragment();
        EditStuInfoFrag.setArguments(bundle);
        replaceAFragment(EditStuInfoFrag,true,R.id.activity_main);
    }

    //Receive data of EditInforFragment and send to StudentInforFragment
    @Override
    public void onArticleSelected4(SinhVien sv,int position) {
        mArr.set(position,sv);
        Log.i("xyz", mArr.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("StudentInfor",sv);
        StudentInforFragment StuInfoFrag = new StudentInforFragment();
        StuInfoFrag.setArguments(bundle);
        replaceAFragment(StuInfoFrag,false,R.id.activity_main);
    }
}
