package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class Exfragment extends AppCompatActivity implements EditFragment.OnHeadlineSelectedListener, InformationFragment.OnHeadlineSelectedListener2 {
    public static String STUDENT_NAME = "student";
    public static String POSITION = "position";
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exfragment);
        ListStudentFragment studentFragment = new ListStudentFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.flContainer, studentFragment, "studentFragment").commit();

    }

    @Override
    public void onArticleSelected(Student student, int position) {
        InformationFragment informationFragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(STUDENT_NAME, student);
        bundle.putSerializable(POSITION, position);
        informationFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flContainer, informationFragment, "abc").commit();
    }

    @Override
    public void onArticleSelected2(Student student, int position) {
        ListStudentFragment listStudentFragment = new ListStudentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(STUDENT_NAME, student);
        bundle.putInt(POSITION, position);
        listStudentFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flContainer, listStudentFragment).commit();
    }
}
