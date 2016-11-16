package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Exfragment extends AppCompatActivity implements EditFragment.OnHeadlineSelectedListener,
        InformationFragment.OnHeadlineSelectedListener2, AddFragment.OnFragmentInteractionListener {
    public static String STUDENT_NAME = "student";
    public static String POSITION = "position";
    FragmentManager fragmentManager;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exfragment);
        students = new ArrayList<Student>();
        students.add(new Student("DUT", "Duy Le", "Da Nang", "21"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "22"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "23"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "24"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "25"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "26"));
        ListStudentFragment studentFragment = new ListStudentFragment();
        fragmentManager = getSupportFragmentManager();
        studentFragment.passData(students);
        fragmentManager.beginTransaction().add(R.id.flContainer, studentFragment, "studentFragment").commit();

    }

    @Override
    public void onFragmentInteraction1(Student student, int position) {
        InformationFragment informationFragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(STUDENT_NAME, student);
        bundle.putSerializable(POSITION, position);
        informationFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flContainer, informationFragment).commit();
    }

    @Override
    public void onFragmentInteraction2(Student student, int position) {
        ListStudentFragment listStudentFragment = new ListStudentFragment();
        students.set(position, student);
        listStudentFragment.passData(students);
        fragmentManager.beginTransaction().replace(R.id.flContainer, listStudentFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Student student) {
        students.add(student);
        ListStudentFragment listStudentFragment = new ListStudentFragment();
        listStudentFragment.passData(students);
        fragmentManager.beginTransaction().replace(R.id.flContainer, listStudentFragment).commit();
    }
}
