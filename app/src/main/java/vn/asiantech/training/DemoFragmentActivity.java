package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class DemoFragmentActivity extends AppCompatActivity implements EditFragment.OnHeadlineSelectedListener,
        InformationFragment.OnHeadlineSelectedListener2, AddFragment.OnFragmentInteractionListener {

    public static String KEY_STUDENT = "student";
    public static String KEY_POSITION = "position";
    private FragmentManager mFragmentManager;
    private ArrayList<Student> mStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demofragment);
        mStudents = new ArrayList<Student>();
        mStudents.add(new Student("DUT", "Duy Le", "Da Nang", "21"));
        mStudents.add(new Student("DUT", "Duy Le", "Da Nang", "22"));
        mStudents.add(new Student("DUT", "Duy Le", "Da Nang", "23"));
        mStudents.add(new Student("DUT", "Duy Le", "Da Nang", "24"));
        mStudents.add(new Student("DUT", "Duy Le", "Da Nang", "25"));
        mStudents.add(new Student("DUT", "Duy Le", "Da Nang", "26"));
        ListStudentFragment studentFragment = new ListStudentFragment_();
        mFragmentManager = getSupportFragmentManager();
        studentFragment.passData(mStudents);
        mFragmentManager.beginTransaction().add(R.id.flContainer, studentFragment).commit();

    }

    @Override
    public void onFragmentEditInteraction(Student student, int position) {
        InformationFragment informationFragment = new InformationFragment_();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_STUDENT, student);
        bundle.putInt(KEY_POSITION, position);
        informationFragment.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.flContainer, informationFragment).commit();
    }

    @Override
    public void onFragmentUpdateInteraction(Student student, int position) {
        ListStudentFragment listStudentFragment = new ListStudentFragment_();
        mStudents.set(position, student);
        listStudentFragment.passData(mStudents);
        mFragmentManager.beginTransaction().replace(R.id.flContainer, listStudentFragment).commit();
    }

    @Override
    public void onFragmentAddInteraction(Student student) {
        mStudents.add(student);
        ListStudentFragment listStudentFragment = new ListStudentFragment_();
        listStudentFragment.passData(mStudents);
        mFragmentManager.beginTransaction().replace(R.id.flContainer, listStudentFragment).commit();
    }
}
