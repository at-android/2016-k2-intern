package vn.asiantech.training;

import android.app.Application;

import java.util.ArrayList;

import vn.asiantech.training.model.Student;

/**
 * Created by phuong on 15/11/2016.
 */

public class App extends Application {
    private ArrayList<Student> mArrStudent = new ArrayList<>();

    public ArrayList<Student> getData() {
        mArrStudent.add(new Student("Phuong", "22", "Quang Nam", "Bach Khoa"));
        mArrStudent.add(new Student("Phu", "20", "Quang Binh", "Su Pham"));
        mArrStudent.add(new Student("Huong", "23", "Da Nang", "Bach Khoa"));
        mArrStudent.add(new Student("Thanh", "21", "Quang Tri", "Ngoai Ngu"));
        mArrStudent.add(new Student("Van", "19", "Quang Ngai", "Bach Khoa"));
        return mArrStudent;
    }

    public void addStudent(Student student) {
        mArrStudent.add(student);
    }

    public void editStudent(Student student, Integer position) {
        mArrStudent.remove(position);
        mArrStudent.add(position, student);
    }
}
